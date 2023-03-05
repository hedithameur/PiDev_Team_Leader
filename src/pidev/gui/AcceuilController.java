/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.entity.Evenement;
import pidev.services.EvenementService;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class AcceuilController implements Initializable {

    @FXML
    private HBox cardLayout;
    private List<Evenement> Acceuil;
EvenementService es =new EvenementService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Acceuil = new ArrayList<>(AcceuilEvents());
        System.out.println("ok ok");
        try{
            for (int i= 0;i<Acceuil.size();i++)
            {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("card.fxml"));
                 System.out.println("test1");
                VBox cardbox = fxmlloader.load();
                 System.out.println("test2");
                CardController CC =fxmlloader.getController();
                 System.out.println("test3");
                CC.setData(Acceuil.get(i));
                System.out.println("test4");
                cardLayout.getChildren().add(cardbox);
                System.out.println("testFinale");
                
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    private List<Evenement> AcceuilEvents(){
         List<Evenement> events = es.affichier();
        
         System.out.println(events);
         return events;
        
    }
    
}
