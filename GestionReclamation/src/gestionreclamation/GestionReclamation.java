/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamation;

import Service.ServiceReclamation;
import entites.Reclamation;
import java.time.LocalDate;
import tools.MaConnection;

/**
 *
 * @author user
 */
public class GestionReclamation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MaConnection ds1 = MaConnection.getInstance();
        //System.out.println(ds1);
        LocalDate ld=LocalDate.of(2001, 12, 13);
        Reclamation o = new Reclamation(ld, "evenement", "hasbi allah w ne3ma el wakil");
        //LocalDate ld1=LocalDate.of(2001, 12, 13);
        ServiceReclamation o1 =new ServiceReclamation();
      
      //o1.ajouterReclamation(o); 
      //o1.afficherReclamation();
      //o1.modifierOffre(new Offre( 11,"oumayma", "aaaaaa", "blabhjklla", "tunis",20.5f,10, ld1));
      //o1.modifierReclamation(new Reclamation( 2, ld, "titre", "Description")) ;
      //o1.supprimerReclamation(1);
      //System.out.println(o1.displayByID(2));
        
    }
    
}
