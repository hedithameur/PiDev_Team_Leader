/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Evenement;
import tn.esprit.entity.Ticket;
import tn.esprit.services.TicketService;

/**
 * FXML Controller class
 *
 * @author hedit
 */
public class TicketfrontController implements Initializable {
    @FXML
    private Button idback;
    @FXML
    private TableView<Ticket> tabticket;
    @FXML
    private TableColumn<Ticket, String> idtab;
    @FXML
    private TableColumn<Ticket, String> prixtab;
    @FXML
    private TableColumn<Ticket, String> nb_tickettab;
    @FXML
    private TableColumn<Ticket, String> typetab;
    @FXML
    private TableColumn<Ticket, String> id_evenementtab;
    TicketService ps =  new TicketService();
    Ticket p = new Ticket();
    Evenement e1 = new Evenement(1,"pop art event","tunis","2023-02-12",30,15,"poster.png");
    @FXML
    private TextField id;
    @FXML
    private Button ticketbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affichetable();
    }    

    @FXML
    private void back(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Options.fxml"));
            idback.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void affichetable() {
        
        List<Ticket> tickets = ps.findByEventId(e1.getId());
        ObservableList<Ticket> list = FXCollections.observableArrayList(tickets);// convertir list to ObservableList fiha iterator
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nb_tickettab.setCellValueFactory(new PropertyValueFactory<>("nb_tickets"));
        typetab.setCellValueFactory(new PropertyValueFactory<>("type"));
        //id_evenementtab.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
        tabticket.setItems(list);
    }

    @FXML
    private void commande(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("commandefront.fxml"));
            idback.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
