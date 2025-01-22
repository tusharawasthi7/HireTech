<%@ page import="hire.tech.user.entities.User"%>
<%@ page import="hire.tech.jobseeker.entities.Jobseeker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
<link href="../css/myprofile.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/jobseeker/openIndexPage" class="back-button">Back To
			Dashboard</a>
	</div>
	<div class="container">
		<div class="content">
			<div class="profile-details">

				<!-- Display success message -->
				<c:if test="${not empty successMessage}">
					<div class="success-message">${successMessage}</div>
				</c:if>

				<!-- Display error message -->
				<c:if test="${not empty errorMessage}">
					<div class="error-message">${errorMessage}</div>
				</c:if>

				<h1>My Profile</h1>
				<%
				User user = (User) request.getAttribute("user");
				Jobseeker jobseeker = (Jobseeker) request.getAttribute("jobseeker");
				if (user != null && jobseeker != null) {
				%>
				<table>
					<tr>
						<th>Username</th>
						<td><%=user.getUsername()%></td>
					</tr>
					<tr>
						<th>Full Name</th>
						<td><%=user.getFullName()%></td>
					</tr>
					<tr>
						<th>Email</th>
						<td><%=user.getEmail()%></td>
					</tr>
					<tr>
						<th>Contact Number</th>
						<td><%=user.getContactNumber()%></td>
					</tr>
					<tr>
						<th>Date of Birth</th>
						<td><%=jobseeker.getDateOfBirth()%></td>
					</tr>
					<tr>
						<th>Gender</th>
						<td><%=jobseeker.getGender()%></td>
					</tr>
					<tr>
						<th>Higher Education</th>
						<td><%=jobseeker.getHigherEducation()%></td>
					</tr>
					<tr>
						<th>Skills</th>
						<td><%=jobseeker.getSkills()%></td>
					</tr>
					<tr>
						<th>Resume</th>
						<td><a
							href="/jobseeker/downloadResume?jobSeekerId=<%=jobseeker.getJobseekerId()%>">Download
								Resume</a></td>
					</tr>
				</table>
				<a href="<%=request.getContextPath()%>/jobseeker/editprofile"
					class="update-button">Edit Profile</a> <a
					href="<%=request.getContextPath()%>/jobseeker/resetPasswordPage"
					class="reset-button">Reset Password</a>
				<%
				} else {
				%>
				<p>Profile details not found.</p>
				<%
				}
				%>
			</div>
			<div class="profile-picture">
				<%
				if (user != null && user.getProfilePicture() != null) {
				%>
				<img
					src="data:image/jpeg;base64,<%=java.util.Base64.getEncoder().encodeToString(user.getProfilePicture().getBytes())%>"
					alt="Profile Picture">
				<%
				}
				%>
			</div>
		</div>

	</div>
</body>
</html>
