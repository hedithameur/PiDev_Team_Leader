/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import static java.awt.SystemColor.control;
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
import tn.esprit.entity.Ticket;
import tn.esprit.services.TicketService;

/**
 * FXML Controller class
 *
 * @author hedit
 */
public class AjouterTicketsController implements Initializable {

    @FXML
    private Button display;
    @FXML
    private Button afficher;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Label prixdis;
    @FXML
    private Label nb_ticketsdis;
    @FXML
    private Label typedis;
    @FXML
    private Label id_evenementdis;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfnb_tickets;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfid_evenement;
    @FXML
    private Button Back;
    @FXML
    private Button ajouter;
    TicketService ts = new TicketService();
    @FXML
    private Label control;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void fcDisplay(ActionEvent event) {
        prixdis.setText(tfprix.getText());
        nb_ticketsdis.setText(tfnb_tickets.getText());
        typedis.setText(tftype.getText());
        id_evenementdis.setText(tfid_evenement.getText());
    }

    @FXML
    private void fcAfficher(ActionEvent event) {
        
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AfficherTickets.fxml"));
            afficher.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void fcModifier(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource(".fxml"));
            afficher.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void fcSupprimer(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource(""));
            afficher.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void fcBack(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Options.fxml"));
            afficher.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void fcajouter(ActionEvent event) {
        if (tfprix.getText().isEmpty()|| tfnb_tickets.getText().isEmpty()|| tftype.getText().isEmpty()|| tfid_evenement.getText().isEmpty()){
        control.setText("field is empty");
        }else{
        
        Ticket t = new Ticket();
        t.setPrix(Double.parseDouble(tfprix.getText()));
        t.setNb_tickets(Integer.parseInt(tfnb_tickets.getText()));
        t.setType((tftype.getText()));
        
        
        try {
            ps.ajouter(p);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }
    
}
