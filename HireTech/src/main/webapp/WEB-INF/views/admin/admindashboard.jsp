<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link href="../css/admindashboard.css" rel="stylesheet" type="text/css" />
<script src="../js/admin.js"></script>
</head>
<body>
	<!-- Header -->
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo">
		<h1 class="title">Welcome Admin!</h1>
		<div class="right-menu">
			<a href="/admin/logout" onclick="logout(event)" class="logout-button">Logout</a>
		</div>
	</div>

	<!-- Sidebar -->
	<div class="sidebar">
		<a href="#" class="home-link active">Home</a> <a
			href="/admin/rejectrequests" class="rejected-link">Rejected
			Requests</a> <a href="/admin/approvedrequests" class="approved-link">Approved
			Requests</a> <a href="/admin/pendingrequests" class="pending-link">Pending
			Requests</a> <a href="/admin/subscriptions" class="subscription-link">View
			Subscriptions</a> <a href="/admin/profile" class="profile-link">Admin
			Profile</a>
		<!-- Moved Profile link to the bottom -->
	</div>

	<!-- Content -->
	<div class="content">
		<!-- Dashboard -->
		<div class="dashboard">
			<p class="overview-text">Here's an overview of your dashboard
				statistics</p>
			<div class="stats-container">
				<div class="stat-box">
					<h2>Total Employers</h2>
					<button onclick="redirectTo('/admin/getemployerinfo')">View
						Employers</button>
				</div>
				<div class="stat-box">
					<h2>Total Job Seekers</h2>
					<button onclick="redirectTo('/admin/getjobseekersinfo')">View
						Job Seekers</button>
				</div>
				<div class="stat-box">
					<h2>Total Jobs</h2>
					<button onclick="redirectTo('/admin/getjobsinfo')">View
						Jobs</button>
				</div>
			</div>
		</div>

		<!-- Subscriptions -->
		<div class="subscriptions" style="display: none;">
			<!--  <p class="overview-text">Here's an overview of Jobseeker subscriptions</p> -->
			<div class="stats-container">
				<div class="stat-box">
					<h2>Jobseeker Subscriptions</h2>
					<button onclick="redirectTo('/admin/jobseekersubscriptions')">View</button>
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
                if (this.classList.contains('home-link')) {
                    document.querySelector('.dashboard').style.display = 'block';
                } else if (this.classList.contains('pending-link')) {
                    window.location.href = '/admin/pendingrequests';
                } else if (this.classList.contains('approved-link')) {
                    window.location.href = '/admin/approvedrequests';
                } else if (this.classList.contains('rejected-link')) {
                    window.location.href = '/admin/rejectrequests';
                } else if (this.classList.contains('profile-link')) {
                    window.location.href = '/admin/profile';
                } else if (this.classList.contains('subscription-link')) {
                    document.querySelector('.subscriptions').style.display = 'block';
                }
            });
        });
        
        // Set Home as default
        document.querySelector('.dashboard').style.display = 'block';
    </script>
</body>
</html>
