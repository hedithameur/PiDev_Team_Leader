/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.service.ServiceCategorie;
import tn.esprit.service.ServiceInstrument;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class FXMLController implements Initializable {
ServiceCategorie ct = new ServiceCategorie();
    @FXML
    private TextField titreid;
    @FXML
    private TextField descriptionid;
    @FXML
    private Button ajouterBtn;
    
    @FXML
    private Label control;
    @FXML
    private Button afficherbutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterInstrument(ActionEvent event) {
        if (titreid.getText().isEmpty()|| descriptionid.getText().isEmpty()){
        control.setText("field is empty");
        }else{
        
        CategorieInstrument p = new CategorieInstrument();
        
        p.setNom(titreid.getText());
        p.setDescription(descriptionid.getText());
        ct.ajouter(p);
        reset();
    }}

    private void reset() {
       titreid.setText("");
        
        descriptionid.setText("");
    }

    @FXML
    private void afficherInstrument(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherInstrument.fxml"));
            titreid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    }
    

