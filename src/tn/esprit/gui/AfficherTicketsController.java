/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.entity.Ticket;
import tn.esprit.services.TicketService;

/**
 * FXML Controller class
 *
 * @author hedit
 */
public class AfficherTicketsController implements Initializable {
    TicketService ts = new TicketService();
    @FXML
    private TableColumn<Ticket, Integer> idCol;
    @FXML
    private TableColumn<Ticket, Double> prixCol;
    @FXML
    private TableColumn<Ticket, Integer> nb_ticketsCol;
    @FXML
    private TableColumn<Ticket, String> typeCol;
    @FXML
    private TableColumn<Ticket, Integer> Id_evenementCol;
    @FXML
    private Button back;
    @FXML
    private TableView<Ticket> tblTickets;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Ticket> tickets = ts.getAll();
        ObservableList<Ticket> listTick = FXCollections.observableArrayList(tickets);// convertir list to ObservableList fiha iterator
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nb_ticketsCol.setCellValueFactory(new PropertyValueFactory<>("nb_tickets"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        Id_evenementCol.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
        tblTickets.setItems(listTick);
        
    }    

    @FXML
    private void backfunc(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterTickets.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
