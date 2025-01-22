<%@ page import="hire.tech.employer.entities.Job"%>
<%@ page import="hire.tech.employer.entities.Employer"%>
<%@ page import="hire.tech.employer.entities.Applications"%>
<%@ page import="java.util.List"%>
<%@ page import="hire.tech.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>View Approved Applicants - HireTech</title>
<link href="../css/viewapproved.css" rel="stylesheet" type="text/css" />
<script src="../js/employer.js"></script>
</head>
<body>
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo">
		<div class="nav-buttons">
			<a href="/employer/viewApplicants" class="back-button">Back to
				All Applicants</a> <a href="/employer/dashboard" class="back-button">Back
				to Dashboard</a>
		</div>
	</div>

	<div class="container">
		<h2 class="text-center mb-4">
			<i class="fa fa-spinner fa-spin"></i> Approved Applicants
		</h2>
		<div class="card-container">
			<%
			List<Applications> applications = (List<Applications>) request.getAttribute("applications");
			if (applications == null || applications.isEmpty()) {
			%>
			<div class="alert alert-info" role="alert">
				<h3>No approved applicants available.</h3>
			</div>
			<%
			} else {
			for (Applications applicant : applications) {
			%>
			<div class="applicant-card">
				<h3><%=applicant.getTitle()%></h3>
				<p>
					<b>Applicant Name : </b><%=applicant.getFullName()%></p>
				<p>
					<b>Applicant Email : </b><%=applicant.getEmail()%></p>
				<p>
					<b>Applicant Contact no. : </b><%=applicant.getMobileNo()%></p>
				<p>
					<b>Applicant Highest Qualification : </b><%=applicant.getHigherEducation()%></p>
				<p>
					<b>Applicant Skills : </b><%=applicant.getSkills()%></p>
				<p>
					<b>Application Date : </b><%=applicant.getApplicationDate()%></p>

				<hr />
				<p class="text-primary">
					<i class="fa fa-info-circle mx-2"></i><b>Applicant Status : </b><%=applicant.getApplicationStatus()%>
				</p>
				<hr />
				<%
				if (applicant.getResume() != null) {
					String base64Link = Utils.getBase64PdfDownloadLink(applicant.getResume());
					if (base64Link != null) {
				%>
				<a href="<%=base64Link%>" download="resume.pdf"><i
					class="fas fa-download mx-2"></i>Download Resume</a>
				<%
				} else {
				%>
				<p>Resume unavailable</p>
				<%
				}
				} else {
				%>
				<p>Resume unavailable</p>
				<%
				}
				%>
				<hr />
				<div class="card-buttons">
					<div class="row mt-3 w-100">
						<div class="col-sm-6">
							<a
								href="/employer/rejectApplicant/<%=applicant.getApplicationId()%>/<%=applicant.getApplicationStatus()%>/0">
								<button class="reject-button" onclick="confirmRejection(event)">Reject
									Applicant</button>
							</a>
						</div>
						<div class="col-sm-6">
							<a
								href="/employer/scheduleInterview?email=<%=applicant.getEmail()%>">
								<button class="schedule-button">Schedule Interview</button>
							</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			}
			%>
		</div>
	</div>
</body>
</html>

