/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import pidev.entity.Playlist;
import pidev.entity.Utilisateur;
import pidev.entity.music;
import pidev.entity.music_playlist;
import pidev.services.PlayListService;
import pidev.services.musicService;
import pidev.services.music_playlistService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherPlaylistUserController implements Initializable {
private int iduser;
    music m = new music();
Playlist p = new Playlist();
music_playlist M = new music_playlist(); 
PlayListService ps = new PlayListService();
musicService ms = new musicService();
Utilisateur u = new Utilisateur();
music_playlistService MS = new music_playlistService();
    @FXML
    private TableView<music> tabmusic;
    @FXML
    private TableColumn<music, String> Morceaux;
    @FXML
    private TableColumn<music,String> ColArtiste;
    @FXML
    private TableColumn<music, String> playcolumn;
    @FXML
    private ListView<String> ListView;
    @FXML
    private Button Ajouter;
    @FXML
    private Button affiche;
    @FXML
    private Button playMusic;
    @FXML
    private Button backup;
    @FXML
    private Button stop;
      private MediaPlayer mediaPlayer;
    @FXML
    private MediaView mediaView;
    private ComboBox<String> combob;
    @FXML
    private ListView<String> listview2;
    @FXML
    private Button AFFICHERplaylistsS;
    @FXML
    private ComboBox<String> combobox;

    /**
     * Initializes the controller class.
     * @param iduser
    
   
     */
    void combobox(){
        u.setId(iduser);
        System.out.println("userId"+u.getId());
        System.out.println(ps.getNom(u));
        ObservableList<String> o = FXCollections.observableArrayList(ps.getNom(u));   
    combobox.setItems(o);
    String selected = combobox.getValue();
    if (!o.isEmpty()) {
   combobox.setValue(o.get(0));
    }
    }
     public void setiduser(int iduser) {
        this.iduser = iduser;
       System.out.println("idplaylist "+ iduser);
    }
      public int getIdUser() {
       return iduser;
    }
      void afficherMusicPlaylist()
      {
          String a = listview2.getSelectionModel().getSelectedItem();
          u.setId(iduser);
          p.setNom_Playlist(a);
        ObservableList<String> listmusic1 = FXCollections.observableArrayList(MS.getMusic(p)); 
 ListView.setItems(listmusic1);  
 combobox();
      }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      System.out.println("getIdUser" + getIdUser());
        
     List<music> musics = ms.getAll();
        ObservableList<music> listmusics = FXCollections.observableArrayList(musics);  
        
       
        ColArtiste.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
      Morceaux.setCellValueFactory(new PropertyValueFactory<>("nom_morceaux"));
          playcolumn.setCellValueFactory(new PropertyValueFactory<>("fichier"));
       tabmusic.setItems(listmusics);
             
     
    /**/
    }    

    @FXML
    private void AjouterMusicPlaylist(ActionEvent event) {
        u.setId(iduser);
 m= tabmusic.getSelectionModel().getSelectedItem();
 String s = listview2.getSelectionModel().getSelectedItem();
 System.out.println(s);
 System.out.println(m.getNom_morceaux());
 p.setId_playlist(ps.getIdnomplaylist(s));
System.out.println("l id playlist " +p.getId_playlist() );
 MS.ajouter_Music_Playlist(p, m);
 /*ObservableList<String> listmusic1 = FXCollections.observableArrayList(MS.getMusic(p)); 
 ListView.setItems(listmusic1);*/
    }

    @FXML
    private void afficherMUSIC(ActionEvent event) {
        
        afficherMusicPlaylist();
      
    }

    @FXML
    private void play(ActionEvent event) {
        String n=ListView.getSelectionModel().getSelectedItem();
          String selected = ms.getFichier(n).get(0);
            Media sound = new Media(new File(selected).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    @FXML
    private void back(ActionEvent event) {
              try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("dashmembre.fxml"));
    Parent root = loader.load();
    
    DashmembreController membreController = loader.getController();
    membreController.setUserId(iduser);
            backup.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void StopMuisc(ActionEvent event) {
         mediaPlayer.pause();
    }

  

    @FXML
    private void AFFICHERvotreplaylists(ActionEvent event) {
         u.setId(iduser);
          
        ObservableList<String> listmusic1 = FXCollections.observableArrayList(ps.getNom(u)); 
 listview2.setItems(listmusic1); 
    }

    @FXML
    private void comboboxplaylist(ActionEvent event) {
    }
    
}
