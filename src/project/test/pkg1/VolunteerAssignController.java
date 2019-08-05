/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class VolunteerAssignController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private TableColumn<Assign, String> teventid;
    @FXML
    private TableColumn<Assign, String> tename;
    @FXML
    private TableColumn<Assign, String> tvid;
    @FXML
    private TableColumn<Assign, String> tvname;
    @FXML
    private JFXTextField vid;
    @FXML
    private JFXTextField vname;
    @FXML
    private JFXTextField eid;
    @FXML
    private JFXTextField ename;

    /**
     * Initializes the controller class.
     */
    Connection con;
    Statement stm;
    int res;
    int res1;
    ResultSet rest;
    ObservableList <Assign> alist = FXCollections.observableArrayList();
    @FXML
    private TableView<Assign> assigntable;
    @FXML
    private JFXButton Assignbutton;
    @FXML
    private JFXButton showall;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerAssignController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initColumns();

//        try {
//            AssignloadData2();
//        } catch (SQLException ex) {
//            Logger.getLogger(ManageVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
        private void initColumns() {
        tvid.setCellValueFactory(new PropertyValueFactory<>("volid"));
        tvname.setCellValueFactory(new PropertyValueFactory<>("volname"));
        teventid.setCellValueFactory(new PropertyValueFactory<>("eveid"));
        tename.setCellValueFactory(new PropertyValueFactory<>("evename"));
        }
        
        private void AssignloadData() throws SQLException {
        
        String qu = "SELECT * FROM SUDEEPTO.ASSIGN";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        rest = stm.executeQuery(qu);
        
        try {
            while (rest.next()) {
                String voltid = rest.getString("volunteerid");
                String voltname = rest.getString("volunteername");
                String eveid = rest.getString("eventid");
                String evename = rest.getString("eventname");
              
                VolunteerAssignController.Assign assign = new VolunteerAssignController.Assign (voltid,voltname,eveid,evename);
                alist.add(assign);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerAssignController.class.getName()).log(Level.SEVERE, null, ex);
        }

        assigntable.getItems().setAll(alist);
    }
//    private void AssignloadData2() throws SQLException {
//
//            String qu = "SELECT * FROM SUDEEPTO.ASSIGN";
//            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
//            stm = con.createStatement();
//            rest = stm.executeQuery(qu);
//
//            try {
//                while (rest.next()) {
//                    String voltid = rest.getString("volunteerid");
//                    String voltname = rest.getString("volunteername");
//                    String eveid = rest.getString("eventid");
//                    String evename = rest.getString("eventname");
//
//                    VolunteerAssignController.Assign assign = new VolunteerAssignController.Assign (voltid,voltname,eveid,evename);
//                    alist.add(assign);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(VolunteerAssignController.class.getName()).log(Level.SEVERE, null, ex);
//            }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("AdminDash.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void setassign(ActionEvent event) throws SQLException, IOException {
       String VID = vid.getText();
       String VNAME = vname.getText();
       String EID = eid.getText();
       String ENAME = ename.getText();
       if (VID.isEmpty() || VNAME.isEmpty() || EID.isEmpty() || ENAME.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all the fields");
            alert.showAndWait();
            return;
        }
       String qu = "INSERT INTO ASSIGN (EVENTID,EVENTNAME,VOLUNTEERID,VOLUNTEERNAME) VALUES("
                + "'" + EID + "',"
                + "'" + ENAME + "',"
                + "'" + VID + "',"
                + "'" + VNAME + "'"
                + ")";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
        
        String qu2 = "UPDATE VOLUNTEER SET STATUS = 'Unavailable'" + 
                " WHERE VOLUNTEERID = '" 
                + VID + "'"; 
        System.out.println(qu);
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res1 = stm.executeUpdate(qu2);
        
        
        if (res != 0 && res1 !=0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Added Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("VolunteerAssign.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            window.setTitle("V-Assist");
            window.setScene(adminDashScene);
            window.show();
         
        }
    }

    @FXML
    private void getvid(ActionEvent event) {
    }

    @FXML
    private void getvname(ActionEvent event) {
    }

    @FXML
    private void geteid(ActionEvent event) {
    }

    @FXML
    private void getename(ActionEvent event) {
    }

    @FXML
    private void getshowall(ActionEvent event) throws SQLException {
                AssignloadData();
    }
    
    public class Assign {

        private final SimpleStringProperty volid;
        private final SimpleStringProperty volname;
        private final SimpleStringProperty eveid;
        private final SimpleStringProperty evename;
//        private final SimpleStringProperty voladd;
//        private final SimpleStringProperty volskill;
//        private final SimpleStringProperty vstatus;
        

        public Assign(String volid, String volname, String eveid, String ename) {
            this.volid = new SimpleStringProperty(volid);
            this.volname = new SimpleStringProperty(volname);
            this.eveid = new SimpleStringProperty(eveid);
            this.evename = new SimpleStringProperty(ename);
//            this.voladd = new SimpleStringProperty(voladd);
//            this.volskill = new SimpleStringProperty(volskill);
//            this.vstatus = new SimpleStringProperty(vstatus);
        }
        public String getEveid() {
            return eveid.get();
        }

        public String getEvename() {
            return evename.get();
        }

        public String getVolid() {
            return volid.get();
        }

        public String getVolname() {
            return volname.get();
        }
    }
}
