/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.util.List;

/**
 *
 * @author 21650
 */
public interface InterfaceEvenement<T> {
    public void ajouter(T t);
    public List<T> affichier();
    public void supprimer (T t);
}
