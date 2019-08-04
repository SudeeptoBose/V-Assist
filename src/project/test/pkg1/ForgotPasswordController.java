/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class ForgotPasswordController implements Initializable {

    @FXML
    private JFXButton back;
    @FXML
    private JFXButton update;

    /**
     * Initializes the controller class.
     */
    Connection con;
    Statement stm;
    int res;
    
    @FXML
    private JFXTextField uname;
    @FXML
    private JFXPasswordField npass;
    @FXML
    private JFXPasswordField cpass;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void goback(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }

    @FXML
    private void setupdate(ActionEvent event) throws SQLException, IOException {
    String Uname = uname.getText();
    String DuplicateChecker = "SELECT * FROM CREATE_ACCOUNT WHERE USERNAME = '" + Uname + "'";
        PreparedStatement pst = con.prepareStatement(DuplicateChecker);
        System.out.println(DuplicateChecker);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
        }
        else{
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText(null);
         alert.setContentText("An account doesn't exists for this user name.");
         alert.showAndWait();
         return;
        }
    String Pass = npass.getText();
        if (Pass.length()<8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("The length of password must be more than 7 characters.");
            alert.showAndWait();
            return;
        }
        String DuplicateChecker2 = "SELECT * FROM PASSWORDARCHIVE WHERE USERNAME = '" + Uname + "' AND PASSWORD = '" + Hash.encode(Pass) + "'";
        PreparedStatement pst2 = con.prepareStatement(DuplicateChecker2);
        System.out.println(DuplicateChecker2);
        ResultSet rs2 = pst2.executeQuery();
        if (rs2.next()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Enter different Password. You have already used this password.");
            alert.showAndWait();
            return;
        }
    String ConPass = cpass.getText();
        if(!Pass.equals(ConPass)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Password doesn't match");
            alert.showAndWait();
            return;
        }
        if(Uname.isEmpty() || Pass.isEmpty() || ConPass.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Enter required field");
            alert.showAndWait();
            return;
        }
        
       
       String qu = "UPDATE CREATE_ACCOUNT SET PASSWORD = '" + Hash.encode(Pass) + "' WHERE USERNAME = '" + Uname + "'";
       con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
         if (res != 0) {
            System.out.println("nana done");
        }
        String qu2 = "INSERT INTO PASSWORDARCHIVE VALUES("
                 + "'" + Uname + "',"
                 + "'" + Hash.encode(Pass) + "'"
                 + ")";
         con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
         stm = con.createStatement();
         res = stm.executeUpdate(qu2); 
         if (res != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Update Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            window.setTitle("V-assist");
            window.setScene(adminDashScene);
            window.show();
         }
        

        
       
    }

    @FXML
    private void getuname(ActionEvent event) {
    }

    @FXML
    private void getnpass(ActionEvent event) {
    }

    @FXML
    private void getcpass(ActionEvent event) {
    }
    
}
