/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author bouzi
 */
public interface Interface <T> {
    // T => Categori
    // Z => Instrument
    // H => Commande Instrument
    ////////// Categorie ////////////
     public void ajouter(T t);
     public void supprimer(T t);
     public void modifier(T t);
    
     public ObservableList afficher();
}
        
  