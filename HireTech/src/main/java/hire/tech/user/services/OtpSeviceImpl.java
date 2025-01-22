package hire.tech.user.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpSeviceImpl implements OtpService {

	@Autowired
	private EmailService emailService;

	// In-memory store for OTPs
	private Map<String, String> otpStorage = new HashMap<>();

	// Generate and send OTP
	@Override
	public String generateAndSendOtp(String email) {
		String otp = generateOtp();
		otpStorage.put(email, otp); // Store OTP against the email

		try {
			emailService.sendOtpEmail(email, otp);
		} catch (Exception e) {
			System.err.println("Error sending OTP to " + email + ": " + e.getMessage());
		}
		return otp;
	}

	// Validate OTP
	@Override
	public boolean validateOtp(String email, String otp) {
		String storedOtp = otpStorage.get(email);
		if (storedOtp != null && storedOtp.equals(otp)) {
			otpStorage.remove(email); // Remove OTP after successful validation
			return true;
		}
		return false;
	}

	// Helper method to generate OTP
	public String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000); // Generate 6-digit OTP
		System.out.println("Generated OTP: " + otp); // For debugging; remove in production
		return String.valueOf(otp);
	}

}
