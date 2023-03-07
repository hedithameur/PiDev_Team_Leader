/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entity;

/**
 *
 * @author user
 */
public class music_playlist 
{
    private music m;
    private Playlist P;
 public music_playlist()
 {
     
 }
    public music_playlist(music m, Playlist P) {
        this.m = m;
        this.P = P;
    }

    public music getM() {
        return m;
    }

    public Playlist getP() {
        return P;
    }

    public void setM(music m) {
        this.m = m;
    }

    public void setP(Playlist P) {
        this.P = P;
    }
    
    
    
    
}
