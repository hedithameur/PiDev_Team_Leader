/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entity.Evenement;
import pidev.entity.commentaire;
import pidev.services.EvenementService;
import pidev.services.commentaireService;
import pidev.tools.MaConnection;

/**
 *
 * @author 21650
 */
public class PiDev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    /* MaConnection mc= MaConnection.getInstance();
      MaConnection mc1=MaConnection.getInstance();
       MaConnection mc2=MaConnection.getInstance();
        MaConnection mc3=MaConnection.getInstance();
        System.out.println(mc3);
        System.out.println(mc);*/
    
   Evenement e = new Evenement("Samir Lousif","Hammamet","2023-02-21",200,70.0,"c://photo");
   EvenementService es =new EvenementService();
   
  es.ajouteraffiche(e);
   
    //es.modifierEvenement("Sanfara",e);
   
 // es.supprimer(e);
    //System.out.println(es.affichier());
   /*-------------------------------------------------------------------------------------------------*/     
   //commentaire c = new commentaire(1,"Worth 10 Dinars");
      // commentaireService cs =new commentaireService();
      //  cs.ajouterCommentaire(c);
       // System.out.println(cs.affichierCommentaire());
      // cs.supprimerCommentaire(c);
     //  cs.modifierCommentaire(" top des tops",c);
     //  System.out.println(cs.affichierCommentaire());
    }
    
}
