
package pidev.gui;

import com.sun.webkit.perf.WCFontPerfLogger;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import pidev.entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pidev.services.UtilisateurService;


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
    @FXML
    private TextField recherche;
    private TableColumn<Utilisateur, String> dntab;
    @FXML
    private Button exit;

    
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
        
        
        
        utilisateurs = us.getAll();
        ObservableList<Utilisateur> listuser = FXCollections.observableArrayList(utilisateurs);
        FilteredList<Utilisateur> filteredList = new FilteredList<>(listuser);
        // Configurer le prédicat en fonction de la saisie de l'utilisateur
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(Utilisateur -> {
                // Si le champ de texte est vide, afficher tous les éléments
                if (newValue == null || newValue.isEmpty()) {
                    
                    return true;
                }
                
                // Vérifier si le texte de recherche correspond à l'un des champs de l'utilisateur
                String lowerCaseFilter = newValue.toLowerCase();
                
                // Correspondance trouvée dans le champ utilisateur
                
                
                if (Utilisateur.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (Utilisateur.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; // Pas de correspondance trouvée
            });
        });
        // Attacher le FilteredList à la TableView pour afficher les données filtrées
        tabuser.setItems(filteredList);   

        
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
        if (nomaffiche.getText().isEmpty())
        {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre NOM");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        if (prenomaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre PRENOM");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (telaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre TELEPHONE");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (emailaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre EMAIL");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (mdpaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre MOT DE PASSE");
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
        
        if (nomaffiche.getText().isEmpty())
        {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre NOM");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        if (prenomaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre PRENOM");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (telaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre TELEPHONE");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (emailaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre EMAIL");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (mdpaffiche.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre MOT DE PASSE");
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

    @FXML
    private void exit(ActionEvent event) {
        
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
            exit.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
