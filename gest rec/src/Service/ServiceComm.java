/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import entites.Commentaire_Rec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnection;

/**
 *
 * @author user
 */
public class ServiceComm implements IServiceCommentaire_Rec<Commentaire_Rec>{
    private Connection conn;
    PreparedStatement stmt;
    PreparedStatement ste;

    public ServiceComm() {
         conn = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterCommentaire_Rec(Commentaire_Rec c) {
         String sql ="insert into commentaire_rec (id_comm,date_comm,contenu,id_rec) Values(?,?,?,?)";
        
        try
        {
           ste=conn.prepareStatement(sql);
           ste.setInt(1, c.getId_rec());
           ste.setDate(2,Date.valueOf(c.getDate_comm()));
          ste.setString(3,c.getContenu());
           ste.setInt(4,c.getId_rec());

           ste.executeUpdate();
           System.out.println("Commentaire Ajoutée");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }   
       
    }

    @Override
    public List<Commentaire_Rec> afficherCommentaire_Rec() {
         List<Commentaire_Rec> reclamation =  new ArrayList<>();
      String sql="select * from commentaire_rec";
      
      try
      {
          ste=conn.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      Commentaire_Rec o = new Commentaire_Rec();
                      o.setId_rec(rs.getInt("id_comm"));
                      o.setDate_comm(rs.getDate("date_rec").toLocalDate());
                      o.setContenu(rs.getString("contenu"));
                      o.setId_rec(rs.getInt("id_rec"));
               
                      reclamation.add(o);
                      System.out.println("id commentaire : "+o.getId_comm()+ "\n Date de Commentaire : "+o.getDate_comm().toString()+ "\n contenu : "+o.getContenu()+"\n id_rec: " +o.getId_rec()) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return reclamation;
    }

    @Override
    public List<Commentaire_Rec> recuperer() throws SQLException {
        List<Commentaire_Rec> commentaires = new ArrayList<>();
        String s = "select * from commentaire_rec";
        Statement st = conn.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Commentaire_Rec r = new Commentaire_Rec();
            r.setId_comm(rs.getInt("id_comm"));
            r.setDate_comm(rs.getDate("Date_comm").toLocalDate());
            r.setContenu(rs.getString("contenu"));
            r.setId_rec(rs.getInt("id_rec"));
           
            
            
            commentaires.add(r);
            
        }
        return commentaires;
    }

    @Override
    public void modifierCommentaire_Rec(Commentaire_Rec c) {
       try {
          
         String req = "UPDATE `commentaire_rec` SET  `id_comm`='" + c.getId_comm()+ "',`date_comm`='" + Date.valueOf(c.getDate_comm())+
                 "',`contenu`='" + c.getContenu()+ "', `id_rec`='" + c.getId_rec()+
                 "' WHERE id_rec=" + c.getId_comm();      
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Le commentaire  est modifé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public void supprimerCommentaire_Rec(int id_comm) {
         String sql = "DELETE from commentaire_rec where id_comm= '"+id_comm+"' "; 
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("Commentaire supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    private List<Commentaire_Rec> getListeCommentaires() {
        List<Commentaire_Rec> commentaires = new ArrayList<>();
        
        return commentaires;
    }
    
    
    
    public Commentaire_Rec findById(int id) {
    
        List<Commentaire_Rec> commentaires = getListeCommentaires();
        
        
        for (Commentaire_Rec r : commentaires) {
            if (id== r.getId_comm()) {
                return r;
            }
        }
        
        // Si on n'a pas trouvé de réclamation avec cet ID, on retourne null
        return null;
    }

    @Override
    public List<Commentaire_Rec> displayByID(int id_comm) {
         List <Commentaire_Rec> myList= new ArrayList<>();

    String req="select * from commentaire_rec where id_comm='"+id_comm+"' ";

    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(req);
        while(rs.next()){
        
                      Commentaire_Rec o =new Commentaire_Rec ();
                      
                      o.setId_comm(rs.getInt("id_comm"));
                      o.setDate_comm(rs.getDate("date_comm").toLocalDate());
                      o.setContenu(rs.getString("contenu"));
                      o.setId_rec(rs.getInt("id_rec"));
                      myList.add(o);

        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;

        
    }
    public List<Commentaire_Rec> getAll() {
        List<Commentaire_Rec> commentaires = new ArrayList<>();
        try {
            String sql = "select * from commentaire_rec";
            Statement ste = conn.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Commentaire_Rec c = new Commentaire_Rec(s.getInt(1), s.getDate(2).toLocalDate(),
                        s.getString("contenu"), s.getInt(4));
                commentaires.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commentaires;
    }
    public List<Integer> getid() {
        List<Integer> ids = new ArrayList<>();
        try {
            String sql = "select id_comm from commentaire_rec";
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

    
}
