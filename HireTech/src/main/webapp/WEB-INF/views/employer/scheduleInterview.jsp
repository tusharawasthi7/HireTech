<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Schedule Interview - HireTech</title>
<link href="../css/scheduleinterview.css" rel="stylesheet"
	type="text/css" />
<script src="../js/employer.js"></script>
</head>
<body>
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a href="/employer/dashboard"
			class="back-button">Back to Dashboard</a>
	</div>

	<div class="container">
		<div class="info-container">
			<h1>Schedule Interview</h1>
		</div>
		<div class="form-container">
			<form id="scheduleForm" action="/employer/sendMail" method="post">
				<label for="applicantEmail">Applicant Email</label> <input
					type="email" id="applicantEmail" name="applicantEmail"
					value="${email}" placeholder="Enter Applicant's Email" required />
				<label for="message">Description</label>
				<textarea id="message" name="message" rows="4"
					placeholder="Write your message" required></textarea>
				<button type="submit">Send Mail</button>
				
				
				<c:if test="${not empty successMessage}">
					<div class="alert alert-success" role="alert">
						${successMessage}</div>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>
