/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author bouzi
 */
public class Instrument {

    
    int id_instrument;
    String nom;
    float prix;
    String photo;
    String description;
    int id_vendeur , id_categorie ;

    public Instrument() {
    }

    public Instrument(int id_instrument, String nom, float prix, String photo, String description, int id_vendeur, int id_categorie) {
        this.id_instrument = id_instrument;
        this.nom = nom;
        this.prix = prix;
        this.photo = photo;
        this.description = description;
        this.id_vendeur = id_vendeur;
        this.id_categorie = id_categorie;
    }
    

    public Instrument(int id_instrument, String nom, float prix, String description, int id_vendeur, int id_categorie) {
        this.id_instrument = id_instrument;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.id_vendeur = id_vendeur;
        this.id_categorie = id_categorie;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_vendeur() {
        return id_vendeur;
    }

    public void setId_vendeur(int id_vendeur) {
        this.id_vendeur = id_vendeur;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
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
        return "Instrument{" + "id_instrument=" + id_instrument + ", nom=" + nom + ", prix=" + prix + ", photo=" + photo + ", description=" + description + ", id_vendeur=" + id_vendeur + ", id_categorie=" + id_categorie + '}';
    }
    
    
   
  
}
