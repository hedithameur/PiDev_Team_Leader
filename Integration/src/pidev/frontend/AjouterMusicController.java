/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pidev.entity.music;
import pidev.services.musicService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.entity.Utilisateur;
import pidev.services.UtilisateurService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterMusicController implements Initializable {
   /* @FXML
    private NotificationController notificationController;*/
  private int userId;
    @FXML
    private Label idmorceaux;
    @FXML
    private Label idartiste;
    @FXML
    private TextField morceauxfield;
    @FXML
    private TextField artistefield;
   
    @FXML
    private TextField fichierField;
    @FXML
    private Button idparcourir;
    @FXML
    private Button Ajoutbutton;
        private byte[] fichier;
        music m = new music();
        musicService ms = new musicService();
    @FXML
    private Button back;
    private Button stat;

    /**
     * Initializes the controller class.
     */
      public void setUserId(int userId) {
        this.userId = userId;
       System.out.println("UserID "+ userId);
    }
        
       
                
        @Override
    public void initialize(URL url, ResourceBundle rb) {
   
//System.out.println("userid  " + userId);
   

    
    }    

    @FXML
    private void parcourir(ActionEvent event) {
        
                FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                fichierField.setText(file.getAbsolutePath());
                try {
                    Path path = Paths.get(file.getAbsolutePath());
                    fichier = Files.readAllBytes(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
              UtilisateurService u = new UtilisateurService();
artistefield.setText(u.getNomArtiste(userId));
    }

    @FXML
    private void AJOUTER(ActionEvent event) {
       
        if (artistefield.getText().isEmpty()|| morceauxfield.getText().isEmpty()||fichierField.getText().isEmpty()){
          Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("NOT OK");
    alert.setHeaderText("Ajout non effectue");
    alert.setContentText("Click Cancel to exit.");
    alert.showAndWait();
        }
        else    if (fichier == null) {
        // Affiche un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un fichier avant d'ajouter une entrée à la base de données.");
        alert.showAndWait();
        return;
    }
        
        
        else{
  
    m.setNom_artiste(artistefield.getText());
    m.setNom_morceaux(morceauxfield.getText());
    m.setFichier(fichierField.getText());
    ms.ajouter(m);
    
   
       Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("OK");
    alert.setHeaderText("Ajout effectue");
    alert.setContentText("Click Cancel to exit.");
    alert.showAndWait();
        }
   // notificationController.showNotification("Nouvelle musique ajoutée : " + morceauxfield.getText());


    /*artistefield.setText("");
    morceauxfield.setText("");
    fichierField.setText("");*/
    
    }

 

    @FXML
    private void backEntertainement(ActionEvent event) {
                 try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("artistedash.fxml"));
    Parent root = loader.load();
    
    ArtistedashController Controller = loader.getController();
    Controller.setUserId( userId);
            back.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

 
    
}
