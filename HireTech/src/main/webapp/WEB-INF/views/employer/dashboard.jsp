<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Employer Dashboard - HireTech</title>
<link href="../css/employer.css" rel="stylesheet" type="text/css" />
<script src="../js/employer.js"></script>
</head>
<body>
	<div class="navbar">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo">
		<h1 class="title">Welcome To HireTech!</h1>
		<div class="right-menu">
			<a href="/employer/logout" onclick="logout(event)"
				class="logout-button">Logout</a>
		</div>
	</div>

	<div class="container">
		<aside class="sidebar">
			<a href="/employer/dashboard" class="home-link active">Home</a> <a
				href="/employer/addJobs" class="add-job-link">Add Job</a> <a
				href="/employer/viewApplicants" class="view-applicants-link">View
				Applicants</a> <a href="/employer/viewAllYourJobs"
				class="view-jobs-link">View Your Jobs</a> <a
				href="/employer/scheduleInterview" class="schedule-interview-link">Schedule
				Interview</a> <a href="/employer/profile" class="profile-link">Profile</a>
		</aside>
		<main class="main-content">
			<div class="dashboard">
				<div class="welcome-msg">
					<h3>Here's an Overview of your Dashboard Statistics</h3>
				</div>
				<div class="stats-container">
					<%
					Integer totalJobs = (Integer) request.getAttribute("totalJobs");
					Integer totalApplications = (Integer) request.getAttribute("totalApplications");
					%>
					<div class="stat-box">
						<h2>Total Jobs</h2>
						<button class="stat-button"><%=(totalJobs != null) ? totalJobs.toString() : "3"%></button>
					</div>
					<div class="stat-box">
						<h2>Total Applicants</h2>
						<button class="stat-button"><%=(totalApplications != null) ? totalApplications.toString() : "1"%></button>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
