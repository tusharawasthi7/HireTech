<%@ page import="hire.tech.jobseeker.entities.Application"%>
<%@ page import="hire.tech.jobseeker.entities.Job"%>
<%@ page import="java.util.List"%>
<%@ page import="hire.tech.utils.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Applied Jobs</title>
<link href="../css/appliedjob.css" rel="stylesheet" type="text/css" />
<script src="../js/jobseeker.js"></script>
<body>
	<div class="navbar">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/jobseeker/openIndexPage" class="dashboard-button">Back to
			Dashboard</a>
	</div>
	<h1>Applied Jobs</h1>
	<%
	List<Application> appliedJobs = (List<Application>) request.getAttribute("appliedJobs");
	List<Job> allJobs = (List<Job>) request.getAttribute("allJobs"); // Fetch all jobs from attribute
	if (appliedJobs != null && !appliedJobs.isEmpty()) {
	%>
	<div class="job-container">
		<%
		for (Application app : appliedJobs) {
			Job job = null;
			for (Job j : allJobs) {
				if (j.getJobId() == app.getJobId()) {
			job = j;
			break;
				}
			}
			if (job != null) {
		%>
		<div class="job-card">
			<h2 class="job-title"><%=job.getTitle()%></h2>
			<p>
				<strong>Company Name:</strong>
				<%=job.getCompanyName()%>
			</p>
			<p>
				<strong>Location:</strong>
				<%=job.getJobLocation()%>
			</p>
			<p>
				<strong>Skills:</strong>
				<%=job.getSkills()%>
			</p>
			<p>
				<strong>Application Status:</strong>
				<%=app.getStatus()%>
			</p>
			<p>
				<strong>Application Date:</strong>
				<%=app.getApplicationDate()%>
			</p>
			<p>
				<%-- <strong>Job Description:</strong> <a
					href="/jobseeker/downloadJobDescription?jobId=<%=job.getJobId()%>"
					class="description-button">Download</a> --%>


				<%
				if (job.getJobDescriptionPdf() != null) {
					String base64Link = Utils.getBase64PdfDownloadLink(job.getJobDescriptionPdf());
					if (base64Link != null) {
				%>
				<a href="<%=base64Link%>" download="description.pdf"><i
					class="fas fa-download mx-2"></i>Download Description</a>

				<%
				}
				}
				%>






			</p>
			<form action="/jobseeker/deleteAppliedJob"
				onclick="confirmDelete(event)" method="post" class="delete-form">
				<input type="hidden" name="applicationId"
					value="<%=app.getApplicationId()%>" />
				<button type="submit" class="delete-button">Delete</button>
			</form>
		</div>
		<%
		}
		}
		%>
	</div>
	<%
	} else {
	%>
	<p>No applications found.</p>
	<%
	}
	%>
</body>
</html>
