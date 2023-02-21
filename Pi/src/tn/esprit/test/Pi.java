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
 
 // cc.ajouter(c11);
 cc.modifier(c11);
 
         }

    
LocalDate date = LocalDate.of(2023, 7, 22);

    //Commande_instruments c1 = new Commande_instruments(3, 1, 1, date );
    //cc.ajouter(c1);
    
           
        }
               
    
    
    

