<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>HireTech Employer Registration</title>
<link href="/css/employerregistration.css" rel="stylesheet"
	type="text/css" />
<script src="../js/employer.js"></script>

</head>
<body>
	<div class="container">

		<h1>HireTech Employer Registration</h1>

		<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
		%>
		<div class="warning"><%=message%></div>
		<%
		}
		%>

		<form action="/user/employer/registration" method="post"
			enctype="multipart/form-data" onsubmit="return validateForm()">
			<div class="input-box">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" placeholder="Enter your username"
					oninput="validateUsername()" />
			</div>
			<div class="input-box">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" placeholder="Enter your password"
					oninput="validatePassword()" />
			</div>
			<div class="input-box">
				<label for="confirmPassword">Confirm Password</label> <input
					type="password" id="confirmPassword" name="confirmPassword"
					placeholder="Confirm your password"
					oninput="validateConfirmPassword()" />
			</div>
			<div class="input-box">
				<label for="fullName">Full Name</label> <input type="text"
					id="fullName" name="fullName" placeholder="Enter your full name"
					oninput="validateFullName()" />
			</div>
			<div class="input-box">
				<label for="email">Email</label> <input type="text" id="email"
					name="email" placeholder="Enter your email"
					oninput="validateEmail()" />
			</div>
			<div class="input-box">
				<label for="contactNumber">Contact Number</label> <input type="text"
					id="contactNumber" name="contactNumber"
					placeholder="Enter your mobile number"
					oninput="validateContactNumber()" />
			</div>
			<div class="input-box">
				<label for="profilePicture">Profile Picture</label> <input
					type="file" id="profilePicture" name="profilePicture"
					accept="image/*" required />
			</div>
			<div class="input-box">
				<label for="companyName">Company Name</label> <input type="text"
					id="companyName" name="companyName"
					placeholder="Enter company name" required /> <span
					id="companyNameError" class="error-message" style="color: red"></span>
			</div>
			<div class="input-box">
				<label for="industryType">Industry Type</label> <select
					id="industryType" name="industryType" required>
					<option value="">Select your industry</option>
					<option value="IT">IT</option>
					<option value="Sales">Sales</option>
					<option value="Development">Development</option>
					<option value="HR">HR</option>
				</select> <span id="industryError" class="error-message" style="color: red"></span>
			</div>
			<div class="input-box">
				<label for="companyLogo">Company Logo</label> <input type="file"
					id="companyLogo" name="companyLogo" accept="image/*" required />
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