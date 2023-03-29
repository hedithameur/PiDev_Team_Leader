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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class ArtisteloginController implements Initializable {
    Connection cnx;

    @FXML
    private Hyperlink linkpwd;
    @FXML
    private Button btnseconnecter;
    @FXML
    private TextField emaila;
    @FXML
    private PasswordField mdpsa;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void connecter(ActionEvent event) {
        
        if (emaila.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre EMAIL");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (mdpsa.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre MOT DE PASSE");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        else {
        
        cnx = MaConnection.getInstance().getCnx(); 
        String mail = emaila.getText();
        String password = mdpsa.getText();
         try {
            PreparedStatement statement = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
            statement.setString(1, mail);
            statement.setString(2, password);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                  int  userId = resultSet.getInt("id");
                if (role.equals("Artiste")) {
                   
                    try {
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("artistedash.fxml"));
    Parent root = loader.load();
    
    ArtistedashController artisteController = loader.getController();
    artisteController.setUserId(userId);
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
    private void retour(ActionEvent event) {
        
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("inscriptionartiste.fxml"));
            retour.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
