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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class EventController implements Initializable {

    @FXML
    private JFXTextField eventName;
    @FXML
    private JFXTextField venue;
    @FXML
    private DatePicker date;
    @FXML
    private Button createEvent;
    @FXML
    private ChoiceBox<String> duration;
    @FXML
    private JFXTextField eventID;
    @FXML
    private JFXTextField location;
    @FXML
    private Button cancel;
    Connection con;
    Statement stm;
    int res;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        duration.setItems(observableArrayList("1 hour","2 hours","3 hour","4 hour","5 hour","6 hour","7 hour"));
        
    }    

    @FXML
    private void getEventName(ActionEvent event) {
    }

    @FXML
    private void getVenue(ActionEvent event) {
    }

    @FXML
    private void getDate(ActionEvent event) {
    }

    @FXML
    private void setCreateEvent(ActionEvent event) throws SQLException, IOException {
    String EID = eventID.getText();
    String EName = eventName.getText();
    String EVenue = venue.getText();
    LocalDate EDate = date.getValue();
    String Eduration = duration.getValue();
    String Elocation = location.getText();
    if (EID.isEmpty() || EName.isEmpty() || EVenue.isEmpty() || Eduration==null || Elocation.isEmpty() || EDate==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all the fields");
            alert.showAndWait();
            return;
        }
        System.out.println(EID +" "+ EName + " " + EVenue + " " + EDate + " " + Eduration + " " + Elocation +" ");
        
            String qu = "INSERT INTO EVENT VALUES("
                + "'" + EID + "',"
                + "'" + EName + "',"
                + "'" + EVenue + "',"
                + "'" + EDate + "',"
                + "'" + Eduration + "',"
                + "'" + Elocation + "'"
                + ")";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
        
        if (res != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Added Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("V-Assist");
            window.setScene(adminDashScene);
            window.show();
            
        }
    
    
    
    }


    @FXML
    private void getEventID(ActionEvent event) {
    }

    @FXML
    private void getLocation(ActionEvent event) {
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Volunteer management");
        window.setScene(adminDashScene);
        window.show();
    }
    
}
