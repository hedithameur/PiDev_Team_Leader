/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Ticket;
import tn.esprit.services.TicketService;

/**
 * FXML Controller class
 *
 * @author hedit
 */
public class TicketController implements Initializable {

    @FXML
    private Button idback;
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
    @FXML
    private TableView<Ticket> tabticket;
    
    TicketService ps =  new TicketService();
    Ticket p = new Ticket();
    @FXML
    private TextField prix;
    @FXML
    private TextField nb_tickets;
    @FXML
    private TextField type;
    @FXML
    private TextField id_evenement;
    @FXML
    private Button idadd;
    @FXML
    private Button Reset;
    @FXML
    private Button deletebtn;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField id;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affichetable();
    }    

    @FXML
    private void affichetable() {
        List<Ticket> tickets = ps.getAll();
        ObservableList<Ticket> list = FXCollections.observableArrayList(tickets);// convertir list to ObservableList fiha iterator
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        nb_tickettab.setCellValueFactory(new PropertyValueFactory<>("nb_tickets"));
        typetab.setCellValueFactory(new PropertyValueFactory<>("type"));
        id_evenementtab.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
        tabticket.setItems(list);
    }
    
    @FXML
    private void ajouter(ActionEvent event) {
        if (prix.getText().isEmpty()||nb_tickets.getText().isEmpty() ||type.getText().isEmpty() ||id_evenement.getText().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
            //p.setId(Integer.parseInt(id.getText()));
            p.setNb_tickets(Integer.parseInt(nb_tickets.getText()));
            p.setId_evenement(Integer.parseInt(id_evenement.getText()));
            p.setType(type.getText());
            p.setPrix(Double.parseDouble(prix.getText()));
            ps.ajouter(p);
            affichetable();
            Reset();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Ajout effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }
        
}

    @FXML
    private void Reset() {
        id.setText("");
        prix.setText("");
        nb_tickets.setText("");
        id_evenement.setText("");
        type.setText("");
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
    private void delete(ActionEvent event) {
         if (id.getText().isEmpty()){
             Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Suppression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
         }else{
            ps.supprimerTicket(p);
            affichetable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Suppression effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
         }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        p = tabticket.getSelectionModel().getSelectedItem();
        id.setText("" + p.getId());
        prix.setText("" + p.getPrix());
        nb_tickets.setText("" + p.getNb_tickets());
        id_evenement.setText("" + p.getId_evenement());
        type.setText("" + p.getType());
    }

    @FXML
    private void update(ActionEvent event) {
        if (id.getText().isEmpty() || prix.getText().isEmpty()||nb_tickets.getText().isEmpty() ||type.getText().isEmpty() ||id_evenement.getText().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Modification non effectue");
            alert.setContentText("Click Cancel to  exit.");
            alert.showAndWait();
        }else{
            p.setId(Integer.parseInt(id.getText()));
            p.setNb_tickets(Integer.parseInt(nb_tickets.getText()));
            p.setId_evenement(Integer.parseInt(id_evenement.getText()));
            p.setType(type.getText());
            p.setPrix(Double.parseDouble(prix.getText()));
            ps.modifierTicket(p.getPrix(),p.getNb_tickets() ,p.getType(), p);
            affichetable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Modification effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
    }
    

    
}
}
