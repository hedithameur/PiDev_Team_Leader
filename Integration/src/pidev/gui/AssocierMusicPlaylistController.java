/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import pidev.entity.Playlist;
import pidev.entity.music;
import pidev.services.PlayListService;
import pidev.services.musicService;
import pidev.services.music_playlistService;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AssocierMusicPlaylistController implements Initializable {
music m =new music();
   
    Playlist p = new Playlist();
music_playlistService mp = new music_playlistService();
    musicService ms = new musicService();
        PlayListService P = new PlayListService();
    @FXML
    private ComboBox<String> comboPlaylist;
    @FXML
    private ComboBox<String> updatecomb2;
    @FXML
    private Button back;
    @FXML
    private Button idAssocier;

    /**
     * Initializes the controller class.
     */
    void ComboxPlaylist(){
       ObservableList<String> o1= FXCollections.observableArrayList(P.getnom()); 
        comboPlaylist.setItems(o1);    
    if (!o1.isEmpty()) {
   comboPlaylist.setValue(o1.get(0));
    }
    }
    void ComboMusic(){
        
          ObservableList<String> o2= FXCollections.observableArrayList(ms.getnom_morceaux()); 
        updatecomb2.setItems(o2);    
    if (!o2.isEmpty()) {
  updatecomb2.setValue(o2.get(0));
    } 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ComboxPlaylist();
  ComboMusic();
  
       
        
    } 
   
        

    @FXML
    private void updatecomb1(ActionEvent event) {
      
        
    }

    @FXML
    private void backtoMenu(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterPlaylist.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    @FXML
    private void Associer(ActionEvent event) {
    Connection cnx = MaConnection.getInstance().getCnx();
        String musicSelectionne= updatecomb2.getValue();
        String playlistSelectionee = comboPlaylist.getValue();
   String sqlGetIdUser = "SELECT id FROM music WHERE nom_morceaux='" + musicSelectionne + "'";
Statement statement;
ObservableList<Integer> idList = FXCollections.observableArrayList();
try {
    statement = cnx.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlGetIdUser);
    while (resultSet.next()) {
        int id = resultSet.getInt("id");
        idList.add(id);
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
   int idmusic= idList.get(0);
     int idplaylist = P.getIdFromnom(playlistSelectionee).get(0);
     
    p.setId_playlist(idplaylist);
 m.setId(idmusic);
    
    mp.ajouter_Music_Playlist(p, m);
    
   
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("OK");
    alert.setHeaderText("Ajout effectue");
    alert.setContentText("Click Cancel to exit.");
    alert.showAndWait();
        
    }
    
}
