/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Integration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author user
 */
public class IntegrationAceuilController implements Initializable {

    @FXML
    private Button evenement;
    @FXML
    private Button Instruements;
    @FXML
    private Button ticket;
    @FXML
    private Button Reclamation;
    @FXML
    private Button musique;
    @FXML
    private Button user;
    @FXML
    private Button deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AllerEvenement(ActionEvent event) {
                  try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/AjouterEvenementFXML.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void AllerUtilisateur(ActionEvent event) {
                 try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/emptyy.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void AllerInstruments(ActionEvent event) {
                  try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/Categorie.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void tickets(ActionEvent event) {
                      try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/Options.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AllerReclamation(ActionEvent event) {
                      try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/AfficherTable.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void AllerMusique(ActionEvent event) {
                  try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/musicPlaylist.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deconnexionAdmin(ActionEvent event) {
                 try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/adminlogin.fxml"));
            musique.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
