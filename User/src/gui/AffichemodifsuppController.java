
package gui;

import com.sun.webkit.perf.WCFontPerfLogger;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entity.Utilisateur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.UtilisateurService;


public class AffichemodifsuppController implements Initializable {

    @FXML
    private TableView<Utilisateur> tabuser;
    
    UtilisateurService us = new UtilisateurService();
    Utilisateur u= new Utilisateur();
    
    @FXML
    private TableColumn<Utilisateur, String> idtab;
    @FXML
    private TableColumn<Utilisateur, String> nomtab;
    @FXML
    private TableColumn<Utilisateur, String> prenomtab;
    @FXML
    private TableColumn<Utilisateur, String> roletab;
    @FXML
    private TableColumn<Utilisateur, String> teltab;
    @FXML
    private TableColumn<Utilisateur, String> emailtab;
    @FXML
    private TableColumn<Utilisateur, String> mdptab;
    @FXML
    private TextField idaffiche;
    @FXML
    private TextField nomaffiche;
    @FXML
    private TextField prenomaffiche;
    @FXML
    private TextField roleaffiche;
    @FXML
    private TextField telaffiche;
    @FXML
    private TextField emailaffiche;
    @FXML
    private TextField mdpaffiche;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Utilisateur> utilisateurs = us.getAll();
        ObservableList<Utilisateur> list = FXCollections.observableArrayList(utilisateurs);
        idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomtab.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        roletab.setCellValueFactory(new PropertyValueFactory<>("role"));
        teltab.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        emailtab.setCellValueFactory(new PropertyValueFactory<>("email"));
        mdptab.setCellValueFactory(new PropertyValueFactory<>("mot_de_passe"));
        tabuser.setItems(list);
    }    

    @FXML
    private void affichetab() {
       
    }

    @FXML
    private void handlemouseaction(MouseEvent event) {
        u = tabuser.getSelectionModel().getSelectedItem();
        idaffiche.setText(""+ u.getId());
        nomaffiche.setText("" + u.getNom());
        prenomaffiche.setText("" + u.getPrenom());
        roleaffiche.setText("" + u.getRole());
        telaffiche.setText("" + u.getTelephone());
        emailaffiche.setText("" + u.getEmail());
        mdpaffiche.setText("" + u.getMot_de_passe());
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        if (nomaffiche.getText().isEmpty()||prenomaffiche.getText().isEmpty()||roleaffiche.getText().isEmpty()||
         telaffiche.getText().isEmpty()||emailaffiche.getText().isEmpty()||mdpaffiche.getText().isEmpty()) {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Modification non effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        else{
            u.setNom(nomaffiche.getText());
            u.setPrenom(prenomaffiche.getText());
            u.setRole(roleaffiche.getText());
            u.setTelephone(Integer.parseInt(telaffiche.getText()));
            u.setEmail(emailaffiche.getText());
            u.setMot_de_passe(mdpaffiche.getText());
            
            us.modifierUtilisateur(u.getNom(), u.getPrenom(), u.getEmail(), u);
            
            affichetab();
            reset();
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Modification effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
           
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        if (idaffiche.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("suppression non effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
         
        }
        else{
            us.supprimerUtilisateur(u);
            affichetab();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setHeaderText("suppression effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        
        
    }
    
}
