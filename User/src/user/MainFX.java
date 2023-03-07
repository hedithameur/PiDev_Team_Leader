/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
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
 * @author Mayy
 */
public class MainFX extends Application {
    
    private Stage primaryStage;
    private Parent parent;
   
    
     @Override
 
        public void start(Stage primaryStage) throws IOException {
        this.primaryStage= primaryStage;
        parent=FXMLLoader.load(getClass().getResource("../gui/adminlogin.fxml"));
        Scene scene=new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public static void main(String[] args){
        
        launch(args);
    }
    

    
}
