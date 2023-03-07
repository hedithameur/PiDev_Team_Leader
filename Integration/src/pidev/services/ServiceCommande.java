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
import pidev.entity.Commande_instruments;
import pidev.tools.MaConnection;

/**
 *
 * @author bouzi
 */
public class ServiceCommande implements Interface<Commande_instruments> {

    Connection cnx;

    public ServiceCommande() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commande_instruments t) {
        try {
            String sql = "INSERT INTO commande_instruments ( `nom_produit`, `prix` , `date`)values (?,?,?) ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setFloat(2,t.getPrix() );
            ste.setDate(3, java.sql.Date.valueOf(t.getDate_commande()));
            
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

    public void modifier(String nom_produit , float prix) {
        String sql = "update instruments set nom=? , prix =, where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom_produit);
            ste.setFloat(2, prix);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(String nom, String description, Commande_instruments t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commande_instruments> getAllInstruments() {
        List<Commande_instruments> ct = new ArrayList<>();
        try {
            String sql = "select * from commande_instruments";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Commande_instruments ci = new  Commande_instruments ();
                Date value3 = s.getDate("date");
                LocalDate value3AsLocalDate = value3.toLocalDate();
                Commande_instruments p = new Commande_instruments(s.getInt("id_commande"),s.getString("nom_produit"),s.getFloat("prix"),value3AsLocalDate);
              ct.add(p);
            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;
    }

    }

