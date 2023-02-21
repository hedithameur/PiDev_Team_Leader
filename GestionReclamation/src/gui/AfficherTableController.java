/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceReclamation;
import entites.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author user
 */


public class AfficherTableController implements Initializable {
    ServiceReclamation sr = new ServiceReclamation();
    @FXML
    private TableView<Reclamation> TabRec;
    @FXML
    private TableColumn<Reclamation,Integer > idcolumn;
    @FXML
    private TableColumn<Reclamation,String> datecolumn;
    @FXML
    private TableColumn<Reclamation,String> titrecolumn;
    @FXML
    private TableColumn<Reclamation,String> descrcolumn;
    @FXML
    private Button idretour;
    @FXML
    private Button idcomm;
    @FXML
    private Button idsu;
    private ObservableList<Reclamation> reclamations;
    @FXML
    private Button modif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              try {
            List<Reclamation> reclamations = sr.recuperer();
             ObservableList<Reclamation> listrec = FXCollections.observableArrayList(reclamations);  
             idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
             datecolumn.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
             titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
             descrcolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
             TabRec.setItems(listrec);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
        try{
         Parent loader = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
            idretour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
    
    }
    @FXML
    private void commenter(ActionEvent event) {
          try{
         Parent loader;
              loader = FXMLLoader.load(getClass().getResource("commenter.fxml"));
            idcomm.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}

    }

    @FXML
    private void supp(ActionEvent event) {
        Reclamation rec= TabRec.getSelectionModel().getSelectedItem();
        
        if (rec==null) { Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une reclamation table!");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vous veuillez vraiment supprimer cette reclamation :" +rec.getId_rec()+" ?");
                alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                sr.supprimerReclamation(rec.getId_rec());
                //updating user data after closing popup
                reclamations = FXCollections.observableList(sr.getAll());
                TabRec.setItems(reclamations);
        }
    
    }
    
    }

    @FXML
    private void modifier(ActionEvent event) {
        
                 try{
         Parent loader = FXMLLoader.load(getClass().getResource("ModifierReclamation.fxml"));
            idcomm.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
    
                
                
    
    }
    }


