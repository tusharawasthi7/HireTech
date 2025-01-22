package hire.tech.employer.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hire.tech.employer.*;
import hire.tech.employer.entities.Job;
import hire.tech.employer.services.EmployerService;
import hire.tech.jobseeker.entities.Jobseeker;
import jakarta.servlet.http.HttpSession;
import hire.tech.employer.entities.Applications;
import hire.tech.employer.entities.Employer;
import hire.tech.user.entities.User;
import hire.tech.employer.entities.*;
import hire.tech.user.services.UserService;
import hire.tech.utils.Utils;

@Controller
@RequestMapping("/employer")
public class EmployerController {

	@Autowired
	EmployerService employerService;
	@Autowired
	UserService userService;

	@GetMapping("/registration")
	public String registration() {
		return "employer/employerregistration";
	}

	@GetMapping("/dashboard")
	public ModelAndView employerDashboard(ModelAndView mView, HttpSession session) {

		// Session Management
		Integer loggedInUserRoleId = (Integer) session.getAttribute("loggedInUserRoleId ");
		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;
		Integer roleId = loggedInUserRoleId;

		System.out.println(roleId + "roleId");
		System.out.println(userId + "userId");
		// Check user login or not

		if (userId == null) {
			return new ModelAndView("redirect:/user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(userId);

		int emp_id = employer.getEmployerId();

		int totalJobs = employerService.getTotalJobsPosted(emp_id);
		System.out.println(totalJobs + "total");
		int totalApplications = employerService.getTotalApplications(emp_id);
		System.out.println(totalApplications + "total");

		mView.addObject("totalJobs", totalJobs);
		mView.addObject("totalApplications", totalApplications);

		mView.setViewName("employer/dashboard");
		return mView;
	}

	@GetMapping("/addJobs")
	public String addJobs() {
		return "employer/addJobs";
	}

	@GetMapping("/profile")
	public ModelAndView profile(ModelAndView mView, HttpSession session) {

		// Session Management
		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		Employer employer = employerService.getEmployerByUserId(userId);

		int emp_id = employer.getEmployerId();

		System.out.println(emp_id + "########################");

		if (userId == null) {
			return new ModelAndView("redirect:/user/loginPage");
		}

		List<Employer> employerdetails = employerService.getUpdateEmployer(emp_id);
		List<User> userdetails = userService.fetchUser(userId);
		System.out.println(userdetails);
		mView.addObject("employerdetails", employerdetails);
		mView.addObject("userdetails", userdetails);
		mView.setViewName("employer/myprofile");

		return mView;

	}

	@GetMapping("/editProfile")
	public ModelAndView editProfile(ModelAndView mView, HttpSession session) {

		// Session Management
		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
		Integer userId = loggedInUserId;

		if (loggedInUserId == null) {
			return new ModelAndView("redirect:/user/loginPage");
		}

		// Fetch user details
		User user = userService.getUserById(loggedInUserId);
		if (user == null) {
			return new ModelAndView("redirect:/user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(loggedInUserId);
		int emp_id = employer.getEmployerId();

		List<Employer> employerdetails = employerService.getUpdateEmployer(emp_id);
		List<User> userdetails = userService.fetchUser(userId);

		System.out.println(userdetails);
		System.out.println(employerdetails + "editprofile3151551351251513513");
		mView.addObject("userdetails", userdetails);
		mView.addObject("employerdetails", employerdetails);
		mView.setViewName("employer/editProfile");

		return mView;
	}

	@GetMapping("/viewApplicants")
	public ModelAndView viewApplicants(ModelAndView mView) {
		mView.setViewName("employer/viewJobApplicants");
		return mView;
	}

	@GetMapping("/viewAllYourJobs")
	public ModelAndView viewAllYourJobs(ModelAndView mView, HttpSession session) {

		// Session Management
		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		if (userId == null) {
			return new ModelAndView("redirect:/user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(userId);

		int emp_id = employer.getEmployerId();

		List<Job> listOfJobs = employerService.getAllJobs(emp_id);
		mView.addObject("listOfJobs", listOfJobs);
		mView.setViewName("employer/viewYourJobs");

		return mView;

	}

	@GetMapping("/scheduleInterview")
	public String scheduleInterview(@RequestParam(value = "email", required = false) String email, Model model,
			HttpSession session) {
		model.addAttribute("email", email != null ? email : "");

		// Check for success message in session
		String successMessage = (String) session.getAttribute("successMessage");
		if (successMessage != null) {
			model.addAttribute("successMessage", successMessage);
			session.removeAttribute("successMessage");
		}

		return "employer/scheduleInterview";
	}

	@PostMapping("/sendMail")
	public String sendMail(@RequestParam("applicantEmail") String applicantEmail,
			@RequestParam("message") String message, HttpSession session) {

		System.out.println("Email to: " + applicantEmail);
		System.out.println("Message: " + message);

		session.setAttribute("successMessage", "Email sent successfully!");

		return "redirect:/employer/scheduleInterview";
	}

	/*
	 * @PostMapping("/sendMail") public String
	 * sendMail(@RequestParam("applicantEmail") String
	 * applicantEmail, @RequestParam("message") String message, HttpSession session)
	 * { try { System.out.println("Email to: " + applicantEmail);
	 * System.out.println("Message: " + message);
	 * 
	 * // Assuming you have a service to send the email
	 * emailService.sendEmail(applicantEmail, message);
	 * 
	 * session.setAttribute("successMessage", "Email sent successfully!"); return
	 * "redirect:/employer/scheduleInterview"; } catch (Exception e) {
	 * e.printStackTrace(); session.setAttribute("errorMessage",
	 * "Error sending email. Please try again."); return
	 * "redirect:/employer/scheduleInterview"; } }
	 */

	/*
	 * @PostMapping("/insertJob") public ModelAndView insertJob(@ModelAttribute Job
	 * job, ModelAndView mView, HttpSession session) throws SerialException,
	 * IOException, SQLException {
	 * 
	 * Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");
	 * 
	 * Integer userId = loggedInUserId;
	 * 
	 * if (userId == null) { return new ModelAndView("redirect:/user/loginPage"); }
	 * 
	 * mView.setViewName("employer/employerDashboard");
	 * 
	 * Employer employer = employerService.getEmployerByUserId(userId);
	 * 
	 * int emp_id = employer.getEmployerId();
	 * 
	 * int result = employerService.addJob(job, employer, emp_id);
	 * 
	 * String message = "Job Added Successfully."; if (result < 1) { message =
	 * "Something went wrong in adding Job."; }
	 * 
	 * mView.addObject("message", message);
	 * 
	 * return new ModelAndView("redirect:/employer/viewAllYourJobs");
	 * 
	 * }
	 */

	@PostMapping("/insertJob")
	public ModelAndView insertJob(@ModelAttribute Job job, ModelAndView mView, HttpSession session)
			throws SerialException, IOException, SQLException {

		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		if (loggedInUserId == null) {
			return new ModelAndView("redirect:/user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(loggedInUserId);
		int empId = employer.getEmployerId();

		int result = employerService.addJob(job, employer, empId);

		String message = "Job Added Successfully.";
		if (result < 1) {
			message = "Something went wrong in adding Job.";
		}

		session.setAttribute("message", message);
		return new ModelAndView("redirect:/employer/viewAllYourJobs");
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/loginPage";

	}

	@GetMapping("/viewPendingApplicants")
	public ModelAndView viewPendingApplicants(ModelAndView mView, HttpSession session) {

		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		System.out.println(userId + "view");

		if (loggedInUserId == null) {
			return new ModelAndView("redirect:user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(userId);
		int empId = employer.getEmployerId();
		System.out.println(empId + "employer id");
		List<Applications> applications = employerService.getPendingApplicantsOfEmployer(empId);
		mView.addObject("applications", applications);
		mView.setViewName("employer/viewPendingApplicants");
		return mView;
	}

	@GetMapping("/viewRejectedApplicants")
	public ModelAndView viewRejectedApplicants(ModelAndView mView, HttpSession session) {

		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		System.out.println(userId + "viewrejected");

		if (loggedInUserId == null) {
			return new ModelAndView("redirect:user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(userId);
		int empId = employer.getEmployerId();
		System.out.println(empId + "employer id");
		List<Applications> applications = employerService.getRejectedApplicantsOfEmployer(empId);
		mView.addObject("applications", applications);
		mView.setViewName("employer/viewRejectedApplicants");
		return mView;
	}

	@GetMapping("/viewApprovedApplicants")
	public ModelAndView viewApprovedApplicants(ModelAndView mView, HttpSession session) {
		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		System.out.println(userId + "viewapproved");

		if (loggedInUserId == null) {
			return new ModelAndView("redirect:user/loginPage");
		}

		Employer employer = employerService.getEmployerByUserId(userId);
		int empId = employer.getEmployerId();
		System.out.println(empId + "employer id");
		List<Applications> applications = employerService.getApprovedApplicantsOfEmployer(empId);
		mView.addObject("applications", applications);
		mView.setViewName("employer/viewApprovedApplicants");
		return mView;
	}

	@GetMapping("/approveApplicant/{applicant_id}/{status}/{condition}")
	public String approveRequest(@PathVariable int applicant_id, @PathVariable String status, Model model,
			HttpSession session, @PathVariable int condition) {

		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		System.out.println(userId + "viewapproved");

		if (loggedInUserId == null) {
			return "redirect:user/loginPage";
		}

		String message = employerService.changeApplicationStatus(applicant_id, status, condition);
		model.addAttribute("message", message);
		return "redirect:/employer/viewApprovedApplicants";
	}

	@GetMapping("/rejectApplicant/{applicant_id}/{status}/{condition}")
	public String rejectRequest(@PathVariable int applicant_id, @PathVariable String status, Model model,
			HttpSession session, @PathVariable int condition) {
		Integer loggedInUserId = (Integer) session.getAttribute("loggedInUserId");

		Integer userId = loggedInUserId;

		System.out.println(userId + "viewapproved");

		if (loggedInUserId == null) {
			return "redirect:/login";
		}
		System.out.println("status rejected---" + status);

		String message = employerService.changeApplicationStatus(applicant_id, status, condition);
		model.addAttribute("message", message);
		return "redirect:/employer/viewRejectedApplicants";
	}

	/*
	 * @GetMapping("/deleteJob/{job_id}") public ModelAndView
	 * deleteJob(@PathVariable int job_id, ModelAndView mView) { int rowsAffected =
	 * employerService.deleteJob(job_id); String message =
	 * "Job deleted successfully."; if (rowsAffected <= 0) { message =
	 * "Job not found."; } mView.setViewName("redirect:/employer/viewAllYourJobs");
	 * return mView; }
	 */

	@GetMapping("/deleteJob/{job_id}")
	public ModelAndView deleteJob(@PathVariable int job_id, ModelAndView mView, HttpSession session) {
		int rowsAffected = employerService.deleteJob(job_id);
		String message = "Job deleted successfully.";
		if (rowsAffected <= 0) {
			message = "Job not found.";
		}
		session.setAttribute("message", message);
		mView.setViewName("redirect:/employer/viewAllYourJobs");
		return mView;
	}

	@GetMapping("/editJob/{job_id}")
	public String editJob(@PathVariable int job_id, Model model) {
		Job job = employerService.getJobById(job_id);
		model.addAttribute("job", job);
		return "employer/editJob";
	}

	@PostMapping("/updateJob")
	public String updateJob(@RequestParam int job_id, @RequestParam String title, @RequestParam String skills,
			@RequestParam String job_type, @RequestParam double salary_offered, @RequestParam String job_location,
			@RequestParam("description") MultipartFile description, HttpSession session) {
		try {
			System.out.println(title + skills + salary_offered + job_type + job_location + description);

			int rowsAffected = employerService.updateJob(job_id, title, skills, job_type, salary_offered, job_location,
					description);
			String message = "Job updated successfully.";
			if (rowsAffected <= 0) {
				message = "Something went wrong in updating the profile.";
			}
			session.setAttribute("message", message);
			return "redirect:/employer/viewAllYourJobs"; // Redirect to the dashboard
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", "An error occurred while updating the profile.");
			return "redirect:/employer/viewAllYourJobs";
		}
	}

	@PostMapping("/updateUserProfile")
	public String updateUserProfile(@RequestParam int userId, @RequestParam String username,
			@RequestParam String fullName, @RequestParam String email, @RequestParam String contactNumber,
			@RequestParam MultipartFile profilePicture, HttpSession session) {

		try {
			System.out.println(username + fullName + email + contactNumber);

			int rowsAffected = userService.updateUserEmployer(userId, username, fullName, email, contactNumber,
					profilePicture);
			String message = "Profile updated successfully.";
			if (rowsAffected <= 0) {
				message = "Something went wrong in updating the profile.";
			}
			session.setAttribute("message", message);
			return "redirect:/employer/profile";
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", "An error occurred while updating the profile.");
			return "redirect:/employer/profile";
		}
	}

	@GetMapping("/resetPasswordPage")
	public String showResetPasswordPage() {
		return "employer/resetPasswordPage";
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
					return "employer/resetPasswordPage";
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
		return "employer/resetPasswordPage";
	}

}
