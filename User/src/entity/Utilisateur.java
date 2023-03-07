
package entity;

import java.sql.Date;
import java.time.LocalDate;


 
public class Utilisateur {

    public Utilisateur(int id, String nom, String prenom, String role, int telephone, String email, String mot_de_passe, LocalDate dateNaiss) {
        this.id = id;
        this.telephone = telephone;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        
        this.dateNaiss= dateNaiss;
    }
    
     private int id;
     private int telephone;
     private String nom, prenom, role, email, mot_de_passe;
     private LocalDate dateNaiss;
    
    
    
    public Utilisateur(){}
    
    
    public Utilisateur(String nom, String prenom, String role, int telephone, String email, String mot_de_passe, LocalDate dateNaiss){
    
        
        this.nom=nom;
        this.prenom=prenom;
        this.role=role;
        this.telephone=telephone;
        this.email=email;
        this.mot_de_passe=mot_de_passe;
        this.dateNaiss=dateNaiss;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }
 

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
    public LocalDate getdateNaiss() {
        
        return dateNaiss;
    }

    public void setdateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", telephone=" + telephone + ", nom=" + nom + ", prenom=" + prenom + ", role=" + role + ", email=" + email + ", mot_de_passe=" + mot_de_passe  
                + ", dateNaiss=" + dateNaiss+ '}';
    }
    
    
    
    
    
}
