package services.serviceUsuario;
import javax.mail.PasswordAuthentication;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMailSMTP {

	public static void main(String[] args) {
		
//		String email = "";
//		String titulo = "Mensaje prueba SMTP";
//		String cuerpo = "<h1>Enhorabuena</h1><br/> es un mensaje de prueba de la api de javax-mail realizada el: " + (new Date());
//		enviarCorreo(email, titulo, cuerpo);
	}
	
	
	public static void enviarCorreo(String email, String titulo, String cuerpo) {
		
		String emailFrom = "";//TODO:En esta versión al estar de manera pública he quitado mi email, aquí se debería poner el email desde el que se enviarán los correos
	    String passwordFrom = "";//TODO:En esta versión al estar de manera pública he quitado mi email, aquí se debería poner la clave dada por google del email desde el que se enviarán los correos
	    String emailTo = "";
//	    String emailTo = email;//TODO:Para no enviar emails a correos sin querer lo deshabilito
	    String subject = titulo;
	    String content = cuerpo;

	    Properties mProperties;
	    Session mSession;
	    MimeMessage mCorreo;

	    mProperties = new Properties();
        
         // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);
        
        
        mCorreo = new MimeMessage(mSession);
        try {
        	
			mCorreo.setFrom(new InternetAddress(emailFrom));
			mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			mCorreo.setSubject(subject);
			mCorreo.setText(content, "ISO-8859-1", "html");
			
			
			
			Transport mTransport = mSession.getTransport("smtp");
			mTransport.connect(emailFrom, passwordFrom);
			mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
			mTransport.close();
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
	}
}
