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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;
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
public class VolunteerAddController implements Initializable {

    @FXML
    private ChoiceBox<String> skillchoice;
    @FXML
    private JFXTextField vname;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField phone;
    @FXML
    private JFXTextField address;
    @FXML
    private Button register;
    @FXML
    private Button back;
    
    Connection con;
    Statement stm;
    int res;
   String VID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        skillchoice.setItems(observableArrayList("Graphics","Teaching","Management","Gardening"));
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
    private void setregister(ActionEvent event) throws SQLException, IOException {
//        String VID = UUID.randomUUID().toString();
//        String VID = null;
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT COUNT(*) FROM VOLUNTEER");
            // get the number of rows from the result set
            if(rs.next()){
            int rowCount = rs.getInt(1);
            System.out.println(rowCount);
//            if(rowCount==0){
//            
//            }
//            int newidi = rowCount + 1;
            VID = Integer.toString(rowCount + 1);
        }
        String VName = vname.getText();
        String Age = age.getText();
        String Phone = phone.getText();
        String Address = address.getText();
        String Skillchoice = skillchoice.getValue();
        if (VName.isEmpty() || Age.isEmpty() || Phone==null || Address.isEmpty() || Skillchoice==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all the fields");
            alert.showAndWait();
            return;
        }
            
            String qu = "INSERT INTO VOLUNTEER VALUES("
                + "'" + VID + "',"
                + "'" + VName + "',"
                + "'" + Age + "',"
                + "'" + Phone + "',"
                + "'" + Address + "',"
                + "'" + Skillchoice + "'"
                + ")";
            
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
        stm = con.createStatement();
        res = stm.executeUpdate(qu);
        
        if (res != 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Added Successful");
            alert.showAndWait();
            Parent adminDash = FXMLLoader.load(getClass().getResource("ManageVolunteer.fxml"));
            Scene adminDashScene = new Scene(adminDash);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            window.setTitle("V-Assist");
            window.setScene(adminDashScene);
            window.show();
         
        }
//        else{
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Something is wrong");
//            alert.showAndWait();
//            return;
//        }
    }

    @FXML
    private void gobackbutton(ActionEvent event) throws IOException, SQLException {
                        
            
        Parent adminDash = FXMLLoader.load(getClass().getResource("ManageVolunteer.fxml"));
        Scene adminDashScene = new Scene(adminDash);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        window.setTitle("V-Assist");
        window.setScene(adminDashScene);
        window.show();
    }
    
}
