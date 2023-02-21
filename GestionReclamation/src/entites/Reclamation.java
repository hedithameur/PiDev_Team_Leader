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
public class Reclamation {
    
    private int id_rec;
    private LocalDate date_rec;
    private String titre;
    private String Description ;

    public Reclamation() {
    }

    public Reclamation(int id_rec, LocalDate date_rec, String titre, String Description) {
        this.id_rec = id_rec;
        this.date_rec = date_rec;
        this.titre = titre;
        this.Description = Description;
    }
    

    public Reclamation(LocalDate date_rec, String titre, String Description) {
        this.date_rec = date_rec;
        this.titre = titre ;
        this.Description = Description;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public LocalDate getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(LocalDate date_rec) {
        this.date_rec = date_rec;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", date_rec=" + date_rec + ", titre=" + titre + ", Description=" + Description + '}';
    }
    
    
}
