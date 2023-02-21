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

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class EvenementTypeController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML
    private Label NomLabel;
    @FXML
    private Label LieuLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label Nb_ticketLabel;
    @FXML
    private Label PrixLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setEvent(Evenement e) {
        idLabel.setText(String.valueOf(e.getId()));
        NomLabel.setText(e.getNom());
        LieuLabel.setText(e.getLieu());
        DateLabel.setText(String.valueOf(e.getDate()));
        Nb_ticketLabel.setText(String.valueOf(e.getNb_ticket()));
        PrixLabel.setText(String.valueOf(e.getPrix()));
    }
}
