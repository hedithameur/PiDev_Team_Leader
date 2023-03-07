/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
//import java.sql.Date;
import java.time.LocalDate;


/**
 *
 * @author bouzi
 */
public class Commande_instruments {

   private int id_commande;
   private String nom ;
   private float prix ;
//   Date date_commande ;
   private LocalDate date_commande = LocalDate.now();

    public Commande_instruments() {
    }

    public Commande_instruments(int id_commande, String nom, float prix,LocalDate date_commande) {
        this.id_commande = id_commande;
        this.nom = nom;
        this.prix = prix;
              this.date_commande=date_commande;

    }
    

    public Commande_instruments(String nom, float prix,LocalDate date_commande) {
      
        this.nom = nom;
        this.prix = prix;
      this.date_commande=date_commande;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
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

    public LocalDate getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(LocalDate date_commande) {
        this.date_commande = date_commande;
    }

    @Override
    public String toString() {
        return "Commande_instruments{" + "id_commande=" + id_commande + ", nom=" + nom + ", prix=" + prix + ", date_commande=" + date_commande + '}';
    }
   

    

   
  
}
