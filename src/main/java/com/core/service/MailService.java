package com.core.service;

import com.member.entity.Member;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {
	public void sendMail(String to, String subject, String messageText) {

		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			final String myGmail = "max875e6@gmail.com";
			final String myGmail_password = "betttrnyeufjzrxt";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));

			//設定信中的主旨
			message.setSubject(subject);
			//設定信中的內容
			message.setText(messageText);

			Transport.send(message);
			System.out.println("傳送成功!");
		}catch (MessagingException e){
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}
	public void mailService(Member member,String title, String messageText){

		String to = member.getEmail();

		MailService mailService = new MailService();
		mailService.sendMail(to, title, messageText);
		// 呼叫此方法時 只要提供會員寄發會員的email、title、訊息內容即可
	}
}
