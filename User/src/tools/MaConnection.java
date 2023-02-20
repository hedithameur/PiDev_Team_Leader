
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MaConnection {
   
       private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/pidev";
        String user = "root";
        String pwd = "";
        
        public static MaConnection ct;

        private MaConnection() {
         try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx etablie ");
        } 
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        public static MaConnection getInstance(){
         if(ct ==null)
            ct= new MaConnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }
 public static void main(String[] args) {
       MaConnection cnx= new MaConnection();
        
       
      
        
    }
   
    }

