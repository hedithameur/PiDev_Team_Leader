/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guid;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import tn.esprit.tools.Connexion;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart statid;
    private Statement st;
    private ResultSet rs;
    private Connection  cnx;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx=Connexion.getInstance().getCnx();
        stat();
    }
      private void stat()
    {
         try {
           
          String query = "SELECT COUNT(*),nom FROM instruments GROUP BY nom" ;
       
             PreparedStatement PreparedStatement = cnx.prepareStatement(query);
             rs = PreparedStatement.executeQuery();
            
                     
            while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("nom"),rs.getInt("COUNT(*)")));
            }     
        } catch (SQLException ex) {
            Logger.getLogger(InstrumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        statid.setTitle("Repartition des instruments");
        statid.setLegendSide(Side.LEFT);
        statid.setData(data);
    }
          
    
    @FXML
    private void funcact(MouseEvent event) {
       
    
    }
    
}
