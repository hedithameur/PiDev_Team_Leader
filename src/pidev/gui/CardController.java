/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    img_card.setImage(img);
    nom_card.setText(e.getNom());
    String priks = String.valueOf(e.getPrix());
    prix_card.setText(priks);
    //Date date1= e.getDate();
    //String datestr = date1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    date_card.setText(e.getLieu());
    
    
    
    }
    
}
