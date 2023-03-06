/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import pidev.entity.Evenement;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class CardController implements Initializable {

    @FXML
    private VBox vbox_card;
    @FXML
    private ImageView img_card;
    @FXML
    private VBox vbox_2;
    @FXML
    private Label nom_card;
    @FXML
    private Label prix_card;
    @FXML
    private Label date_card;
    @FXML
    private Button btn_commander;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData (Evenement e)
    {
       Image img = new Image(getClass().getResourceAsStream(".."+e.getAffiche()));
       // ../ressources/balti.jpg
    img_card.setImage(img);
    nom_card.setText(e.getNom());
    String priks = String.valueOf(e.getPrix());
    prix_card.setText(priks);
    //Date date1= e.getDate();
    //String datestr = date1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    date_card.setText(e.getLieu());
    
    
    
    }

    @FXML
    private void commander(ActionEvent event) {
           try {
        
            
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/commentaireFXML.fxml"));
            
            // Scene scene = new Scene(loader, 300, 250);
             btn_commander.getScene().setRoot(loader);
           // primaryStage.setTitle("Suivi Evenement");
            //primaryStage.setScene(scene);
            //primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(EventFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
