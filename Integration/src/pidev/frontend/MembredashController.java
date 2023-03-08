
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
import pidev.services.UtilisateurService;
import pidev.tools.MaConnection;


public class MembredashController implements Initializable {

    
    Connection cnx;
   
    
    private TextField ancienemail;
    private TextField nouvemail;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnexit;

    UtilisateurService us = new UtilisateurService();
    Utilisateur u= new Utilisateur();
    @FXML
    private TextField email;
    @FXML
    private PasswordField ancienmdps;
    @FXML
    private PasswordField nouvmdps;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cnx = MaConnection.getInstance().getCnx();
        
        Utilisateur u= new Utilisateur();
        
    }
        

    @FXML
     void modifier(ActionEvent event) {
        
        if (email.getText().isEmpty())
        {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre ancien email");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        
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
        
        else{
           
            
            
            String sql = "UPDATE utilisateur SET mot_de_passe = ? WHERE email = ?";
          
            try {
                PreparedStatement ste = cnx.prepareStatement(sql);
                ste = cnx.prepareStatement(sql);
                //ste.setString(1,email);
                ste.setString(1, nouvmdps.getText());
                ste.setString(2, email.getText());
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
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("dashmembre.fxml"));
           btnexit.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}

