<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Resume Builder and View</title>
<link href="../css/resumebuilder.css" rel="stylesheet" type="text/css" />
<script src="../js/jobseeker.js"></script>
</head>
<body>
	<div class="navbar">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/jobseeker/openIndexPage" class="dashboard-button">Back to
			Dashboard</a>
	</div>
	<div class="message-container">
		<h2 class="center-text">Build your Resume here, as it defines
			you!</h2>
	</div>
	<div class="container">
		<h2 class="center-text">Edit Resume</h2>
		<form action="/jobseeker/saveResume" method="post"
			onsubmit="return validateJobSeekerForm()" class="resume-form">
			<div class="form-group">
				<label for="fullName">Full Name:</label> <input type="text"
					id="fullName" name="fullName" value="${resume.fullName}"
					oninput="validateFullName()" required />
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email" value="${resume.email}" oninput="validateEmail()"
					required />
			</div>
			<div class="form-group">
				<label for="phoneNumber">Phone Number:</label> <input type="text"
					id="phoneNumber" name="phoneNumber" value="${resume.phoneNumber}"
					oninput="validatePhoneNumber()" required />
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					id="address" name="address" value="${resume.address}" />
			</div>
			<div class="form-group">
				<label for="education">Education:</label>
				<textarea id="education" name="education" required>${resume.education}</textarea>
			</div>
			<div class="form-group">
				<label for="skills">Skills:</label>
				<textarea id="skills" name="skills" required>${resume.skills}</textarea>
			</div>
			<div class="form-group">
				<label for="experience">Experience:</label>
				<textarea id="experience" name="experience" required>${resume.experience}</textarea>
			</div>
			<button type="submit">Save Resume</button>
		</form>

		<h2 class="center-text">Resume View</h2>
		<div class="resume-container">
			<div class="resume-section">
				<h3>Full Name:</h3>
				<p>${resume.fullName}</p>
			</div>
			<div class="resume-section">
				<h3>Email:</h3>
				<p>${resume.email}</p>
			</div>
			<div class="resume-section">
				<h3>Phone Number:</h3>
				<p>${resume.phoneNumber}</p>
			</div>
			<div class="resume-section">
				<h3>Address:</h3>
				<p>${resume.address}</p>
			</div>
			<div class="resume-section">
				<h3>Education:</h3>
				<p>${resume.education}</p>
			</div>
			<div class="resume-section">
				<h3>Skills:</h3>
				<p>${resume.skills}</p>
			</div>
			<div class="resume-section">
				<h3>Experience:</h3>
				<p>${resume.experience}</p>
			</div>
			<button onclick="window.print()">Download Resume</button>
		</div>
	</div>
</body>
</html>
