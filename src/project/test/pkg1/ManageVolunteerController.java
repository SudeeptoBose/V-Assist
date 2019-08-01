/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

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
import javafx.scene.control.Button;
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
public class ManageVolunteerController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private Button exitButton;
    
    ObservableList <Volunteer> vlist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    Connection con;
    Statement stm;
    ResultSet rest;
    int res;
    @FXML
    private TableColumn<Volunteer,String> vid;
    @FXML
    private TableColumn<Volunteer,String> vname;
    @FXML
    private TableColumn<Volunteer,String> vage;
    @FXML
    private TableColumn<Volunteer,String> vphone;
    @FXML
    private TableColumn<Volunteer,String> vaddress;
    @FXML
    private TableColumn<Volunteer,String> vskill;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button searchbutton;
    @FXML
    private Button showall;
    @FXML
    private Button refresh;
    @FXML
    private JFXTextField searchid;
    @FXML
    private TableView<Volunteer> voltable;
    String VSKILL;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(ManageVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initCol();

        try {
            VolunteerloadData2();
        } catch (SQLException ex) {
            Logger.getLogger(ManageVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    private void initCol() {
    vid.setCellValueFactory(new PropertyValueFactory<>("volid"));
    vname.setCellValueFactory(new PropertyValueFactory<>("volname"));
    vage.setCellValueFactory(new PropertyValueFactory<>("volage"));
    vphone.setCellValueFactory(new PropertyValueFactory<>("volnum"));
    vaddress.setCellValueFactory(new PropertyValueFactory<>("voladd"));
    vskill.setCellValueFactory(new PropertyValueFactory<>("volskill"));
    }
    
    private void VolunteerloadData() throws SQLException {
        
        String qu = "SELECT * FROM SUDEEPTO.VOLUNTEER";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        rest = stm.executeQuery(qu);
        
        try {
            while (rest.next()) {
                String voltid = rest.getString("volunteerid");
                String voltname = rest.getString("volunteername");
                String voltage = rest.getString("age");
                String voltnum = rest.getString("phone");
                String voltadd = rest.getString("address");
                String voltskill = rest.getString("skill");
                
                ManageVolunteerController.Volunteer volunteer = new ManageVolunteerController.Volunteer (voltid,voltname,voltage,voltnum,voltadd,voltskill);
                vlist.add(volunteer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        voltable.getItems().setAll(vlist);
    }
    
        private void VolunteerloadData2() throws SQLException {
        
        String qu = "SELECT * FROM SUDEEPTO.VOLUNTEER WHERE SKILL = '" + VSKILL + "'";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        rest = stm.executeQuery(qu);
        
        try {
            while (rest.next()) {
                String voltid = rest.getString("volunteerid");
                String voltname = rest.getString("volunteername");
                String voltage = rest.getString("age");
                String voltnum = rest.getString("phone");
                String voltadd = rest.getString("address");
                String voltskill = rest.getString("skill");
                
                ManageVolunteerController.Volunteer volunteer = new ManageVolunteerController.Volunteer (voltid,voltname,voltage,voltnum,voltadd,voltskill);
                vlist.add(volunteer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        voltable.getItems().setAll(vlist);
    }

    @FXML
    private void gotoAddVolunteer(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("VolunteerAdd.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("Volunteer management");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void gotoDelete(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("VolunteerDelete.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void gotoDash(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("AdminDash.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void gotoupdate(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("VolunteerUpdate.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void getsearchbutton(ActionEvent event) throws SQLException {
        VSKILL = searchid.getText();
        if ( VSKILL.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Volunteer's Skill.");
            alert.showAndWait();
            return;
        }
        VolunteerloadData2();
    }

    @FXML
    private void getshowall(ActionEvent event) throws SQLException {
        VolunteerloadData();
    }

    @FXML
    private void getrefresh(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("ManageVolunteer.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
        
    }

    @FXML
    private void getsearchid(ActionEvent event) throws SQLException {
        
    }
    
     public class Volunteer {

        private final SimpleStringProperty volid;
        private final SimpleStringProperty volname;
        private final SimpleStringProperty volage;
        private final SimpleStringProperty volnum;
        private final SimpleStringProperty voladd;
        private final SimpleStringProperty volskill;
        

        public Volunteer(String volid, String volname, String volage, String volnum, String voladd, String volskill) {
            this.volid = new SimpleStringProperty(volid);
            this.volname = new SimpleStringProperty(volname);
            this.volage = new SimpleStringProperty(volage);
            this.volnum = new SimpleStringProperty(volnum);
            this.voladd = new SimpleStringProperty(voladd);
            this.volskill = new SimpleStringProperty(volskill);
        }
        
        public String getVolid() {
            return volid.get();
        }

        public String getVolname() {
            return volname.get();
        }

        public String getVolage() {
            return volage.get();
        }

        public String getVolnum() {
            return volnum.get();
        }

        public String getVoladd() {
            return voladd.get();
        }

        public String getVolskill() {
            return volskill.get();
        }
        
    }
    
    
}
