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

/**
 * FXML Controller class
 *
 * @author user
 */
public class DashmembreController implements Initializable {
private int userId;
    @FXML
    private Button gerer;
    @FXML
    private Button deconnect;
    @FXML
    private Button goEvenement;
    @FXML
    private Button music;

    /**
     * Initializes the controller class.
     */
        public void setUserId(int userId) {
        this.userId = userId;
       System.out.println("UserID "+ userId);
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotomembredash(ActionEvent event) {
                     try {
                     
            //navigation
              FXMLLoader loader = new FXMLLoader(getClass().getResource("membredash.fxml"));
    Parent root = loader.load();
    
 MembredashController updatepasswordController = loader.getController();
    updatepasswordController.setUserId(userId);
    
    gerer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void deconnectmembre(ActionEvent event) {
            try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("login.fxml"));
            deconnect.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goTOevenement(ActionEvent event) {
                           try {
                     
            //navigation
              FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
    Parent root = loader.load();
    
    AcceuilController AcceuilController = loader.getController();
    AcceuilController.setUserId(userId);
    
    goEvenement.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void gomusic(ActionEvent event) {
                    try {
                     
            //navigation
              FXMLLoader loader = new FXMLLoader(getClass().getResource("CreerPlaylist.fxml"));
    Parent root = loader.load();
    
    CreerPlaylistController playlistController = loader.getController();
    playlistController.setUserId(userId);
    
    music.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
