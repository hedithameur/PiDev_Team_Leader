/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.entity.Instrument;
import pidev.services.ServiceInstrument;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class DashboardController implements Initializable {
private int userId;
    @FXML
    private HBox card;
private List<Instrument> Acceuil ;
ServiceInstrument si = new ServiceInstrument (); 
    @FXML
    private Button ajoutiid;
    @FXML
    private Button retourdash;
    /**
     * Initializes the controller class.
     * @param userId
     */
    
      public void setUserId(int userId) {
        this.userId = userId;
       System.out.println("UserID "+ userId);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        Acceuil = new ArrayList<>(AcceuilEvents());
        System.out.println("ok ok");
        try{
            for (int i= 0;i<Acceuil.size();i++)
            {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Model.fxml"));
                 System.out.println("test1");
                 VBox cardbox = fxmlloader.load();
                  System.out.println("test2");
                ModelController CC =fxmlloader.getController();
                 System.out.println("test3");
                CC.setData(Acceuil.get(i));
                System.out.println("test4");
                card.getChildren().add(cardbox);
                System.out.println("testFinale");
                 }
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
 private List<Instrument> AcceuilEvents(){
         List<Instrument> events = si.getAllInstruments();
        
         System.out.println(events);
         return events;    
}

    @FXML
    private void ajouter(ActionEvent event) {
                      try {
                     
            //navigation
              FXMLLoader loader = new FXMLLoader(getClass().getResource("Cotéclient.fxml"));
    Parent root = loader.load();
    
 CotéclientController clientController = loader.getController();
    clientController.setUserId(userId);
    
    ajoutiid.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}

    @FXML
    private void retourdash(ActionEvent event) {
                         try {
                     
            //navigation
              FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
    Parent root = loader.load();
    
 AcceuilController acceuilController = loader.getController();
    acceuilController.setUserId(userId);
    
    retourdash.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}
    
}
