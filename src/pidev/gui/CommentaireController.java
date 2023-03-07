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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import pidev.entity.Evenement;
import pidev.entity.commentaire;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label comments;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setCommentsData (commentaire c) throws SQLException
    {
       // System.out.println(e.getAffiche());
      // Image img = new Image(getClass().getResourceAsStream(".."+e.getAffiche()));
       // ../ressources/balti.jpg
        int id =c.getId_utilisateur();
        Connection cnx = MaConnection.getInstance().getCnx();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String sql="select nom,prenom from utilisateur where id="+id;
        Statement ste =cnx.createStatement();
        ResultSet res = ste.executeQuery(sql);
        while(res.next())
        {
            String nom= res.getString("nom");
            String prenom= res.getString("prenom");
            
            
            
            
            // String user = String.valueOf(nom);
            username.setText(prenom+nom);
            comments.setText(c.getTexte());
        }
    
    
    }
    
}
