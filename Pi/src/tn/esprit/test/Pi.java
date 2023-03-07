/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;


import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entity.CategorieInstrument;
import tn.esprit.entity.Commande_instruments;
import tn.esprit.entity.Instrument;
import tn.esprit.entity.Utilisateur;
import tn.esprit.service.ServiceCategorie;
import tn.esprit.service.ServiceCommande;
import tn.esprit.service.ServiceInstrument;

import tn.esprit.tools.Connexion;
import tn.esprit.test.Pi;

/**
 *
 * @author bouzi
 */
public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        
 ServiceCategorie cc =new ServiceCategorie();
 CategorieInstrument c11 = new CategorieInstrument(6, "piano", "jdjdj");
  CategorieInstrument c12 = new CategorieInstrument(6, "piano", "jdjdj");
  CategorieInstrument c13 = new CategorieInstrument(14, "yayay", "jdjdj");
  //cc.ajouter(c13);
  ServiceInstrument  sc = new ServiceInstrument();
  Instrument c1 = new Instrument(115,"dd", 400, "dd","gb",c13);
  
 //sc.ajouter(c1);
  ServiceCommande ss = new  ServiceCommande (); 
  LocalDate date_commande = LocalDate.now();
   Commande_instruments s1 =new  Commande_instruments ( "piano",400,date_commande);
 //ss.ajouter(s1);
        System.out.println(ss.getAllInstruments());
    

//sc.ajouter(c1);
        //System.out.println(sc.getAllInstruments());  
//sc.getAllInstruments();
        //cc.findCatById(10);
  //cc.ajouter(c11);
 //cc.supprimer(c13);
 
         }

    
LocalDate date = LocalDate.of(2023, 7, 22);

    //Commande_instruments c1 = new Commande_instruments(3, 1, 1, date );
    //cc.ajouter(c1);
    
           
        }
               
    
    
    

