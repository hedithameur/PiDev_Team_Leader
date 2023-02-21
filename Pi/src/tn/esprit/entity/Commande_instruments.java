/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author bouzi
 */
public class Commande_instruments {

   private int id_commande,id_vendeur,id_instru;
  
    
private LocalDate date_commande;
    //LocalDateTime date = LocalDateTime.now();

    public Commande_instruments(int id_commande, int id_vendeur, int id_instru, LocalDate date_commande) {
        this.id_commande = id_commande;
        this.id_vendeur = id_vendeur;
        this.id_instru = id_instru;
        this.date_commande = date_commande;
    }

    public LocalDate getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(LocalDate date_commande) {
        this.date_commande = date_commande;
    }

   
    

    public Commande_instruments() {
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_vendeur() {
        return id_vendeur;
    }

    public void setId_vendeur(int id_vendeur) {
        this.id_vendeur = id_vendeur;
    }

    public int getId_instru() {
        return id_instru;
    }

    public void setId_instru(int id_instru) {
        this.id_instru = id_instru;
    }

    

    
  
    
}
