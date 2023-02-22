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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.UtilisateurService;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.input.TouchEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import tools.MaConnection;


/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class EmptyyController implements Initializable {

    @FXML
    private Label labelnom;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelrole;
    @FXML
    private Label labelemail;
    @FXML
    private Label labeltel;
    @FXML
    private Label labelmotdepasse;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField texttel;
    @FXML
    private TextField textemail;
    @FXML
    private Button btnajout;
    @FXML
    private PasswordField textmotdepasse;
    @FXML
    private Button btnseconnecter2;
    @FXML
    

     UtilisateurService us=new UtilisateurService();
     Utilisateur u= new Utilisateur();
     
    @FXML
    private Button afficher;
    @FXML
    private ComboBox<String> comb;
    
    @FXML
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> list = FXCollections.observableArrayList("Artiste","Membre");
        comb.setItems(list);
    
    }    

     private void ajouter(ActionEvent event) {
        
      
    }
   
  
     
    @FXML
    private void gotologin(ActionEvent event) {
        
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
            btnseconnecter2.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void affichetable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void sinscrire(ActionEvent event) {
        
          if (textnom.getText().isEmpty()||textprenom.getText().isEmpty()
         ||textemail.getText().isEmpty()|| texttel.getText().isEmpty()||textmotdepasse.getText().isEmpty()
           )
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        
        else{
        u.setNom(textnom.getText());
        u.setPrenom(textprenom.getText());
        u.setRole(comb.getValue());
        u.setEmail(textemail.getText());
        u.setTelephone(Integer.parseInt(texttel.getText()));
        u.setMot_de_passe(textmotdepasse.getText());
        us.ajouter(u);
        //affichetable();
        reset();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Ajout effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void gotoafficher(ActionEvent event) {
        
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("affichemodifsupp.fxml"));
            afficher.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void touch(TouchEvent event) {
    }

        
    }
    

