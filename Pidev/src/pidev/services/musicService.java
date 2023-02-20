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
            String sql = "insert into music(id,nom_morceaux,nom_artiste,fichier,id_playlist)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId());
            ste.setString(2,t.getNom_morceaux());
            ste.setString(3, t.getNom_artiste());
            ste.setBytes(4,t.getBytes() );
            ste.setInt(5, t.getId_Playlist());
           
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
               m= new music(s.getInt(1), s.getString("nom_morceaux"), s.getString("nom_artiste"),s.getInt(5), s.getBytes(4));
                musics.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return musics; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<music> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier_Music(music t,String nom) {
          String sql = "update music set nom_morceaux=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,t.getId());
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
    
}
