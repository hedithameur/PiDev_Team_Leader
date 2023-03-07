package pidev.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
import pidev.entity.Utilisateur;
import java.util.List;
public interface InterfService <U>{
     public void ajouter(U u);
    public List<U> getAll();
    public List<String> getNom();
    
}
