/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import pidev.entity.Reclamation;
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
import pidev.tools.MaConnection;

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
             
        String sql ="insert into reclamation (id_rec,date_rec,titre,description,tel) Values(?,?,?,?,?)";
        
        try
        {
           ste=conn.prepareStatement(sql);
           ste.setInt(1, c.getId_rec());
           ste.setDate(2,Date.valueOf(c.getDate_rec()));
          ste.setString(3,c.getTitre());
           ste.setString(4,c.getDescription());
           ste.setString(5,c.getTel());

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
        //while(rs.next()){
        //i=0
      //i++
              
        String s = "select * from reclamation";/*String s = "select email from utilisateur where id="+ i;*/
        Statement st = conn.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            //String t=new 
            Reclamation r = new Reclamation();
            r.setId_rec(rs.getInt("id_rec"));
            r.setDate_rec(rs.getDate("Date_rec").toLocalDate());
            r.setTitre(rs.getString("titre"));
            r.setDescription(rs.getString("description"));
            r.setTel(rs.getString("tel"));
           
            
            
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
                       o.setTel(rs.getString("tel"));
                      
               
                      reclamation.add(o);
                      System.out.println("id reclamation : "+o.getId_rec()+ "\n Date de Reclamation : "+o.getDate_rec().toString()+ "\n Titre : "+o.getTitre()+"\n Descrption: " +o.getDescription()+ "\n Tel : "+o.getTel()) ;
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
                 "',`titre`='" + e.getTitre()+ "', `description`='" + e.getDescription()+ "', `tel`='" + e.getTel()+
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
                        s.getString("titre"), s.getString("description"),s.getString("tel"));
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
                       o.setTel(rs.getString("tel"));
                      myList.add(o);

        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;

    
    }
      public List<Reclamation> rechercheReclamations(String type, String valeur) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("titre")) {
                requete = "SELECT * from Reclamation where titre like '%" + valeur + "%'"; 
            } else if (type.equals("description")) {
                requete = "SELECT * from Reclamation where description like '%" + valeur + "%'"; 
           
            }
            else if (type.equals("tel")) {
                requete = "SELECT * from Reclamation where tel like '%" + valeur + "%'"; }
            else if (type.equals("date_rec")) {
                requete = "SELECT * from Reclamation where date_rec like '%" + valeur + "%'"; 
           
            }
            else if (type.equals("Tout")) {
                requete = "SELECT * from Reclamation where titre like '%" + valeur + "%' or description like '%" + valeur + "%' or date_rec like '%" + valeur+ "%' "+ "%' or tel like '%" + valeur+ "%' "; 
           }

            Statement pst = MaConnection.getInstance().getCnx().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setId_rec(rs.getInt(1));
                R.setDate_rec(rs.getDate("date_rec").toLocalDate());
                R.setTitre(rs.getString(3));
                R.setDescription(rs.getString(4));
                R.setTel(rs.getString(5));
               
//              R.setDate_rec(rs.getDate(8));
              /* if (rs.getDate(9) != null) {
                    Date a = new Date(((Timestamp) rs.getObject(9)).getTime());
                    R.setDate_creation(a);
                } else {
                    R.setDate_creation(rs.getDate(9));
                }*/

                
               
              

//                if(rs.getDate(17)!=null) // test temps dans date
//                {System.out.println(new Date(((Timestamp)rs.getObject(17)).getTime()));
//                System.out.println((Timestamp)rs.getObject(17));
//      SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//      System.out.println("Current Date: " + ft.format(new Date(((Timestamp)rs.getObject(17)).getTime())));
//                }
//                R.setDate_disponibilite(rs.getDate(17));
                myList.add(R);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
   /* public List<Reclamation> recupereremail() throws SQLException {
        List<String> emails = new ArrayList<>();
         String s = "select email from utilisateur where id=";
        Statement st = conn.createStatement();
        ResultSet rs =  st.executeQuery(s);
        
       
        
        while(rs.next()){
            //String t=new 
            Reclamation r = new Reclamation();
            r.setId_rec(rs.getInt("id_rec"));
            r.setDate_rec(rs.getDate("Date_rec").toLocalDate());
            r.setTitre(rs.getString("titre"));
            r.setDescription(rs.getString("description"));
           
            
            
            emails.add(t);
            
        }
        return emails;
    }*/
    
}
