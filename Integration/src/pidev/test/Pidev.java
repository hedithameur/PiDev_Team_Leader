/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.test;

/**
 *
 * @author user
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pidev.entity.music;
import pidev.tools.MaConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import pidev.entity.Playlist;
import pidev.services.PlayListService;

import pidev.services.musicService;
import java.util.Date;
import pidev.entity.Utilisateur;

public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException  {
        // TODO code application logic here
    
File file = new File("D:\\S2.mp3");
      FileInputStream fis = new FileInputStream(file);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];
      for (int readNum; (readNum = fis.read(buf)) != -1;) {
        bos.write(buf, 0, readNum);
      }
      /*
      byte[] bytes = bos.toByteArray();    
    music m = new music(1,"ezrzer","zezfz",1,bytes);
    music m2 = new music (2,"comme pas deux","PNL",1,bytes);
    music m3 = new music (3,"venom ","eminem",1,bytes);
    
    Date d =  new Date("31/09/2022");

*/
PlayListService P = new PlayListService();
Utilisateur u =new Utilisateur();
u.getId();
LocalDate d = LocalDate.of(2023,2,10);
int a=1;
u.setId(a);
//Playlist p = new Playlist(4,"Music2",d,u);
//Playlist p = new Playlist();
//p.setId_playlist(1);
//P.modifier_Playlist("adam", d, p);
//P.ajouter(p,u);
//System.out.println(P.getAll());


/*musicService M = new musicService();
LocalDate d = LocalDate.of(2023,2,10);
LocalDate d1 = LocalDate.of(2028,2,10);
Playlist p = new Playlist(2,"Music2",d,1);
System.out.println(M.getFichier());
System.out.println(M.searchMusic("blanka"));*/
//P.modifier_Playlist("m", d1, 1, p);
//P.ajouter(p);
//Playlist
//P.ajouter(p);

//System.out.println(P.getAll());
//P.modifier_Music(p, "MUSIC23");
//P.supprimer_music(p);






//Music
//M.ajouter(m3);  
//System.out.println(M.getAll());

//M.ajouter(m2);
  //M.supprimer_music(m3);
  //   fis.close();
    //  bos.close();
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    }
    
}
   /* Connection cnx;
          String url = "jdbc:mysql://localhost:3306/test";
          String user="root";
          String pwd="";
        try{
          cnx=DriverManager.getConnection(url,user,pwd);
          String sql="insert into client(id,nom,prenom)"
                  +"values(5,'aziz','taha')";
          Statement ste=cnx.createStatement();
          ste.executeUpdate(sql);
          System.out.println("Client ajouté!!");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }*/
    