
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
        Utilisateur u = new Utilisateur("hmidi", "mayar", "artiste", 26382407 ,"mayar@gmail.com","mm12");
        us.ajouter(u);
    }
    
}
