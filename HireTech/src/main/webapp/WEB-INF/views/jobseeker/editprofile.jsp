<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
<link href="../css/editprofilej.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/jobseeker/openIndexPage" class="back-button">Back To
			Dashboard</a>
		<script src="../js/jobseeker.js"></script>
	</div>
	<div class="container">
		<h1>Edit Profile</h1>
		<form action="/jobseeker/updateProfile" method="POST"
			enctype="multipart/form-data"
			onsubmit="return validateJobSeekerForm()">
			<div class="form-container">

				<label for="username">Username</label> <input type="text"
					id="username" name="username" value="${user.username}"
					oninput="validateUsername()" required> <label
					for="fullName">Full Name</label> <input type="text" id="fullName"
					name="fullName" value="${user.fullName}"
					oninput="validateFullName()" required> <label for="email">Email</label>
				<input type="email" id="email" name="email" value="${user.email}"
					oninput="validateEmail()" required> <label
					for="contactNumber">Contact Number</label> <input type="text"
					id="contactNumber" name="contactNumber"
					value="${user.contactNumber}" oninput="validateContactNumber()"
					required> <label for="dateOfBirth">Date of Birth</label> <input
					type="date" id="dateOfBirth" name="dateOfBirth"
					value="${jobseeker.dateOfBirth}" oninput="validateDateOfBirth()"
					required> <label for="gender">Gender</label> <input
					type="text" id="gender" name="gender" value="${jobseeker.gender}"
					required> <label for="higherEducation">Higher
					Education</label> <input type="text" id="higherEducation"
					name="higherEducation" value="${jobseeker.higherEducation}"
					required> <label for="skills">Skills</label> <input
					type="text" id="skills" name="skills" value="${jobseeker.skills}"
					required> <label for="resume">Resume</label> <input
					type="file" id="resume" name="resume" accept="application/pdf">

				<label for="profilePicture">Profile Picture:</label> <input
					type="file" id="profilePicture" name="profilePicture"
					accept="image/*">


				<button type="submit">Update Profile</button>
			</div>
		</form>
	</div>
</body>
</html>
