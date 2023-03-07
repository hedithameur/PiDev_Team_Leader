/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import javax.swing.JComboBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Double.parseDouble;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import pidev.entity.CategorieInstrument;
import pidev.entity.Instrument;
import pidev.services.ServiceCategorie;
import pidev.services.ServiceInstrument;
import pidev.tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author bouzi
 */
public class InstrumentController implements Initializable {

    ServiceInstrument ps = new ServiceInstrument();
    Instrument p = new Instrument();
    ServiceCategorie m = new ServiceCategorie();
    private ObservableList<Instrument> categorie;
    @FXML
    private TextField nomid;
    @FXML
    private TextField descriptionid;
    @FXML
    private AnchorPane ajouterbtn;
    @FXML
    private Button Ajouterbtn;
    @FXML
    private TextField prixid;
    @FXML
    private Label control;
    private TextField categorieid;
    @FXML
    private TableView<Instrument> tableaucategorie;
    @FXML
    private TableColumn<Instrument, String> idcolumn;
    @FXML
    private TableColumn<Instrument, String> nomcolumn;
    @FXML
    private TableColumn<Instrument, Float> prixcolumn;
    @FXML
    private TableColumn<Instrument, String> descriptioncolumn;
    private TableColumn<CategorieInstrument, String> idccolumn;
    private TableColumn<Instrument, String> nomjcolumn;
    @FXML
    private Text idtext;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button supprimerid;
    private ObservableList<Instrument> inst;
    @FXML
    private ComboBox<String> idcombo;
    private TableColumn<CategorieInstrument, String> categoriecolumn;
    Connection cnx;
    @FXML
    private Label labeleaffiche;

    @FXML
    private TableColumn<Instrument, String> photocolumn;
    @FXML
    private Button importcolumn;
    @FXML
    private Text idt;
    @FXML
    private Text yar;
    @FXML
    private TableColumn<CategorieInstrument, String> idcc;
    @FXML
    private Button statid;
    @FXML
    private Button acc;
    @FXML
    private ImageView img;
    @FXML
    private TextField textsearch;
    @FXML
    private ImageView imageqr;
    @FXML
    private Button butonnqr;

