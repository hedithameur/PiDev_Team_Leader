/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;

/**
 *
 * @author bouzi
 */
public class Instrument {

    public static Object get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    int id_instrument;
    String nom;
    float prix;
    String photo ;
    String description;
    
    CategorieInstrument categorie ;
    Utilisateur user ;
    

    public Instrument() {
    }

    public Instrument(String nom, float prix, String photo, String description) {
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
    }

    public Instrument(String nom, float prix, String photo) {
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
    }

    public Instrument(int id_instrument, String nom, float prix, String photo, String description, CategorieInstrument categorie) {
        this.id_instrument = id_instrument;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
    }

    public Instrument(int id_instrument, String nom, float prix, String photo, String description) {
        this.id_instrument = id_instrument;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
    }

    public Instrument(String nom, float prix, String photo, String description, CategorieInstrument categorie) {
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
    }

    public Instrument(int id_instrument, String nom, float prix, String photo, String description,  Utilisateur user) {
        this.id_instrument = id_instrument;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
    
        this.user = user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Utilisateur getUser() {
        return user;
    }
    
    public int getId_instrument() {
        return id_instrument;
    }

    public void setId_instrument(int id_instrument) {
        this.id_instrument = id_instrument;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategorieInstrument getCategorie() {
        return categorie;
    }
    

    public void setCategorie(CategorieInstrument categorie) {
        this.categorie = categorie;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id_instrument;
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
        final Instrument other = (Instrument) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Instrument{" + "id_instrument=" + id_instrument + ", nom=" + nom + ", prix=" + prix + ", photo=" + photo + ", description=" + description + ", categorie=" + categorie + '}';
    }

   

  

  

    
    
    
   
  
}
