/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;
import pidev.entity.Playlist;
import pidev.entity.music;

/**
 *
 * @author user
 */
public interface playlist_interface <T,U> {
  
    public void ajouter(T t,U u);
    public List<T> getAll();
     void supprimer_music(T t);
      public void modifier_Playlist(String nom,LocalDate  d,Playlist t);
      public List<String> getnom();
      public ObservableList<Integer> getIdFromnom(String nom);
      public List<Playlist> getIdplaylist(String nom);
      public List<String> getNom(U u);
      public Integer getIdnomplaylist(String nom);
   /* public List<T> findById(int id);
    public void modifier_Playlist(Playlist t) ;
    
      public void ajouter_Music_Playlist(T t,music M);
       public List<Integer> getIdUser();
         public List<Integer> getplaylist();
        */
   //public List<music> getmusic(int id);
}
