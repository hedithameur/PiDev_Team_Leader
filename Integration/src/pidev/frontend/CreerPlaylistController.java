/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import pidev.entity.Playlist;
import pidev.entity.Utilisateur;
import pidev.services.PlayListService;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CreerPlaylistController implements Initializable {
     Connection cnx;
private int userId;
Playlist p = new Playlist();
PlayListService ps = new PlayListService (); 
Utilisateur u = new Utilisateur();
    @FXML
    private Button Creer;
    @FXML
    private TextField playlist;
    @FXML
    private Button VOIRPLAYLIST;
    @FXML
    private Button retour;
    @FXML
    private Button download;
    @FXML
    private Button ecouterMusic;
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
    private void CreerPlaylist(ActionEvent event) {
       if (playlist.getText().isEmpty())
       {
            Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("NOT OK");
    alert.setHeaderText("remplir le champ ");
    alert.setContentText("Click Cancel to exit.");
    alert.showAndWait();
       }
       else{

    // récupérer l'utilisateur connecté
   u.setId(userId);

    // créer une instance de la classe Playlist
   
    p.setNom_Playlist(playlist.getText());
    p.setDate_Creation(LocalDate.now());
    p.setUser(u);

    // appeler la méthode ajouter de la classe qui gère la base de données
    ps.ajouter(p, u);

    // afficher un message de confirmation à l'utilisateur
       
    
       
       
       
       
       
       
       
       
       
       
       
       }
       
    }

    @FXML
    private void voirplaylist(ActionEvent event)  {
        
                      try {
                     
            //navigation
              FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPlaylistUser.fxml"));
    Parent root = loader.load();
    
    AfficherPlaylistUserController playlistController = loader.getController();
    playlistController.setiduser(userId);
    
    VOIRPLAYLIST.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
                try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("dashmembre.fxml"));
    Parent root = loader.load();
    
    DashmembreController membreController = loader.getController();
    membreController.setUserId( userId);
            retour.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void doawnloadVideo(ActionEvent event) {
                 try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("YoutubeDownloader.fxml"));
    Parent root = loader.load();
    
     YoutubeDownloaderController youtubeController = loader.getController();
    youtubeController.setUserId( userId);
            download.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void ecouterMusic(ActionEvent event) {
        
                  try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPlay.fxml"));
    Parent root = loader.load();
    
    MusicPlayController MusicController = loader.getController();
    MusicController.setUserId( userId);
            ecouterMusic.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
