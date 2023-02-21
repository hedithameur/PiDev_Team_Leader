/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;

/**
 *
 * @author 21650
 */
public class commentaire {
   
    private int id,id_utilisateur;
    private String texte;
    
    
    public commentaire(){
        
    }
    public commentaire(int id,int id_utilisateur,String texte){
        this.id=id;
        this.id_utilisateur=id_utilisateur;
        this.texte=texte;
       
    }
    public commentaire(int id_utilisateur,String texte){
        this.id_utilisateur=id_utilisateur;
        this.texte=texte;
       
    }
     public commentaire(String texte){
        
        this.texte=texte;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", id_utilisateur=" + id_utilisateur + ", texte=" + texte + '}';
    }
       
}

    

