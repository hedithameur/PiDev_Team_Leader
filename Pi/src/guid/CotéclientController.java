/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.entity.Instrument;
import tn.esprit.service.ServiceCategorie;
import tn.esprit.service.ServiceInstrument;
import tn.esprit.tools.Connexion;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class CotéclientController implements Initializable {

    @FXML
    private TextField nomid;
    @FXML
    private TextField prixid;
    @FXML
    private TextField descriptionid;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Button importid;
    Connection cnx;
    
ServiceInstrument ps = new ServiceInstrument();
    Instrument p = new Instrument();
    ServiceCategorie m = new ServiceCategorie();
    @FXML
    private Text yar;
    /**
     * Initializes the controller class.
     */
    public CotéclientController(){
    cnx = Connexion.getInstance().getCnx();
    
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    try {
            List<Instrument> categorie = ps.newaffciher();
            ObservableList<String> options = FXCollections.observableArrayList(m.getnom());
            combo.setItems(options);
            String selectednom = combo.getValue();

        } catch (SQLException ex) {
            Logger.getLogger(InstrumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }    

    @FXML
    private void ajouterphoto(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fch = new FileChooser();
        
        File f = fch.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:\\Users\\bouzi\\Desktop\\nouveaucopie\\taraji\\src\\Images\\" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Done");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("/Images/" + f.getName());
//            System.out.println(file.toURI().toString());
            //UserImg.setImage(new Image(file.toURI().toString()));
            String Imguser = f.getName();
           System.out.println(Imguser);
            //ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }
        
        
                yar.setText("/Images/" + f.getName());

    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (nomid.getText().isEmpty() || prixid.getText().isEmpty() || descriptionid.getText().isEmpty()  ) {;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("ajouter des champs");

            alert.showAndWait();
        } else {
            String categorieSelectionnee = combo.getValue();
            String SqlGetIdCat = "select id from categorie_instrument where nom_categorie='" + categorieSelectionnee + "'";
            Statement ste;

            try {
                ste = cnx.createStatement();

                ResultSet c = ste.executeQuery(SqlGetIdCat);
                ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();
                while (c.next()) {
                    int IDD_C = c.getInt("id");
                    IDlisteC.add(IDD_C);
                }
                int FINAL_ID_C = IDlisteC.get(0);

                System.out.println(FINAL_ID_C);
                //id de 
                CategorieInstrument cc = new CategorieInstrument();
                cc.setId(FINAL_ID_C);
                System.out.println(FINAL_ID_C);

                p.setCategorie(cc);
                p.setNom(nomid.getText());
                p.setPrix(Float.parseFloat(prixid.getText()));;
                p.setPhoto(yar.getText());
                p.setDescription(descriptionid.getText());

                ps.ajouterphoto(p);
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("msg");
                alert.setHeaderText("succés");

                alert.showAndWait();
                //categorie = FXCollections.observableList(ps.getAllInstruments());
                //tableaucategorie.setItems(categorie);
            } catch (SQLException ex) {
                Logger.getLogger(InstrumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
}
