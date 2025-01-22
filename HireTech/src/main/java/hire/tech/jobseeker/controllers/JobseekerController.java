package hire.tech.jobseeker.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import hire.tech.jobseeker.entities.Application;
import hire.tech.jobseeker.entities.Job;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.jobseeker.entities.SavedJob;
import hire.tech.jobseeker.entities.Subscription;
import hire.tech.jobseeker.services.JobSeekerService;
import hire.tech.user.entities.User;
import hire.tech.user.services.UserService;
import hire.tech.utils.Utils;

@Controller
@RequestMapping("/jobseeker")
public class JobseekerController {

	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private UserService userService;

	@GetMapping("/registration")
	public String registration() {
		return "jobseeker/jobseekerregistration";
	}

	@GetMapping("/profile")
	public String getJobseekerProfile(Model model, HttpSession session) throws IOException {
		int userId = getUserIdFromSession(session);
		int jobSeekerId = getJobSeekerIdFromSession(session);
		Jobseeker jobseeker = jobSeekerService.getJobseekerById(jobSeekerId);
		User user = jobSeekerService.getUserById(userId);
		if (jobseeker != null && user != null) {
			model.addAttribute("jobseeker", jobseeker);
			model.addAttribute("user", user);
			return "jobseeker/myprofile"; // JSP page name
		} else {
			model.addAttribute("message", "Profile details not found.");
			return "errorPage";
		}
	}

	@GetMapping("/openIndexPage")
	public String openIndexPage(Model model) {
		List<Job> jobs = jobSeekerService.getAllJobs();
		model.addAttribute("jobs", jobs);
		return "jobseeker/jobseekerdashboard";
	}

	@GetMapping("/apply")
	public String showApplyJobs(Model model, @RequestParam(required = false) Integer jobId,
			@RequestParam(required = false) Integer jobSeekerId, HttpSession session) {
		if (jobSeekerId == null) {
			jobSeekerId = getJobSeekerIdFromSession(session); // Retrieve jobseeker ID from session if not provided
		}

		List<Job> jobs = jobSeekerService.getAllJobs();
		model.addAttribute("jobs", jobs);

		if (jobId != null) {
			Job selectedJob = jobSeekerService.findById(jobId);
			model.addAttribute("selectedJob", selectedJob);
		}

		model.addAttribute("jobSeekerId", jobSeekerId);

		return "jobseeker/apply";
	}

	@GetMapping("/appliedjob")
	public String getAppliedJobs(Model model, HttpSession session) {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		List<Application> appliedJobs = jobSeekerService.getAppliedJobs(jobSeekerId);
		List<Job> allJobs = jobSeekerService.getAllJobs();
		model.addAttribute("appliedJobs", appliedJobs);
		model.addAttribute("allJobs", allJobs);
		return "jobseeker/appliedjob";
	}

	@PostMapping("/submitApplication")
	public String submitApplication(@RequestParam("jobId") int jobId, HttpSession session,
	    @RequestParam("name") String name, @RequestParam("gender") String gender,
	    @RequestParam("dateOfBirth") Date dateOfBirth, @RequestParam("skills") String skills,
		@RequestParam("resume") MultipartFile resume, @RequestParam("availability") String availability, Model model) {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		Application application = new Application(jobId, jobSeekerId, "pending", new Date(System.currentTimeMillis()));
		jobSeekerService.applyForJob(application);
		model.addAttribute("message", "Job applied successfully!");
		return "redirect:/jobseeker/appliedjob";
	}

	@PostMapping("/save")
	public String saveJob(@RequestParam("jobId") int jobId, HttpSession session, Model model) {
	    int jobSeekerId = getJobSeekerIdFromSession(session);
	    SavedJob savedJob = new SavedJob(0, jobId, jobSeekerId, new Date(System.currentTimeMillis()));
	    jobSeekerService.saveJob(savedJob);
	    model.addAttribute("message", "Job saved successfully!");
	    return "redirect:/jobseeker/savedjobs";
	}


	@GetMapping("/savedjobs")
	public String getSavedJobs(Model model, HttpSession session) {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		List<SavedJob> savedJobs = jobSeekerService.getSavedJobs(jobSeekerId);
		List<Job> allJobs = jobSeekerService.getAllJobs();
		model.addAttribute("savedJobs", savedJobs);
		model.addAttribute("allJobs", allJobs);
		model.addAttribute("jobSeekerId", jobSeekerId); 
		return "jobseeker/savedjobs";
	}

	@PostMapping("/subscriptions")
	public String processPayment(HttpSession session, @RequestParam("name") String name,
			@RequestParam("gender") String gender, @RequestParam("subscriptionType") String subscriptionType,
			@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
			@RequestParam("paymentMode") String paymentMode, @RequestParam("paymentId") String paymentId, Model model) {
		int userId = getUserIdFromSession(session);
		int jobSeekerId = getJobSeekerIdFromSession(session);
		Subscription subscription = new Subscription(0, userId, jobSeekerId, subscriptionType, startDate, endDate,
				"active");
		jobSeekerService.subscribe(subscription);
		model.addAttribute("message",
				"Congratulations! Now you are one of the HireTech subscribers. Enjoy the features.");
		return "jobseeker/subscriptionConfirmation"; // This will render the confirmation page
	}

