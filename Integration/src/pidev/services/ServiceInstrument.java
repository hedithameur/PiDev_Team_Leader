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
import pidev.entity.CategorieInstrument;
import pidev.entity.Instrument;
import pidev.tools.MaConnection;

/**
 *
 * @author bouzi
 */
public class ServiceInstrument implements Interface<Instrument> {

    Connection cnx;

    public ServiceInstrument() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Instrument t) {
        try {
            String sql = "INSERT INTO instruments ( `nom` , `prix`, `description`,`id_categorie`)  VALUES (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setFloat(2, t.getPrix());
            ste.setString(3, t.getDescription());
            ste.setInt(4, t.getCategorie().getId());
            ste.executeUpdate();
            System.out.println("Intrument ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterphoto(Instrument t) {
        try {
            String sql = "INSERT INTO instruments ( `nom` , `prix`, `photo`,`description`,`id_categorie`)  VALUES (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setFloat(2, t.getPrix());
            ste.setString(3, t.getPhoto());
            ste.setString(4, t.getDescription());
            ste.setInt(5, t.getCategorie().getId());
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

    public void modifier(String nom, float prix, String description, Instrument t) {
        String sql = "update instruments set nom=?,  prix =? , description=? where id_instrument=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setFloat(2, prix);
            ste.setString(3, description);

            ste.setInt(4, t.getId_instrument());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Instrument getInstrument(int id) {
        Instrument i = new Instrument();
        String sql = "select * from categorie_instruments where id = " + id;
        try {
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            if (s.next()) {
                // Si un résultat est trouvé, on met à jour l'objet Instrument avec les données de la base de données

                i.setNom(s.getString("nom"));
                //i.setDescription(s.getString("description"));
            } else {
                // Si aucun résultat n'est trouvé, on retourne null
                i = null;
            }
            s.close(); // On ferme le ResultSet
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            i = null;
        }
        return i; // On retourne l'objet Instrument mis à jour ou null s'il n'a pas été trouvé
    }

    @Override
    public List<Instrument> getAllInstruments() {
        List<Instrument> ct = new ArrayList<>();
        try {
            String sql = "select * from instruments";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                CategorieInstrument c = new CategorieInstrument();
                Instrument i = new Instrument();
                Instrument p = new Instrument(s.getInt(1), s.getString(2), s.getFloat(3), s.getString(4), s.getString(5));
                c.setId(s.getInt(6));

                ServiceCategorie myser = new ServiceCategorie();
                c = myser.findCatById(c.getId());
                p.setCategorie(c);
                ct.add(p);
                //int id_categorie = s.getInt("id_categorie");

            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;
    }

    
    public List<Instrument> displayInstruments() {
        List<Instrument> ct = new ArrayList<>();
        try {
            String sql = "select * from instruments";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                CategorieInstrument c = new CategorieInstrument();
               Instrument i = new Instrument();
                Instrument p = new Instrument(s.getString("nom"), s.getFloat("prix"),s.getString("photo"));

//                ServiceCategorie myser = new ServiceCategorie();
//                c = myser.findCatById(c.getId());
               p.setCategorie(c);
                ct.add(p);
//                //int id_categorie = s.getInt("id_categorie");

            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;
    }

    public List<Instrument> newaffciher() {
        List<Instrument> ct = new ArrayList<>();
        try {
            String sql = "select * from instruments";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                CategorieInstrument c = new CategorieInstrument();
                Instrument i = new Instrument();
                Instrument p = new Instrument(s.getInt(1), s.getString(2), s.getFloat(3), s.getString(4), s.getString(5));
                c.setId(s.getInt(6));

                ServiceCategorie myser = new ServiceCategorie();
                c = myser.findCatById(c.getId());
                p.setCategorie(c);
                ct.add(p);
                //int id_categorie = s.getInt("id_categorie");

            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;
    }

    public List<Instrument> getidCat() {

        //La classe Pair est une classe générique fournie par Java qui vous permet de combiner deux types de données différents dans une seule paire
        List<Instrument> ct = new ArrayList<>();
        try {
            String sql = "SELECT I.id_instrument , I.nom , I.prix , I.description, C.nom_categorie FROM instruments as I , categorie_instrument as C WHERE I.id_categorie = C.id";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                //Instrument b = new Instrument (s.getInt("id_instrument"),s.getString("nom"),s.getFloat("prix"),s.getString("description"),s.getString("nom_categorie"));
                // ct.add(b);            
            }
            System.out.println(ct);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ct;

    }

    @Override
    public void modifier(String nom, String description, Instrument t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
