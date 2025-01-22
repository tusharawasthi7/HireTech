package hire.tech.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendWelcomeEmail(String toEmail, String userName) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("_______email id_______"); // Your email address
			message.setTo(toEmail);
			message.setSubject("_______Subject______");
			message.setText("________text________");

			mailSender.send(message);

			System.out.println("Welcome email sent successfully to: " + toEmail);
		} catch (MailException e) {
			System.err.println("Error sending email to " + toEmail + ": " + e.getMessage());
		}
	}

	// SEND OTP
	public void sendOtpEmail(String toEmail, String otp) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("your-email@example.com");
			message.setTo(toEmail);
			message.setSubject("Password Reset OTP");
			message.setText("Your OTP for password reset is: " + otp + "\n\n"
					+ "If you did not request this, please ignore this email.");
			mailSender.send(message);

			System.out.println("OTP email sent successfully to: " + toEmail);
		} catch (MailException e) {
			System.err.println("Error sending OTP email to " + toEmail + ": " + e.getMessage());
		}
	}

}
