/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev.entity.Evenement;
import pidev.services.EvenementService;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class SuiviTableController implements Initializable {

   @FXML
    private TableView<Evenement> tblEvents;
    @FXML
    private TableColumn<Evenement, String> idColumn;
    @FXML
    private TableColumn<Evenement, String> NomColumn;
    @FXML
    private TableColumn<Evenement, String> LieuColumn;
    @FXML
    private TableColumn<Evenement, String> DateColumn;
    @FXML
    private TableColumn<Evenement, String> NombreTicketColumn;
    @FXML
    private TableColumn<Evenement, String> prixColumn;
    @FXML
    private Button back;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnMod;
    @FXML
    private TextField combobox_enter1;
    @FXML
    public TextField champ;
   
EvenementService es = new EvenementService();
private ObservableList<Evenement> AllEvents;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Evenement> events = es.affichier();
        ObservableList<Evenement> listEvents = FXCollections.observableArrayList(events);// convertir list to ObservableList fiha iterator
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        LieuColumn.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        NombreTicketColumn.setCellValueFactory(new PropertyValueFactory<>("nb_ticket"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tblEvents.setItems(listEvents);
        
    }    

    @FXML
    private void backfunc(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterEvenementFXML.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GridView(ActionEvent event) {
          
        Evenement rec= tblEvents.getSelectionModel().getSelectedItem();
        
        if (rec==null) { Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur l'evenement que vou voulez supprimer");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Vous etes sur que vos voulez supprimer l'evenement :" +rec.getNom()+" qui va etre fait "+rec.getDate()+" à "+rec.getLieu());
                alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
               // es.supprimerReclamation(rec.getId_rec());
               es.supprimerParNom(rec);
                //updating user data after closing popup
                AllEvents = FXCollections.observableList(es.affichier());
                tblEvents.setItems(AllEvents);
        }
        }
    }

    @FXML
    private void ModifierTable(ActionEvent event) {
        Connection cnx ;
        cnx=MaConnection.getInstance().getCnx();
        
        
         Evenement rec= tblEvents.getSelectionModel().getSelectedItem();
        
        if (rec==null) { Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur l'evenement que vou voulez modifier");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Vous etes sur que vos voulez modifier l'evenement :" +rec.getNom()+" qui va etre fait "+rec.getDate()+" à "+rec.getLieu());
                alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
               
           
            String s=combobox_enter1.getText();
             String s1 = champ.getText();
           es.modifierEvenement1(s,s1,rec);
                //updating user data after closing popup
                AllEvents = FXCollections.observableList(es.affichier());
                tblEvents.setItems(AllEvents);
        }
        }
        
        
        
       /*
        String s = champ.getText();
        String s1=combobox_enter1.getText();
        
        //String sql = "update Evenement set nom=? where id=1";
        String sql = "update Evenement set "+s1+"=? where id=1";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,s);
           // ste.setString(2,s);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
    }
    @FXML
    private void Print(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tblEvents;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }
        }
    }
     @FXML
    private void StatistiqueLieu(ActionEvent event) {
          try {

            Parent parent = FXMLLoader.load(getClass().getResource("chartLieu.fxml"));
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
          
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SuiviTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
}