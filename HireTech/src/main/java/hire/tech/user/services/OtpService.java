package hire.tech.user.services;

public interface OtpService {

	String generateAndSendOtp(String email);

	boolean validateOtp(String email, String otp);

	String generateOtp();

}
