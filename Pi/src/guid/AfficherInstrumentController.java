/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

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
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.service.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class AfficherInstrumentController implements Initializable {
    ServiceCategorie ps = new ServiceCategorie();
    @FXML
    private TableView<CategorieInstrument> tableauinstrument;
    @FXML
    private TableColumn<CategorieInstrument, String> descriptioncolumn;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<CategorieInstrument, String> idcomlumn;
    @FXML
    private TableColumn<CategorieInstrument, String> nomcolumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List<CategorieInstrument> categorie = ps.afficher();
        ObservableList<CategorieInstrument> listCat = FXCollections.observableArrayList(categorie);// convertir list to ObservableList fiha iterator
        idcomlumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableauinstrument.setItems(listCat);
        
    }    

    @FXML
    private void retourfonction(ActionEvent event) {
      try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AddInstrument.fxml"));
            retour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      

    
}
