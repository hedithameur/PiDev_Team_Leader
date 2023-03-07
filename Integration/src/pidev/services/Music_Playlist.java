/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.util.List;
import pidev.entity.Playlist;
import pidev.entity.music;

/**
 *
 * @author user
 */
public interface Music_Playlist <P,M> {
    public void ajouter_Music_Playlist(Playlist t, music M);
     public List<String> getMusic(P t); 
    
    
}
