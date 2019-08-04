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
public class ManageEvent1Controller implements Initializable {
    ObservableList <Event> elist = FXCollections.observableArrayList();

    @FXML
    private JFXButton search;
    @FXML
    private JFXButton showall;
    @FXML
    private JFXButton refresh;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton back;
    @FXML
    private TableColumn<Event, String> eid;
    @FXML
    private TableColumn<Event, String> ename;
    @FXML
    private TableColumn<Event, String> venue;
    @FXML
    private TableColumn<Event, String> date;
    @FXML
    private TableColumn<Event, String> location;
    @FXML
    private TableColumn<Event, String> duration;
    @FXML
    private JFXTextField txtsearch;
    
    Connection con;
    Statement stm;
    ResultSet rest;
    int res;
    String ENAME;
    @FXML
    private TableView<Event> evetable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(ManageEvent1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        initColumn();

        try {
            EventloadData2();
        } catch (SQLException ex) {
            Logger.getLogger(ManageEvent1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initColumn() {
    eid.setCellValueFactory(new PropertyValueFactory<>("eveid"));
    ename.setCellValueFactory(new PropertyValueFactory<>("evename"));
    venue.setCellValueFactory(new PropertyValueFactory<>("evenue"));
    date.setCellValueFactory(new PropertyValueFactory<>("edate"));
    location.setCellValueFactory(new PropertyValueFactory<>("elocation"));
    duration.setCellValueFactory(new PropertyValueFactory<>("eduration"));
    }
    private void EventloadData() throws SQLException {
        
        String qu = "SELECT * FROM SUDEEPTO.EVENT";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        rest = stm.executeQuery(qu);
        
        try {
            while (rest.next()) {
                String eveid = rest.getString("eventid");
                String evename = rest.getString("eventname");
                String evenue = rest.getString("venue");
                String edate = rest.getString("date");
                String eduration = rest.getString("duration");
                String elocation = rest.getString("location");
                
                ManageEvent1Controller.Event event = new ManageEvent1Controller.Event (eveid,evename,evenue,edate,eduration, elocation);
                elist.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        evetable.getItems().setAll(elist);
    }
    private void EventloadData2() throws SQLException {
        
        String qu = "SELECT * FROM SUDEEPTO.EVENT WHERE EVENTNAME = '" + ENAME + "'";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        rest = stm.executeQuery(qu);
        
        try {
            while (rest.next()) {
                String eveid = rest.getString("eventid");
                String evename = rest.getString("eventname");
                String evenue = rest.getString("venue");
                String edate = rest.getString("date");
                String eduration = rest.getString("duration");
                String elocation = rest.getString("location");
                
                ManageEvent1Controller.Event event = new ManageEvent1Controller.Event (eveid,evename,evenue,edate,eduration, elocation);
                elist.add(event);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageEvent1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        evetable.getItems().setAll(elist);
    }

    @FXML
    private void getsearch(ActionEvent event) throws SQLException {
        ENAME = txtsearch.getText();
        if ( ENAME.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Event Name.");
            alert.showAndWait();
            return;
        }
        EventloadData2();
    }

    @FXML
    private void getshowall(ActionEvent event) throws SQLException {
        EventloadData();
    }

    @FXML
    private void getrefresh(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent1.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void getadd(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void getupdate(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("EventUpdate.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void getdelete(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("EventDelete.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

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
    private void txtsearch(ActionEvent event) {
    }
    public class Event {

        private final SimpleStringProperty eveid;
        private final SimpleStringProperty evename;
        private final SimpleStringProperty evenue;
        private final SimpleStringProperty edate;
        private final SimpleStringProperty elocation;
        private final SimpleStringProperty eduration;
        

        public Event(String eveid, String evename, String evenue, String edate, String elocation, String eduration) {
            this.eveid = new SimpleStringProperty(eveid);
            this.evename = new SimpleStringProperty(evename);
            this.evenue = new SimpleStringProperty(evenue);
            this.edate = new SimpleStringProperty(edate);
            this.elocation = new SimpleStringProperty(elocation);
            this.eduration = new SimpleStringProperty(eduration);
        }

        public String getEveid() {
            return eveid.get();
        }

        public String getEvename() {
            return evename.get();
        }

        public String getEvenue() {
            return evenue.get();
        }

        public String getEdate() {
            return edate.get;
        }

        public String getElocation() {
            return elocation.get;
        }

        public String getEduration() {
            return eduration.get();
        }
        
    }
}
