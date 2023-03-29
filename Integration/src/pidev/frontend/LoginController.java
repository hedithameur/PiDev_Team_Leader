/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sun.security.rsa.RSACore;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class LoginController implements Initializable {

    @FXML
    private Label labelemail2;
    @FXML
    private Label labelmotdepasse2;
    @FXML
    private TextField textemail2;
    @FXML
    private PasswordField textmotdepasse2;
    @FXML
    private Button btnseconnecter;
    @FXML
    private AnchorPane checkartiste;
    @FXML
    private Hyperlink linkpwd;

   
    Connection cnx ;
    @FXML
    private Button retourdash;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       cnx = MaConnection.getInstance().getCnx();  
    }    

    @FXML
    private void gotopwd(ActionEvent event) {
        
        
         try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("password.fxml"));
            linkpwd.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void login(ActionEvent event) {
        
        String email = textemail2.getText();
        String pwd = textmotdepasse2.getText();
        
        if (textemail2.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText(" veuillez saisir votre EMAIL ");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
         if (textmotdepasse2.getText().isEmpty())
              
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText(" veuillez saisir votre MOT DE PASSE ");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
          
        else {
        
        cnx = MaConnection.getInstance().getCnx(); 
        
         try {
            PreparedStatement statement = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
            statement.setString(1, email);
            statement.setString(2, pwd);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                   int  userId = resultSet.getInt("id");
                if (role.equals("Membre")) {
                   /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("ok");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion réussie");
                    alert.showAndWait();*/
                    try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("dashmembre.fxml"));
    Parent root = loader.load();
    
    DashmembreController membreController = loader.getController();
    membreController.setUserId(userId);
            btnseconnecter.getScene().setRoot(root);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                    // Rediriger l'utilisateur vers la page d'accueil de l'application
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de connexion");
                    alert.setHeaderText(null);
                    alert.setContentText("Vous n'avez pas les autorisations nécessaires pour accéder à cette application.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Email ou mot de passe incorrect.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    }

    @FXML
    private void retourmembre(ActionEvent event) {
             try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("inscriptionuser.fxml"));
            retourdash.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
   

  
    

