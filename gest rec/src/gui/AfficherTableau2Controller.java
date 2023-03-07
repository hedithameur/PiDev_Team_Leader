/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceComm;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import entites.Commentaire_Rec;
//import entites.Reclamation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherTableau2Controller implements Initializable {
ServiceComm sc = new ServiceComm();
Commentaire_Rec c=new Commentaire_Rec();
private ObservableList<Commentaire_Rec> commentaires;
    @FXML
    private Button ret;
    @FXML
    private TableView<Commentaire_Rec> tab;
    @FXML
    private TableColumn<Commentaire_Rec, Integer> idc;
    @FXML
    private TableColumn<Commentaire_Rec, String> idd;
    @FXML
    private TableColumn<Commentaire_Rec, Integer> idr;
    @FXML
    private TableColumn<Commentaire_Rec, String> idco;
    @FXML
    private Button supprimer;
    @FXML
    private Button modif;
    @FXML
    private Button idreset;
    @FXML
    private DatePicker iddate;
    @FXML
    private TextArea idcontenu;
    @FXML
    private TextField idid;
    @FXML
    private TextField idrech;
    @FXML
    private Button idreset2;
    @FXML
    private Button idpdf1;
    @FXML
    private Button idtri1;
    @FXML
    private Button idtri2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Commentaire_Rec> commentaires = sc.recuperer();
             ObservableList<Commentaire_Rec> listrec = FXCollections.observableArrayList(commentaires);  
             idc.setCellValueFactory(new PropertyValueFactory<>("id_comm"));
             idd.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
             idco.setCellValueFactory(new PropertyValueFactory<>("contenu"));
             idr.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
             tab.setItems(listrec);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          List<Commentaire_Rec> commentaires;
        try {
            commentaires = sc.recuperer();
              ObservableList<Commentaire_Rec> listrec = FXCollections.observableArrayList(commentaires); 
FilteredList<Commentaire_Rec> filteredList = new FilteredList<>(listrec);

// Configurer le prédicat en fonction de la saisie de l'utilisateur
idrech.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredList.setPredicate(com -> {
        if (newValue == null || newValue.isEmpty()) {
            // Si le champ de texte est vide, afficher tous les éléments
            return true;
        }

        // Vérifier si le texte de recherche correspond à l'un des champs de la réclamation
        String lowerCaseFilter = newValue.toLowerCase();
         
       if (com.getContenu().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Correspondance trouvée dans le champ Titre
        } 
       else if (com.getDate_comm().toString().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Correspondance trouvée dans le champ Titre
        } 
        return false; // Pas de correspondance trouvée
    });
});

