<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link href="../css/resetPassword.css" rel="stylesheet" type="text/css" />
<script src="../js/jobseeker.js"></script>
</head>
<body>



	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo">
		<div class="nav-buttons">
			<a href="/employer/profile" class="back-button">Back to Profile</a> <a
				href="/employer/dashboard" class="back-button">Back to Dashboard</a>
		</div>
	</div>
	<div class="content">
		<h1>Reset Password</h1>

		<!-- Display success message -->
		<c:if test="${not empty successMessage}">
			<div class="success-message">${successMessage}</div>
		</c:if>

		<!-- Display error message -->
		<c:if test="${not empty errorMessage}">
			<div class="error-message">${errorMessage}</div>
		</c:if>

		<form action="/employer/updatePassword" method="POST"
			onsubmit="return validateJobSeekerForm()">
			<label for="currentPassword">Current Password</label> <input
				type="password" id="currentPassword" name="currentPassword" required>

			<label for="newPassword">New Password</label> <input type="password"
				id="newPassword" name="newPassword" oninput="validateNewPassword()"
				required> <label for="confirmPassword">Confirm
				Password</label> <input type="password" id="confirmPassword"
				name="confirmPassword" oninput="validateConfirmPassword()" required>

			<button type="submit">Reset Password</button>
		</form>
	</div>
</body>
</html>
