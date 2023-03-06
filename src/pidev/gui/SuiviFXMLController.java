/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import pidev.entity.Evenement;
import pidev.entity.commentaire;
import pidev.services.EvenementService;
import pidev.services.commentaireService;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class SuiviFXMLController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button back;
 commentaireService es = new commentaireService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     try {
            List<commentaire> Events = es.affichierCommentaire();
            int row = 0;
            int column = 0;
            for (int i = 0; i <3; i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenementType.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                EvenementTypeController controller = loader.getController();
                controller.setEvent(Events.get(i));

                grid.add(pane, column, row);
                row++;
              /*  if (column > 1) {
                    column = 0;
                    row++;
                }*/
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
        

    @FXML
    private void backfunc(ActionEvent event) {
    }
    
    
    
}
