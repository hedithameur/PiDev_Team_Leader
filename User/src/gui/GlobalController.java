/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mayy
 */
public class GlobalController implements Initializable {

    @FXML
    private Button btnartiste;
    @FXML
    private Button btnmembre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoartiste(ActionEvent event) {
        
    }

    @FXML
    private void gotomembre(ActionEvent event) {
        try {
            
            Parent loader = FXMLLoader.load(getClass().getResource("inscriptionuser.fxml"));
            btnmembre.getScene().setRoot(loader);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
