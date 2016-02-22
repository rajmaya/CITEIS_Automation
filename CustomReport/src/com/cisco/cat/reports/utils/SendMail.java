package com.cisco.cat.reports.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

/*import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;*/

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.IOUtils;

/*import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;*/

public class SendMail {

	public static void sendReportToMail(int tno, int passed, int failed, int skipped){

		String to = "sobakshi@cisco.com";
		final String from = "engineeer.sb@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");



		Session session = Session.getInstance(properties,
				new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "renault@1234");
			}
		});
		try{
			
			String strResultpath = "ftp:\\10.197.64.122\\Gopal\\results\\CAT%20Reporter\\Results\\"+Directory.RUNName+Directory.RUNDir.split("_")[1]+"\\"+"CurrentRun.html";
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.addRecipients(Message.RecipientType.CC, 
                    InternetAddress.parse("majampan@cisco.com,udondeti@cisco.com,pponguru@cisco.com,ramyselv@cisco.com"));
			message.setSubject("Citeis Execution status report");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			String html = " \n <html><head>  <title>CITEIS Automation report</title></head><body>  <p>Please find the report below</p>  <table border = 1>    <tr>      <th>Total No</th><th>Passed</th><th>Failed</th><th>Skipped</th>    </tr>    <tr>      <td>"+tno+"</td><td>"+passed+"</td><td>"+failed+"</td><td>"+skipped+"</td>    </tr>     </table><p>Click on following link to view the result <a href= "+strResultpath+">Click Here</a> <br><br> Thanks, <br> CITIES Automation Team</p></body></html>"; 
			messageBodyPart.setContent(html, "text/html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);

			Transport.send(message);
		}catch (MessagingException mex) {
			mex.printStackTrace();}

	}



}