/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceComm;
import Service.ServiceReclamation;
import entites.Commentaire_Rec;
import entites.Reclamation;
import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/*import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;*/
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import tools.MaConnection;
import java.net.URL;
/*import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;*/
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
/*import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

/**
 * FXML Controller class
 *
 * @author user
 */
public class CommenterController implements Initializable {
    ServiceComm sc = new ServiceComm();
    Commentaire_Rec c=new Commentaire_Rec();
    ServiceReclamation sr = new ServiceReclamation();
    Reclamation r=new Reclamation();
    @FXML
    private ComboBox<Integer> idchoix;
    @FXML
    private DatePicker dField;
    @FXML
    private TextArea conField;
    @FXML
    private Button aj;
    @FXML
    private Button aff;
    @FXML
    private Button mod;
    @FXML
    private Button supp;
    @FXML
    private ComboBox<Integer> idcho;
    @FXML
    private Button retour2;
    @FXML
    private Button res;
    @FXML
    private Label idcontrol3;
    @FXML
    private Label idcontrol4;
    @FXML
    private Label idtit;
    @FXML
    private Button idpub;
    @FXML
    private TextField idemail;
    @FXML
    private Label idlabel10;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> options = FXCollections.observableArrayList(sr.getid());
idchoix.setItems(options);
Integer selectedId =idchoix.getValue();
if (!options.isEmpty()) {
   idchoix.setValue((options.get(0)));
}  
ObservableList<Integer> options1 = FXCollections.observableArrayList(sc.getid());
idcho.setItems(options1);
Integer selectedId1 =idcho.getValue();
if (!options1.isEmpty()) {
   idcho.setValue((options1.get(0)));
}   
idchoix.setValue(AfficherTableController.selectionedReclamation.getId_rec());
idemail.setText(AfficherTableController.selectionedReclamation.getTel());
idtit.setText(AfficherTableController.selectionedReclamation.getTitre());
idchoix.setDisable(true);
idemail.setDisable(true);

        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) {
          if (dField.getValue() == null|| conField.getText().isEmpty()||idchoix.getValue() == null||idemail.getText().isEmpty()){
        idcontrol3.setText("Champ(s) non inséré(s)");
        }else{
             
        Integer selectedId = idchoix.getValue();
        c.setId_rec(selectedId);
         c.setDate_comm(dField.getValue());
        c.setContenu(conField.getText());
         
         
         sc.ajouterCommentaire_Rec(c);
          idcontrol3.setText("Ajout avec succès");
          sms s = new sms();
          
         s.sendSms("'Bonjour cher client, "+ conField.getText()+" , Merci pour votre patience!'",idemail.getText() );
          reset();
         
ObservableList<Integer> options1 = FXCollections.observableArrayList(sc.getid());
idcho.setItems(options1);

        
        } 

    }
    

    @FXML
    private void afficher(ActionEvent event) {
         try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherTableau2.fxml"));
            dField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
         try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("ModifierCommentaire.fxml"));
            dField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        if (idcho.getValue() == null){
        idcontrol4.setText("id non inséré");
        }else{
            
      Integer selectedId1 =idcho.getValue();
        c.setId_comm(selectedId1);
        sc.supprimerCommentaire_Rec(selectedId1);
               ObservableList<Integer> options1 = FXCollections.observableArrayList(sc.getid());
idcho.setItems(options1);

if (!options1.isEmpty()) {
   idcho.setValue(options1.get(0)); 
   
   idcontrol4.setText("Suppression avec succès");
   reset();
   
}
    }
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherTable.fxml"));
            conField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void reset() {
        dField.setValue(null); 
         conField.setText(""); 
          
         // idchoix.setValue(null);
          idcho.setValue(null);
    }

    @FXML
    private void publier(ActionEvent event) {
          try {
           
            Parent loader = FXMLLoader.load(getClass().getResource("Publication.fxml"));
            conField.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
 
    
}
