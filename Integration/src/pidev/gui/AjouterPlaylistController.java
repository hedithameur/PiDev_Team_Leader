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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pidev.entity.Playlist;
import pidev.entity.Utilisateur;
import pidev.services.PlayListService;
import pidev.services.UtilisateurService;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterPlaylistController implements Initializable {
Playlist p = new Playlist();
PlayListService ps = new PlayListService();
UtilisateurService U = new UtilisateurService(); 

    @FXML
    private DatePicker datecreation;
    @FXML
    private TextField idnomS;
    @FXML
    private ComboBox<String> comboUser;
    @FXML
    private Button ajouterPlaylist;
    @FXML
    private Button idAffiche;
    @FXML
    private Button back;
    @FXML
    private Button gomodifier;
    @FXML
    private Button idassocier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> o = FXCollections.observableArrayList(U.getNom());   
    comboUser.setItems(o);
    String selected = comboUser.getValue();
    if (!o.isEmpty()) {
   comboUser.setValue(o.get(0));    
    }
    }    

    @FXML
private void ajouterPlaylist(ActionEvent event) {
   Connection cnx = MaConnection.getInstance().getCnx();
    if (idnomS.getText().isEmpty() || datecreation.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Ajout non effectué");
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
    } else {
      String userSelectionnee = comboUser.getValue();
String sqlGetIdUser = "SELECT id FROM utilisateur WHERE nom='" + userSelectionnee + "'";
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
if (idList.isEmpty()) {
    System.out.println(" l'utilisateur sélectionné n'existe pas dans la table utilisateur");
    // afficher une erreur et sortir de la méthode
    return;
}
int idUser = idList.get(0);
System.out.println("ID utilisateur: " + idUser);

// insérer la playlist en utilisant l'ID utilisateur récupéré

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(idUser);

        Playlist playlist = new Playlist();
        playlist.setNom_Playlist(idnomS.getText());
        playlist.setDate_Creation(datecreation.getValue());
        playlist.setUser(utilisateur);
        ps.ajouter(playlist,utilisateur);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Ajout effectué");
        alert.setContentText("Playlist ajoutée avec succès.");
        alert.showAndWait();
    }

    idnomS.setText("");
    datecreation.setValue(null);
}

    @FXML
    private void GOaffichePlaylist(ActionEvent event) {
             try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherPlaylist.fxml"));
            idnomS.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void backentertainement(ActionEvent event) {
             try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("MusicPlaylist.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goModifier(ActionEvent event) {
          try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ModifierPlaylist.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goAssocier(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AssocierMusicPlaylist.fxml"));
            idassocier.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
