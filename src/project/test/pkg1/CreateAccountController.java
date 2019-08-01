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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author sudee
 */
public class CreateAccountController implements Initializable {
    private Button buttonNext;
    @FXML
    private Text loginLabel;
    
    Connection con;
    Statement stm;
    int res;
    
    @FXML
    private JFXTextField txtFirstName;
    @FXML
    private JFXTextField txtLastName;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtConfirm;
    @FXML
    private Button buttonRegister;
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void getFirstName(ActionEvent event) {
    }

    @FXML
    private void getLastName(ActionEvent event) {
    }

    @FXML
    private void getUsername(ActionEvent event) {
    }

    @FXML
    private void getPassword(ActionEvent event) {
    }

    @FXML
    private void getConfirmPassword(ActionEvent event) {
    }

    @FXML
    private void handleLogin(MouseEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void setRegister(ActionEvent event) throws SQLException, IOException {
    String Fname = txtFirstName.getText();
    String Lname = txtLastName.getText();
    String Uname = txtUsername.getText();
    String DuplicateChecker = "SELECT * FROM CREATE_ACCOUNT WHERE USERNAME = '" + Uname + "'";
        PreparedStatement pst = con.prepareStatement(DuplicateChecker);
        System.out.println(DuplicateChecker);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("An account already exists for this user name.");
            alert.showAndWait();
            return;
        }
    String Pass = txtPassword.getText();
     if (Pass.length()<8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("The length of password must be more than 7 characters.");
            alert.showAndWait();
            return;
        }
    String ConPass = txtConfirm.getText();
    
    if(!Pass.equals(ConPass)){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Password doesn't match");
        alert.showAndWait();
        return;
    }
    if(Fname.isEmpty() || Lname.isEmpty() || Uname.isEmpty() || Pass.isEmpty() || ConPass.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Enter required field");
        alert.showAndWait();
        return;
    }
    String hashpassword = Hash.encode(Pass);
    System.out.println(Hash.decode(hashpassword));
    
    
    String qu = "INSERT INTO CREATE_ACCOUNT VALUES("
            + "'" + Uname + "',"
            + "'" + Fname + "',"
            + "'" + Lname + "',"
            + "'" + hashpassword + "',"
            + "'" + hashpassword + "'"
            + ")";
    con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
    stm = con.createStatement();
    res = stm.executeUpdate(qu);
    if (res != 0){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Register successful");
        alert.showAndWait();
        Parent adminDash = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }    
    }
}