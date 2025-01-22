<%@ page import="hire.tech.user.entities.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Profile</title>
<link href="../css/updateprofile.css" rel="stylesheet" type="text/css" />
<script src="../js/admin.js"></script>
</head>
<body>
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/admin/backToDashboard" class="back-button">Back To
			Dashboard</a>
	</div>
	<div class="container">
		<h2>Edit Profile</h2>
		<form action="<%=request.getContextPath()%>/admin/updateProfile"
			method="post" enctype="multipart/form-data"
			onsubmit="return validateForm()">
			<!-- Hidden userId to identify which user is being updated -->
			<input type="hidden" name="userId"
				value="<%=((User) request.getAttribute("user")).getUserId()%>">

			<!-- Username -->
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username"
					value="<%=((User) request.getAttribute("user")).getUsername()%>"
					oninput="validateUsername()">
			</div>

			<!-- Full Name -->
			<div class="form-group">
				<label for="fullName">Full Name:</label> <input type="text"
					id="fullName" name="fullName"
					value="<%=((User) request.getAttribute("user")).getFullName()%>"
					oninput="validateFullName()">
			</div>

			<!-- Email -->
			<div class="form-group">
				<label for="email">Email:</label> <input type="email" id="email"
					name="email"
					value="<%=((User) request.getAttribute("user")).getEmail()%>"
					oninput="validateEmail()">
			</div>

			<!-- Contact Number -->
			<div class="form-group">
				<label for="contactNumber">Contact Number:</label> <input
					type="text" id="contactNumber" name="contactNumber"
					value="<%=((User) request.getAttribute("user")).getContactNumber()%>"
					oninput="validateContactNumber()">
			</div>

			<!-- Profile Picture -->
			<div class="form-group">
				<label for="profilePicture">Profile Picture:</label> <input
					type="file" id="profilePicture" name="profilePicture"
					accept="image/*" required>
			</div>

			<div class="form-group">
				<button type="submit">Update Profile</button>
			</div>
		</form>
	</div>
</body>
</html>
