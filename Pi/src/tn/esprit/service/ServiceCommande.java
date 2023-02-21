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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.entity.Commande_instruments;
import tn.esprit.tools.Connexion;

/**
 *
 * @author bouzi
 */
public class ServiceCommande implements Interface<Commande_instruments>{
    Connection cnx;

    public ServiceCommande() {
        cnx = Connexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commande_instruments t) {
       try {
            String sql = "INSERT INTO `commande_instruments`(`id_commande`, `id_vendeur`,`id_instru`,`date_commande`) VALUES (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_commande());
          
         
        
      
            ste.setInt(2, t.getId_vendeur());
            ste.setInt(3,t.getId_instru() );
            ste.setDate(4, java.sql.Date.valueOf(t.getDate_commande()));
            ste.executeUpdate();
            System.out.println("Categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Commande_instruments t) {
      String sql = "delete from commande_instruments where id_commande=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_commande());
            ste.executeUpdate();
            System.out.println("commande supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(String nom, Commande_instruments t) {
       
    }

    @Override
    public ObservableList afficher() {
       String req = "SELECT * FROM commande_instruments";
        
        ObservableList<Commande_instruments> ca = FXCollections.observableArrayList();
        Statement stm;
        try {
            stm = this.cnx.createStatement();
            ResultSet rs = stm.executeQuery(req);
            while (rs.next()) {
                Commande_instruments ct = new Commande_instruments();
                ct.setId_commande(rs.getInt("id_commande"));
              
                ct.setId_vendeur(rs.getInt("id_vendeur"));
                 ct.setId_instru(rs.getInt("id_instru"));
               ct.setDate_commande(rs.getDate("date").toLocalDate());  
                ca.add(ct);
                System.out.println(ct+"\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ca;
    }
   
    }
    

