/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Service.ServiceReclamation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import entites.Reclamation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author user
 */


public class AfficherTableController implements Initializable {
    // private final ObservableList<Reclamation> dataList = FXCollections.observableArrayList();
    
    ServiceReclamation sr = new ServiceReclamation();
     Reclamation r=new Reclamation();
     ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("date","titre", "description","tel");
    static Reclamation selectionedReclamation;
     @FXML
    private TableView<Reclamation> TabRec;
    @FXML
    private TableColumn<Reclamation,Integer > idcolumn;
    @FXML
    private TableColumn<Reclamation,String> datecolumn;
    @FXML
    private TableColumn<Reclamation,String> titrecolumn;
    @FXML
    private TableColumn<Reclamation,String> descrcolumn;
    @FXML
    private Button idretour;
    @FXML
    private Button idcomm;
    @FXML
    private Button idsu;
    private ObservableList<Reclamation> reclamations;
    @FXML
    private Button modif;
    @FXML
    private TextField idtitre;
    @FXML
    private TextArea iddes;
    @FXML
    private DatePicker iddate;
    @FXML
    private Button idreset;
    @FXML
    private HBox hbox;
    @FXML
    private ComboBox<String> typeRecherche;
    @FXML
    private TextField RechercheTextField;
    @FXML
    private Button search;
    @FXML
    private Button reset;
    @FXML
    private Button idpdf;
    @FXML
    private Button idtri;
    @FXML
    private TableColumn<Reclamation,String> idtel;
    @FXML
    private TextField telF;
    @FXML
    private Label idlabel20;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              try {
            List<Reclamation> reclamations = sr.recuperer();
             ObservableList<Reclamation> listrec = FXCollections.observableArrayList(reclamations);  
             idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
             datecolumn.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
             titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
             descrcolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
             idtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
             //telcolumn.setCellValueFactory(new PropertyValueFactory<>("tel"));
             
             TabRec.setItems(listrec);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                typeRecherche.setItems(listeTypeRecherche);
                typeRecherche.setValue(null);
                // Créer un objet FilteredList pour filtrer les données en fonction d'un prédicat
                List<Reclamation> reclamations;
        try {
            reclamations = sr.recuperer();
              ObservableList<Reclamation> listrec = FXCollections.observableArrayList(reclamations); 
FilteredList<Reclamation> filteredList = new FilteredList<>(listrec);

// Configurer le prédicat en fonction de la saisie de l'utilisateur
RechercheTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredList.setPredicate(reclamation -> {
        if (newValue == null || newValue.isEmpty()) {
            // Si le champ de texte est vide, afficher tous les éléments
            return true;
        }

        // Vérifier si le texte de recherche correspond à l'un des champs de la réclamation
        String lowerCaseFilter = newValue.toLowerCase();
         if (reclamation.getTitre().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Titre
        } else if (reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Description
        } 
         else if (reclamation.getDate_rec().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Description
            
        } 
          else if (reclamation.getTel().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Description
        } 
        else if (reclamation.getTel().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="tel") {
            return true;}
        else if (reclamation.getDate_rec().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="date") {
            return true;}

       else if (reclamation.getTitre().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="titre") {
            return true; // Correspondance trouvée dans le champ Titre
        } else if (reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="description") {
            return true; // Correspondance trouvée dans le champ Description
        } 
          
        
        return false; // Pas de correspondance trouvée
    });
});

// Attacher le FilteredList à la TableView pour afficher les données filtrées
TabRec.setItems(filteredList);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

                
                // Créer un objet ObservableList pour stocker les éléments du tableau

                
                /*  FilteredList<Reclamation> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		RechercheTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Reclamation.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Reclamation.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(TabRec.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		TabRec.setItems(sortedData);
               
                
             */   
        
    }    
           
        

    @FXML
    private void retour(ActionEvent event) throws IOException {
        try{
         Parent loader = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
            idretour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
    
    }
    @FXML
    private void commenter(ActionEvent event) {
         selectionedReclamation = TabRec.getSelectionModel().getSelectedItem();
       
      if (selectionedReclamation==null) { Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une reclamation !");
            alert.showAndWait();   }

//        Stage stageModifier = new Stage();
     
        else{
             try{
         Parent loader;
              loader = FXMLLoader.load(getClass().getResource("commenter.fxml"));
            idcomm.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
}
        }

         
    }
    

    @FXML
    private void supp(ActionEvent event) {
        Reclamation rec= TabRec.getSelectionModel().getSelectedItem();
        
        if (rec==null) { Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une reclamation !");
            alert.showAndWait();                 
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "voulez Vous  vraiment supprimer cette reclamation :" +rec.getId_rec()+" ?");
                alert.setHeaderText(null);
            //
            Optional<ButtonType> result = alert.showAndWait();
            
            if (result.isPresent() && result.get() == ButtonType.OK) {
                sr.supprimerReclamation(rec.getId_rec());
                
                reclamations = FXCollections.observableList(sr.getAll());
                TabRec.setItems(reclamations);
        }
    
    }
    
    }

   

    @FXML
    private void handlemouse(MouseEvent event) {
        r = TabRec.getSelectionModel().getSelectedItem();
        idtitre.setText(""+ r.getTitre());
        iddes.setText("" + r.getDescription());
        iddate.setValue(r.getDate_rec());
         telF.setText("" + r.getTel());
        
        
    }

    @FXML
    private void affichetab() {
    }

    @FXML
    private void reset() {
         iddate.setValue(null); 
         idtitre.setText(""); 
          iddes.setText("");
          telF.setText("");
          
    }
     @FXML
    private void modifier(ActionEvent event) {
        if(telF.getText().length()!=8)
            idlabel20.setText("Num doit contenir 8 chiffres");
            
        
        
        else if (iddate.getValue()==null||iddes.getText().isEmpty()||idtitre.getText().isEmpty()||telF.getText().isEmpty()) {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("Modification non effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
        else{
            r.setDate_rec(iddate.getValue());
            r.setTitre(idtitre.getText());
            r.setDescription(iddes.getText());
              r.setTel(telF.getText());
              idlabel20.setText("");
            
            sr.modifierReclamation(r);
            
            affichetab();
            reset();
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("OK");
            alert.setHeaderText("Modification effectué");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
           reclamations = FXCollections.observableList(sr.getAll());
                TabRec.setItems(reclamations);
        }
    
                
                   
    
    }

    @FXML
    private void list() {
          /*   ArrayList arrayList = null;
        ServiceReclamation produitService = new ServiceReclamation();
        if (typeRecherche == null) {
            typeRecherche = new ComboBox<String>();
        }
        typeRecherche.setOnAction(e -> list());

       
       if (!typeRecherche.getValue().equals("") && !RechercheTextField.getText().equals(""))
       { arrayList = (ArrayList) produitService.rechercheReclamations(typeRecherche.getValue(), RechercheTextField.getText());}
        //}

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TabRec.setItems(observableList);*/

    
    }

    @FXML
    private void search(ActionEvent event) {
         ArrayList arrayList = null;
        ServiceReclamation produitService = new ServiceReclamation();
        if (typeRecherche == null) {
            typeRecherche = new ComboBox<String>();
        }
        typeRecherche.setOnAction(e -> list());

          if (RechercheTextField.getText().isEmpty()) {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("il faut ecrire ta recherche");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
        }
          else if (typeRecherche.getValue().isEmpty()) {
            
         Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("NOT OK");
            alert.setHeaderText("il faut choisir un type");
            alert.setContentText("Click cancel to exit.");
            alert.showAndWait();
    }
        else{
       if (!typeRecherche.getValue().equals("") && !RechercheTextField.getText().equals(""))
       { arrayList = (ArrayList) produitService.rechercheReclamations(typeRecherche.getValue(), RechercheTextField.getText());}
        //}

        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TabRec.setItems(observableList);
    }
    }
    @FXML
    private void reset2(ActionEvent event) {
                RechercheTextField.setText(""); 
         typeRecherche.setValue(null);
         reclamations = FXCollections.observableList(sr.getAll());
                TabRec.setItems(reclamations);
                 List<Reclamation> reclamations;
        try {
            reclamations = sr.recuperer();
              ObservableList<Reclamation> listrec = FXCollections.observableArrayList(reclamations); 
FilteredList<Reclamation> filteredList = new FilteredList<>(listrec);

// Configurer le prédicat en fonction de la saisie de l'utilisateur
RechercheTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    filteredList.setPredicate(reclamation -> {
        if (newValue == null || newValue.isEmpty()) {
            // Si le champ de texte est vide, afficher tous les éléments
            return true;
        }

        // Vérifier si le texte de recherche correspond à l'un des champs de la réclamation
        String lowerCaseFilter = newValue.toLowerCase();
         if (reclamation.getTitre().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Titre
        } else if (reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Description
        } 
        else if (reclamation.getDate_rec().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Description
        } 
         else if (reclamation.getTel().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()==null) {
            return true; // Correspondance trouvée dans le champ Description
        } 
        else if (reclamation.getTel().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="tel") {
            return true;}
        else if (reclamation.getDate_rec().toString().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="date") {
            return true;}

        
       else if (reclamation.getTitre().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="titre") {
            return true; // Correspondance trouvée dans le champ Titre
        } else if (reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)&&typeRecherche.getValue()=="description") {
            return true; // Correspondance trouvée dans le champ Description
        } 
          
        
         
        return false; // Pas de correspondance trouvée
    });
});

// Attacher le FilteredList à la TableView pour afficher les données filtrées
TabRec.setItems(filteredList);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
          
    }

    @FXML
    private void ecrire(ActionEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event)throws IOException, DocumentException {
        
           Document document=new  Document() {};
           //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Reclamation.pdf"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "/Desktop/Reclamation.pdf"));
         //PdfWriter.getInstance(document, new FileOutputStream("Reclamation.pdf"));
        document.open();
        com.itextpdf.text.Font font = new com.itextpdf.text.Font();
            font.setSize(20);
            font.setColor(BaseColor.BLUE);
            font.isBold();
            font.isItalic();
            com.itextpdf.text.Font font1 = new com.itextpdf.text.Font();
            font1.setSize(15);
            font1.setColor(BaseColor.RED);
            font1.isBold();
            
      
        ServiceReclamation m=new ServiceReclamation();
        List<Reclamation> list=m.afficherReclamation();    
        document.add(new Paragraph("La liste des Reclamation :",font));
        document.add(new Paragraph("     "));
        int i=0;
         for(Reclamation r:list)
             
        {
         i+=1;
         document.add(new Paragraph("     "));
         document.add(new Paragraph("Reclamation num° " + i +":",font1));
          
        document.add(new Paragraph("Id Reclamation : " + r.getId_rec()));
                document.add(new Paragraph("Date de Reclamation : " + r.getDate_rec()));
            document.add(new Paragraph("Titre : " + r.getTitre()));
            document.add(new Paragraph("Description : " + r.getDescription()));
             document.add(new Paragraph("Num Tel : " + r.getTel()));

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        
        }
         
         document.add(new Paragraph("ily'a " + i + " Reclamation(s)",font));
      document.newPage();
            document.close();

            writer.close();

            Desktop.getDesktop().open(new File("Reclamation.pdf"));
            
        }
        
    

    

    @FXML
    private void tri(ActionEvent event) {
         List<Reclamation> reclamation = sr.getAll();
        ObservableList<Reclamation> listreclamations = FXCollections.observableArrayList(reclamation);  
        
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
      titrecolumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
          descrcolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
          idtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
       TabRec.setItems(listreclamations);
 Comparator <Reclamation> comparator = Comparator.comparing(Reclamation::getDate_rec).thenComparing(Reclamation::getTitre).thenComparing(Reclamation::getDescription).thenComparing(Reclamation::getTel);
  TabRec.getItems().sort(comparator);
    }

}
