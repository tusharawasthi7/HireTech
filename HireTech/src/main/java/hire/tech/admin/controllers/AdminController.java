package hire.tech.admin.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.admin.entities.Subscription;
import hire.tech.admin.services.AdminService;
import hire.tech.user.entities.User;
import hire.tech.user.services.UserService;
import hire.tech.utils.Utils;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminservice;

	@Autowired
	UserService userService;

	@GetMapping("/dashboard")
	public String showAdminDashboard() {
		return "admin/adminDashboard";
	}

	@GetMapping("/profile")
	public String getAdminProfile(Model model) throws IOException {
		int userId = 1;
		List<User> users = adminservice.getAdminById(userId);
		if (users != null && !users.isEmpty()) {
			User user = users.get(0);
			model.addAttribute("user", user);
			return "admin/myprofile";
		} else {
			model.addAttribute("message", "Profile details not found.");
			return "errorPage";
		}
	}

	@GetMapping("/openUpdateProfilePage")
	public String openUpdateProfilePage(Model model) {
		int ADMIN_USER_ID = 1;
		Optional<User> optionalUser = adminservice.getUserById(ADMIN_USER_ID);
		if (!optionalUser.isPresent()) {
			model.addAttribute("message", "Admin user not found.");
			return "admin/admindashboard";
		}
		User user = optionalUser.get();
		model.addAttribute("user", user);
		return "admin/updateprofile";
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@ModelAttribute User user, @RequestParam("profilePicture") MultipartFile file,
			Model model) {
		try {
			if (!file.isEmpty()) {
				user.setProfilePicture(file);
			}
			adminservice.updateUser(user);
			model.addAttribute("message", "Profile updated successfully.");
			return "admin/myprofile";
		} catch (Exception e) {
			model.addAttribute("error", "Error updating profile: " + e.getMessage());
		}
		return "redirect:/admin/openUpdateProfilePage";
	}

	@GetMapping("/getemployerinfo")
	public String getemployerinfo(Model model) {
		List<Map<String, Object>> employerInfo = adminservice.getAllEmployers();
		System.out.println(employerInfo);
		for (Map<String, Object> info : employerInfo) {
			System.out.println("Employer Info: " + info);
		}
		model.addAttribute("employerInfo", employerInfo);
		return "admin/employerinfo_list";
	}

	@GetMapping("/toggleAuthority/{userId}")
	public String toggleAuthority(@PathVariable String userId) {
		int Id = Integer.parseInt(userId);
		userService.updateAuthority(Id);
		adminservice.updateEmployerStatus(Id);
		return "redirect:/admin/getemployerinfo";
	}

	@GetMapping("/getjobseekersinfo")
	public String getjobseekersinfo(Model model) {
		List<Map<String, Object>> jobseekersInfo = adminservice.getAllJobSeekers();
		if (jobseekersInfo == null || jobseekersInfo.isEmpty()) {
			System.out.println("No job seekers information found.");
		} else {
			for (Map<String, Object> info : jobseekersInfo) {
				System.out.println("Job Seeker Info: " + info);
			}
		}
		model.addAttribute("jobseekersInfo", jobseekersInfo);
		return "admin/jobseekersinfo_list";
	}

	@GetMapping("/getjobsinfo")
	public String getjobsinfo(Model model) {
		List<Map<String, Object>> jobsInfo = adminservice.getAllJobsInfo();
		if (jobsInfo == null || jobsInfo.isEmpty()) {
			System.out.println("No job information found.");
		} else {
			for (Map<String, Object> info : jobsInfo) {
				System.out.println("Job Info: " + info);
			}
		}
		model.addAttribute("jobsInfo", jobsInfo);
		return "admin/jobs_list";
	}

	@GetMapping("/pendingrequests")
	public String viewPendingRequests(Model model) {
		List<Map<String, Object>> pendingEmployers = adminservice.getPendingEmployerDetails();
		model.addAttribute("pendingEmployers", pendingEmployers);
		return "admin/pending_requests";
	}

	@GetMapping("/approvedrequests")
	public String viewApprovedRequests(Model model) {
		List<Map<String, Object>> approvedEmployers = adminservice.getApprovedEmployerDetails();
		model.addAttribute("approvedEmployers", approvedEmployers);
		return "admin/approved_requests";
	}

	@GetMapping("/rejectrequests")
	public String viewRejectRequests(Model model) {
		List<Map<String, Object>> rejectEmployers = adminservice.getRejectEmployerDetails();
		model.addAttribute("rejectEmployers", rejectEmployers);
		return "admin/reject_requests";
	}

	@GetMapping("/logout")
	public String logoutPage() {
		return "user/login";
	}

	@GetMapping("/backToDashboard")
	public String backToDashboard() {
		return "admin/admindashboard";
	}

	@GetMapping("/jobseekersubscriptions")
	public String viewJobseekerSubscriptions(Model model) {
		List<Subscription> jobseekerSubscriptions = adminservice.getSubscriptionsByjobseekerId();
		if (jobseekerSubscriptions == null || jobseekerSubscriptions.isEmpty()) {
			System.out.println("No job seekers information found.");
		} else {
			for (Subscription info : jobseekerSubscriptions) {
				System.out.println("Job Seeker Info: " + info);
			}
		}
		model.addAttribute("subscriptions", jobseekerSubscriptions);
		return "admin/jobseekers_subscription";
	}
	
	@GetMapping("/resetPasswordPage")
		public String showResetPasswordPage() {
			return "admin/resetPasswordPage";
		}
	 
			
		@PostMapping("/updatePassword")
		public String updatePassword(@RequestParam("currentPassword") String currentPassword,
				@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
				HttpSession session, Model model) {
			Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
			Integer userId = loggedInUserId;
			User user = userService.getUserById(userId);
			if (user != null && userService.matchPassword(currentPassword, user)) {
				if (newPassword.equals(confirmPassword)) {
					try {
						String salt = Utils.generateSalt();
						String hashedPassword = Utils.generateHash(newPassword + salt);
						user.setPasswordSalt(salt);
						user.setPasswordHash(hashedPassword);
						userService.updateUser(user);
						model.addAttribute("successMessage", "Password updated successfully!");
						/* return "redirect:/admin/resetPasswordPage"; */
					} catch (IOException e) {
						e.printStackTrace();
						model.addAttribute("errorMessage", "Error updating password. Please try again.");
					}
				} else {
					model.addAttribute("errorMessage", "New passwords do not match.");
				}
			} else {
				model.addAttribute("errorMessage", "Current password is incorrect.");
			}
			return "admin/resetPasswordPage";
		}	
}
