<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Certifications</title>
<link href="../css/certifications.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="navbar">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/jobseeker/openIndexPage" class="dashboard-button">Back to
			Dashboard</a>
	</div>
	<div class="container">
		<c:choose>
			<c:when test="${subscribed}">
				<h2 class="center-text">Here's the place where you can Learn
					and Earn the Certifications!</h2>
				<p class="center-text">You can explore to exclusive
					certification content.</p>
			</c:when>
		</c:choose>
		<div class="course-list">
			<div class="course-card">
				<h2>Introduction to Java</h2>
				<p>Learn the basics of Java programming.</p>
				<a href="https://www.example.com/java-intro" target="_blank"
					class="watch-video-button">Watch Video</a>
			</div>
			<div class="course-card">
				<h2>Spring Framework Basics</h2>
				<p>Understand the fundamentals of Spring Framework.</p>
				<a href="https://www.example.com/spring-basics" target="_blank"
					class="watch-video-button">Watch Video</a>
			</div>
			<div class="course-card">
				<h2>Advanced Java</h2>
				<p>Deep dive into advanced Java concepts.</p>
				<a href="https://www.example.com/advanced-java" target="_blank"
					class="watch-video-button">Watch Video</a>
			</div>
		</div>
	</div>
</body>
</html>
