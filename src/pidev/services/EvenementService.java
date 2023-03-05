/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.activation.DataSource;
import pidev.entity.Evenement;
import pidev.tools.MaConnection;

/**
 *
 * @author 21650
 */
public class EvenementService implements InterfaceEvenement<Evenement> {
 Connection cnx;
 String sql;
 
 public EvenementService(){
     cnx=MaConnection.getInstance().getCnx();
 }
    @Override
    public void ajouter(Evenement t) {
     try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /* String sql ="insert into evenement(nom,lieu,date,nb_ticket,prix)values('"+t.getNom()+ "','" +t.getLieu()+"','" +t.getDate()+"','" +t.getNb_ticket()+"','" +t.getPrix()+ "')";
         Statement ste = cnx.createStatement();
         ste.executeUpdate(sql);*/
         
         String sql ="insert into evenement(nom,lieu,date,nb_ticket,prix) values (?,?,?,?,?)";
         
         PreparedStatement ste =cnx.prepareStatement(sql);
         /*---------------------------- INSERTION NOM-----------------------------------------*/
         if("".equals(t.getNom())){
         System.out.println("Veuillez inserer un nom s'il vous plait ");
         }
         else{
              ste.setString(1,t.getNom());
              }
         /*---------------------------- INSERTION LIEU-----------------------------------------*/
          if("".equals(t.getLieu())){
         System.out.println("Veuillez inserer un lieu s'il vous plait ");
         }
          else
          {
         ste.setString(2,t.getLieu());
          }
          /*---------------------------- INSERTION DATE-----------------------------------------*/
        String str_date_entred=t.getDate().toString();
        /*----------- CONTROL SAISIE---------------------------*/
        int date_entred_year_int = Integer.parseInt(str_date_entred.substring(0,4));
        int date_entred_month_int = Integer.parseInt(str_date_entred.substring(6,7));
        int date_entred_day_int = Integer.parseInt(str_date_entred.substring(9,10));
        
        
        LocalDate datenow = LocalDate.now(); 
        String str_datenow=datenow.toString();
       int datenow_entred_year_int = Integer.parseInt(str_datenow.substring(0,4));
       int datenow_entred_month_int = Integer.parseInt(str_datenow.substring(6,7));
        int datenow_entred_day_int = Integer.parseInt(str_datenow.substring(9,10));
        
        if(date_entred_year_int-datenow_entred_year_int<0){
          System.out.println("Veuillez entrer Date valide SVP! l'annee "+date_entred_year_int+" a ecoulé ");
        }
        else if(date_entred_month_int-datenow_entred_month_int<0){
          System.out.println("Veuillez entrer Date valide SVP! l'annee "+date_entred_year_int+" a ecoulé ");
        }
        /**************************-------------------------------------------------*/
        else{
         ste.setDate(3,t.getDate());
        }
        /*----------------------------- Ticket NB------------------------------------------------------------------------*/
        if(t.getNb_ticket()==0){
         System.out.println("Veuillez inserer un nombre de ticket superieur a 0 s'il vous plait ");
         }
          else
        {
         ste.setInt(4,t.getNb_ticket());
          }
        
        if(t.getPrix()==0.0){
         System.out.println("Veuillez inserer un prix de ticket superieur a 0 s'il vous plait ");
         }
          else
        {
         ste.setDouble(5,t.getPrix());
          }
        
       
        // Statement ste = cnx.createStatement();
         ste.executeUpdate();
          System.out.println("Evenement Ajouté");
         
     } catch (SQLException ex) {
         //Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     System.out.println(ex.getMessage());
     }
    }
public void ajouteraffiche(Evenement t) {
     
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /* String sql ="insert into evenement(nom,lieu,date,nb_ticket,prix)values('"+t.getNom()+ "','" +t.getLieu()+"','" +t.getDate()+"','" +t.getNb_ticket()+"','" +t.getPrix()+ "')";
         Statement ste = cnx.createStatement();
         ste.executeUpdate(sql);*/
          try {
         String sql ="insert into evenement(nom,lieu,date,nb_ticket,prix,affiche) values (?,?,?,?,?,?)";
         
         PreparedStatement ste;
      
             ste = cnx.prepareStatement(sql);
   
         
         ste.setString(1,t.getNom());
          ste.setString(2,t.getLieu());
           ste.setDate(3,t.getDate());
           ste.setInt(4,t.getNb_ticket());
            ste.setDouble(5,t.getPrix());
            ste.setString(6,t.getAffiche());
             ste.executeUpdate();
          System.out.println("Evenement Ajouté // Affiche");
            } catch (SQLException ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         
     }
}
    @Override
    public List<Evenement> affichier() {
     List<Evenement> Events = new ArrayList<>();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from Evenement";
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
   /* public List<Evenement> affichier1() {
     List<Evenement> Events = new ArrayList<>();
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from Evenement";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             Evenement e = new Evenement(res.getString("affiche"));
         Events.add(e);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }
        return Events;
    }*/
    
    

    @Override
    public void supprimer(Evenement t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String sql = "delete from Evenement where nom=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimerParNom(Evenement t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String sql = "delete from Evenement where nom=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierEvenement(String nom,Evenement e) {
        String sql = "update Evenement set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,e.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void modifierEvenement1(String s,String s1,Evenement e) {
        //String sql = "update Evenement set nom=? where id=?";
         String sql = "update Evenement set "+s+"=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,s1);
            ste.setInt(2,e.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public int rowEvent(){
        ObservableList<Evenement> liste = FXCollections.observableArrayList();
        int i=0;
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select * from Evenement";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
               i=i+1;
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
 return i;
        
     }
}
    
    
     

