<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enter OTP</title>
<link href="../css/enterotp.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<div class="logo">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="Company Logo">
	</div>

	<div class="container">
		<h1>Enter OTP</h1>
		<!-- Form to enter OTP -->
		<form action="/verifyOtp" method="post">

			<input type="hidden" name="email" value="${email}" /> <label
				for="otp">Enter OTP sent to your email:</label> <input type="text"
				id="otp" name="otp" required placeholder="Enter OTP" maxlength="6">
			<input type="submit" value="Verify OTP">

		</form>
		<p>
			<a href="/forgotPassword">Back to Forgot Password</a>
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
