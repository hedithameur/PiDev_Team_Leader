
package user;

import entity.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import service.UtilisateurService;
import tools.MaConnection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;


public class User {

    
    public static void main(String[] args) {
       
        UtilisateurService us = new UtilisateurService();
        LocalDate ld= LocalDate.of(2000, 11, 17);
        Utilisateur u = new Utilisateur("slimi", "rahma", "admin", 96506632 ,"rahma.slimi@gmail.com","admin123",ld);
        us.ajouter(u);
       // System.out.println(us.getPassword("mayar.hmidi@esprit.tn"));
        
       // us.modifierUtilisateur("xxx","hedi","hedi9@gmail.com", u);
        
       // us.supprimerUtilisateur(u);
    }
    
}
