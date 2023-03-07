/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

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
import tn.esprit.entity.Instrument;
import tn.esprit.service.ServiceInstrument;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class DashboardController implements Initializable {

    @FXML
    private HBox card;
private List<Instrument> Acceuil ;
ServiceInstrument si = new ServiceInstrument (); 
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
}
    

