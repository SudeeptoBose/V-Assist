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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class LoginController implements Initializable {

    @FXML
    private Button loginButtonNext;
    @FXML
    private Label createAccount;

    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public void handleLabelPress(MouseEvent event) throws IOException, SQLException {

        if(event.getSource() == createAccount){
            Scene newScene = new Scene(FXMLLoader.load(getClass().getResource("Create_account")));
            
            Stage stage = new Stage();
            stage.setScene(newScene);
            stage.show();
        }
    }
    public void handleButtonPress(MouseEvent event){
        if(event.getSource() == loginButtonNext){
            System.exit(0); 
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    
    
}
