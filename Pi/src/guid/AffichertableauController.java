/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.entity.Commande_instruments;
import tn.esprit.entity.Instrument;
import tn.esprit.service.ServiceCommande;
import tn.esprit.service.ServiceInstrument;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class AffichertableauController implements Initializable {

    @FXML
    private TableView<Commande_instruments> tableau;
    @FXML
    private TableColumn<Commande_instruments, Integer> idcolumn;
    @FXML
    private TableColumn<Commande_instruments, String> nomcolumn;
    @FXML
    private TableColumn<Commande_instruments, Float> prixcolumn;
        private ObservableList<Commande_instruments> inst;


    /**
     * Initializes the controller class.
     */
    ServiceCommande ps = new ServiceCommande ();
    Commande_instruments p =new  Commande_instruments ( );
    @FXML
    private TextField idnom;
    @FXML
    private TextField prixid;
    @FXML
    private Text idtext;
    @FXML
    private Button ajouterid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
    }    
    @FXML
    public void afficher(){
         List<Commande_instruments> c = ps.getAllInstruments();
        ObservableList<Commande_instruments> listCat = FXCollections.observableArrayList(c);// convertir list to ObservableList fiha iterator
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tableau.setItems(listCat);
    }

    @FXML
    private void ajouter(ActionEvent event) {
         if (idnom.getText().isEmpty()|| prixid.getText().isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Erreur");
            alert.setHeaderText("ajouter des champs");
            
            alert.showAndWait();
        }else{
        p.setNom(idnom.getText());
         p.setPrix(Float.parseFloat(prixid.getText()));
        ps.ajouter(p);
        afficher(); 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("msg");
            alert.setHeaderText("succés");
            
            alert.showAndWait();
      
       
          }
    
    }

    @FXML
    private void mouseclicked(MouseEvent event) {
    
    }

    @FXML
    private void mouse(MouseEvent event) {
         p = tableau.getSelectionModel().getSelectedItem();
    idnom.setText(""+p.getNom());
    prixid.setText(""+p.getPrix());
    
    idtext.setText("");
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        Commande_instruments crt= tableau.getSelectionModel().getSelectedItem();
        
        if (crt==null) { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une categorie table!");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "voulez Vous  vraiment supprimer cet categorie :" +crt.getId_commande()+" ?");
                alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ps.supprimer(crt);
                //updating user data after closing popup
                inst = FXCollections.observableList(ps.getAllInstruments());
                tableau.setItems(inst);
    }

   
    
    
    
        }
}
}

 

    
    
    

