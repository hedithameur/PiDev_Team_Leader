/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class ArtistedashController implements Initializable {

    @FXML
    private Button deconnexion;
private int userId;
    @FXML
    private Button addEvent;
    @FXML
    private Button musicAdd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setUserId(int userId) {
        this.userId = userId;
       System.out.println("UserID "+ userId);
    }
    @FXML
    private void gotoglobal(ActionEvent event) {
        
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("global.fxml"));
            deconnexion.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AddEvent(ActionEvent event) {
            try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenementFXML.fxml"));
    Parent root = loader.load();
    
    AjouterEvenementFXMLController ADDeventController = loader.getController();
    ADDeventController.setUserId( userId);
            addEvent.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @FXML
    private void AddMusic(ActionEvent event) {
            try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterMusic.fxml"));
    Parent root = loader.load();
    
    AjouterMusicController Controller = loader.getController();
    Controller.setUserId( userId);
            musicAdd.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    }
    
}
