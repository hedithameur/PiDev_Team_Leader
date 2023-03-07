/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 *
 * @author user
 */
public class music {
    private int id;
    private String nom_artiste;
    private String nom_morceaux;
  
      /*File file = new File("D:\\S2.mp3");
      FileInputStream fis;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();*/
String  fichier;
public music()
{
    
}

    public music(int id, String nom_artiste, String nom_morceaux,  String fichier) {
        this.id = id;
        this.nom_artiste = nom_artiste;
        this.nom_morceaux = nom_morceaux;
    
        this.fichier = fichier;
       
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

    public String getFichier() {
        return fichier;
    }

    @Override
    public String toString() {
        return "music{" + "id=" + id + ", nom_artiste=" + nom_artiste + ", nom_morceaux=" + nom_morceaux + ", fichier=" + fichier + '}';
    }

    
   

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public void setNom_morceaux(String nom_morceaux) {
        this.nom_morceaux = nom_morceaux;
    }

    

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

 

    

   
    

    
    



    
    
    
    
}
