<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Page</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<script src="../js/user.js"></script>
</head>

<body>
	<div class="logo">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="Company Logo">
	</div>
	<div class="login-container">
		<h1>Welcome Back</h1>
		<p>Please login to continue</p>


		<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
		%>
		<div class="warning"><%=message%></div>
		<%
		}
		%>

		<!-- Login Form -->
		<form action="/user/login" method="post">
			<label for="username">Username</label> <input type="text"
				id="username" name="username" placeholder="Enter Your Username"
				required> <label for="password">Password</label> <input
				type="password" id="password" name="password"
				placeholder="Enter Password" required>

			<button id="login-button" type="submit">Login</button>
		</form>

		<p>
			Don't have an account? <a href="/user/registrationPage">Register
				Here</a>
		</p>

		<div class="links">
			<a href="/forgotPassword" class="forgotpassword">Forgot Password</a>
			<a href="/user/indexPage" class="home">Home</a>
		</div>
	</div>
</body>
</html>