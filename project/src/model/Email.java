package model;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class Email {

	String sender = "mackantestar@gmail.com";
	String senderPassword = "katten1234";
	String reciver;
	String subject;
	String mailContent;

	Properties MailServerProperties = new Properties();

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getReciver() {
		return reciver;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailServerProperties() {
		MailServerProperties.put("mail.smtp.host", "true");
		MailServerProperties.put("mail.smtp.starttls.enable", "true");
		MailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		MailServerProperties.put("mail.smtp.port", "587");
		MailServerProperties.put("mail.smtp.auth", "true");
	}

	public Session createSession() {
		Session session = Session.getInstance(MailServerProperties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, senderPassword);
			}
		});
		return session;
	}

	public void sendMail() {
		setMailServerProperties();

		try {

			MimeMessage message = new MimeMessage(createSession());

			message.setFrom(new InternetAddress(sender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciver));
			message.setSubject(subject);
			message.setText(mailContent);

			Transport.send(message);

			System.out.println("Message sent.");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
