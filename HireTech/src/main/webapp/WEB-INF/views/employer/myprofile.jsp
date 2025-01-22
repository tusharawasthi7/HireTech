<%@ page import="hire.tech.employer.entities.Job"%>
<%@ page import="hire.tech.employer.entities.Employer"%>
<%@ page import="hire.tech.user.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page import="hire.tech.utils.Utils"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Employer Profile - HireTech</title>
<link href="/css/myprofilee.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a href="/employer/dashboard"
			class="back-button">Back to Dashboard</a>
	</div>
	<div class="container">
		<div class="content">
			<div class="profile-images">
				<div class="company-logo">
					<%
                    List<Employer> employerdetails = (List<Employer>) request.getAttribute("employerdetails");
                    if (employerdetails != null && !employerdetails.isEmpty()) {
                        Employer employer = employerdetails.get(0);
                        if (employer != null && employer.getCompanyLogo() != null) {
                    %>
					<img
						src="data:image/jpeg;base64,<%=java.util.Base64.getEncoder().encodeToString(employer.getCompanyLogo().getBytes())%>"
						alt="Company Logo">
					<%
                        }
                    }
                    %>
				</div>
				<div class="profile-picture">
					<%
                    List<User> userdetails = (List<User>) request.getAttribute("userdetails");
                    if (userdetails != null && !userdetails.isEmpty()) {
                        User user = userdetails.get(0);
                        if (user != null && user.getProfilePicture() != null) {
                    %>
					<img
						src="data:image/jpeg;base64,<%=java.util.Base64.getEncoder().encodeToString(user.getProfilePicture().getBytes())%>"
						alt="Profile Picture">
					<%
                        }
                    }
                    %>
				</div>
			</div>
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
                if (employerdetails != null && !employerdetails.isEmpty() && userdetails != null && !userdetails.isEmpty()) {
                    Employer employer = employerdetails.get(0);
                    User user = userdetails.get(0);
                %>
				<table>
					<tr>
						<th>Company Name</th>
						<td><%=employer.getCompanyName()%></td>
					</tr>
					<tr>
						<th>Industry Type</th>
						<td><%=employer.getIndustryType()%></td>
					</tr>
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
				<a href="<%=request.getContextPath()%>/employer/editProfile"
					class="update-button">Edit Profile</a> <a
					href="<%=request.getContextPath()%>/employer/resetPasswordPage"
					class="reset-button">Reset Password</a>
				<%
                } else {
                %>
				<p>Profile details not found.</p>
				<%
                }
                %>
			</div>
		</div>
	</div>
	<script>
        
        <% String message = (String) session.getAttribute("message"); %>
        <% if (message != null) { %>
            alert("<%= message %>");
            <% session.removeAttribute("message"); %>
        <% } %>
    </script>
	
</body>
</html>
