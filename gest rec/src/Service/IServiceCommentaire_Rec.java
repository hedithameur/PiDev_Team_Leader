/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entites.Commentaire_Rec;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author user
 * @param <C>
 */
public interface IServiceCommentaire_Rec <C>{
    
    public void ajouterCommentaire_Rec(C c);
    public List<C> afficherCommentaire_Rec();

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<C> recuperer() throws SQLException;
    public void modifierCommentaire_Rec(C c) ;
    public void supprimerCommentaire_Rec(int id_rec);
   public  List <C> displayByID(int id_rec) ;

    
}
