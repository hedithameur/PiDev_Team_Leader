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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.entity.Commande_instruments;
import tn.esprit.entity.Instrument;
import tn.esprit.entity.Utilisateur;
import tn.esprit.tools.Connexion;

/**
 *
 * @author bouzi
 */
public class ServiceInstrument implements Interface< Instrument> {

    Connection cnx;

    public ServiceInstrument() {
        cnx = Connexion.getInstance().getCnx();
    }
    




   

   
   

    
    @Override
    public void ajouter(Instrument t) {
        try {
            String sql = "INSERT INTO `instruments`(`id_instrument`,`nom`, `prix`, `description`, `id_vendeur`, `id_categorie`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_instrument());
            ste.setString(2, t.getNom());
            ste.setFloat(3, t.getPrix());
            ste.setString(4, t.getDescription());
            ste.setInt(5, t.getId_vendeur());
            ste.setInt(6, t.getId_categorie());
            ste.executeUpdate();
            System.out.println("Intrument ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Instrument t) {
   String sql = "delete from instruments where id_instrument=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_instrument());
            ste.executeUpdate();
            System.out.println("categorie supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public void modifier(String nom, Instrument t) {
        try {
            String requete4 = " UPDATE instruments SET " + "  nom= ? WHERE id_instrument= " + t.getId_instrument();
            PreparedStatement pst = Connexion.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1, nom);

            pst.executeUpdate();
            System.out.println("instrument modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList afficher() {
         String req = "SELECT * FROM instruments";
        ObservableList<Instrument> ca = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Instrument ct = new Instrument();
                ct.setId_instrument(rs.getInt("id_instrument"));
                ct.setNom(rs.getString("nom"));
                ct.setPrix(rs.getFloat("prix"));
                ct.setDescription(rs.getString("description"));
                ct.setId_vendeur(rs.getInt("id_vendeur"));
                ct.setId_categorie(rs.getInt("id_vendeur"));
                ca.add(ct);
                System.out.println(ct+"\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ca;
    }



  

   
}
