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
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sudee
 */
public class VolunteerUpdateController implements Initializable {

    @FXML
    private JFXTextField vid;
    @FXML
    private JFXTextField vname;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField address;
    @FXML
    private ChoiceBox<String> skill;
    @FXML
    private Button back;
    @FXML
    private Button update;
    
    Connection con;
    Statement stm;
    int res;
    String qu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        skill.setItems(observableArrayList("Graphics","Teaching","Management","Gardening"));
    }    


    @FXML
    private void getvname(ActionEvent event) {
    }

    @FXML
    private void getage(ActionEvent event) {
    }

    @FXML
    private void getphone(ActionEvent event) {
    }

    @FXML
    private void getaddress(ActionEvent event) {
    }

    @FXML
    private void gotoback(ActionEvent event) throws IOException {
        Parent adminDash = FXMLLoader.load(getClass().getResource("ManageVolunteer.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    
    }

    @FXML
    private void setupdate(ActionEvent event) throws SQLException, IOException {
        String VID = vid.getText();
        if(VID.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Event ID");
            alert.showAndWait();
            return;
    }
        String Vname = vname.getText();
        String Age = age.getText();
        String Phone = phone.getText();
        String Skill = skill.getValue();
        String Address = address.getText();
                if (VID.isEmpty() || Vname.isEmpty() || Age.isEmpty() || Phone.isEmpty() || Skill==null || Address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all the fields");
            alert.showAndWait();
            return;
        }

        qu = "UPDATE VOLUNTEER SET VOLUNTEERNAME = '" 
                + Vname + "',AGE = '" 
                + Age+ "',PHONE = '" 
                + Phone + "',ADDRESS = '" 
                + Address + "',SKILL = '" 
                + Skill + "' WHERE VOLUNTEERID = '" 
                + VID + "'"; 
        System.out.println(qu);
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
         if (res != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Update Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("ManageVolunteer.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            window.setTitle("V-assist");
            window.setScene(adminDashScene);
            window.show();
        }
        
    }

    @FXML
    private void getvid(ActionEvent event) {
    }
    
}
