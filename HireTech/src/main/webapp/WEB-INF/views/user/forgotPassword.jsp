<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>
<link href="../css/forgot.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="logo">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="Company Logo">
	</div>
	<div class="container">
		<h1>Forgot Password</h1>
		<!-- Form for entering email to request OTP -->
		<form action="/sendOtp" method="post">
			<label for="email">Email</label> <input type="email" id="email"
				name="email" required placeholder="Enter your mail"> <input
				type="submit" value="Send OTP">
		</form>

		<p>
			<a href="/user/loginPage">Back to Login</a>
		</p>

		<!-- Display error or success messages -->
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
