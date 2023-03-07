/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pidev.entity.Playlist;
import pidev.entity.music;
import pidev.services.PlayListService;
import pidev.entity.Utilisateur;
import pidev.entity.music;
import pidev.services.musicService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherPlaylistController implements Initializable {
Playlist p = new Playlist();
PlayListService ps = new PlayListService();
musicService ms = new musicService();
music m = new music();
@FXML
    private TableColumn<Playlist,Integer> idCol;
    @FXML
    private TableColumn<Playlist,String> nomCol;
    @FXML
    private TableColumn<Playlist,LocalDate> dateCreationCol;
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker date;
    private TextField idnom;
    @FXML
    private TableColumn<Playlist, String> userCol;
    @FXML
    private TableView<Playlist> tableview;
    @FXML
    private Button supp;
    @FXML
    private Button modifier;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   idCol.setCellValueFactory(cellData -> {
    return new SimpleIntegerProperty(cellData.getValue().getId_playlist()).asObject();
});

nomCol.setCellValueFactory(cellData -> {
    return new SimpleStringProperty(cellData.getValue().getNom_Playlist());
});

dateCreationCol.setCellValueFactory(cellData -> {
    return new SimpleObjectProperty<LocalDate>(cellData.getValue().getDate_Creation());
});

userCol.setCellValueFactory(cellData -> {
    return new SimpleStringProperty(cellData.getValue().getUser().getNom());
});

// Créer la TableView


// Ajouter les colonnes à la TableView
//tableview.getColumns().addAll( nomCol, dateCreationCol, userCol);

// Récupérer les données de la base de données
List<Playlist> playlists = ps.getAll();

// Ajouter les données à la TableView
ObservableList<Playlist> observablePlaylists = FXCollections.observableArrayList(playlists);
tableview.setItems(observablePlaylists);



tableview.refresh();

System.out.println("Nombre de playlists ajoutées à la TableView : " + observablePlaylists.size());
System.out.println(tableview.getSelectionModel().getSelectedItem());
        System.out.println(tableview.getItems());

    }    

    @FXML
    private void mouseAction(MouseEvent event) {
            p= tableview.getSelectionModel().getSelectedItem();
      
        nom.setText("" + p.getNom_Playlist());
        date.setValue( p.getDate_Creation());
       
      //  System.out.println(tableview.getColumns());
    }

    @FXML
    private void Supprimer(ActionEvent event) {
               p= tableview.getSelectionModel().getSelectedItem();
        
        if (p==null) { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une reclamation table!");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "voulez Vous  vraiment supprimer cette reclamation :" +m.getId()+" ?");
                alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ps.supprimer_music(p);
                //updating user data after closing popup
                        ObservableList<Playlist> Playlists= FXCollections.observableList(ps.getAll());
                tableview.setItems(Playlists);
        }
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
            LocalDate d = date.getValue();
             
        if (nom.getText().isEmpty()|| date.getValue()==null ){
          Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("NOT OK");
    alert.setHeaderText("modification non effectue");
    alert.setContentText("Click Cancel to exit.");
    System.out.println(d);
    alert.showAndWait();
        }
   
        
        else{
      

    p.setNom_Playlist(nom.getText());
    System.out.println(tableview.getSelectionModel().getSelectedItem().getId_playlist());
p.setId_playlist(tableview.getSelectionModel().getSelectedItem().getId_playlist());
    p.setDate_Creation(d);
    ps.modifier_Playlist(p.getNom_Playlist(), p.getDate_Creation(),  p);
    
   
    
        }
         ObservableList<Playlist> Playlists= FXCollections.observableList(ps.getAll());
                tableview.setItems(Playlists);
                    nom.setText("");
date.setValue(null);
    }

    @FXML
    private void backTOAjouter(ActionEvent event) {
            try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterPlaylist.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
