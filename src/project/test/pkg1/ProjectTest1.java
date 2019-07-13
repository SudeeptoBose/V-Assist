/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.test.pkg1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author sudee
 */
public class ProjectTest1 extends Application {
  
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
        launch(args);
//        Connection myConnectionObject = null;
//        Statement myStatementObject = null;
//        ResultSet myResultObject = null;
//        ResultSetMetaData myMetaData = null;
//        String query = "SELECT * FROM SUDEEPTO.CREATE_ACCOUNT";
//        
//        try{
//            myConnectionObject = DriverManager.getConnection("jdbc:derby://localhost:1527/VM", "sudeepto", "sudeepto");
//            myStatementObject = myConnectionObject.createStatement();
//            myResultObject = myStatementObject.executeQuery(query);
//            myMetaData = myResultObject.getMetaData();
//            int columnNumber = myMetaData.getColumnCount();
//            System.out.println(columnNumber);
//            while(myResultObject.next()){
//                String username = myResultObject.getString("USERNAME");
//                String firstName = myResultObject.getString("FIRST_NAME");
//                String lastName = myResultObject.getString("LAST_NAME");
//                System.out.println(username + "\t " + firstName + "\t" + lastName);
//            }
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
    }
}

    

