/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Commentaire_Rec {
    private int id_comm;
    private LocalDate date_comm;
    private String Contenu;
    private int id_rec;

    public Commentaire_Rec() {
    }

    public Commentaire_Rec(int id_comm, LocalDate date_comm, String Contenu, int id_rec) {
        this.id_comm = id_comm;
        this.date_comm = date_comm;
        this.Contenu = Contenu;
        this.id_rec = id_rec;
    }
    

    public Commentaire_Rec(LocalDate date_comm, String Contenu, int id_rec) {
        this.date_comm = date_comm;
        this.Contenu = Contenu;
        this.id_rec = id_rec;
    }
    

    public int getId_comm() {
        return id_comm;
    }

    public void setId_comm(int id_comm) {
        this.id_comm = id_comm;
    }

    public LocalDate getDate_comm() {
        return date_comm;
    }

    public void setDate_comm(LocalDate date_comm) {
        this.date_comm = date_comm;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    @Override
    public String toString() {
        return "Commantaire_Rec{" + "id_comm=" + id_comm + ", date_comm=" + date_comm + ", Contenu=" + Contenu + ", id_rec=" + id_rec + '}';
    }
    
    
    
    

    
}
