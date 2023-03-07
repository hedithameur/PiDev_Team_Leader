/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;

//import javax.sound.midi.Instrument;

/**
 *
 * @author bouzi
 */
public class CategorieInstrument {
   private int id;
    private String nom_categorie;
    private String description;
   
    

    public CategorieInstrument() {
        
    }

    public CategorieInstrument(int id, String nom_categorie) {
        this.id = id;
        this.nom_categorie = nom_categorie;
    }
    

    public CategorieInstrument(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }
    
    

    public CategorieInstrument(String nom_categorie, String description) {
        this.nom_categorie = nom_categorie;
        this.description = description;
    }

    public CategorieInstrument(int id, String nom_categorie, String description) {
        this.id = id;
        this.nom_categorie = nom_categorie;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

   

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CategorieInstrument other = (CategorieInstrument) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategorieInstrument{" + "id=" + id + ", nom_categorie=" + nom_categorie + ", description=" + description + '}';
    }

   // public void getId(int aInt) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public void getNom_categorie(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

  
    
    
}
