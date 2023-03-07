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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pidev.entity.CategorieInstrument;
import pidev.entity.Instrument;
import pidev.tools.MaConnection;

/**
 *
 * @author bouzi
 */
public class ServiceCategorie implements Interface<CategorieInstrument>{
 Connection cnx;

    public ServiceCategorie() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(CategorieInstrument t) {
         try {
            String sql = "INSERT INTO `categorie_instrument`(`id`,`nom_categorie`, `description`) VALUES (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.setString(2, t.getNom_categorie());
            ste.setString(3, t.getDescription());
            ste.executeUpdate();
            System.out.println("Categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(CategorieInstrument t) {
        String sql = "delete from categorie_instrument where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("categorie supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
     public List<String> getnom() throws SQLException {
    List<String> ct = new ArrayList<>();
    String sql = "SELECT nom_categorie  FROM categorie_instrument";
    PreparedStatement statement = cnx.prepareStatement(sql);
    ResultSet rs = statement.executeQuery();
    while (rs.next()) {
        ct.add(rs.getString("nom_categorie"));
        
                }
    return ct;
}


                
           

            


    
    

    
    
 @Override
 public void modifier(String nom_categorie,String description,CategorieInstrument t) {
        String sql = "update categorie_instrument set nom_categorie=? , description=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom_categorie);
            ste.setString(2, description);
            
            
            ste.setInt(3,t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 }
    

    

    @Override
    public List<CategorieInstrument> getAllInstruments() {
         List<CategorieInstrument> ct = new ArrayList<>();
        try {
            String sql = "select * from Categorie_instrument";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                CategorieInstrument c = new CategorieInstrument(s.getInt("id"), 
                        s.getString("nom_categorie"), s.getString("description"));
                ct.add(c);
           

            }
                 System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct ;
    }
     public CategorieInstrument findCatById (int id) { 
        String sql = "select * from   categorie_instrument  where id = "+id;
        CategorieInstrument c = new CategorieInstrument();
        try{
          PreparedStatement ste = cnx.prepareStatement(sql);
          //ste.setInt(1,id);
           ResultSet Res = ste.executeQuery(sql);
           if (Res.next()){
             c.setId(Res.getInt("id"));
             c.setNom_categorie(Res.getString("nom_categorie"));
             c.setDescription(Res.getString("description"));
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
             return c;
     }
}
           

       
    

  
   
    
 
    

    

    

