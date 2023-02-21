/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entites.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnection;

/**
 *
 * @author user
 */
public class ServiceReclamation implements IServiceReclamation<Reclamation>{
    
    private Connection conn;
    PreparedStatement stmt;
    PreparedStatement ste;

    public ServiceReclamation() {
         conn = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterReclamation(Reclamation c) {
             
        String sql ="insert into reclamation (id_rec,date_rec,titre,description) Values(?,?,?,?)";
        
        try
        {
           ste=conn.prepareStatement(sql);
           ste.setInt(1, c.getId_rec());
           ste.setDate(2,Date.valueOf(c.getDate_rec()));
          ste.setString(3,c.getTitre());
           ste.setString(4,c.getDescription());

           ste.executeUpdate();
           System.out.println("Reclamation Ajoutée");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }   
    }
    @Override
    public List<Reclamation> recuperer() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String s = "select * from reclamation";
        Statement st = conn.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Reclamation r = new Reclamation();
            r.setId_rec(rs.getInt("id_rec"));
            r.setDate_rec(rs.getDate("Date_rec").toLocalDate());
            r.setTitre(rs.getString("titre"));
            r.setDescription(rs.getString("description"));
           
            
            
            reclamations.add(r);
            
        }
        return reclamations;
    }
    
    
    public List<Integer> getid() {
        List<Integer> ids = new ArrayList<>();
        try {
            String sql = "select id_rec from reclamation";
            Statement ste = conn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

               
                ids.add(s.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ids;
    }
    @Override
    public List<Reclamation> afficherReclamation() {
        List<Reclamation> reclamation =  new ArrayList<>();
      String sql="select * from reclamation";
      
      try
      {
          ste=conn.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      Reclamation o = new Reclamation();
                      o.setId_rec(rs.getInt("id_rec"));
                      o.setDate_rec(rs.getDate("date_rec").toLocalDate());
                      o.setTitre(rs.getString("titre"));
                      o.setDescription(rs.getString("description"));
               
                      reclamation.add(o);
                      System.out.println("id reclamation : "+o.getId_rec()+ "\n Date de Reclamation : "+o.getDate_rec().toString()+ "\n Titre : "+o.getTitre()+"\n Descrption: " +o.getDescription()) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return reclamation;
    }

    @Override
    public void modifierReclamation(Reclamation e) {
            try {
          
         String req = "UPDATE `reclamation` SET  `id_rec`='" + e.getId_rec()+ "',`date_rec`='" + Date.valueOf(e.getDate_rec())+
                 "',`titre`='" + e.getTitre()+ "', `description`='" + e.getDescription()+
                 "' WHERE id_rec=" + e.getId_rec();      
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("La reclamation   est modifé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            String sql = "select * from reclamation";
            Statement ste = conn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Reclamation r = new Reclamation(s.getInt(1), s.getDate(2).toLocalDate(),
                        s.getString("titre"), s.getString("description"));
                reclamations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    @Override
    public void supprimerReclamation(int id_rec) {
         
        String sql = "DELETE from reclamation where id_rec= '"+id_rec+"' "; 
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("Reclamation supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    
    }
    
    private List<Reclamation> getListeReclamations() {
        List<Reclamation> reclamations = new ArrayList<>();
        
        return reclamations;
    }
    
    
    
    public Reclamation findById(int id) {
    
        List<Reclamation> reclamations = getListeReclamations();
        
        
        for (Reclamation r : reclamations) {
            if (id== r.getId_rec()) {
                return r;
            }
        }
        
        // Si on n'a pas trouvé de réclamation avec cet ID, on retourne null
        return null;
    }

    @Override
    public List<Reclamation> displayByID(int id_rec) {
     
   List <Reclamation> myList= new ArrayList<>();

    String req="select * from reclamation where id_rec='"+id_rec+"' ";

    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(req);
        while(rs.next()){
        
                      Reclamation o =new Reclamation ();
                      
                      o.setId_rec(rs.getInt("id_rec"));
                      o.setDate_rec(rs.getDate("date_rec").toLocalDate());
                      o.setTitre(rs.getString("titre"));
                      o.setDescription(rs.getString("description"));
                      myList.add(o);

        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;

    
    }
    
}
