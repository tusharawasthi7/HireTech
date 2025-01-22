<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Reset Password</title>
<link href="../css/change.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="logo">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="Company Logo">
	</div>
	<div class="container">
		<h1>Reset Your Password</h1>
		<!-- Form to reset the password -->
		<form action="/resetPassword" method="post">
			<input type="hidden" name="email" value="${email}" /> <label
				for="newPassword">New Password:</label> <input type="password"
				id="newPassword" name="newPassword" required
				placeholder="Enter new password"> <label
				for="confirmPassword">Confirm Password:</label> <input
				type="password" id="confirmPassword" name="confirmPassword" required
				placeholder="Confirm new password"> <input type="submit"
				value="Reset Password">
		</form>
		<p>
			<a href="/user/loginPage">Back to Login</a>
		</p>
		<p style="color: red;">
			<% String error = (String) request.getAttribute("error");
        if (error != null) {
          out.print(error);
        }
      %>
		</p>
		<p style="color: green;">
			<% String message = (String) request.getAttribute("message");
        if (message != null) {
          out.print(message);
        }
      %>
		</p>
	</div>
</body>
</html>
