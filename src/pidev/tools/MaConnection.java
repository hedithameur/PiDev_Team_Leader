/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 21650
 */
public class MaConnection {
    Connection cnx;
        String url = "jdbc:mysql://localhost:3306/pidev";
        String user="root";
        String pwd="";
       
        public static MaConnection ct;
        
        public MaConnection(){
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion Etabli ");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connexion NON Etabli !! ");
            }
        }
public static MaConnection getInstance(){
    if(ct==null)
        ct = new MaConnection();
    return ct;
}
    public Connection getCnx() {
        return cnx;
    }
        
        
        
    
}
