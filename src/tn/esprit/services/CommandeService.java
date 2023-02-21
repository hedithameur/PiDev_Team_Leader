/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.Commande;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author hedi
 */
public class CommandeService implements InterfaceService<Commande> {
 Connection cnx;

    public CommandeService() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Commande t) {
        try {
            String sql = "insert into commande_ticket(nom_evenement,prix,id_ticket,id_utilisateur)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom_evenement());
            ste.setDouble(2, t.getPrix());
            ste.setInt(3, t.getId_ticket());
            ste.setInt(4, t.getId_utilisateur());
            
            ste.executeUpdate();
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Commande> getAll() {
        List<Commande> commandes = new ArrayList<>();
        try {
            String sql = "select * from commande_ticket";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Commande c = new Commande(s.getInt("id"), s.getString("nom_evenement"),s.getDouble("prix"),s.getInt("id_ticket"),s.getInt("id_utilisateur") );
                        
                commandes.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }

    public void supprimerCommande(Commande c) {
        String sql = "delete from commande_ticket where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, c.getId());
            ste.executeUpdate();
            System.out.println("Commande Supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierCommande(String nom_evenement,double prix,Commande c) {
        String sql = "update commande_ticket set nom_evenement=?,prix=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,nom_evenement);
            ste.setDouble(2,prix);
            ste.setInt(3,c.getId());
            ste.executeUpdate();
            System.out.println("Commande modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Integer> getid() {
        List<Integer> ids = new ArrayList<>();
        try {
            String sql = "select id from commande_ticket";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                ids.add(s.getInt(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ids;    
    }

    @Override
    public List<Commande> findById(int id) {
        List<Commande> commandes = new ArrayList<>();
        try {
            String sql = "select nom_evenement, prix, id_ticket, id_utilisateur from commande_ticket where id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id);
            ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
            while (s.next()) {
                Commande c = new Commande(id, s.getString("nom_evenement"),s.getDouble("prix"),s.getInt("id_ticket"),s.getInt("id_utilisateur"));
                commandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }
   
}