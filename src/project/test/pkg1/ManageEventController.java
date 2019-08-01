/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class ManageEventController implements Initializable {

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button exitButton;
    
    Connection con;
    Statement stm;
    int res;
    
    @FXML
    private TextField seventid;
    @FXML
    private Button search;
    @FXML
    private Button showall;
    @FXML
    private Button refresh;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(ManageEventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void gotoCreateEvent(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("Event.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("Volunteer management");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void gotoUpdate(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("EventUpdate.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("Volunteer management");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void gotoDelete(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("EventDelete.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("Volunteer management");
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
    private void geteventidforsearch(ActionEvent event) {
    }

    @FXML
    private void getSearchbyid(ActionEvent event) {
    }

    @FXML
    private void showallevent(ActionEvent event) {
    }

    @FXML
    private void getrefresh(ActionEvent event) {
    }
    
}
