/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entites.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 * @param <R>
 */
public interface IServiceReclamation <R>{
    public void ajouterReclamation(Reclamation R);
    public List<Reclamation> afficherReclamation();

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Reclamation> recuperer() throws SQLException;
    public void modifierReclamation(Reclamation R) ;
    public void supprimerReclamation(int id_rec);
   public  List <Reclamation> displayByID(int id_rec) ;


    
}
