/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;
import java.util.Date;
/**
 *
 * @author user
 */
public class Playlist {
    private int id_playlist;
    private String nom_Playlist;
    private Date date_Creation;
    private int id_User;
    public Playlist()
    {
        
    }

    public Playlist(int id_playlist, String nom_Playlist, Date date_Creation, int id_User) {
        this.id_playlist = id_playlist;
        this.nom_Playlist = nom_Playlist;
        this.date_Creation = date_Creation;
        this.id_User = id_User;
    }

    public int getId_playlist() {
        return id_playlist;
    }

    public String getNom_Playlist() {
        return nom_Playlist;
    }

    public Date getDate_Creation() {
        return date_Creation;
    }

    public int getId_User() {
        return id_User;
    }

    @Override
    public String toString() {
        return "Playlist{" + "id_playlist=" + id_playlist + ", nom_Playlist=" + nom_Playlist + ", date_Creation=" + date_Creation + ", id_User=" + id_User + '}';
    }

            
            
            
            
            
            
            
            
            
            
            
            
            }
