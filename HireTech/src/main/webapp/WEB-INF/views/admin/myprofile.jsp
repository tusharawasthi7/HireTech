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
			href="/admin/backToDashboard" class="back-button">Back To
			Dashboard</a>
	</div>
	<div class="container">

		<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.print("<div class='message'><strong>" + message + "</strong></div>");
		}
		%>
		<div class="content">
			<div class="profile-details">
				<h1>My Profile</h1>
				<%
				User user = (User) request.getAttribute("user");

				if (user != null) {
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
				</table>
				<a href="<%=request.getContextPath()%>/admin/openUpdateProfilePage"
					class="update-button">Edit Profile</a> <a
					href="<%=request.getContextPath()%>/admin/resetPasswordPage"
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
