/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceReclamation;
import entites.Reclamation;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierReclamationController implements Initializable {
ServiceReclamation sr = new ServiceReclamation();
    Reclamation r=new Reclamation();
    @FXML
    private Button idmodif;
    @FXML
    private Button retour1;
    @FXML
    private Label dater;
    @FXML
    private Label idm;
    @FXML
    private Label titrer;
    @FXML
    private Label descrr;
    @FXML
    private Label idr;
    @FXML
    private DatePicker idda;
    @FXML
    private TextField idti;
    @FXML
    private TextArea iddes;
    @FXML
    private ComboBox<Integer> idch;
    @FXML
    private Label idcontrol1;
    @FXML
    private Button idrese;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> options = FXCollections.observableArrayList(sr.getid());
idch.setItems(options);
Integer selectedId = idch.getValue();
if (!options.isEmpty()) {
  /* idch.setValue(options.get(0));
    idda.setValue(sr.findById(options.get(0)).get(0).getDate_rec());
      idti.setText(sr.findById(options.get(0)).get(0).getTitre());
        iddes.setText(String.valueOf(sr.findById(options.get(0)).get(0).getDescription()));*/
        // TODO
    }  
    }
    @FXML
    private void modif(ActionEvent event) {
         if (idda.getValue() == null|| idti.getText().isEmpty()||iddes.getText().isEmpty()||idch.getValue() == null){
        idcontrol1.setText("Champ(s) non inséré(s)");
        }else{
             
        Integer selectedId = idch.getValue();
        r.setId_rec(selectedId);
        r.setDate_rec(idda.getValue());
    r.setTitre(idti.getText());
    r.setDescription(iddes.getText());
    sr.modifierReclamation(r);
    idcontrol1.setText("Reclamation Modifié");
             reset();
    }
    }
    @FXML
    private void retour1(ActionEvent event) {
         try{
         Parent loader = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
            retour1.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
    }

    @FXML
    private void reset() {
         idda.setValue(null); 
         idti.setText(""); 
          iddes.setText(""); 
          idch.setValue(null);
}
    }
    
    
