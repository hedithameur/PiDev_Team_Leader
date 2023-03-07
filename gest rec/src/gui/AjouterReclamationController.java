/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceReclamation;
import entites.Reclamation;
//import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDate;
//import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tools.MaConnection;

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
    
 Connection cnx;
    @FXML
    private TextField idtel;
    @FXML
    private Label idlabel11;
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
   idsupp.setValue(null);
}    
    }    
   /* @FXML
    private Boolean testTel() {
        if (telField.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < telField.getText().trim().length(); i++) {
                char ch = telField.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

          
        }

        return true;

    }*/
    public void Notification() throws SQLException{
        String req = "select * from reclamation where description ='"+descrField.getText()+"' ";
        cnx=MaConnection.getInstance().getCnx();
    Statement stm = cnx.createStatement(); 
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
        Notifications notifications=Notifications.create();
        notifications.text(" Date_rec: "+rst.getString("date_rec")+" \n Titre: "+rst.getString("titre")+" \n Description: "+rst.getString("description")+" \n Tel: "+rst.getString("tel"));
        notifications.title("Reclamation Enregistée");
        notifications.hideAfter(Duration.seconds(10));
        notifications.darkStyle();
        notifications.position(Pos.BOTTOM_RIGHT);
        notifications.show();
        }   
    
  }
 
    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if(idtel.getText().length()!=8)
            idlabel11.setText("Num doit contenir 8 chiffres");
        else if (dateField.getValue() == null|| titreField.getText().isEmpty()||descrField.getText().isEmpty()||idtel.getText().isEmpty()){
        idcontrol.setText("Champ(s) non inséré(s)");
        
        
        }else{
             
        
         r.setDate_rec(dateField.getValue());
        r.setTitre(titreField.getText());
         r.setDescription(descrField.getText());
         r.setTel(idtel.getText());
         
         sr.ajouterReclamation(r);
          idcontrol.setText("Ajout avec succès");
          idlabel11.setText("");
          Notification();
          reset();
          ObservableList<Integer> options = FXCollections.observableArrayList(sr.getid());
idsupp.setItems(options);

        
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
          idtel.setText("");
         
          
          
          
    }

   
}
