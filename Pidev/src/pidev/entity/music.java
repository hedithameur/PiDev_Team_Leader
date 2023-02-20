/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author user
 */
public class music {
    private int id;
    private String nom_artiste;
    private String nom_morceaux;
    private int id_Playlist;
      /*File file = new File("D:\\S2.mp3");
      FileInputStream fis;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();*/
byte[] bytes ;
public music()
{
    
}

    public music(int id, String nom_artiste, String nom_morceaux, int id_Playlist, byte[] bytes) {
        this.id = id;
        this.nom_artiste = nom_artiste;
        this.nom_morceaux = nom_morceaux;
        this.id_Playlist = id_Playlist;
        this.bytes = bytes;
    }

    public int getId_Playlist() {
        return id_Playlist;
    }
    

    public int getId() {
        return id;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public String getNom_morceaux() {
        return nom_morceaux;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "music{" + "id=" + id + ", nom_artiste=" + nom_artiste + ", nom_morceaux=" + nom_morceaux + ", id_Playlist=" + id_Playlist + ", bytes=" + bytes + '}';
    }

    

    
    



    
    
    
    
}
