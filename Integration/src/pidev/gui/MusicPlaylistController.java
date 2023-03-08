/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

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
public class MusicPlaylistController implements Initializable {

    @FXML
    private Button music;
    @FXML
    private Button playlist;
    @FXML
    private Button retourMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoMusic(ActionEvent event) {
               try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterMusic.fxml"));
            music.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void goPlaylist(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterPlaylist.fxml"));
            playlist.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void RetourAttegrationMain(ActionEvent event) {
          try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../Integration/IntegrationAceuil.fxml"));
            playlist.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
