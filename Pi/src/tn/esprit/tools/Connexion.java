/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bouzi
 */
public class Connexion {
     private Connection cnx ;
               String url ="jdbc:mysql://localhost:3306/pi";
               String user="root";
               String pwd ="";
               public static Connexion ct;
    

         private Connexion() {
     try {
            cnx =DriverManager.getConnection(url, user, pwd);
            System.out.println("conexion etabli");
}        catch (SQLException ex) {      
         System.out.println(ex.getMessage());
}      
}
         //singleton java (methide de travaille) (creation d'une seul instance)
      public static Connexion getInstance(){
        if(ct ==null)
            ct= new Connexion();
        return ct;     
}

    public Connection getCnx() {
        return cnx;
    }

    

    
      
}