/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class AdminloginController implements Initializable {
    
    Connection cnx;

    @FXML
    private Button login;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    }    

    @FXML
    private void loginadmin(ActionEvent event) {
        
            
        if (email.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre EMAIL");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        if (mdps.getText().isEmpty())
              
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("veuillez saisir votre MOT DE PASSE");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            
        }
        else {
        
        cnx = MaConnection.getInstance().getCnx(); 
        String mail = email.getText();
        String password = mdps.getText();
         try {
            PreparedStatement statement = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?");
            statement.setString(1, mail);
            statement.setString(2, password);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                if (role.equals("admin")) {
                   /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("ok");
                    alert.setHeaderText(null);
                    alert.setContentText("Connexion réussie");
                    alert.showAndWait();*/
                    try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("affichemodifsupp.fxml"));
            login.getScene().setRoot(loader);
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
}}
