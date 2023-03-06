/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import pidev.entity.Evenement;
import pidev.entity.commentaire;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class EvenementTypeController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML
    private Label commentaireLabel;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setEvent(commentaire c) {
        idLabel.setText(String.valueOf(c.getId_utilisateur()));
        commentaireLabel.setText(c.getTexte());
       
    }
}
