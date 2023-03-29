/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class MusicINterface extends Application {
    
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
       
       Parent root = null;
        try {
       //root = FXMLLoader.load(getClass().getResource("../gui/adminlogin.fxml"));
               
         

//root = FXMLLoader.load(getClass().getResource("../API/youtubeDownloader.fxml"));
//                root = FXMLLoader.load(getClass().getResource("../gui/instrument.fxml"));
               root = FXMLLoader.load(getClass().getResource("../frontend/global.fxml"));
              // root = FXMLLoader.load(getClass().getResource("../gui/Instrument.fxml"));
                 
                  
        } catch (IOException ex) {
            Logger.getLogger( MusicINterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       /* ImageView titleImage = new ImageView(new Image("file:logo6.png"));
StackPane stackPane = new StackPane();
stackPane.getChildren().addAll(titleImage, root);
        */
       FileInputStream input = new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Integration\\src\\pidev\\test\\logo6.png");
        Image image =new Image (input);
        
        
        Scene scene = new Scene(root,1200, 900);
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("  Day Event");
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
