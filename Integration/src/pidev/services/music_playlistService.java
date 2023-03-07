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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entity.Playlist;
import pidev.entity.music;
import pidev.tools.MaConnection;

/**
 *
 * @author user
 */
public class music_playlistService implements Music_Playlist<Playlist,music>{
Connection cnx;
public music_playlistService()
{
  cnx = MaConnection.getInstance().getCnx();
} 
    
    @Override
    public void ajouter_Music_Playlist(Playlist t, music M) {
     
         try {
            String sql = "insert into music_playlist(id_playlist,id_music)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId_playlist());
            ste.setInt(2,M.getId());
            
            
           
            ste.executeUpdate();
            System.out.println("playlist music ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getMusic(Playlist t) {
    List<String> morceaux = new ArrayList<>();
        try {
        String sql = "SELECT m.nom_morceaux FROM music m "
                + "JOIN music_playlist mp ON m.id = mp.id_music "
                + "JOIN playlist p ON mp.id_playlist = p.id "
                + "WHERE p.nom = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setString(1,t.getNom_Playlist());
         ResultSet rs = stmt.executeQuery();
             while (rs.next()) {
                morceaux.add(rs.getString("nom_morceaux"));
            }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
            return morceaux;
    }
    
    }
    

