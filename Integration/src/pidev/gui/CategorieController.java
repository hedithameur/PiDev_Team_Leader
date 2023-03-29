/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pidev.entity.CategorieInstrument;
import pidev.services.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class CategorieController implements Initializable {
ServiceCategorie ct = new ServiceCategorie();
CategorieInstrument c = new CategorieInstrument() ;
    @FXML
    private TextField Nomid;
    @FXML
    private TextField descriptionid;
    @FXML
    private Button ajouterBtn;
    @FXML
    private Button SupprimerBtn;
    @FXML
    private Button ModifierBtn;
    @FXML
    private Button idInst;
    @FXML
    private TableColumn<CategorieInstrument, Integer> idcomlumn;
    @FXML
    private TableColumn<CategorieInstrument, String> descriptioncolumn;
    @FXML
    private Text IDtext;
    @FXML
    private Label control;
    @FXML
    private TableView<CategorieInstrument> tableauinstrument;
    @FXML
    private TableColumn<CategorieInstrument, String> nomcomlumn;
    private ObservableList<CategorieInstrument> inst;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichertab();
        // TODO
    }    

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        
          if (Nomid.getText().isEmpty()|| descriptionid.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
            alert.setHeaderText("ajouter des champs");
            
            alert.showAndWait();
        }else{
               c.setNom_categorie(Nomid.getText());
         c.setDescription(descriptionid.getText());
        ct.ajouter(c);
         affichertab();
        reset();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("msg");
            alert.setHeaderText("succés");
            
            alert.showAndWait();
      
          }
    }
 private void reset() {
       Nomid.setText("");
       descriptionid.setText("");
    }
    @FXML
    private void supprimerCategorie(ActionEvent event) {
    CategorieInstrument crt= tableauinstrument.getSelectionModel().getSelectedItem();
        
        if (crt==null) { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une categorie table!");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "voulez Vous  vraiment supprimer cet categorie :" +crt.getId()+" ?");
                alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ct.supprimer(crt);
                //updating user data after closing popup
                inst = FXCollections.observableList(ct.getAllInstruments());
                tableauinstrument.setItems(inst);
        }
    
    }}

    @FXML
    private void modifierCategorie(ActionEvent event) {
          if (Nomid.getText().isEmpty()  || descriptionid.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
            alert.setHeaderText("mofication rejeté");
            
            alert.showAndWait();}
    else{
    c.setNom_categorie(Nomid.getText());
    c.setDescription(descriptionid.getText());
    ct.modifier(c.getNom_categorie(), c.getDescription(), c);
              affichertab();
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("msg");
            alert.setHeaderText("modification avec sucées");
            
            alert.showAndWait();
          }
    }

    @FXML
    private void action(ActionEvent event) {
        try{
         Parent loader;
              loader = FXMLLoader.load(getClass().getResource("../gui/Instrument.fxml"));
            Nomid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        c = tableauinstrument.getSelectionModel().getSelectedItem();
    Nomid.setText(""+c.getNom_categorie());
    descriptionid.setText(""+c.getDescription());
    
    IDtext.setText("");
    }

    @FXML
    private void affichertab() {
        List<CategorieInstrument> categorie = ct.getAllInstruments()
                
                
                
                
              
                
     ;
        ObservableList<CategorieInstrument> listCat = FXCollections.observableArrayList(categorie);// convertir list to ObservableList fiha iterator
        idcomlumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcomlumn.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableauinstrument.setItems(listCat);
    }

    @FXML
    private void back(ActionEvent event) {
                try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../Integration/IntegrationAceuil.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
    
