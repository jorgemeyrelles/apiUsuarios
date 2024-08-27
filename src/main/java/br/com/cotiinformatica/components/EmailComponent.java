package br.com.cotiinformatica.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;


@Component
public class EmailComponent {
	@Value("${spring.mail.username}")
	private String userName;
	
	@Autowired
	private JavaMailSender javaMailSender; // injeção de dependencia para serviço de enviar e-mail
	
	public void sendMail(String to, String subject, String body) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		
		messageHelper.setFrom(userName);
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(body);
		
		javaMailSender.send(message);
	}
}
