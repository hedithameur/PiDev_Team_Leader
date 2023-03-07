/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

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
 * @author user
 */
public class MusicINterface extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
       
       Parent root = null;
        try {
         root = FXMLLoader.load(getClass().getResource("../frontend/adminlogin.fxml"));
                // root = FXMLLoader.load(getClass().getResource("../API/youtubeDownloader.fxml"));
                 //  root = FXMLLoader.load(getClass().getResource("../gui/AfficherPlaylist.fxml"));
                   // root = FXMLLoader.load(getClass().getResource("../gui/Stat.fxml"));
                 
                  
        } catch (IOException ex) {
            Logger.getLogger( MusicINterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setTitle("Day Event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
