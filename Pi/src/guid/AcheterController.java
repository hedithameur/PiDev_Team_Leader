/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.esprit.entity.Commande_instruments;
import tn.esprit.entity.Instrument;
import tn.esprit.service.ServiceCommande;
import tn.esprit.service.ServiceInstrument;
import tn.esprit.tools.Connexion;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class AcheterController implements Initializable {

    @FXML
    private Label nom_acheteur;
    @FXML
    private Label article_nom;
    @FXML
    private Label prix_article;
    @FXML
    private Button commander;
     Commande_instruments ci = new  Commande_instruments ();
     ServiceCommande i = new ServiceCommande (); 
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void commander(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
        ci.setNom(article_nom.getText());
        ci.setPrix(Float.parseFloat(prix_article.getText()));
        i.ajouter(ci);
        //Commande_instruments c = new Commande_instruments();
        
        Document document = new Document();
        if (article_nom.getText().isEmpty()||prix_article.getText().isEmpty() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Impression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Commande_instruments.pdf"));
            document.open();
            com.itextpdf.text.Font font = new com.itextpdf.text.Font();
            font.setSize(20);
            font.setColor(BaseColor.GREEN);
            font.isBold();
            font.isItalic();

            com.itextpdf.text.Font fontSmall = new com.itextpdf.text.Font();
            fontSmall.setSize(8);
            document.add(new LineSeparator());

            document.add(new Paragraph("Commande", font));
            document.add(new Paragraph(" "));

            document.add(new LineSeparator());

            //  document.add(new Paragraph("id_commande : " + c.getId_commande()));
            document.add(new Paragraph("nom_produit : " +  article_nom.getText()));
            document.add(new Paragraph("Prix : " + prix_article.getText()));
            
            document.add(new Paragraph(" "));

            document.newPage();
            document.close();

            writer.close();

            Desktop.getDesktop().open(new File("Commande_instruments.pdf"));
        } /*catch (DocumentException | IOException e) {
            e.printStackTrace();
        }*/
     
        
        
        
        
    }
    public void acheter(int id){
        List<Instrument> Events = new ArrayList<>();
        Connection cnx = Connexion.getInstance().getCnx();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
          String sql="select * from instruments where id_instrument="+id;
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         
         
         while(res.next())
         {
            String titre= res.getString("nom");
            
            double prix =res.getDouble("prix");
            
            
            
            
            article_nom.setText(titre);
      //      Image img = new Image(getClass().getResourceAsStream(".."+url));
       // ../ressources/balti.jpg
    //img_single.setImage(img);
   String pb=String.valueOf(prix);
    prix_article.setText(pb);
   // type_billet.setText(titre+" ticket normal");
   // loc_affiche.setText(lieu);
    
   /* String pattern = "dd/MM/yyyy";

DateFormat df = new SimpleDateFormat(pattern);

String dateString = df.format(d);
date_affiche.setText(dateString);
    
            /* Evenement e = new Evenement(res.getInt("id"),res.getString("nom"),res.getString("lieu"),res.getString("date"),res.getInt("nb_ticket"),res.getDouble("prix"),res.getString("affiche"));
         System.out.println(res.getString("affiche"));*/
             //Events.add(e);*/
         }
     } catch (SQLException ex) {
         Logger.getLogger(ServiceInstrument.class.getName()).log(Level.SEVERE, null, ex);
     }
       // return Events;
    }
    
    
    
}
