/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;
import java.util.ArrayList;
import java.util.List;
import pidev.entity.music;
/**
 *
 * @author user
 */
public interface InterfaceMusic <T>  {
    public void ajouter(T t);
    public List<T> getAll();
    public List<T> findById(int id);
  public void modifier_Music(String nom_morceaux,String nom_Artiste,String fichier,music t);
     void supprimer_music(T t);
     public List<Integer> getId();
     public List<String> getFichier();
      public List<String> getnom_morceaux();           
    public List<music> searchMusic(String songName);
     public List<String> getFichier(String nom);
    // public List<byte[]> getBytes(int id);
         
}
