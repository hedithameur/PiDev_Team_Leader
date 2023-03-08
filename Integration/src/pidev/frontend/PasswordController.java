
package pidev.frontend;


import java.io.IOException;
import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pidev.services.UtilisateurService;
import pidev.tools.MaConnection;


public class PasswordController implements Initializable {

    @FXML
    private Button email;
    
    Connection cnx;
    String sql;
    Message message;
    
    @FXML
    private TextField textemail3;
    String sql1; 
    @FXML
    private Button retour;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    }   
    
     
    public static void main(String[] args){
        
      launch(args);
    }
    private void sendEmail(String recipient, String newPassword) {

        // Informations de connexion au serveur SMTP
        String host = "smtp.gmail.com";
        String username = "pidevpidev612@gmail.com"; // Adresse Gmail à partir de laquelle l'email sera envoyé
        String password = "jcgcysqftxaqgewn";
        int port = 587;

        // Propriétés pour la configuration de la connexion SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Crée une session SMTP avec les informations de connexion et d'authentification
        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                      }
        });

    try {
        // Crée un message électronique
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(recipient));
        message.setSubject("Password Reset");
        message.setText("Your new password is: " + newPassword);

        // Envoie le message électronique
        Transport.send(message);

    } catch (MessagingException e) {
        // En cas d'erreur, affiche un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Email Error");
        alert.setHeaderText("Failed to send email.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
    
    @FXML
    
    private void sendemail(ActionEvent event) throws SQLException, MessagingException {
       // Vérifie si l'email est valide
        if (!isValidEmail(textemail3.getText())) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Invalid Email");
            alert.setHeaderText("Please enter a valid email address.");
            alert.showAndWait();
            return;
        }

        // Génère un nouveau mot de passe aléatoire
        String newPass = generateRandomPassword();

        // Envoie l'email avec le nouveau mot de passe
        sendEmail(textemail3.getText(), newPass);

        // Met à jour le mot de passe dans la base de données
        updatePasswordInDatabase(textemail3.getText(), newPass);

        // Affiche un message de confirmation
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Password Reset");
        alert.setHeaderText("A new password has been sent to your email address.");
        alert.showAndWait();
    }
    
    

    
    private static Message prepareMessage(javax.mail.Session session, String myAccountEmail,String recepient, String Body){
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Information de compte");
            message.setText(Body);
            //message.setText(Act.getListActivite2().toString());
            return message;
        } catch (MessagingException ex) {
           // Logger.getLogger(MailEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void handleSendEmailButtonAction(MouseEvent event) {
    
       
    }
     private boolean isValidEmail(String email) {
        // TODO: Ajouter une validation plus robuste de l'email
        return email.contains("@");
    }
         private String generateRandomPassword() {
        // TODO: Utiliser une méthode plus sûre pour générer un mot de passe aléatoire
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 8) { // Génère un mot de passe de 8 caractères
            int index = (int) (rnd.nextFloat() * allowedChars.length());
            sb.append(allowedChars.charAt(index));
        }
        return sb.toString();
    }

private void updatePasswordInDatabase(String email, String password) {
    // Connexion à la base de données
  cnx = MaConnection.getInstance().getCnx(); 
    try  {

        // Prépare la requête SQL pour mettre à jour le mot de passe
        String sql = "UPDATE utilisateur SET mot_de_passe = ? WHERE email = ?";
        PreparedStatement stmt = cnx.prepareStatement(sql);
        stmt.setString(1, password);
        stmt.setString(2, email);

        // Exécute la requête SQL
        stmt.executeUpdate();

    } catch (SQLException e) {
        // En cas d'erreur, affiche un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText("Failed to update password.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}

    @FXML
    private void retour(ActionEvent event) {
            try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("global.fxml"));
            retour.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }












}

        
    




    

        
        
    


