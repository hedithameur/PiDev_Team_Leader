/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;

import java.sql.Date;
import java.time.LocalDate;

//import java.time.LocalDate;

/**
 *
 * @author 21650
 */
public class Evenement {
    private int id,nb_ticket;
    private String nom,lieu;
    private double prix;
    Date date;
    public String af;
   // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    //SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
    //String dateF=formater.format(date);
    public Evenement(){
        
    }
   /* public Evenement(int id,String nom,String lieu,String date,int nb_ticket,double prix){
        this.id=id;
        this.nom=nom;
        this.lieu=lieu;
        
      
         this.date=Date.valueOf(date);
        
         
        this.nb_ticket=nb_ticket;
        this.prix=prix;
    }*/
    public Evenement(int id,String nom,String lieu,String date,int nb_ticket,double prix,String a){
        this.id=id;
        this.nom=nom;
        this.lieu=lieu;
        
      
         this.date=Date.valueOf(date);
        this.nb_ticket=nb_ticket;
        this.prix=prix;
        this.af=a;
    }
    
   public Evenement(String nom,String lieu,String date,int nb_ticket,double prix,String a){
        
        this.nom=nom;
        this.lieu=lieu;
        
      
         this.date=Date.valueOf(date);
        
         
        this.nb_ticket=nb_ticket;
        this.prix=prix;
        this.af=a;
    }
  /* public Evenement(String affiche){
        
        
        this.affiche=affiche;
    }*/

    public String getAffiche() {
        return af;
    }

    public void setAffiche(String a) {
        this.af = a;
    }
        public Evenement(String nom,String lieu,String date,int nb_ticket,double prix){
        this.nom=nom;
        this.lieu=lieu;
        this.date=Date.valueOf(date);
        this.nb_ticket=nb_ticket;
        this.prix=prix;
    }

    public int getId() {
        return id;
    }

    public int getNb_ticket() {
        return nb_ticket;
    }

    public Date getDate() {
        return date;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNb_ticket(int nb_ticket) {
        this.nb_ticket = nb_ticket;
    }

    
    public void setDate(Date date) {
        this.date = date;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", nb_ticket=" + nb_ticket + ", nom=" + nom + ", lieu=" + lieu + ", prix=" + prix + ", date=" + date + ", af=" + af + '}';
    }

   
    
}
