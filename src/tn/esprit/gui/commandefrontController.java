/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.Commande;
import tn.esprit.services.CommandeService;

/**
 * FXML Controller class
 *
 * @author hedit
 */
public class commandefrontController implements Initializable {

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
    private Button deletebtn;
    @FXML
    private TableView<Commande> tabcommande;
    @FXML
    private TableColumn<Commande,String> idtab;
    @FXML
    private TableColumn<Commande,String> nom_evenementtab;
    @FXML
    private TableColumn<Commande,String> prixtab;
    @FXML
    private TableColumn<Commande,String> id_tickettab;
    @FXML
    private TableColumn<Commande,String> id_utilisateurtab;
    @FXML
    private Button Reset;
    @FXML
    private Button pdfbtn;
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
    private void back(ActionEvent event) throws IOException {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ticketfront.fxml"));
            idback.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (nom_evenement.getText().isEmpty()||prix.getText().isEmpty() ||id_ticket.getText().isEmpty() ||id_utilisateur.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Ajout non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
            //p.setId(Integer.parseInt(id.getText()));
            p.setNom_evenement(nom_evenement.getText());
            p.setPrix(Double.parseDouble(prix.getText()));
            p.setId_ticket(Integer.parseInt(id_ticket.getText()));//a changer avec id ticket 
            p.setId_utilisateur(Integer.parseInt(id_utilisateur.getText()));//a changer avec id de l'utilisateur
            ps.ajouter(p);
            affichetable();
            Reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Ajout effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }
    }


    @FXML
    private void delete(ActionEvent event) {
        if (id.getText().isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Suppression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
         }else{
            ps.supprimerCommande(p);
            affichetable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Suppression effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
    }
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
    private void affichetable() {
        List<Commande> commandes =  ps.getCommandeById(26, 1);
        ObservableList<Commande> list = FXCollections.observableArrayList(commandes);// convertir list to ObservableList fiha iterator
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_evenementtab.setCellValueFactory(new PropertyValueFactory<>("nom_evenement"));
        prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        //id_tickettab.setCellValueFactory(new PropertyValueFactory<>("id_ticket"));
        //id_utilisateurtab.setCellValueFactory(new PropertyValueFactory<>("id_utilisateur"));
        tabcommande.setItems(list);
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
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException, IOException {
        Commande c = new Commande();
        c = tabcommande.getSelectionModel().getSelectedItem();
        Document document = new Document();
        if (nom_evenement.getText().isEmpty()||prix.getText().isEmpty() ||id_ticket.getText().isEmpty() ||id_utilisateur.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Impression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Commande.pdf"));
            document.open();
            com.itextpdf.text.Font font = new com.itextpdf.text.Font();
            font.setSize(20);
            font.setColor(BaseColor.GREEN);
            font.isBold();
            font.isItalic();

            com.itextpdf.text.Font fontSmall = new com.itextpdf.text.Font();
            fontSmall.setSize(8);
            document.add(new LineSeparator());

            document.add(new Paragraph("Commande", font));
            document.add(new Paragraph(" "));

            document.add(new LineSeparator());

            document.add(new Paragraph("Identifiant : " + c.getId()));
            document.add(new Paragraph("Nom Evenement : " + c.getNom_evenement()));
            document.add(new Paragraph("Prix : " + c.getPrix()));
            document.add(new Paragraph("Id_Ticket : " + c.getId_ticket()));
            document.add(new Paragraph("Id_User : " + c.getId_utilisateur()));
            document.add(new Paragraph(" "));

            document.newPage();
            document.close();

            writer.close();

            Desktop.getDesktop().open(new File("Commande.pdf"));
        }
    }

    
}
