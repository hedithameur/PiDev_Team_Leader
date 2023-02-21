/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

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
import pidev.entity.commentaire;
import pidev.services.EvenementService;
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
 
 public CommentaireFXMLController(){
     cnx=MaConnection.getInstance().getCnx();
 }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from commentaire";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             //commentaire c = new commentaire(res.getInt(1),res.getInt(3),res.getString("texte"));
        String text = res.getString("texte");
        listView_id.getItems().add(text);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
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
        
        System.out.println("ajouteeeee");
        
        // Statement ste = cnx.createStatement();
         ste.executeUpdate();
         
         
     } catch (SQLException ex) {
         //Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println(ex.getMessage());
     }
    }
    
}
