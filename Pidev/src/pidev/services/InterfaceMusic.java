/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;
import java.util.List;
/**
 *
 * @author user
 */
public interface InterfaceMusic <T>  {
    public void ajouter(T t);
    public List<T> getAll();
    public List<T> findById(int id);
    public void modifier_Music(T t,String nom);
     void supprimer_music(T t);
}
