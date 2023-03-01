/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pidev.entity.Evenement;
import pidev.services.EvenementService;
import pidev.tools.MaConnection;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;





/**
 * FXML Controller class
 *
 * @author 21650
 */
public class EmailController implements Initializable {

    @FXML
    private Button btn_envoi_mail;
    @FXML
    private TextField mail_text;
    @FXML
    private TextField objet;
    @FXML
    private Button back_id;
    @FXML
    private Button pdf;

    Connection cnx;
 String sql;
 Message message;
 //public String o = objet.getText();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_mail(ActionEvent event) throws MessagingException, SQLException {
       /* cnx=MaConnection.getInstance().getCnx();
        String mail = mail_text.getText();
        List<String> emails = new ArrayList<>();
        
        try {
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select email from utilisateur";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
            
             String m = res.getString("email");
         emails.add(m);
         }
     } catch (SQLException ex) {
         Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
     }*/
       cnx=MaConnection.getInstance().getCnx();
        String mail = mail_text.getText();
        String obj = objet.getText();
        
        //List<String> emails = new ArrayList<>();
        
    
         //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
         sql="select email from utilisateur";
         Statement ste =cnx.createStatement();
         ResultSet res = ste.executeQuery(sql);
         
         while(res.next())
         {
             
         String maill = res.getString("email");
  System.out.println("Preparing to send:");
        Properties properties = new Properties();
        
        
        String myAccountEmail ="hediammarx@gmail.com";
        String password ="lovzkamznkvqprfb";
        String recepient =maill;
        String Body= mail;
        
        properties.put("com.hof.email.starttime","20170519094544");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.connectiontimeout","60000");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","25");
        properties.put("mail.smtp.ssl.trust","*");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.timeout","60000");
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        
        
        javax.mail.Session session = javax.mail.Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        Message m = prepareMessage(session,myAccountEmail,recepient,Body,obj);
        
        Transport.send(m);
        System.out.println("message sent successfully");
        
    
     }
    }
    
    

    
    private static Message prepareMessage(javax.mail.Session session, String myAccountEmail,String recepient, String Body, String obj){
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(obj);
            message.setText(Body);
            
            return message;
        } catch (MessagingException ex) {
           // Logger.getLogger(MailEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @FXML
    private void back(ActionEvent event) {
    }
    
    
    @FXML
    private void Pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        String path = "";

        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(j);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();

        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "/mail.pdf"));
            doc.open();

            PdfPTable table = new PdfPTable(3);
            table.addCell("Date");
           
            table.addCell("Objet");
          
        
            table.addCell("Contenu");

            EvenementService e = new EvenementService();
            //for (int i = 0; i < e.rowEvent(); i++) {

                //Instant Date = java.time.Clock.systemUTC().instant();
               
                String Objet = objet.getText();
                
                String Conetnu = mail_text.getText();

                final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                LocalDateTime ldt = LocalDateTime.now();
                String formattedString = ldt.format(CUSTOM_FORMATTER);
                
                table.addCell(formattedString);
               
                table.addCell(Objet);
               
                table.addCell(Conetnu);

            

            doc.add(table);

        } catch (FileNotFoundException | DocumentException ex) {

        }

        doc.close();
    }
}
