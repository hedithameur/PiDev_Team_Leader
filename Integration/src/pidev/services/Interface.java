/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.util.List;

/**
 *
 * @author bouzi
 * @param <T>
 */
public interface Interface<T> {

    // T => Categori
    // Z => Instrument
    // H => Commande Instrument
    ////////// Categorie ////////////
    public void ajouter(T t);

    public void supprimer(T t);

    public void modifier(String nom, String description, T t);

    public List<T> getAllInstruments();
}
