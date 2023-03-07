/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;
import java.util.Date;
import java.time.LocalDate;
/**
 *
 * @author user
 */
public class Playlist {
    private int id_playlist;
    private String nom_Playlist;
    private  LocalDate date_Creation;
     private Utilisateur User;
    public Playlist()
    {
        
    }
 public Playlist(String nom_Playlist)
    {
        this.nom_Playlist = nom_Playlist;
    }
     public Playlist(int id_playlist,String nom_Playlist)
    {
        this.id_playlist = id_playlist;
        this.nom_Playlist = nom_Playlist;
    }
    public Playlist (int id_playlist)
{
     this.id_playlist = id_playlist;
}
    public Playlist(int id_playlist, String nom_Playlist, LocalDate date_Creation, Utilisateur User) {
        this.id_playlist = id_playlist;
        this.nom_Playlist = nom_Playlist;
        this.date_Creation = date_Creation;
        this.User = User;
    }

   

    public int getId_playlist() {
        return id_playlist;
    }

    public String getNom_Playlist() {
        return nom_Playlist;
    }

    public LocalDate getDate_Creation() {
        return date_Creation;
    }

    public void setId_playlist(int id_playlist) {
        this.id_playlist = id_playlist;
    }

    public void setNom_Playlist(String nom_Playlist) {
        this.nom_Playlist = nom_Playlist;
    }

    public void setDate_Creation(LocalDate date_Creation) {
        this.date_Creation = date_Creation;
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }

    @Override
    public String toString() {
        return "Playlist{" + "id_playlist=" + id_playlist + ", nom_Playlist=" + nom_Playlist + ", date_Creation=" + date_Creation + ", User=" + User + '}';
    }

    

 

            
            
            
            
            
            
            
            
            
            
            
            
            }
