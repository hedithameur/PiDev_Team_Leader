/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entity.commentaire;
import pidev.tools.MaConnection;

/**
 *
 * @author 21650
 */
public class commentaireService {
    Connection cnx;
 String sql;
 
 public commentaireService(){
     cnx=MaConnection.getInstance().getCnx();
 }
    
    public void ajouterCommentaire(commentaire c) {
     try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /* String sql ="insert into evenement(nom,lieu,date,nb_ticket,prix)values('"+t.getNom()+ "','" +t.getLieu()+"','" +t.getDate()+"','" +t.getNb_ticket()+"','" +t.getPrix()+ "')";
         Statement ste = cnx.createStatement();
         ste.executeUpdate(sql);*/
         
         String sql ="insert into commentaire(texte,id_utilisateur) values (?,?)";
         
         PreparedStatement ste =cnx.prepareStatement(sql);
         ste.setString(1,c.getTexte());
         ste.setInt(2,c.getId_utilisateur());
        
        
        // Statement ste = cnx.createStatement();
         ste.executeUpdate();
         
         
     } catch (SQLException ex) {
         //Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println(ex.getMessage());
     }
    }

    
    public List<commentaire> affichierCommentaire() {
     List<commentaire> comments = new ArrayList<>();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from commentaire";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             commentaire c = new commentaire(res.getInt(1),res.getInt(3),res.getString("texte"));
         comments.add(c);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
        return comments;
    }

    
    public void supprimerCommentaire(commentaire c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String sql = "delete from commentaire where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void modifierCommentaire(String texte,commentaire e) {
        String sql = "update commentaire set texte=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, texte);
            ste.setInt(2,e.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}


