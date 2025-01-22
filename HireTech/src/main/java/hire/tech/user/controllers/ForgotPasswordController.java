package hire.tech.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hire.tech.user.services.OtpService;
import hire.tech.user.services.UserService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private OtpService otpService;
	@Autowired
	private UserService userService;

	// Step 1: Display the Forgot Password Page
	@GetMapping("/forgotPassword")
	public String showForgotPasswordPage() {
		return "user/forgotPassword";
	}

	// Step 2: Send OTP to Email
	@PostMapping("/sendOtp")
	public String sendOtp(@RequestParam("email") String email, Model model) {
		boolean isUserExists = userService.isEmailRegistered(email);
		if (!isUserExists) {
			model.addAttribute("error", "Email not registered!");
			return "user/forgotPassword";
		}
		otpService.generateAndSendOtp(email);
		model.addAttribute("email", email);
		model.addAttribute("message", "OTP sent to your email.");
		return "user/enterOtp"; // Redirect to OTP entry page
	}

	// Step 3: Verify OTP
	@PostMapping("/verifyOtp")
	public String verifyOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {
		boolean isOtpValid = otpService.validateOtp(email, otp);
		if (!isOtpValid) {
			model.addAttribute("error", "Invalid OTP. Please try again.");
			model.addAttribute("email", email);
			return "user/enterOtp"; // Stay on the OTP entry page
		}
		model.addAttribute("email", email);
		return "user/resetPassword"; // Redirect to the Reset Password page
	}

	// Step 4: Display Reset Password Page
	@GetMapping("/resetPassword")
	public String showResetPasswordPage(@RequestParam("email") String email, Model model) {
		model.addAttribute("email", email);
		return "user/resetPassword";
	}

	// Step 5: Handle Password Reset
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam("email") String email, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword, Model model) {
		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("error", "Passwords do not match!");
			model.addAttribute("email", email); // Ensure the email is retained
			return "user/resetPassword";
		}
		try {
			userService.updatePassword(email, newPassword);
			model.addAttribute("message", "Password reset successfull. Login now.");
			return "user/login";
		} catch (Exception e) {
			model.addAttribute("error", "An error occurred while resetting the password. Please try again.");
			e.printStackTrace();
			return "user/resetPassword";
		}
	}
}