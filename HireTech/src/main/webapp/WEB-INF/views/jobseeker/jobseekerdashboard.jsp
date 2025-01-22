<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Job Seeker Dashboard</title>
<link href="../css/jobseekerstyle.css" rel="stylesheet" type="text/css" />
<link href="../css/jobseekerdashboard.css" rel="stylesheet"
	type="text/css" />
<!-- Link to the new CSS file -->
<script src="../js/jobseeker.js"></script>
</head>
<body>
	<!-- Header -->
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo">
		<h1 class="title">Welcome To HireTech!</h1>
		<div class="right-menu">
			<a href="${pageContext.request.contextPath}/jobseeker/profile"
				class="profile-button">Profile</a> <a
				href="${pageContext.request.contextPath}/jobseeker/logout"
				onclick="logout(event)" class="logout-button">Logout</a>
		</div>
	</div>

	<!-- Sidebar -->
	<div class="sidebar">
		<a href="${pageContext.request.contextPath}/jobseeker/search"
			class="search-link active">Home</a> <a
			href="${pageContext.request.contextPath}/jobseeker/appliedjob"
			class="applied-link">Applied Jobs</a> <a
			href="${pageContext.request.contextPath}/jobseeker/savedjobs"
			class="saved-link">Saved Jobs</a> <a
			href="${pageContext.request.contextPath}/jobseeker/subscriptions"
			class="subscriptions-link">Subscription Plans</a> <a
			href="${pageContext.request.contextPath}/jobseeker/certifications"
			class="certifications-link">Certifications</a> <a
			href="${pageContext.request.contextPath}/jobseeker/resumeBuilder"
			class="resume-builder-link">Resume Builder</a> <a
			href="${pageContext.request.contextPath}/jobseeker/help"
			class="help-link">Help</a>
	</div>

	<!-- Content -->
	<div class="content">
		<!-- Dashboard -->
		<div class="dashboard" style="display: block;">
			<h3 class="overview-text">HireTech, your gateway to a world of
				opportunities!</h3>
			<div class="stats-container">
				<div class="stat-box">
					<h4>HireTech, offers a comprehensive range of job listings
						tailored to your skills and preferences, making your job search
						efficient and effective. We are dedicated to connecting talented
						job seekers with top employers across various industries.</h4>
					<button class="view-jobs-button"
						onclick="redirectTo('/jobseeker/search')"><strong> Click here to Search all Jobs</strong></button>
				</div>
			</div>
		</div>
	</div>

	<script>
        // Redirect Function
        function redirectTo(url) {
            window.location.href = url;
        }
 
        // Sidebar Navigation
        document.querySelectorAll('.sidebar a').forEach(link => {
            link.addEventListener('click', function (event) {
                event.preventDefault();
                
                // Highlight the active link
                document.querySelectorAll('.sidebar a').forEach(l => l.classList.remove('active'));
                this.classList.add('active');
 
                // Hide all sections
                document.querySelectorAll('.content > div').forEach(div => div.style.display = 'none');
                
                // Show the selected section
                if (this.classList.contains('search-link')) {
                    document.querySelector('.dashboard').style.display = 'block';
                } else if (this.classList.contains('applied-link')) {
                    window.location.href = '/jobseeker/appliedjob';
                } else if (this.classList.contains('saved-link')) {
                    window.location.href = '/jobseeker/savedjobs';
                } else if (this.classList.contains('subscriptions-link')) {
                    window.location.href = '/jobseeker/subscriptions';
                } else if (this.classList.contains('certifications-link')) {
                    window.location.href = '/jobseeker/certifications';
                } else if (this.classList.contains('resume-builder-link')) {
                    window.location.href = '/jobseeker/resumeBuilder';
                } else if (this.classList.contains('help-link')) {
                    window.location.href = '/jobseeker/help';
                }
            });
        });
 
        // Set Search Jobs as default
        document.querySelector('.search-link').click();
    </script>
</body>
</html>
