/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.frontend;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.util.resources.LocaleData;
import pidev.entity.Commande_instruments;
import pidev.entity.Instrument;
import pidev.services.ServiceCommande;
import pidev.services.ServiceInstrument;
import pidev.test.MusicINterface;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class AnnonceController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private ImageView imgid;
    Connection cnx ;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    Commande_instruments cs = new Commande_instruments ();
    ServiceCommande sc = new ServiceCommande ();
    private TableView<Commande_instruments> tableauannonce;
    private TableColumn<Commande_instruments, LocaleData> datecolumn;
    @FXML
    private Text text;
    @FXML
    private Button comid;
private Stage stage;
	private Scene scene;
	private Parent root;
        public int id_instru; 
    @FXML
    private Button annulerid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }    
//    private List<Instrument> AcceuilEvents(){
//       List<Instrument> Events = new ArrayList<>();
//          cnx=Connexion.getInstance().getCnx();
//        try {
//         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    
//          String sql="select * from Instruments where id=";
//         Statement ste =cnx.createStatement();
//         ResultSet res = ste.executeQuery(sql);
//         
//         while(res.next())
//         {
//            
//             Instrument i = new Evenement(res.getInt("id"),res.getString("nom"),res.getString("lieu"),res.getString("date"),res.getInt("nb_ticket"),res.getDouble("prix"),res.getString("affiche"));
//         System.out.println(res.getString("affiche"));
//             Events.add(e);
//         }
//     } catch (SQLException ex) {
//         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
//     }
//        return Events;
//    }
     public void Var(int v) {
      
      
      List<Instrument> Events = new ArrayList<>();
          cnx=MaConnection.getInstance().getCnx();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
          String sql="select * from instruments where id_instrument="+v;
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         
         
         while(res.next())
         {
            String titre= res.getString("nom");
            String url =res.getString("photo");
              float tit  = res.getFloat("prix");
              String desc = res.getString("description");
            nom.setText(titre);
            Image img = new Image(getClass().getResourceAsStream(".."+url));
       // ../ressources/balti.jpg
    imgid.setImage(img);
   prix.setText(Float.toString(tit));
   description.setText(desc);
            /* Evenement e = new Evenement(res.getInt("id"),res.getString("nom"),res.getString("lieu"),res.getString("date"),res.getInt("nb_ticket"),res.getDouble("prix"),res.getString("affiche"));
         System.out.println(res.getString("affiche"));*/
             //Events.add(e);
         }
     } catch (SQLException ex) {
         Logger.getLogger(ServiceInstrument.class.getName()).log(Level.SEVERE, null, ex);
     }
        id_instru=v;
       // return Events;
    
  }



//    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
//       Commande_instruments c = new Commande_instruments();
//        c = tableauannonce.getSelectionModel().getSelectedItem();
//        Document document = new Document();
//        if (nom.getText().isEmpty()||prix.getText().isEmpty() ){
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("NOT OK");
//            alert.setHeaderText("Impression non effectue");
//            alert.setContentText("Click Cancel to exit.");
//            alert.showAndWait();
//        }else{
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Commande_instrument.pdf"));
//            document.open();
//            com.itextpdf.text.Font font = new com.itextpdf.text.Font();
//            font.setSize(20);
//            font.setColor(BaseColor.GREEN);
//            font.isBold();
//            font.isItalic();
//
//            com.itextpdf.text.Font fontSmall = new com.itextpdf.text.Font();
//            fontSmall.setSize(8);
//            document.add(new LineSeparator());
//
//            document.add(new Paragraph("Commande", font));
//            document.add(new Paragraph(" "));
//
//            document.add(new LineSeparator());
//
//            //  document.add(new Paragraph("id_commande : " + c.getId_commande()));
//            document.add(new Paragraph("nom_produit : " + c.getNom()));
//            document.add(new Paragraph("Prix : " + c.getPrix()));
//            
//            document.add(new Paragraph(" "));
//
//            document.newPage();
//            document.close();
//
//            writer.close();
//
//            Desktop.getDesktop().open(new File("Commande_instrumente.pdf"));
//        } /*catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        }*/
//     
//    }


    @FXML
    private void commander(ActionEvent event) throws SQLException {
           try {
         //  String username = nameTextField.getText();
		//int a=id_single;
                
                int a ;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("acheter.fxml"));	
		root = loader.load();	
		
		AcheterController Controller = loader.getController();
		Controller.acheter(id_instru);
                Controller.suppArticleAcheter(id_instru);
		
		//root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		
	
        } catch (IOException ex) {
            Logger.getLogger(MusicINterface.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @FXML
    private void annuler(ActionEvent event) {
           try {
            Parent loader;
            loader = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            annulerid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }

    }
}

