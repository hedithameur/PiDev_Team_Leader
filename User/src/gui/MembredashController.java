
package gui;

import entity.Utilisateur;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.UtilisateurService;
import tools.MaConnection;


public class MembredashController implements Initializable {

    
    Connection cnx;
   
    
    @FXML
    private TextField ancienemail;
    @FXML
    private TextField nouvemail;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnexit;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cnx = MaConnection.getInstance().getCnx();
        
        Utilisateur u= new Utilisateur();
        
    }
        

    @FXML
     void modifier(ActionEvent event) {
        
        if (ancienemail.getText().isEmpty())
        {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre ancien email");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        
        if (nouvemail.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre nouvel email");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        
        else{
           
            u.setEmail(ancienemail.getText());
            
            String sql = "update utilisateur where email=?";
          
            try {
                PreparedStatement ste = cnx.prepareStatement(sql);
                ste = cnx.prepareStatement(sql);
                //ste.setString(1,email);
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

    
    
}}

