/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CommenterController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event) {
    }

    @FXML
    private void afficher(ActionEvent event) {
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
    private void reset(ActionEvent event) {
        dField.setValue(null); 
         conField.setText(""); 
          
          idchoix.setValue(null);
    }
    
}
