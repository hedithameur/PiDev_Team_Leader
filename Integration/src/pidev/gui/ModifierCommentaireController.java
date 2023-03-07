/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import pidev.services.ServiceComm;
import pidev.services.ServiceReclamation;
import pidev.entity.Commentaire_Rec;
import pidev.entity.Reclamation;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ModifierCommentaireController implements Initializable {
ServiceComm sc = new ServiceComm();
Commentaire_Rec c=new Commentaire_Rec();
ServiceReclamation sr = new ServiceReclamation();
    Reclamation r=new Reclamation();
    @FXML
    private ComboBox<Integer> cid;
    @FXML
    private DatePicker datid;
    @FXML
    private TextArea conid;
    @FXML
    private Button retour11;
    @FXML
    private Button modif;
    private Label idcontrol5;
    @FXML
    private Button idres;
    @FXML
    private Label idcontrol6;
    @FXML
    private ComboBox<Integer> rid;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Integer> options = FXCollections.observableArrayList(sc.getid());
         cid.setItems(options);
Integer selectedId = cid.getValue();
if (!options.isEmpty()) {
        // TODO
    }   
ObservableList<Integer> options1 = FXCollections.observableArrayList(sr.getid());
         rid.setItems(options1);
Integer selectedId1 = rid.getValue();
if (!options1.isEmpty()) {
        // TODO
    }    
    }
    @FXML
    private void retour(ActionEvent event) {
         try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("Commenter.fxml"));
            cid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
     if (datid.getValue() == null|| conid.getText().isEmpty()||cid.getValue() == null||rid.getValue() == null){
        idcontrol6.setText("Champ(s) non inséré(s)");
        }else{
             
        Integer selectedId = cid.getValue();
        c.setId_comm(selectedId);
        Integer selectedId1= rid.getValue();
        c.setId_rec(selectedId1);
        
        c.setDate_comm(datid.getValue());
    c.setContenu(conid.getText());
    c.setId_rec(rid.getValue());
    sc.modifierCommentaire_Rec(c);
    idcontrol6.setText("Commentaire Modifié");
             reset();
    }   
    }

    @FXML
    private void reset() {
        datid.setValue(null); 
         conid.setText(""); 
           rid.setValue(null);
          cid.setValue(null);
        
    }
    
    } 

