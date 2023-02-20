
package gui;

import com.sun.webkit.perf.WCFontPerfLogger;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entity.Utilisateur;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import service.UtilisateurService;


public class EmptyyController implements Initializable {

    
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
    @FXML
    private TextField textrole;
    @FXML
    private TextField texttel;
    @FXML
    private TextField textemail;
    @FXML
    private TextField textmotdepasse;
    
     @FXML
    private Label labelnom;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelrole;
     @FXML
    private Label labelemail;
    @FXML
    private Label labeltel;
    @FXML
    private Label labelmot_de_passe;
    
    @FXML
     private Button btnajout;
    
    UtilisateurService us=new UtilisateurService();
    Utilisateur u= new Utilisateur();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void ajouter(ActionEvent event){
        
    u.setNom(textnom.getText());
    u.setNom(textprenom.getText());
    u.setNom(textrole.getText());
    u.setNom(texttel.getText());
    u.setNom(textemail.getText());
    u.setNom(textmotdepasse.getText());
    
        if (textnom.getText().isEmpty()) {
            System.out.println("Nom is empty");
        }
        if (textprenom.getText().isEmpty()) {
            System.out.println("Prenom is empty");
        }
        if (textrole.getText().isEmpty()) {
            System.out.println("role is empty");
        }
        if (texttel.getText().isEmpty()) {
            System.out.println("Teléphone is empty");
        }
        if (textemail.getText().isEmpty()) {
            System.out.println("Email is empty");
        }
        if (textmotdepasse.getText().isEmpty()) {
            System.out.println("Mot_de_passe is empty");
        }
        
        //
        
        else {
            us.ajouter(u);
            System.out.println("Utilisateur ajouter avec succés !");
            reset();
        }
    
    }
    
    @FXML
    private void display(ActionEvent event) {
        
    }
    private void reset(){
    
    nom.setText("");
    prenom.setText("");
    role.setText("");
    telephone.setText("");
    email.setText("");
    mot_de_passe.setText("");
    
    
    }
    
}
