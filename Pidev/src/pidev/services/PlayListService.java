/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.util.List;
import pidev.entity.Playlist;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pidev.tools.MaConnection;
/**
 *
 * @author user
 */
public class PlayListService implements playlist_interface <Playlist> {
 Connection cnx;
    public PlayListService ()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Playlist t) {
      try {
            String sql = "insert into playlist(id,nom,date_creation,id_User)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId_playlist());
            ste.setString(2,t.getNom_Playlist());
            ste.setDate(3, new Date(t.getDate_Creation().getTime()));
            ste.setInt(4,t.getId_User() );
            
           
            ste.executeUpdate();
            System.out.println("playlist ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List getAll() {
        List<Playlist> playlists= new ArrayList<>();
        try {
            String sql = "select * from playlist";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Playlist m;
               m= new Playlist(s.getInt(1), s.getString("nom"), s.getDate(3), s.getInt(4));
                playlists.add(m);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return playlists;
    }

    @Override
    public List findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public void modifier_Music(Playlist t, String nom) {
       String sql = "update playlist set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,t.getId_playlist());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer_music(Playlist t) {
       String sql = "delete from playlist where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_playlist());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }//To change body of generated methods, choose Tools | Templates.
    }
    }
 