// Attacher le FilteredList à la TableView pour afficher les données filtrées
tab.setItems(filteredList);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

    }    
        // TODO

    @FXML
    private void retour(ActionEvent event) {
         try{
         Parent loader = FXMLLoader.load(getClass().getResource("commenter.fxml"));
            ret.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
    }

    @FXML
    private void supp(ActionEvent event) {
         Commentaire_Rec com= tab.getSelectionModel().getSelectedItem();
        
        if (com==null) { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur un commentaire !");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "voulez Vous  vraiment supprimer ce commentaire :" +c.getId_comm() +" ?");
                alert.setHeaderText(null);
            //
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.isPresent() && result.get() == ButtonType.OK) {
                sc.supprimerCommentaire_Rec(com.getId_comm());
                
                commentaires = FXCollections.observableList(sc.getAll());
                tab.setItems(commentaires);
        }
    
    }
    }

    @FXML
    private void modifier(ActionEvent event) {
         if (iddate.getValue()==null||idid.getText().isEmpty()||idcontenu.getText().isEmpty()) {
            
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Modification non effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        else{
            c.setDate_comm(iddate.getValue());
            c.setContenu(idcontenu.getText());
            c.setId_rec(Integer.parseInt(idid.getText()));
            
            sc.modifierCommentaire_Rec(c);
            
            afftab();
            reset();
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("OK");
            alert.setHeaderText("Modification effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
            commentaires = FXCollections.observableList(sc.getAll());
                tab.setItems(commentaires);
           
        }
    
                
                
    
    }

    

    @FXML
    private void reset() {
         iddate.setValue(null); 
         idcontenu.setText(""); 
          idid.setText(""); 
    }

    @FXML
    private void handlemouse1(MouseEvent event) {
        c = tab.getSelectionModel().getSelectedItem();
        idcontenu.setText(""+ c.getContenu());
        idid.setText("" + Integer.toString(c.getId_rec()));
        iddate.setValue(c.getDate_comm());
    }

    @FXML
    private void afftab() {
    }

    @FXML
    private void reset2(ActionEvent event) {
        
        
        idrech.setText(""); 
        
         commentaires = FXCollections.observableList(sc.getAll());
                tab.setItems(commentaires);
        
          List<Commentaire_Rec> commentaires;
        try {
            commentaires = sc.recuperer();
              ObservableList<Commentaire_Rec> listrec = FXCollections.observableArrayList(commentaires); 
FilteredList<Commentaire_Rec> filteredList = new FilteredList<>(listrec);

// Configurer le prédicat en fonction de la saisie de l'utilisateur
idrech.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredList.setPredicate(com -> {
        if (newValue == null || newValue.isEmpty()) {
            // Si le champ de texte est vide, afficher tous les éléments
            return true;
        }

        // Vérifier si le texte de recherche correspond à l'un des champs de la réclamation
        String lowerCaseFilter = newValue.toLowerCase();
         
       if (com.getContenu().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Correspondance trouvée dans le champ Titre
        } 
       else if (com.getDate_comm().toString().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Correspondance trouvée dans le champ Titre
        } 
        return false; // Pas de correspondance trouvée
    });
});
tab.setItems(filteredList);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

    }

    @FXML
    private void pdf1(ActionEvent event) throws IOException, DocumentException{
         Commentaire_Rec com= tab.getSelectionModel().getSelectedItem();
        
        if (com==null) { Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur un commentaire !");
            alert.showAndWait();                 
        } else {
          Commentaire_Rec c = new Commentaire_Rec();
        c= tab.getSelectionModel().getSelectedItem();
        Document document = new Document();
        if (idc.getText().isEmpty()||idd.getText().isEmpty() ||idco.getText().isEmpty() ||idr.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Impression non effectue");
            alert.setContentText("Click Cancel to exit.");
            alert.showAndWait();
        }else{
          // PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "/Desktop/Commentaire.pdf"));
PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Commentaire.pdf"));
            document.open();
            com.itextpdf.text.Font font = new com.itextpdf.text.Font();
            font.setSize(20);
            font.setColor(BaseColor.RED);
            font.isBold();
            font.isItalic();

            com.itextpdf.text.Font fontSmall = new com.itextpdf.text.Font();
            fontSmall.setSize(8);
            document.add(new LineSeparator());

            document.add(new Paragraph("Commentaire Reclamation", font));
            document.add(new Paragraph(" "));

            document.add(new LineSeparator());

            document.add(new Paragraph("Id Commentaire : " + c.getId_comm()));
                document.add(new Paragraph("Date de Commentaire : " + c.getDate_comm()));
            document.add(new Paragraph("Contenu : " + c.getDate_comm()));
            document.add(new Paragraph("Id Reclamation à commenter : " + c.getId_rec()));
            
           
            document.add(new Paragraph(" "));

            document.newPage();
            document.close();

            writer.close();

            Desktop.getDesktop().open(new File("Commentaire.pdf"));
        
    }
    }
}

    

    @FXML
    private void trierpardate(ActionEvent event) {
        List<Commentaire_Rec> commentaire = sc.getAll();
        ObservableList<Commentaire_Rec> listcommentaires = FXCollections.observableArrayList(commentaire);  
        
        idc.setCellValueFactory(new PropertyValueFactory<>("id_comm"));
        idd.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
      idco.setCellValueFactory(new PropertyValueFactory<>("contenu"));
          idr.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
       tab.setItems(listcommentaires);
 Comparator <Commentaire_Rec> comparator = Comparator.comparing(Commentaire_Rec::getDate_comm);
  tab.getItems().sort(comparator);
    }

    @FXML
    private void trierparcontenu(ActionEvent event) {
        List<Commentaire_Rec> commentaire = sc.getAll();
        ObservableList<Commentaire_Rec> listcommentaires = FXCollections.observableArrayList(commentaire);  
        
        idc.setCellValueFactory(new PropertyValueFactory<>("id_comm"));
        idd.setCellValueFactory(new PropertyValueFactory<>("date_comm"));
      idco.setCellValueFactory(new PropertyValueFactory<>("contenu"));
          idr.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
       tab.setItems(listcommentaires);
 Comparator <Commentaire_Rec> comparator = Comparator.comparing(Commentaire_Rec::getContenu);
  tab.getItems().sort(comparator);
    }
    
}

   