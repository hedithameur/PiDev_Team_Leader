
package pidev.frontend;

import java.io.IOException;
import pidev.entity.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pidev.services.UtilisateurService;
import pidev.tools.MaConnection;


public class MembredashController implements Initializable {

    
    Connection cnx;
   private int userId;
    
    private TextField ancienemail;
    private TextField nouvemail;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnexit;

    UtilisateurService us = new UtilisateurService();
    Utilisateur u= new Utilisateur();
    private TextField email;
    @FXML
    private PasswordField ancienmdps;
    @FXML
    private PasswordField nouvmdps;
    
    @FXML
    private PasswordField nouveaumotdepasse;
   
    @FXML
    private AnchorPane nouveau;
   
   
     public void setUserId(int userId) {
        this.userId = userId;
       System.out.println("UserID "+ userId);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cnx = MaConnection.getInstance().getCnx();
        
        Utilisateur u= new Utilisateur();

    }
          public static boolean validerMotDePasse(String motDePasse) {
        // Vérifier la longueur du mot de passe
        if (motDePasse.length() < 8) {
            return false;
        }

        // Vérifier s'il y a au moins un chiffre dans le mot de passe
        boolean contientChiffre = false;
        for (char c : motDePasse.toCharArray()) {
            if (Character.isDigit(c)) {
                contientChiffre = true;
                break;
            }
        }

        return contientChiffre;
    }

    @FXML
     void modifier(ActionEvent event) {
        cnx = MaConnection.getInstance().getCnx();
              System.out.println("mot_de_passe" +  us.get_mot_de_passe(userId));
        
        if (ancienmdps.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre ancien mot de passe");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (nouvmdps.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre nouveau mot de passe");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
            if (nouveaumotdepasse.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez confirmer votre nouveau mot de passe");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
         if( !us.get_mot_de_passe(userId).equals(ancienmdps.getText()))
         {
                 Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Verifier Votre ancien mot de passe ");
                alert.showAndWait();
         }
            
      if (!validerMotDePasse(nouvmdps.getText())) {
                // Afficher une alerte si le mot de passe n'est pas valide
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Le mot de passe doit contenir au moins 8 caractères et au moins un chiffre.");
                alert.showAndWait();
            }
            else if(us.get_mot_de_passe(userId).equals(ancienmdps.getText()) && nouveaumotdepasse.getText().equals(nouvmdps.getText()) ){
           
            
            
            String sql = "UPDATE utilisateur SET mot_de_passe = ? WHERE id = ?";
          
            try {
                PreparedStatement ste = cnx.prepareStatement(sql);
                ste = cnx.prepareStatement(sql);
                //ste.setString(1,email);
                ste.setString(1, nouvmdps.getText());
                ste.setInt(2, userId);
                ste.executeUpdate();
            } 
            catch (SQLException ex) {
                Logger.getLogger(MembredashController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
               
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("OK");
            alert.setHeaderText("Modification effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
           
        
    }

    @FXML
    private void exit(ActionEvent event) {
                  try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("dashmembre.fxml"));
    Parent root = loader.load();
    
    DashmembreController membreController = loader.getController();
    membreController.setUserId(userId);
            btnexit.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}

