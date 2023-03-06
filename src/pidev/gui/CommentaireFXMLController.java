/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.entity.commentaire;
import pidev.services.EvenementService;
import pidev.services.commentaireService;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class CommentaireFXMLController implements Initializable {

    @FXML
    private ListView<String> listView_id;
    @FXML
    private TextField ecrireComment_id;
    @FXML
    private Button btn_ok_id;
    
    

    Connection cnx;
 String sql;
 commentaireService cs = new commentaireService();
 public CommentaireFXMLController(){
     cnx=MaConnection.getInstance().getCnx();
 }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from commentaire";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             //commentaire c = new commentaire(res.getInt(1),res.getInt(3),res.getString("texte"));
        Integer id = res.getInt("id_utilisateur");
        String idstr=String.valueOf(id);
             String text = res.getString("texte");
             String total = idstr +" || "+text;
        listView_id.getItems().add(total);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }*/
        affiche_comments();
        
      /* 
        try {
           FileInputStream input = new FileInputStream("C:\\Users\\21650\\Downloads\5(6)");
        

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CommentaireFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    

    @FXML
    private void Btn_ok(ActionEvent event) {
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /* String sql ="insert into evenement(nom,lieu,date,nb_ticket,prix)values('"+t.getNom()+ "','" +t.getLieu()+"','" +t.getDate()+"','" +t.getNb_ticket()+"','" +t.getPrix()+ "')";
         Statement ste = cnx.createStatement();
         ste.executeUpdate(sql);*/
         
         String sql ="insert into commentaire(texte,id_utilisateur) values (?,1)";
         
         PreparedStatement ste =cnx.prepareStatement(sql);
         ste.setString(1,ecrireComment_id.getText());
        
      //  System.out.println("ajouteeeee");
        
        // Statement ste = cnx.createStatement();
         ste.executeUpdate();
         
      affiche_comments();
         
     } catch (SQLException ex) {
         //Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println(ex.getMessage());
     }
    }
    
    public void affiche_comments(){
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from commentaire";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             //commentaire c = new commentaire(res.getInt(1),res.getInt(3),res.getString("texte"));
        Integer id = res.getInt("id_utilisateur");
        String idstr=String.valueOf(id);
             String text = res.getString("texte");
             String total = idstr +" || "+text;
        listView_id.getItems().add(total);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void affiche_last(){
        Integer id_new=0;
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        // sql="select * from commentaire";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             //commentaire c = new commentaire(res.getInt(1),res.getInt(3),res.getString("texte"));
        id_new = res.getInt("id_utilisateur");
        
         }
           sql="select * from commentaire where id="+id_new;
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
}
