/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/**
 *
 * @author user
 */
public class sms {
    
    public static final String ACCOUNT_SID = "ACe4b091c7a2f2d726989eeff24965ab56";     /// 
    public static final String AUTH_TOKEN = "9b22601bdad71a4bc1607a236558c995"; ///   
    public static final String TWILIO_NUMBER = "+15746266341";
    
    public static void sendSms(String contenu,String num) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       String phoneNumber = "+216"+num;
       String msg="Bonjour cher client, "+contenu +" , Merci pur votre patience";
      //  Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(TWILIO_NUMBER),"garage ajouter").create();
         Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER), contenu).create();
        
        System.out.println(message.getSid());
	}
}
    

