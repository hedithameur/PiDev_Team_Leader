/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidev.entity.Evenement;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class ModelEventController implements Initializable {

    @FXML
    private ImageView img_card;
    @FXML
    private Label nom_card;
    @FXML
    private Label lieu_card;
    @FXML
    private Label date_card;
    @FXML
    private Label nb_card;
    @FXML
    private Label prix_card;

	private Stage stage;
	private Scene scene;
	private Parent root;
	int id_single;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
  
     public void setData (Evenement e)
    {
       // System.out.println(e.getAffiche());
       Image img = new Image(getClass().getResourceAsStream(".."+e.getAffiche()));
       // ../ressources/balti.jpg
    img_card.setImage(img);
    nom_card.setText(e.getNom());
    String priks = String.valueOf(e.getPrix()) + "DINARS";
    prix_card.setText(priks);
    Date date1= e.getDate();
    String pattern = "dd/MM/yyyy";

DateFormat df = new SimpleDateFormat(pattern);

String dateString = df.format(date1);
 
    lieu_card.setText(e.getLieu());
    String nb=String.valueOf(e.getNb_ticket());
    nb_card.setText(nb+" places disponibles");
    date_card.setText(dateString);
     id_single=e.getId();
    
    
    }
    
    
   

    @FXML
    private void commander(MouseEvent event) throws IOException {
         try {
         //  String username = nameTextField.getText();
		int a=id_single;
                
                
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/SingleEventModel.fxml"));	
		root = loader.load();	
		
		SingleEventModelController Controller = loader.getController();
		Controller.Var(a);
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		
	
        } catch (IOException ex) {
            Logger.getLogger(EventFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    }
    

