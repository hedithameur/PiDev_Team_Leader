/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pidev.entity.Evenement;
import pidev.entity.commentaire;
import pidev.services.EvenementService;
import pidev.services.commentaireService;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class SingleEventModelController implements Initializable {

    @FXML
    private Label titre_single;
    @FXML
    private ImageView img_single;
     Connection cnx;
EvenementService es = new EvenementService();
commentaireService cs = new commentaireService();
Evenement e = new Evenement();
    @FXML
    private ScrollPane pane_comments;
    @FXML
    private VBox box_comments;
    private List<commentaire> com;
    @FXML
    private Label date_affiche;
    @FXML
    private Label loc_affiche;
    @FXML
    private Label type_billet;
    @FXML
    private Label prix_billet;
    @FXML
    private Button btn_ajoute_panier;
    private Button back;
    @FXML
    private Button btn_instru;
    @FXML
    private Button btn_panier;
    @FXML
    private Label acceuil_btn;
    @FXML
    private Label contact_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int i = e.getId();
        
        com = new ArrayList<>(Comments());
        System.out.println("ok ok");
        try{
            for (int j= 0;j<com.size();j++)
            {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("commentaire.fxml"));
                 System.out.println("test1");
                VBox cardbox = fxmlloader.load();
                 System.out.println("test2");
                CommentaireController CC =fxmlloader.getController();
                 System.out.println("test3");
                CC.setCommentsData(com.get(j));
                System.out.println("test4");
                box_comments.getChildren().add(cardbox);
                System.out.println("testFinale");
                
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(SingleEventModelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SingleEventModelController.class.getName()).log(Level.SEVERE, null, ex);
        }
      // System.out.print("aa"+v);
        
    }    
     private List<commentaire> Comments(){
         List<commentaire> c = cs.affichierCommentaire();
        
         System.out.println(c);
         return c;
        
    }
    
    /* private List<Evenement> AcceuilEvents(){
       List<Evenement> Events = new ArrayList<>();
          cnx=MaConnection.getInstance().getCnx();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
          String sql="select * from Evenement where id=";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             Evenement e = new Evenement(res.getInt("id"),res.getString("nom"),res.getString("lieu"),res.getString("date"),res.getInt("nb_ticket"),res.getDouble("prix"),res.getString("affiche"));
         System.out.println(res.getString("affiche"));
             Events.add(e);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
        return Events;
    }*/
     public void Var(int v) {
      
      
      List<Evenement> Events = new ArrayList<>();
          cnx=MaConnection.getInstance().getCnx();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
          String sql="select * from Evenement where id="+v;
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         
         
         while(res.next())
         {
            String titre= res.getString("nom");
            String url =res.getString("affiche");
            double prix =res.getDouble("prix");
            String lieu =res.getString("lieu");
            Date d=res.getDate("date");
            
            
            titre_single.setText(titre);
            Image img = new Image(getClass().getResourceAsStream(".."+url));
       // ../ressources/balti.jpg
    img_single.setImage(img);
    String pb=String.valueOf(prix);
    prix_billet.setText(pb);
    type_billet.setText(titre+" ticket normal");
    loc_affiche.setText(lieu);
    
    String pattern = "dd/MM/yyyy";

DateFormat df = new SimpleDateFormat(pattern);

String dateString = df.format(d);
date_affiche.setText(dateString);
    
            /* Evenement e = new Evenement(res.getInt("id"),res.getString("nom"),res.getString("lieu"),res.getString("date"),res.getInt("nb_ticket"),res.getDouble("prix"),res.getString("affiche"));
         System.out.println(res.getString("affiche"));*/
             //Events.add(e);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
       // return Events;
    
  }

    @FXML
    private void ajoute_panier(ActionEvent event) {
    }

    private void back(ActionEvent event) {
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void acceuil(MouseEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            acceuil_btn.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void contact(MouseEvent event) {
    }

    @FXML
    private void instrument(ActionEvent event) {
    }

    @FXML
    private void panier(ActionEvent event) {
    }
        
}
    

