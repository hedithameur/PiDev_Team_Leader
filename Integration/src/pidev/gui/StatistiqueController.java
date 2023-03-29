/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import pidev.tools.MaConnection;

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
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cnx = MaConnection.getInstance().getCnx();
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

    @FXML
    private void backToMenu(ActionEvent event) {
              try {
            //navigation
                   Parent loader = FXMLLoader.load(getClass().getResource("../Integration/IntegrationAceuil.fxml"));
            back.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
