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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
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
    private JFXButton Assign;
    @FXML
    private TableColumn<?, ?> teventid;
    @FXML
    private TableColumn<?, ?> tename;
    @FXML
    private TableColumn<?, ?> tvid;
    @FXML
    private TableColumn<?, ?> tvname;
    @FXML
    private TableColumn<?, ?> sts;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void setassign(ActionEvent event) {
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
    
}
