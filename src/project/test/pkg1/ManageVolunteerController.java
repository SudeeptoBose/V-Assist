/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button deleteButton;
    @FXML
    private Button exitButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoAddVolunteer(ActionEvent event) {
    }

    @FXML
    private void gotoDelete(ActionEvent event) {
    }

    @FXML
    private void gotoDash(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("AdminDash.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }
    
}
