/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Commande;
import tn.esprit.services.CommandeService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author hedit
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom_evenement;
    @FXML
    private TextField prix;
    @FXML
    private TextField id_ticket;
    @FXML
    private TextField id_utilisateur;
    @FXML
    private Button idback;
    @FXML
    private Button idadd;
    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private TableView<Commande> tabcommande;
    @FXML
    private TableColumn<Commande, String> idtab;
    @FXML
    private TableColumn<Commande, String> nom_evenementtab;
    @FXML
    private TableColumn<Commande, String> prixtab;
    @FXML
    private TableColumn<Commande, String> id_tickettab;
    @FXML
    private TableColumn<Commande, String> id_utilisateurtab;
    @FXML
    private Button Reset;
    CommandeService ps = new CommandeService();
    Commande p = new Commande();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        affichetable();
    }  
    
    @FXML
    private void Reset() {
        id.setText("");
        nom_evenement.setText("");
        prix.setText("");
        id_ticket.setText("");
        id_utilisateur.setText("");
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) {
        p = tabcommande.getSelectionModel().getSelectedItem();
        nom_evenement.setText("" + p.getNom_evenement());
        id.setText("" + p.getId());
        prix.setText("" + p.getPrix());
        id_ticket.setText("" + p.getId_ticket());
        id_utilisateur.setText("" + p.getId_utilisateur());
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
    private void ajouter(ActionEvent event) {
        if (nom_evenement.getText().isEmpty()||prix.getText().isEmpty() ||id_ticket.getText().isEmpty() ||id_utilisateur.getText().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
            //p.setId(Integer.parseInt(id.getText()));
            p.setNom_evenement(nom_evenement.getText());
            p.setPrix(Double.parseDouble(prix.getText()));
            p.setId_ticket(Integer.parseInt(id_ticket.getText()));
            p.setId_utilisateur(Integer.parseInt(id_utilisateur.getText()));
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
    private void update(ActionEvent event) {
        if (nom_evenement.getText().isEmpty()||prix.getText().isEmpty() ||id_ticket.getText().isEmpty() ||id_utilisateur.getText().isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
            //p.setId(Integer.parseInt(id.getText()));
            p.setNom_evenement(nom_evenement.getText());
            p.setPrix(Double.parseDouble(prix.getText()));
            p.setId_ticket(Integer.parseInt(id_ticket.getText()));
            p.setId_utilisateur(Integer.parseInt(id_utilisateur.getText()));
            p.setPrix(Double.parseDouble(prix.getText()));
            ps.modifierCommande(p.getNom_evenement(),p.getPrix(), p);
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
    private void delete(ActionEvent event) {
        if (id.getText().isEmpty()){
             Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Suppression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
         }else{
            ps.supprimerCommande(p);
            affichetable();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Suppression effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
         }
    }

    

    @FXML
    private void affichetable() {
        List<Commande> commandes = ps.getAll();
        ObservableList<Commande> list = FXCollections.observableArrayList(commandes);// convertir list to ObservableList fiha iterator
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_evenementtab.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
        prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_tickettab.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));
        id_utilisateurtab.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        tabcommande.setItems(list);
    }

}

    
