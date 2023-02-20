/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hedit
 */
public class NewFXMain extends Application {

   @Override
    public void start(Stage primaryStage) throws Exception {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Options.fxml"));
            Scene scene = new Scene(root, 800, 650);
            
            primaryStage.setTitle("Gestion Tickets et Commandes");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }

    
    
    
}
