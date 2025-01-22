package hire.tech.user.services;

public interface EmailService {

	void sendWelcomeEmail(String toEmail, String userName);

	void sendOtpEmail(String toEmail, String otp);

}
