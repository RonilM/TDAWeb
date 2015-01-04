package tda;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class TDAServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			
		
			String Name = req.getParameter("Name");
			String PhoneNum = req.getParameter("PhoneNumber");
			String Email = req.getParameter("Email");
			String Location = req.getParameter("Location");
			String DanceForm =  ""+((req.getParameter("Salsa")!=null)?"Salsa  \n":"");
		    DanceForm +=  (req.getParameter("Jive")!=null)?"Jive  \n":"";
			DanceForm +=  (req.getParameter("StreetChaCha")!=null)?"StreetChaCha  \n":"";
			DanceForm +=  (req.getParameter("Bachata")!=null)?"Bachata  \n":"";
			DanceForm +=  (req.getParameter("Zouk")!=null)?"Zouk  \n":"";
			DanceForm +=  (req.getParameter("Bollywood")!=null)?"Bollywood  \n":"";
			DanceForm +=  (req.getParameter("HipHop")!=null)?"HipHop  \n":"";
			DanceForm +=  (req.getParameter("Contemporary")!=null)?"Contemporary  \n":"";
			
			String Mesg =  "Name: "+Name
						 +"\nPhone Number: "+PhoneNum
						 +"\nLocation: "+Location
						 +"\nEmail: "+Email
						 +"\nDance Forms : "+DanceForm;
			
			
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			
		try {
			   
		      Message msg = new MimeMessage(session);  
			  msg.setFrom(new InternetAddress("roni.almighty@gmail.com", "TDA"));
			  msg.addRecipient(Message.RecipientType.TO,
			     new InternetAddress("tijodanceacademy@gmail.com",Name));
			    msg.setSubject("Enquiry: TDA Website");
			    //msg.setText(Mesg);
			    msg.setContent(Mesg, "text/plain");
			    Transport.send(msg);
			    resp.sendRedirect("index.html?greeting=ThankYou");
			    
			} catch (AddressException e) {
			//log.severe("Line 194: "+e.getMessage());
				resp.sendRedirect("index.html?greeting=Sorry");
			    // ...
			} catch (MessagingException e) {
			//log.severe("Line 197: "+e.getMessage());
			    // ...
				resp.sendRedirect("index.html?greeting=Sorry");
			}
		
		
	}
}
