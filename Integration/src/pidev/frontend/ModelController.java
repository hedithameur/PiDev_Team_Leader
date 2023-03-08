/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidev.entity.Commande_instruments;
import pidev.entity.Instrument;
import pidev.services.ServiceCommande;
import pidev.test.MusicINterface;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class ModelController implements Initializable {

    @FXML
    private ImageView imgid;
    @FXML
    private Label nomid;
    @FXML
    private Label prixid;
    @FXML
    private Button comm;
   int  id_single ;
   private Stage stage;
	private Scene scene;
	private Parent root;
    /**
     * Initializes the controller class.
     */
    Commande_instruments cs = new Commande_instruments ();
    ServiceCommande sc = new ServiceCommande ();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData (Instrument i)
    { Image img = new Image(getClass().getResourceAsStream(".."+i.getPhoto()));
    
    nomid.setText(i.getNom());
    String priks = String.valueOf(i.getPrix());
     prixid.setText(priks);
    imgid.setImage(img);
    id_single=i.getId_instrument();

    }

    @FXML
    private void commande(ActionEvent event) {
//       cs.setNom(nomid.getText());
//         float prix = Float.parseFloat(prixid.getText());
//       cs.setPrix(prix);
//         sc.ajouter(cs);
        
    }

    @FXML
    private void commander(MouseEvent event) {
        try {
         //  String username = nameTextField.getText();
		int a=id_single;
                
                
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Annonce.fxml"));	
		root = loader.load();	
		
		AnnonceController Controller = loader.getController();
		Controller.Var(a);
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		
	
        } catch (IOException ex) {
            Logger.getLogger(MusicINterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
