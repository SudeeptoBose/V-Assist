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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class EventDeleteController implements Initializable {

    @FXML
    private JFXTextField eventid;
    @FXML
    private Button cancel;
    @FXML
    private Button deleteevent;
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
            Logger.getLogger(EventDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void getid(ActionEvent event) {
    }

    @FXML
    private void goback(ActionEvent event) throws IOException {
    Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent1.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void donedelete(ActionEvent event) throws SQLException, IOException {
    String EID = eventid.getText();
    if(EID.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Event ID");
            alert.showAndWait();
            return;
    }
    String qu = "DELETE FROM EVENT WHERE EVENTID = '" + EID + "'";
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
        
        if (res != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Deleted Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("ManageEvent1.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            window.setTitle("V-Assist");
            window.setScene(adminDashScene);
            window.show();
            
        }
    }
    
}
