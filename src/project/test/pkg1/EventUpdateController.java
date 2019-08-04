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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class EventUpdateController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField eventname;
    @FXML
    private JFXTextField venue;
    @FXML
    private ChoiceBox<String> duration;
    @FXML
    private JFXTextField location;
    @FXML
    private Button cancel;
    @FXML
    private Button update;
    
        Connection con;
        Statement stm;
        int res;
        String qu;
        
    @FXML
    private JFXTextField eventId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(EventUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        duration.setItems(observableArrayList("1 hour","2 hours","3 hour","4 hour","5 hour","6 hour","7 hour"));
    }    

    @FXML
    private void getdate(ActionEvent event) {
    }


    @FXML
    private void geteventname(ActionEvent event) {
    }

    @FXML
    private void getvenue(ActionEvent event) {
    }


    @FXML
    private void getlocation(ActionEvent event) {
    }

    @FXML
    private void gobackto(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent1.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void setupdate(ActionEvent event) throws SQLException, IOException {
        String EID = eventId.getText();
//String EID = eventid.getText();
//    String DuplicateChecker = "SELECT * FROM EVENT WHERE INITIAL = '" + EID + "'";
//        PreparedStatement pst = con.prepareStatement(DuplicateChecker);
//        System.out.println(DuplicateChecker);
//        ResultSet rs = pst.executeQuery();
//        if (rs.next()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("An event already exists with this ");
//            alert.showAndWait();
//            return;
//        }
        if(EID.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Event ID");
            alert.showAndWait();
            return;
    }
        String EName = eventname.getText();
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

        qu = "UPDATE EVENT SET EVENTNAME = '" + EName + "',VENUE = '" + EVenue + "',DATE = '" + EDate + "',DURATION = '" + Eduration + "',LOCATION = '" + Elocation + "' WHERE EVENTID = '" + EID + "'"; 
        System.out.println(qu);
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
         if (res != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Update Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent1.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            window.setTitle("V-assist");
            window.setScene(adminDashScene);
            window.show();
        }
        
    
    }

    @FXML
    private void getEID(ActionEvent event) {
    }

    
}
