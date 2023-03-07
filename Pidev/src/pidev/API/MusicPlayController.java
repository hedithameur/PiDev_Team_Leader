/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.API;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import pidev.entity.music;
import pidev.services.musicService;

/**
 * FXML Controller class
 *
 * @author user
 */

public class MusicPlayController implements Initializable {
music m = new music();
musicService M = new musicService();

    @FXML
    private Button play;
    @FXML
    private Button Stop;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button Pause;
      private MediaPlayer mediaPlayer;
    
    @FXML
    private ComboBox<String> fichiercombo;
     

    /**
     * Initializes the controller class.
     */
    void combobox (){
         ObservableList<String> o = FXCollections.observableArrayList(M.getFichier());   
    fichiercombo.setItems(o);
    String selected = fichiercombo.getValue();
    
    fichiercombo.setItems(o);
    if (!o.isEmpty()) {
         fichiercombo.setValue(o.get(0));
    }
 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox();

        /*Scene scene = new Scene(tabfichier);
        Stage primaryStage = new Stage();
primaryStage.setScene(scene);
primaryStage.show();
*/
// Ajouter des instructions d'impression pour déboguer le problème

        
    }    

    @FXML
    private void PlayMusic(ActionEvent event) {
            /*  String musicFile = File(musicFile).toURI().toString(); // Remplacez cela avec le chemin de votre propre fichier audio
        Media media = new Media(musicFile);*/
             String selected = fichiercombo.getValue();
            Media sound = new Media(new File(selected).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    private void StopMusic(ActionEvent event) {
          mediaPlayer.pause();
    }

    @FXML
    private void PauseMusic(ActionEvent event) {
           mediaPlayer.pause();
    }

    private Object File(String musicFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
