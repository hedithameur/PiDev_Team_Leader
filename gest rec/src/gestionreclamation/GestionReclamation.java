/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionreclamation;

import Service.ServiceComm;
import Service.ServiceReclamation;
import entites.Commentaire_Rec;
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
        Reclamation o = new Reclamation(ld, "evenement", "artiste mauvais","54655555");
        Commentaire_Rec c=new Commentaire_Rec(ld, "ticket", 32);
        //LocalDate ld1=LocalDate.of(2001, 12, 13);
        ServiceReclamation o1 =new ServiceReclamation();
        ServiceComm c1 =new ServiceComm();
      
     //o1.ajouterReclamation(o); 
      o1.afficherReclamation();
      
      //o1.modifierReclamation(new Reclamation( 2, ld, "titre", "Description")) ;
      //o1.supprimerReclamation(33);
      //System.out.println(o1.displayByID(2));
     // c1.ajouterCommentaire_Rec(c);
      //c1.supprimerCommentaire_Rec(33);
      
       //c1.modifierCommentaire_Rec(new Commentaire_Rec(32, ld, "title", 32)) ; 
    }
    
}