	@GetMapping("/subscriptions")
	public String showSubscriptionPage(Model model) {
		return "jobseeker/subscriptions";
	}

	@GetMapping("/search")
	public String searchJobs(@RequestParam(name = "skills", defaultValue = "") String skills,
		@RequestParam(name = "location", defaultValue = "") String location,
		@RequestParam(name = "company", defaultValue = "") String company, HttpSession session, Model model) {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		List<Job> searchResults = jobSeekerService.searchJobs(skills, location, company);
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("jobSeekerId", jobSeekerId); // Ensure jobSeekerId is added to the model
		return "jobseeker/search";
	}
	
	
	@GetMapping("/resumeBuilder")
	public String showResumeForm(HttpSession session, Model model) {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		List<Subscription> subscriptions = jobSeekerService.getSubscriptions(jobSeekerId);
		boolean isSubscribed = subscriptions != null
				&& subscriptions.stream().anyMatch(sub -> sub.getStatus().equals("active"));
		if (isSubscribed) {
			Resume resume = jobSeekerService.getResumeByUserId(jobSeekerId);
			if (resume == null) {
				resume = new Resume();
			}
			model.addAttribute("resume", resume);
			return "jobseeker/resumeBuilder";
		} else {
			model.addAttribute("message", "Please subscribe to access the Resume Builder and its features.");
			return "jobseeker/subscriptionsPrompt";
		}
	}
	

	@PostMapping("/saveResume")
	public String saveResume(@ModelAttribute Resume resume, HttpSession session) throws IOException {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		resume.setUserId(jobSeekerId);
		jobSeekerService.saveResume(resume);
		return "redirect:/jobseeker/resumeBuilder";
	}

