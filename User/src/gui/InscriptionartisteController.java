/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entity.Utilisateur;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class InscriptionartisteController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> combox;
    @FXML
    private PasswordField mdps;
    @FXML
    private DatePicker date;
    @FXML
    private Button btnconnecter;
    @FXML
    private Button btninscrire;
    
    UtilisateurService us=new UtilisateurService();
     Utilisateur u= new Utilisateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ObservableList<String> list = FXCollections.observableArrayList("Artiste");
       combox.setItems(list);
        
    }    

    

    @FXML
    private void inscrire(ActionEvent event) {
        
        if (nom.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre NOM");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
          if (prenom.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre PRENOM");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
          if (tel.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre TELEPHONE");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
          
          if (email.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre EMAIL");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
          if (mdps.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre MOT DE PASSE");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
         
          if(tel.getLength()!=8) {
              
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Numéro de téléphone incorrecte ! veuillez saisir 8 chiffres");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
          if (!email.getText().contains("@")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("L'adresse e-mail doit contenir un '@'.");
            alert.showAndWait();
        }
        
        else{
            //  selectionUtilisateur=textemail.getSelectedText()
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setRole(combox.getValue());
        u.setEmail(email.getText());
        u.setTelephone(Integer.parseInt(tel.getText()));
        u.setMot_de_passe(mdps.getText());
        u.setdateNaiss(date.getValue());
        us.ajouter(u);
        
        reset();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Artiste ajouter avec succés");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
    }
    }
    
    
    @FXML
    private void gotologin(ActionEvent event) {
        
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("artistelogin.fxml"));
            btnconnecter.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
