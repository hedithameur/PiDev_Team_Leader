/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pidev.entity.music;
import pidev.services.musicService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatController implements Initializable {
musicService ms = new musicService();
    @FXML
    private PieChart pieChart;
    @FXML
    private Label statusLabel;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<music> musics = ms.getAll();

        // Générer les données du PieChart
        List<PieChart.Data> data = new ArrayList<>();
        for (music m : musics) {
            // Vérifier si l'artiste est déjà dans la liste
            boolean artistExists = false;
            for (PieChart.Data d : data) {
                if (d.getName().equals(m.getNom_artiste())) {
                    artistExists = true;
                    d.setPieValue(d.getPieValue() + 1);
                    break;
                }
            }
            // Si l'artiste n'est pas dans la liste, l'ajouter
            if (!artistExists) {
                data.add(new PieChart.Data(m.getNom_artiste(), 1));
            }
        }

        // Ajouter les données au PieChart
        pieChart.setData(FXCollections.observableArrayList(data));
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
               try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("MusicPlaylist.fxml"));
            retour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
