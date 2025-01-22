<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="/css/employerregistration.css" rel="stylesheet"
	type="text/css" />
<script src="../js/jobseeker.js"></script>
<title>HireTech Job Seeker Registration</title>
</head>
<body>
	<div class="container">
		<h1>HireTech Job Seeker Registration</h1>


		<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
		%>
		<div class="warning"><%=message%></div>
		<%
		}
		%>
		<form id="registrationForm" action="/user/jobseeker/registration"
			method="post" enctype="multipart/form-data"
			onsubmit="return validateJobSeekerForm()">
			<div class="input-box">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"
					oninput="validateUsername()" required /> <span id="usernameError"
					class="error-message"></span>
			</div>
			<div class="input-box">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password"
					oninput="validatePassword()" required /> <span id="passwordError"
					class="error-message"></span>
			</div>
			<div class="input-box">
				<label for="confirmPassword">Confirm Password</label> <input
					type="password" id="confirmPassword" name="confirmPassword"
					placeholder="Confirm your password"
					oninput="validateConfirmPassword()" required /> <span
					id="confirmPasswordError" class="error-message"></span>
			</div>
			<div class="row">
				<div class="input-box">
					<label for="fullName">Full Name</label> <input type="text"
						id="fullName" name="fullName" placeholder="Enter your full name"
						oninput="validateFullName()" required /> <span id="fullNameError"
						class="error-message"></span>
				</div>
				<div class="input-box">
					<label for="email">Email</label> <input type="text" id="email"
						name="email" placeholder="Enter your email"
						oninput="validateEmail()" required /> <span id="emailError"
						class="error-message"></span>
				</div>
				<div class="input-box">
					<label for="contactNumber">Contact Number</label> <input
						type="text" id="contactNumber" name="contactNumber"
						placeholder="Enter your contact number"
						oninput="validateContactNumber()" required /> <span
						id="contactNumberError" class="error-message"></span>
				</div>
				<div class="input-box">
					<label for="dateOfBirth">Date of Birth</label> <input type="date"
						id="dateOfBirth" name="dateOfBirth"
						placeholder="Enter your date of birth"
						oninput="validateDateOfBirth()" /> <span id="dateOfBirthError"
						class="error-message"></span>
				</div>
				<div class="input-box">
					<label for="gender">Gender</label> <select id="gender"
						name="gender" required>
						<option value="">Select your gender</option>
						<option value="male">Male</option>
						<option value="female">Female</option>
						<option value="others">Others</option>
					</select>
				</div>
				<div class="input-box">
					<label for="profilePicture">Profile Picture</label> <input
						type="file" id="profilePicture" name="profilePicture"
						accept="image/*" required />
				</div>
				<div class="input-box">
					<label for="higherEducation">Higher Education</label> <input
						type="text" id="higherEducation" name="higherEducation"
						placeholder="Enter your higher education" required />
				</div>
				<div class="input-box">
					<label for="skills">Skills</label> <input type="text" id="skills"
						name="skills" placeholder="Enter your skills" required />
				</div>
				<div class="input-box">
					<label for="preferredLocation">Preferred Location</label> <input
						type="text" id="preferredLocation" name="preferredLocation"
						placeholder="Enter your preferred location" required />
				</div>
				<div class="input-box">
					<label for="expectedSalary">Expected Salary (per month)</label> <input
						type="text" id="expectedSalary" name="expectedSalary"
						placeholder="Enter your expected salary"
						oninput="validateExpectedSalary()" /> <span
						id="expectedSalaryError" class="error-message"></span>
				</div>
				<div class="input-box">
					<label for="resume">Resume</label> <input type="file" id="resume"
						name="resume" accept="application/pdf" required />
				</div>
				<div class="input-box">
					<label for="jobStatus">Job Status</label> <select id="jobStatus"
						name="jobStatus" required>
						<option value="">Select your status</option>
						<option value="searching">Searching for a job</option>
						<option value="offerAccepted">Offer Accepted</option>
						<option value="hired">Hired</option>
					</select>
				</div>
				<button type="submit" class="btn">Create Account</button>
		</form>
		<div class="register-link">
			<p>
				Already have an account? <a href="/user/loginPage">Login</a>
			</p>
		</div>

	</div>
</body>
</html>