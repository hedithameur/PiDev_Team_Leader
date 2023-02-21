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
public class Commantaire_Rec {
    private int id_comm;
    private LocalDate date_comm;
    private String Contenu;
    private Reclamation rec;

    public Commantaire_Rec() {
    }

    public Commantaire_Rec(int id_comm, LocalDate date_comm, String Contenu, Reclamation rec) {
        this.id_comm = id_comm;
        this.date_comm = date_comm;
        this.Contenu = Contenu;
        this.rec = rec;
    }
    

    public Commantaire_Rec(LocalDate date_comm, String Contenu, Reclamation rec) {
        this.date_comm = date_comm;
        this.Contenu = Contenu;
        this.rec = rec;
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

    public Reclamation getRec() {
        return rec;
    }

    public void setRec(Reclamation rec) {
        this.rec = rec;
    }

    @Override
    public String toString() {
        return "Commantaire_Rec{" + "id_comm=" + id_comm + ", date_comm=" + date_comm + ", Contenu=" + Contenu + ", rec=" + rec + '}';
    }
    
    
    
    

    
}
