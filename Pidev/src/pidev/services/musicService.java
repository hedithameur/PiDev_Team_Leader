/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

/**
 *
 * @author user
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entity.music;
import pidev.tools.MaConnection;


public class musicService implements InterfaceMusic <music>{

    Connection cnx;
    public musicService()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(music t) {
      try {
            String sql = "insert into music(id,nom_morceaux,nom_artiste,fichier)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId());
            ste.setString(2,t.getNom_morceaux());
            ste.setString(3, t.getNom_artiste());
            ste.setString(4,t.getFichier() );
           
           
            ste.executeUpdate();
            System.out.println("music ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<music> getAll() {
        List<music> musics= new ArrayList<>();
        try {
            String sql = "select * from music";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                music m;
               m= new music(s.getInt(1), s.getString("nom_artiste"), s.getString("nom_morceaux"), s.getString(4));
                musics.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return musics; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<music> findById(int id) {
           List<music> musics = new ArrayList<>();
    try {
        String sql = "select nom_morceaux,nom_artiste,fichier from music where id=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
        while (s.next()) {
          music  m= new music(id, s.getString("nom_morceaux"), s.getString("nom_artiste"), s.getString("fichier"));
            musics.add(m);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return musics;
    }
@Override
    public void modifier_Music(String nom_morceaux,String nom_Artiste,String fichier,music t) {
          String sql = "update music set nom_morceaux=?,nom_artiste=?,fichier=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
              ste.setString(1, nom_morceaux);
             ste.setString(2, nom_Artiste);
             ste.setString(3, fichier);
            ste.setInt(4,t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer_music(music t) {
         String sql = "delete from music where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Integer> getId() {
         List<Integer> ids = new ArrayList<>();
            try {
                String sql = "select id from music";
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

  /*  @Override
    public List<byte[]> getBytes(int id) {
        List<byte[]> bytes = new ArrayList<>();
            try {
                String sql = "select fichier from  where id=?";
                PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet s = ste.executeQuery();
                while (s.next()) {

                
                    bytes.add(s.getBytes("fichier"));

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return bytes;
    }*/

    @Override
    public List<String> getFichier() {
         List<String> fichiers = new ArrayList<>();
            try {
                String sql = "select fichier from music";
                Statement ste = cnx.createStatement();
                ResultSet s = ste.executeQuery(sql);
                while (s.next()) {

                
                    fichiers.add(s.getString(1));

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return fichiers;
       
    }

    @Override
    public List<music> searchMusic(String songName) {
          List<music> results = new ArrayList<>();
        try {
            Statement stmt = cnx.createStatement();
            String query = "SELECT * FROM music WHERE nom_morceaux LIKE '%" + songName + "%'";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                music music = new music(rs.getInt("id"), rs.getString("nom_morceaux"), rs.getString("nom_artiste"), rs.getString("fichier"));
                results.add(music);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<String> getnom_morceaux() {
        List<String> noms = new ArrayList<>();
            try {
                String sql = "select nom_morceaux from music";
                Statement ste = cnx.createStatement();
                ResultSet s = ste.executeQuery(sql);
                while (s.next()) {

                
                    noms.add(s.getString(1));

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return noms;
    }

    @Override
    public List<String> getFichier(String nom) {
      List<String> fichiers = new ArrayList<>();
    try {
        String sql = "select fichier from music where nom_morceaux=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, nom);
        ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
        while (s.next()) {
           fichiers.add(s.getString(1));
           
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return fichiers;

    }



    

   

    
    
}