	@GetMapping("/downloadResume")
	@ResponseBody
	public void downloadResume(HttpServletResponse response, HttpSession session) throws IOException {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		List<Subscription> subscriptions = jobSeekerService.getSubscriptions(jobSeekerId);
		boolean isSubscribed = subscriptions != null && subscriptions.stream().anyMatch(sub -> sub.getStatus().equals("active"));
		if (isSubscribed) {
			Resume resume = jobSeekerService.getResumeByUserId(jobSeekerId);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=resume_" + resume.getFullName() + ".pdf");
			try (PDDocument document = new PDDocument()) {
				PDPage page = new PDPage();
				document.addPage(page);
				try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
					contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
					contentStream.beginText();
					contentStream.newLineAtOffset(100, 700);
					contentStream.showText("Resume");
					contentStream.endText();
					contentStream.setFont(PDType1Font.HELVETICA, 12);
					contentStream.beginText();
					contentStream.newLineAtOffset(100, 650);
					contentStream.showText("Name: " + resume.getFullName());
					contentStream.newLine();
					contentStream.showText("Email: " + resume.getEmail());
					contentStream.newLine();
					contentStream.showText("Phone: " + resume.getPhoneNumber());
					contentStream.newLine();
					contentStream.showText("Address: " + resume.getAddress());
					contentStream.newLine();
					contentStream.showText("Education: " + resume.getEducation());
					contentStream.newLine();
					contentStream.showText("Skills: " + resume.getSkills());
					contentStream.newLine();
					contentStream.showText("Experience: " + resume.getExperience());
					contentStream.endText();
				}
				document.save(response.getOutputStream());
			}
		} else {
			response.sendRedirect("/jobseeker/subscriptionsPrompt");
		}
	}

	@GetMapping("/certifications")
	public String certifications(HttpSession session, Model model) {
		int jobSeekerId = getJobSeekerIdFromSession(session);
		List<Subscription> subscriptions = jobSeekerService.getSubscriptions(jobSeekerId);
		boolean isSubscribed = subscriptions != null && subscriptions.stream()
				.anyMatch(sub -> sub.getStatus().equals("active") && sub.getSubscriptionType().equals("Premium Plan"));
		if (isSubscribed) {
			model.addAttribute("subscribed", true);
			return "jobseeker/certifications";
		} else {
			model.addAttribute("message",
					"Please subscribe to the Premium Plan to access certifications and its features.");
			return "jobseeker/subscriptionsPrompt";
		}
	}

	@GetMapping("/downloadJobDescription")
	@ResponseBody
	public void downloadJobDescription(@RequestParam("jobId") int jobId, HttpServletResponse response)
			throws IOException {
		Job job = jobSeekerService.findById(jobId);
		if (job.getJobDescriptionPdf() != null) {
			jobSeekerService.downloadJobDescriptionPdf(response, job.getJobDescriptionPdf());
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Job description not found");
		}
	}

	@GetMapping("/help")
	public String helppage() {
		return "jobseeker/help";
	}

	@GetMapping("/deleteSavedJob")
	public String deleteSavedJob(@RequestParam("savedJobId") int savedJobId) {
		jobSeekerService.deleteSavedJob(savedJobId);
		return "redirect:/jobseeker/savedjobs";
	}

	@PostMapping("/deleteAppliedJob")
	public String deleteAppliedJob(@RequestParam("applicationId") int applicationId) {
		jobSeekerService.deleteAppliedJob(applicationId);
		return "redirect:/jobseeker/appliedjob";
	}

	@GetMapping("/editprofile")
	public String showEditProfilePage(Model model, HttpSession session) throws IOException {
		int userId = getUserIdFromSession(session);
		int jobSeekerId = getJobSeekerIdFromSession(session);
		Jobseeker jobseeker = jobSeekerService.getJobseekerById(jobSeekerId);
		User user = jobSeekerService.getUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("jobseeker", jobseeker);
		return "jobseeker/editprofile"; // Ensure this matches your JSP file name without .jsp extension
	}

	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "fullName", required = false) String fullName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "contactNumber", required = false) String contactNumber,
			@RequestParam(value = "dateOfBirth", required = false) Date dateOfBirth,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "higherEducation", required = false) String higherEducation,
			@RequestParam(value = "skills", required = false) String skills,
			@RequestParam(value = "preferredLocation", required = false) String preferredLocation,
			@RequestParam(value = "expectedSalary", required = false) String expectedSalary,
			@RequestParam(value = "jobStatus", required = false) String jobStatus,
			@RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
			@RequestParam(value = "resume", required = false) MultipartFile resume,
			RedirectAttributes redirectAttributes, HttpSession session) throws IOException {
		int jobseekerId = getJobSeekerIdFromSession(session);
		int userId = getUserIdFromSession(session);

		User user = userService.getUserById(userId);
		Jobseeker jobseeker = jobSeekerService.getJobseekerById(jobseekerId);

		updateUserDetails(user, username, fullName, email, contactNumber);
		updateJobseekerDetails(jobseeker, dateOfBirth, gender, higherEducation, skills, preferredLocation,
				expectedSalary, jobStatus);

		if (profilePicture != null && !profilePicture.isEmpty()) {
			user.setProfilePicture(profilePicture);
		}
		if (resume != null && !resume.isEmpty()) {
			jobseeker.setResume(resume);
		}

		try {
			userService.updateUser(user);
			jobSeekerService.updateJobseeker(jobseeker);
			redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
			return "redirect:/jobseeker/profile";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error updating profile. Please try again.");
		}
		return "redirect:/jobseeker/editprofile";
	}

	private void updateUserDetails(User user, String username, String fullName, String email, String contactNumber) {
		if (username != null && !username.isEmpty()) {
			user.setUsername(username);
		}
		if (fullName != null && !fullName.isEmpty()) {
			user.setFullName(fullName);
		}
		if (email != null && !email.isEmpty()) {
			user.setEmail(email);
		}
		if (contactNumber != null && !contactNumber.isEmpty()) {
			user.setContactNumber(contactNumber);
		}
	}

	private void updateJobseekerDetails(Jobseeker jobseeker, Date dateOfBirth, String gender, String higherEducation,
			String skills, String preferredLocation, String expectedSalary, String jobStatus) {
		if (dateOfBirth != null) {
			jobseeker.setDateOfBirth(dateOfBirth);
		}
		if (gender != null && !gender.isEmpty()) {
			jobseeker.setGender(gender);
		}
		if (higherEducation != null && !higherEducation.isEmpty()) {
			jobseeker.setHigherEducation(higherEducation);
		}
		if (skills != null && !skills.isEmpty()) {
			jobseeker.setSkills(skills);
		}
		if (preferredLocation != null && !preferredLocation.isEmpty()) {
			jobseeker.setPreferredLocation(preferredLocation);
		}
		if (expectedSalary != null && !expectedSalary.isEmpty()) {
			jobseeker.setExpectedSalary(expectedSalary);
		}
		if (jobStatus != null && !jobStatus.isEmpty()) {
			jobseeker.setJobStatus(jobStatus);
		}
	}

	@GetMapping("/resetPasswordPage")
	public String showResetPasswordPage() {
		return "jobseeker/resetPasswordPage";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword,
			HttpSession session, Model model) {
		int userId = getUserIdFromSession(session);
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
		return "jobseeker/resetPasswordPage";
	}

	private int getUserIdFromSession(HttpSession session) {
		return (int) session.getAttribute("loggedInUserId");
	}

	private int getJobSeekerIdFromSession(HttpSession session) {
		return (int) session.getAttribute("loggedInUserId"); // Assuming job seeker ID is same as user ID
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/loginPage";
 
	}

}
