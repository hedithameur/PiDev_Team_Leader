/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.entity.music;
import pidev.services.musicService;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherMusicController implements Initializable {
   music m =new music();
   musicService ms = new musicService();
   byte[] fichier;
    @FXML
    private TableColumn<music, String> coloneId;
    @FXML
    private TableColumn<music, String> colMorceaux;
    @FXML
    private TableColumn<music, String> colArtiste;
    @FXML
    private TableColumn<music, String> playcolumn;
    
    @FXML
    private TableView<music> tabmusic;
    @FXML
    private Button idSupprimer;
    @FXML
    private Button back;
    
    @FXML
    private TextField identifiant;
    @FXML
    private TextField artisteMod;
    @FXML
    private TextField fichierMod;
    @FXML
    private TextField morceauxMof;
    @FXML
    private Button idModf;
    @FXML
    private Button idParcourir;
    @FXML
    private TextField textfieldSearch;
    @FXML
    private Button TrierButton;

    /**
     * Initializes the controller class.
     */
    void Search()
    {
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<music> musics = ms.getAll();
        ObservableList<music> listmusics = FXCollections.observableArrayList(musics);  
        
        coloneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colArtiste.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
      colMorceaux.setCellValueFactory(new PropertyValueFactory<>("nom_morceaux"));
          playcolumn.setCellValueFactory(new PropertyValueFactory<>("fichier"));
       tabmusic.setItems(listmusics);
      
       
        textfieldSearch.setPromptText("Rechercher...");
    FilteredList<music> filteredMusicList = new FilteredList<>(listmusics, music -> true);

    // Ajouter un écouteur sur le champ de recherche pour mettre à jour le prédicat de filtrage
    textfieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredMusicList.setPredicate(music ->
                music.getNom_morceaux().toLowerCase().contains(newValue.toLowerCase())
                || music.getNom_artiste().toLowerCase().contains(newValue.toLowerCase())
                || music.getFichier().toLowerCase().contains(newValue.toLowerCase())
        );
    });

    // Mettre à jour le contenu du tableau avec la liste filtrée
    tabmusic.setItems(filteredMusicList);
  
    }

    @FXML
    private void updatebutton(MouseEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
         music m= tabmusic.getSelectionModel().getSelectedItem();
        
        if (m==null) { Alert alert = new Alert(AlertType.ERROR);
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
                ms.supprimer_music(m);
                //updating user data after closing popup
                        ObservableList<music> musics = FXCollections.observableList(ms.getAll());
                tabmusic.setItems(musics);
        }
    }
    }
    @FXML
    private void ReturnAjout(ActionEvent event) {
            try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterMusic.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
    }

  /*  @FXML
    private void search(ActionEvent event) {
        
        
        List<music> musics = ms.searchMusic(textfiealdSearch.getText());
        ObservableList<music> listmusics = FXCollections.observableArrayList(musics);  
        
        coloneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colArtiste.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
      colMorceaux.setCellValueFactory(new PropertyValueFactory<>("nom_morceaux"));
          playcolumn.setCellValueFactory(new PropertyValueFactory<>("fichier"));
       tabmusic.setItems(listmusics);
    }
*/
    @FXML
    private void handlemouseaction(MouseEvent event) {
           m= tabmusic.getSelectionModel().getSelectedItem();
        identifiant.setText(""+ m.getId());
        artisteMod.setText("" + m.getNom_artiste());
        fichierMod.setText("" + m.getFichier());
        morceauxMof.setText("" + m.getNom_morceaux());
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        if ( artisteMod.getText().isEmpty()|| morceauxMof.getText().isEmpty()|| fichierMod.getText().isEmpty()){
          Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("NOT OK");
    alert.setHeaderText("Modification non effectue");
    alert.setContentText("Click Cancel to exit.");
    alert.showAndWait();
        }
        else    if (fichier == null) {
        // Affiche un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un fichier avant d'ajouter une entrée à la base de données.");
        alert.showAndWait();
        return;
    }
        
        
        else{
       

        m.setId(Integer.parseInt(identifiant.getText()));
    m.setNom_artiste(artisteMod.getText());
    m.setNom_morceaux(morceauxMof.getText());
    m.setFichier(fichierMod.getText());
    ms.modifier_Music(m.getNom_morceaux(), m.getNom_artiste(), m.getFichier(), m);
    
   
    
        }
     List<music> musics = ms.getAll();
        ObservableList<music> listmusics = FXCollections.observableArrayList(musics);  
        
        coloneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colArtiste.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
      colMorceaux.setCellValueFactory(new PropertyValueFactory<>("nom_morceaux"));
          playcolumn.setCellValueFactory(new PropertyValueFactory<>("fichier"));
       tabmusic.setItems(listmusics);
    }

    @FXML
    private void Parcourir(ActionEvent event) {
           FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                fichierMod.setText(file.getAbsolutePath());
                try {
                    Path path = Paths.get(file.getAbsolutePath());
                    fichier = Files.readAllBytes(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    @FXML
    private void Trier(ActionEvent event) {
        
        List<music> musics = ms.getAll();
        ObservableList<music> listmusics = FXCollections.observableArrayList(musics);  
        
        coloneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colArtiste.setCellValueFactory(new PropertyValueFactory<>("nom_artiste"));
      colMorceaux.setCellValueFactory(new PropertyValueFactory<>("nom_morceaux"));
          playcolumn.setCellValueFactory(new PropertyValueFactory<>("fichier"));
       tabmusic.setItems(listmusics);
        /*tabmusic.setSortPolicy(tv -> {
            ObservableList<TableColumn<music, ?>> cols = tabmusic.getSortOrder();
            if (cols.isEmpty()) {
                return true;
            }
            TableColumn<music, ?> col = cols.get(0);
            tabmusic.getItems().sort((o1, o2) -> {
                if (col == colArtiste) {
                    return o1. getNom_artiste().compareTo(o2. getNom_artiste());
                } else if (col == colMorceaux) {
                    return o1.getNom_morceaux().compareTo(o2.getNom_morceaux() );
                } else if (col == playcolumn) {
                    return o1.getFichier().compareTo(o2.getFichier());
                } else {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });
            return true;
        });
*/
        // Définir la colonne de tri par défaut
        
        //playcolumn.setSortType(TableColumn.SortType.ASCENDING);
        //tabmusic.getSortOrder().add(colArtiste);
  
  Comparator <music> comparator = Comparator.comparing(music::getNom_morceaux).thenComparing(music::getNom_artiste);
  tabmusic.getItems().sort(comparator);
    }

   

}


  


