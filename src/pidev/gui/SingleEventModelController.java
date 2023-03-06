/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pidev.entity.Evenement;
import pidev.services.EvenementService;
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
Evenement e = new Evenement();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int i = e.getId();
      // System.out.print("aa"+v);
        
    }    
    
     private List<Evenement> AcceuilEvents(){
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
    }
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
            titre_single.setText(titre);
            Image img = new Image(getClass().getResourceAsStream(".."+url));
       // ../ressources/balti.jpg
    img_single.setImage(img);
            /* Evenement e = new Evenement(res.getInt("id"),res.getString("nom"),res.getString("lieu"),res.getString("date"),res.getInt("nb_ticket"),res.getDouble("prix"),res.getString("affiche"));
         System.out.println(res.getString("affiche"));*/
             //Events.add(e);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
       // return Events;
    
  }
        
}
    

