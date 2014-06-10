package com.mobile.tool.stock.manager.utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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

public class SendingMailsUtility {

	private static final String GMAIL_USERNAME = "shashank180287@gmail.com";
	private static final String GMAIL_PASSWORD = "mylovesurbhi";


	public static void sendMailUsingGmailSMTP(String to, String file) {
		Properties props = getGMailSMTPProeprties();
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(GMAIL_USERNAME, GMAIL_PASSWORD);
			}
		  });
 
		try{
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("shashank180287@gmail.com"));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject("Congrates..Your Order is booked successfully.");

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText("Congrates..Your Order is booked successfully.");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(file);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}

	}

	private static Properties getGMailSMTPProeprties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}
	
	public static void main(String[] args) {
		sendMailUsingGmailSMTP("shanky_rmn@yahoo.co.in", "D:\\demo.pdf");
	}
}
