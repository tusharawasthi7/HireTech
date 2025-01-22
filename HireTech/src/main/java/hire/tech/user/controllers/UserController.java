package hire.tech.user.controllers;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hire.tech.employer.entities.Employer;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.user.entities.User;
import hire.tech.user.services.UserService;
import hire.tech.utils.Utils;

//27-12-2024
import hire.tech.employer.*;
import hire.tech.employer.entities.Job;
import hire.tech.employer.services.EmployerService;
import jakarta.servlet.http.HttpSession;
import hire.tech.employer.entities.Applications;
import hire.tech.employer.entities.Employer;
import hire.tech.user.entities.User;
import hire.tech.employer.entities.*;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/indexPage")
	public String homePage() {
		return "user/index";
	}

	@GetMapping("/loginPage")
	public String loginPage() {
		return "user/login";
	}

	@GetMapping("/registrationPage")
	public String registrationPage() {
		return "user/registration";
	}

	@GetMapping("/employerRegistrationPage")
	public String employerRegistration() {
		return "employer/employerregistration";
	}

	@GetMapping("/jobSeekerRegistrationPage")
	public String jobSeekerRegistration() {
		return "jobseeker/jobseekerregistration";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {
		System.out.println("\n username :" + username);
		System.out.println("\n password :" + password);
		Optional<User> optUser = userService.fetchUserBy(username);
		if (optUser.isEmpty()) {
			model.addAttribute("message", "Entered wrong username");
			return "user/login";
		}
		User registeredUser = optUser.get();
		System.out.println("\n user from database---------");
		System.out.println(registeredUser);
		int userId = registeredUser.getUserId();
		if (!userService.matchPassword(password, registeredUser)) {
			System.out.println();
			model.addAttribute("message", "Entered wrong password");
			return "user/login";
		}

		model.addAttribute("registeredUser", registeredUser);
		int roleId = registeredUser.getRoleId();
		boolean isAuthorized = registeredUser.isAuthorizationStatus();
		if (roleId == 1) {
			session.setAttribute("loggedInUserId", userId);
			return "admin/admindashboard";
		} else if (roleId == 2) {
			if (isAuthorized) {
				session.setAttribute("loggedInUserRoleId", roleId);
				session.setAttribute("loggedInUserId", userId);
				return "redirect:/employer/dashboard";
			} else {
				model.addAttribute("message", "Your application is pending");
				return "user/login";
			}

		} else if (roleId == 3) {
			session.setAttribute("loggedInUserRoleId", roleId);
			session.setAttribute("loggedInUserId", userId);
			return "jobseeker/jobseekerdashboard";
		} else {
			model.addAttribute("message", "Your application is pending");
			return "user/login";
		}
	}

	@PostMapping("/employer/registration")
	public ModelAndView registration(@ModelAttribute User user, @ModelAttribute Employer employer, ModelAndView mView) {
		mView.setViewName("employer/employerregistration");
		user.setRoleId(2);
		System.out.println("\n User details-------");
		System.out.println(user);
		System.out.println(employer);

		if (!userService.isEmailUnique(user.getEmail())) {
			mView.setViewName("employer/employerregistration");
			mView.addObject("message", "Email already exists. Please choose a different Email.");
			return mView;
		}

		if (!userService.isUsernameUnique(user.getUsername())) {
			mView.setViewName("employer/employerregistration");
			mView.addObject("message", "Username already exists. Please choose a different username.");
			return mView;
		}

		int result = userService.registerEmployer(user, employer);
		if (result > 0) {
			mView.setViewName("user/login");
			mView.addObject("message", "\n Registration successful. Please login to continue.");
		} else {
			mView.setViewName("employer/employerregistration");
			mView.addObject("message", "\n Registration failed. Please try again.");
		}
		return mView;
	}

	@PostMapping("/jobseeker/registration")
	public ModelAndView registration(@ModelAttribute User user, @ModelAttribute Jobseeker jobseeker,
			ModelAndView mView) {
		user.setRoleId(3);
		user.setAuthorizationStatus(true);
		System.out.println("\n User details-------");
		System.out.println(user);
		System.out.println(jobseeker);

		if (!userService.isEmailUnique(user.getEmail())) {
			mView.setViewName("jobseeker/jobseekerregistration");
			mView.addObject("message", "Email already exists. Please choose a different Email.");
			return mView;
		}

		if (!userService.isUsernameUnique(user.getUsername())) {
			mView.setViewName("jobseeker/jobseekerregistration");
			mView.addObject("message", "Username already exists. Please choose a different username.");
			return mView;
		}

		int result = userService.registerJobseeker(user, jobseeker);
		if (result > 0) {
			mView.setViewName("user/login");
			mView.addObject("message", "\n Registration successful.Please login to continue");
		} else {
			mView.setViewName("jobseeker/jobseekerregistration");
			mView.addObject("message", "\n Registration failed. Please try again");
		}
		return mView;
	}

}