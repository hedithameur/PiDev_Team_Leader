/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceReclamation;
import entites.Reclamation;
import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterReclamationController implements Initializable {
ServiceReclamation sr = new ServiceReclamation();
    Reclamation r=new Reclamation();
    @FXML
    private Label idgestrec;
    @FXML
    private Label iddate;
    @FXML
    private Label iddesc;
    @FXML
    private Label idtitre;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField titreField;
    @FXML
    private TextArea descrField;
    @FXML
    private Button idadd;
    @FXML
    private Button idmod;
    @FXML
    private Button idsup;
    @FXML
    private Button idaff;

   // private LocalDate selectedDate;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button idreset;
    
 
    private TextField id;
    @FXML
    private ComboBox<Integer> idsupp;
    @FXML
    private ImageView idimage;
    @FXML
    private Label idcontrol;
    @FXML
    private Label idcontrol2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* datePicker.setOnAction(event -> {
        selectedDate = datePicker.getValue();
    });*/
      
        ObservableList<Integer> options = FXCollections.observableArrayList(sr.getid());
idsupp.setItems(options);
Integer selectedId =idsupp.getValue();
if (!options.isEmpty()) {
   idsupp.setValue((options.get(0)));
}    
    }    
 
    @FXML
    private void ajouter(ActionEvent event) {
        if (dateField.getValue() == null|| titreField.getText().isEmpty()||descrField.getText().isEmpty()){
        idcontrol.setText("Champ(s) non inséré(s)");
        }else{
             
        
         r.setDate_rec(dateField.getValue());
        r.setTitre(titreField.getText());
         r.setDescription(descrField.getText());
         
         sr.ajouterReclamation(r);
          idcontrol.setText("Ajout avec succès");
          reset();
        
        } 
    }

    @FXML
    private void modifier(ActionEvent event) {
         try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
            titreField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        if (idsupp.getValue() == null){
        idcontrol2.setText("id non inséré");
        }else{
            
      Integer selectedId =idsupp.getValue();
        r.setId_rec(selectedId);
        sr.supprimerReclamation(selectedId);
               ObservableList<Integer> options = FXCollections.observableArrayList(sr.getid());
idsupp.setItems(options);

if (!options.isEmpty()) {
   idsupp.setValue(options.get(0)); 
   
   idcontrol2.setText("Suppression avec succès");
   reset();
   
}
    }
    }
    @FXML
    private void afficher(ActionEvent event) {
        try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherTable.fxml"));
            titreField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void reset() {
        dateField.setValue(null); 
         titreField.setText(""); 
          descrField.setText(""); 
          idsupp.setValue(null);
          
          
          
    }

   
}
