/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label forgotPassword;
    
    Connection con;
    Statement stm;
    int res;

    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @FXML
    public void handleLabelPress(MouseEvent event) throws IOException, SQLException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }    

    @FXML
    private void getUsername(ActionEvent event) {
    }

    @FXML
    private void getPassword(ActionEvent event) {
    }

    @FXML
    private void goToAdminDash(ActionEvent event) throws SQLException, IOException {
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        String Uname = txtUsername.getText();
        String Pass = txtPassword.getText();
        if( Uname.isEmpty() || Pass.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Enter required field");
        alert.showAndWait();
        return;
        }
        
        String qu = "SELECT * FROM SUDEEPTO.CREATE_ACCOUNT WHERE USERNAME = '"
            + Uname + "' AND PASSWORD = '"
            + Hash.encode(Pass) + "'";
        System.out.println(qu);
        PreparedStatement pst = con.prepareStatement(qu);
        //con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        //stm = con.createStatement();
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
        Parent adminDash = FXMLLoader.load(getClass().getResource("AdminDash.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
//        window.setResizable(true);
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
        
    }
    else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Username & Password doesn't match");
        alert.showAndWait();
        return;
        }
}

    @FXML
    private void handleButtonPress(MouseEvent event) {
    
    }

    @FXML
    private void getforgotpassword(MouseEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }
}
