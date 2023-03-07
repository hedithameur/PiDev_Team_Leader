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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.objects.Global.getDate;
import pidev.entity.music;
import pidev.entity.Utilisateur;
import pidev.tools.MaConnection;
/**
 *
 * @author user
 */
public class PlayListService implements playlist_interface <Playlist,Utilisateur > {
 Connection cnx;
    public PlayListService ()
    {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Playlist t,Utilisateur u) {
      try {
            String sql = "insert into playlist(id,nom,date_creation,id_User)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
           ste.setInt(1, t.getId_playlist());
            ste.setString(2,t.getNom_Playlist());
            ste.setDate(3, Date.valueOf(t.getDate_Creation()));
            ste.setInt(4,u.getId() );
            
           
            ste.executeUpdate();
            System.out.println("playlist ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  @Override
  public List<Playlist> getAll() {

    List<Playlist> playlists= new ArrayList<>();
    try {
        String sql = "SELECT playlist.id, playlist.nom, playlist.date_creation, utilisateur.nom AS user_nom " +
                     "FROM playlist " +
                     "JOIN utilisateur ON playlist.id_User = utilisateur.id";
        Statement ste = cnx.createStatement();
        ResultSet s = ste.executeQuery(sql);
        
        while (s.next()) {
            Playlist m = new Playlist(s.getInt("id"), s.getString("nom"), s.getDate("date_creation").toLocalDate(), new Utilisateur(s.getString("user_nom")));
            playlists.add(m);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return playlists;
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
    @Override
    public void modifier_Playlist(String nom, LocalDate d, Playlist t) {
         
       String sql = "update playlist set nom=?,date_creation=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setDate(2, Date.valueOf(d));
            ste.setInt(3,t.getId_playlist());
            ste.executeUpdate();
            System.out.println("modification effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<String> getnom() {
  List<String> noms = new ArrayList<>();
            try {
                String sql = "select nom from playlist";
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
    public ObservableList<Integer> getIdFromnom(String nom) {
         String sqlGetIdUser = "SELECT id FROM playlist WHERE nom='" +nom + "'";
Statement statement;
ObservableList<Integer> idList = FXCollections.observableArrayList();
try {
    statement = cnx.createStatement();
    ResultSet resultSet = statement.executeQuery(sqlGetIdUser);
    while (resultSet.next()) {
        int id = resultSet.getInt("id");
        idList.add(id);
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
return idList;
    }

    @Override
   public List<Playlist> getIdplaylist( String nom) {
                List<Playlist> playlists= new ArrayList<>();
    try {
        String sql = "select id from playlist where  nom=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
       
        ste.setString(1, nom);
        ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
        while (s.next()) {
       Playlist P = new Playlist(s.getString(1));
            playlists.add(P);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return playlists;   
    }

    public List<String> getNom(Utilisateur u) {
                   List<String> playlists= new ArrayList<>();
    try {
        String sql = "select nom from playlist where id_User=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, u.getId());
        ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
        while (s.next()) {
       
            playlists.add(s.getString(1));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return playlists; 
    }

    @Override
    public Integer getIdnomplaylist(String nom) {
        int i=0;         //    List<Playlist> playlists= new ArrayList<>();
    try {
        String sql = "select * from playlist where  nom=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
       
        ste.setString(1, nom);
        ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
        while (s.next()) {
     i = s.getInt("id");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return i; 
        
    }
}


 /* 
    @Override
    public List<Playlist> findById(int id) {
                List<Playlist> playlists= new ArrayList<>();
    try {
        String sql = "select nom,date_creation,id_User from playlist where id=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);
        ResultSet s = ste.executeQuery(); // utilise executeQuery() au lieu de execute()
        while (s.next()) {
          Playlist  p= new Playlist(id, s.getString("nom"), s.getDate("date_creation").toLocalDate(), s.getInt("id_User"));
            playlists.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return playlists;
    }

   
 

  

    @Override
    public List<Integer> getIdUser() {
             List<Integer> ids = new ArrayList<>();
            try {
                String sql = "select id from utilisateur";
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
    public List<Integer> getplaylist() {
           List<Integer> ids = new ArrayList<>();
            try {
                String sql = "select id from playlist";
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

 

   /* @Override
    public List<music> getmusic(int id) {
String query = "SELECT id, nom_morceaux, nom_artiste, fichier FROM music "
             + "JOIN playlist_music ON music.id = playlist_music.idmusic "
             + "WHERE id = ?";
    }*/
    
    


