
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Utilisateur;
import tools.MaConnection;
import javax.swing.*;
import java.io.File;
import java.io.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileInputStream;
import java.io.InputStream;


public class UtilisateurService implements InterfService<Utilisateur> { 
  
   
   Connection cnx;

    public UtilisateurService() {
        cnx = MaConnection.getInstance().getCnx(); 
    }
   
    @Override
    public void ajouter(Utilisateur u) {
        try {
           String sql ="INSERT  INTO utilisateur(nom, prenom, role, telephone, email, mot_de_passe) VALUES (?,?,?,?,?,?)";
           PreparedStatement ste = cnx.prepareStatement(sql);
          
           ste.setString(1, u.getNom());
           ste.setString(2, u.getPrenom());
           ste.setString(3, u.getRole());
           ste.setInt(4, u.getTelephone());
           ste.setString(5, u.getEmail());
           ste.setString(6, u.getMot_de_passe());
           
           ste.executeUpdate();
           System.out.println("Utilisateur ajoutée");
        } 
        catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
    }
    

    @Override
    public List<Utilisateur> getAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try {
            String sql = "select * from utilisateur";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                
               ///
                
                Utilisateur u = new Utilisateur();
                utilisateurs.add(u);

            }
        } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    }

    @Override
    public List<Utilisateur> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void modifierUtilisateur(String nom,Utilisateur u) {
        String sql = "update utilisateur set nom=? where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom);
            ste.setInt(2,u.getId());
            ste.executeUpdate();
            System.out.println("Utilisateur modifié ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    public void supprimerUtilisateur(Utilisateur u) {
        String sql = "delete from utilisateur where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, u.getId());
            ste.executeUpdate();
            System.out.println("Utilisateur supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    
    
}

 
           
           
          



