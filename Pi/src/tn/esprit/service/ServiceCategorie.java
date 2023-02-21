/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.tools.Connexion;

/**
 *
 * @author bouzi
 */
public class ServiceCategorie implements Interface<CategorieInstrument>{
 Connection cnx;

    public ServiceCategorie() {
        cnx = Connexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(CategorieInstrument t) {
         try {
            String sql = "INSERT INTO `categorie_instrument`(`id`,`nom`, `description`) VALUES (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.setString(2, t.getNom());
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
    

    @Override
    public void modifier( CategorieInstrument t) {
 
        try {
            PreparedStatement pst = cnx.prepareStatement (" UPDATE categorie_instrument SET nom =? , description =? , where id=? "); 
            pst.setInt(1, t.getId()); 
            pst.setString(2, t.getNom());
             pst.setString(3, t.getDescription());
            pst.executeUpdate();
            System.out.println("categorie modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       }
    

    

    @Override
    public ObservableList afficher() {
       String req = "SELECT * FROM categorie_instrument";
        ObservableList<CategorieInstrument> ca = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                CategorieInstrument ct = new CategorieInstrument();
                ct.setId(rs.getInt("id"));
                ct.setNom(rs.getString("nom"));
                ct.setDescription(rs.getString("description"));
                ca.add(ct);
                System.out.println(ct+"\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ca;
    }

   
    }
 
    

    

    

