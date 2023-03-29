/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidev.entity.Evenement;
import pidev.services.EvenementService;
import pidev.services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AjouterEvenementFXMLController implements Initializable {
private int userId;
    @FXML
    private TextField nom_artiste;
    @FXML
    private TextField nom_spectacle;
    @FXML
    private DatePicker date;
    @FXML
    private TextField lieu;
    @FXML
    private TextField nombre_ticket;
    @FXML
    private TextField prix_ticket;
    @FXML
    private Button btn_choisir_affiche;
    @FXML
    private Label label_affiche;
    @FXML
    private Button btn_confirmer;
    @FXML
    private Button btn_suivi;
    @FXML
    private Button btn_historique;
    @FXML
    private Label control;
EvenementService es = new EvenementService();
String nomPath;
    @FXML
    private Button retour;
   public void setUserId(int userId) {
        this.userId = userId;
       System.out.println("UserID "+ userId);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void ChoisirAffiche(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", listFichier));
        File f = fc.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Integration\\ressources" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Done");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("/ressources/" + f.getName());
//            System.out.println(file.toURI().toString());
            //UserImg.setImage(new Image(file.toURI().toString()));
            String Imguser = f.getName();
           System.out.println(Imguser);
            //ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }
        
        
                label_affiche.setText("/ressources/" + f.getName());
       UtilisateurService u = new UtilisateurService();
nom_artiste.setText(u.getNomArtiste(userId));
    }

    @FXML
    private void btn_confirmer(ActionEvent event) {
     
        if ( nom_spectacle.getText().isEmpty()){
       control.setText("nom spectacle non rempli");
        }
       if ( nombre_ticket.getText().isEmpty()){
       control.setText("Nombre ticket non specifié");
       }
       if (prix_ticket.getText().isEmpty()){
       control.setText("prix de ticket non specifié");
       }
       if ( lieu.getText().isEmpty()){
       control.setText("lieu non specifié");
      
        }else{
        
        Evenement e = new Evenement();
        //e.setId(Integer.parseInt(nom_artiste.getText()));
        e.setNom(nom_spectacle.getText());
        e.setLieu(lieu.getText());
        e.setNb_ticket(Integer.parseInt(nombre_ticket.getText()));
        e.setPrix(Double.parseDouble(prix_ticket.getText()));
        e.setAffiche(label_affiche.getText());
        System.out.println(label_affiche.getText());
        
        
       //SimpleDateFormat date_format = new SimpleDateFormat("yyyy-mm-dd");
       
        // print date in the specified format
        //String date_string = date_format.format(date);
        String datestr = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(datestr);
       // e.setDate(Date.valueOf(date_string));
       e.setDate(Date.valueOf(datestr));
        es.ajouteraffiche(e);
        
        
        reset();
    }}
       
     
    
    private void reset(){
        nom_artiste.setText("");
        nom_spectacle.setText("");
        nombre_ticket.setText("");
        prix_ticket.setText("");
    }

    @FXML
    private void btn_suivi(ActionEvent event) {
                     try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SuiviTable.fxml"));
    Parent root = loader.load();
    
    SuiviTableController Controller = loader.getController();
    Controller.setUserId( userId);
            btn_suivi.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btn_historique(ActionEvent event) {
    }

    @FXML
    private void RetourArtisteDash(ActionEvent event) {
                     try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("artistedash.fxml"));
    Parent root = loader.load();
    
    ArtistedashController Controller = loader.getController();
    Controller.setUserId( userId);
            retour.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