    public InstrumentController() {
        cnx = MaConnection.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        aff();
        try {
            List<Instrument> categorie = ps.newaffciher();
            ObservableList<String> options = FXCollections.observableArrayList(m.getnom());
            idcombo.setItems(options);
            String selectednom = idcombo.getValue();

        } catch (SQLException ex) {
            Logger.getLogger(InstrumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (nomid.getText().isEmpty() || prixid.getText().isEmpty() || descriptionid.getText().isEmpty()  || importcolumn.getText().isEmpty() ) {;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("ajouter des champs");

            alert.showAndWait();
        } else {
            String categorieSelectionnee = idcombo.getValue();
            String SqlGetIdCat = "select id from categorie_instrument where nom_categorie='" + categorieSelectionnee + "'";
            Statement ste;

            try {
                ste = cnx.createStatement();

                ResultSet c = ste.executeQuery(SqlGetIdCat);
                ObservableList<Integer> IDlisteC = FXCollections.observableArrayList();
                while (c.next()) {
                    int IDD_C = c.getInt("id");
                    IDlisteC.add(IDD_C);
                }
                int FINAL_ID_C = IDlisteC.get(0);

                System.out.println(FINAL_ID_C);
                //id de 
                CategorieInstrument cc = new CategorieInstrument();
                cc.setId(FINAL_ID_C);
                System.out.println(FINAL_ID_C);

                p.setCategorie(cc);
                p.setNom(nomid.getText());
                p.setPrix(Float.parseFloat(prixid.getText()));;
                p.setPhoto(yar.getText());
                p.setDescription(descriptionid.getText());

                ps.ajouterphoto(p);
                aff();

                reset();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("msg");
                alert.setHeaderText("succés");

                alert.showAndWait();
                //categorie = FXCollections.observableList(ps.getAllInstruments());
                //tableaucategorie.setItems(categorie);
            } catch (SQLException ex) {
                Logger.getLogger(InstrumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void reset() {
        nomid.setText("");
        prixid.setText("");
        yar.setText("");
        descriptionid.setText("");

    }

    @FXML
    private void mouseclicked(MouseEvent event) {
        p = tableaucategorie.getSelectionModel().getSelectedItem();
        nomid.setText("" + p.getNom());
        prixid.setText("" + p.getPrix());
        yar.setText("" + p.getPhoto());
        descriptionid.setText("" + p.getDescription());
        idt.setText("");

    }

    @FXML
    private void modifier(ActionEvent event) {

        p.setNom(nomid.getText());
        p.setPrix(Float.parseFloat(prixid.getText()));
        p.setDescription(descriptionid.getText());
        ps.modifier(p.getNom(), p.getPrix(), p.getDescription(), p);
        aff();
        reset();
        categorie = FXCollections.observableList(ps.getAllInstruments());
        tableaucategorie.setItems(categorie);

    }

    @FXML
    private void supprimer(ActionEvent event) {
        Instrument crt = tableaucategorie.getSelectionModel().getSelectedItem();

        if (crt == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Cliquez sur une reclamation table!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "voulez Vous  vraiment supprimer cette reclamation :" + crt.getId_instrument() + " ?");
            alert.setHeaderText(null);
            //Getting Buttons
            Optional<ButtonType> result = alert.showAndWait();
            //Testing if the user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ps.supprimer(crt);
                //updating user data after closing popup
                inst = FXCollections.observableList(ps.getAllInstruments());
                tableaucategorie.setItems(inst);
            }

        }
    }

    @FXML
    private void aff() {
        List<Instrument> cat = ps.getAllInstruments();

        ObservableList<Instrument> list = FXCollections.observableArrayList(cat);// convertir list to ObservableList fiha iterator
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id_instrument"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prixcolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        photocolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        idcc.setCellValueFactory(new PropertyValueFactory<>("cat_nom"));

        tableaucategorie.setItems(list);
        textsearch.setPromptText("recherche");
        FilteredList<Instrument> filteredInstrumentList = new FilteredList<>(list, Instrument -> true);
        textsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredInstrumentList.setPredicate(Instrument ->
                    Instrument.getNom().toLowerCase().contains(newValue.toLowerCase())
                    || Instrument.getDescription().toLowerCase().contains(newValue.toLowerCase())
            );
                    });
        tableaucategorie.setItems(filteredInstrumentList);
                }
    public void refresh() {
        List<Instrument> c = ps.newaffciher();
        ObservableList<Instrument> listCat = FXCollections.observableArrayList(c);// convertir list to ObservableList fiha iterator
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcolumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        photocolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
        descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        tableaucategorie.setItems(listCat);
    }

    @FXML
    private void ajouterphoto(ActionEvent event) throws FileNotFoundException, IOException  {
        FileChooser fch = new FileChooser();
        
        File f = fch.showOpenDialog(null);
        if (f != null) {

            //Commentaire.setText("Image selectionnée" + f.getAbsolutePath());
            InputStream is = null;
            OutputStream os = null;
            try {
                
                is = new FileInputStream(new File(f.getAbsolutePath()));
//             
                os = new FileOutputStream(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\Integration\\src\\pidev\\Images" + f.getName()));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                System.out.println("Done");

            } finally {
                is.close();
                os.close();

            }

            File file = new File("/Images/" + f.getName());
//            System.out.println(file.toURI().toString());
            //UserImg.setImage(new Image(file.toURI().toString()));
            String Imguser = f.getName();
           System.out.println(Imguser);
            //ImageName.setText(Imguser);
        } else if (f == null) {
            //Commentaire.setText("Erreur chargement de l'image");
            System.out.println("Erreur !");
        }
        
        
                yar.setText("/Images/" + f.getName());

   
    }

    
    @FXML
    private void newpage(ActionEvent event) {
        try {
            Parent loader;
            loader = FXMLLoader.load(getClass().getResource("statistique.fxml"));
            statid.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @FXML
    private void accueil(ActionEvent event) {
        try {
            Parent loader;
            loader = FXMLLoader.load(getClass().getResource("affichertableau.fxml"));
            acc.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void qrcode(ActionEvent event) throws WriterException {
         
        Instrument selectedPiece = tableaucategorie.getSelectionModel().getSelectedItem();
        if (selectedPiece != null) {
            String qrText = selectedPiece.getPhoto();
            createQR(qrText);
        } else {
            alert("Please select a row.");
        }
    }

    private void createQR(String qrText) throws WriterException {
        try {
            String path = System.getProperty("user.home") + File.separatorChar + "Desktop" + File.separatorChar + "qr_code.png";
            BitMatrix matrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
            MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
            alert("QR Code Created");
            setQRImage(path);
            //hl.setVisible(false);
        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    private void setQRImage(String path) {
        try {
            FileInputStream stream = new FileInputStream(path);
            Image image = new Image(stream);
            imageqr.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    }

