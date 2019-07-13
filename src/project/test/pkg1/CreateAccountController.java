/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author sudee
 */
public class CreateAccountController implements Initializable {
    @FXML
    private Button buttonNext;
    @FXML
    private Label loginLabel;
    
    public void handleButtonAction(MouseEvent event) throws IOException, SQLException {
    if (event.getSource() == loginLabel) {
//            Node node = (Node) event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();

//            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("New.fxml")));
//            stage.setScene(scene);
//            stage.show();
            Scene secondScene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));

            Stage stage = new Stage();
            stage.setScene(secondScene);
            stage.show();
    /**
     * Initializes the controller class.
     */    
    
    }   
}
    public void handleLabelAction(MouseEvent event) throws IOException, SQLException{
        if(event.getSource() == buttonNext){
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //To change body of generated methods, choose Tools | Templates.
    }
}