/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 21650
 */
public class EventFXMain extends Application {
    
  @Override
    public void start(Stage primaryStage) throws Exception {
        try{
     // Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterEvenementFXML.fxml"));
      //Parent root = FXMLLoader.load(getClass().getResource("../gui/SuiviTable.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("../gui/CommentaireFXML.fxml"));
       //Parent root = FXMLLoader.load(getClass().getResource("../gui/Email.fxml"));
     Parent root = FXMLLoader.load(getClass().getResource("../gui/Acceuil.fxml"));
        
        Scene scene = new Scene(root,900,600); 
        primaryStage.setTitle("Ajouter Evenement");
        primaryStage.setScene(scene);
        primaryStage.show();
      } catch (IOException ex) {
            Logger.getLogger(EventFXMain.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("problem");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
