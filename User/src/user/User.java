
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


public class User {

    
    public static void main(String[] args) {
       
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = new Utilisateur("slimi", "rahma", "admin", 222222 ,"slimi@gmail.com","admin123");
        us.ajouter(u);
        
        Utilisateur u1 = new Utilisateur(10,"slimi", "rahma", "admin", 222222 ,"slimi@gmail.com","admin123");
        us.modifierUtilisateur("xxx","hedi","hedi9@gmail.com", u);
        
        us.supprimerUtilisateur(u1);
    }
    
}
