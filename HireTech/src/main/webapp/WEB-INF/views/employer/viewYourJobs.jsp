<%@ page import="java.util.List"%>
<%@ page import="hire.tech.employer.entities.Job"%>
<%@ page import="hire.tech.utils.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Your Jobs - HireTech</title>
<link href="../css/viewYourJobs.css" rel="stylesheet" type="text/css" />
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
		<h2 class="text-center mb-4">Posted Jobs</h2>
		<div id="jobList">
			<%
			List<Job> jobs = (List<Job>) request.getAttribute("jobs");
			%>
			<%
			List<Job> listOfJobs = (List<Job>) request.getAttribute("listOfJobs");
			if (listOfJobs == null || listOfJobs.isEmpty()) {
			%>
			<div class="alert alert-info" role="alert">No jobs posted yet.</div>
			<%
			} else {
			for (Job job : listOfJobs) {
			%>
			<div class="job-card mx-auto">
				<div>
					<h3>
						Job title :
						<%=job.getTitle()%></h3>
					<p>
						Job Skills :
						<%=job.getSkills()%></p>
					<p>
					<p>
						Job Location :
						<%=job.getJob_location()%></p>
					<p>
						Salary :
						<%=job.getSalary_offered()%></p>
					<p>
						Job type :
						<%=job.getJob_type()%></p>

					<hr />
					<%
					if (job.getDescription() != null) {
						String base64Link = Utils.getBase64PdfDownloadLink(job.getDescription());
						if (base64Link != null) {
					%>
					<a href="<%=base64Link%>" download="description.pdf"><i
						class="fas fa-download mx-2"></i>Download Description</a>
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

				</div>
				<br />
				<div class="button-group">
					<a href="/employer/editJob/<%=job.getJob_id()%>">
						<button class="btn btn-warning">Update</button>
					</a> <a href="/employer/deleteJob/<%=job.getJob_id()%>"
						onclick="return confirm('Are you sure you want to delete this job?');">
						<button class="btn btn-danger">Delete</button>
					</a>
				</div>

			</div>
			<br />
			<%
			}
			}
			%>
		</div>

	</div>
<script>
        
        <% String message = (String) session.getAttribute("message"); %>
        <% if (message != null) { %>
            alert("<%= message %>");
            <% session.removeAttribute("message"); %>
        <% } %>
    </script>


</body>
</html>
