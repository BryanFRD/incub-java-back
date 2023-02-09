package fr.insy2s.commerce.shoponlineback.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

	private final JavaMailSender mailSender;

	public void sendSimpleEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("buyoyaneymar@gmail.com");
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
	}

}