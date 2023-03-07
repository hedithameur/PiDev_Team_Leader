/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import pidev.entity.Ticket;
import pidev.services.TicketService;


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
    @FXML
    private Button exportButton;
  
    //private int itemsPerPage = 5;
    @FXML
    private TextField textfiealdSearch;
    
    TicketService ps =  new TicketService();
    Ticket p = new Ticket();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Initialize pagination
        //pagination();
        affichetable();
        search();
            
        
    }    
    private void search(){
        List<Ticket> tickets = ps.getAll();
        ObservableList<Ticket> list = FXCollections.observableArrayList(tickets);
        textfiealdSearch.setPromptText("Rechercher...");
        FilteredList<Ticket> filteredTicketList = new FilteredList<>(list, Ticket -> true);

        // Ajouter un écouteur sur le champ de recherche pour mettre à jour le prédicat de filtrage
        textfiealdSearch.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredTicketList.setPredicate(Ticket ->
                    Ticket.getType().toLowerCase().contains(newValue.toLowerCase())                    
            );
        });
        // Mettre à jour le contenu du tableau avec la liste filtrée
        tabticket.setItems(filteredTicketList);
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
    
    @FXML
    private void exportToCSV(ActionEvent event) {
        // Create a new file chooser dialog
    FileChooser fileChooser = new FileChooser();

    // Set the extension filter to only show CSV files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show the save file dialog
    File file = fileChooser.showSaveDialog(null);

    if (file != null) {
        try {
            // Create a new FileWriter with the selected file
            FileWriter fileWriter = new FileWriter(file);

            // Write the table data to the file
            for (Ticket ticket : tabticket.getItems()) {
                fileWriter.write(ticket.getId() + "," + ticket.getPrix() + "," + ticket.getNb_tickets() + "," + ticket.getType() + "," + ticket.getId_evenement() + "\n");
            }

            // Close the file writer
            fileWriter.close();

            // Show a success message to the user
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Export Success");
            alert.setHeaderText(null);
            alert.setContentText("Table data has been exported to " + file.getName() + ".");
            alert.showAndWait();
        } catch (IOException e) {
            // Show an error message to the user if the export fails
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Export Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting the table data to " + file.getName() + ".");
            alert.showAndWait();
        }
    }
    }
}
