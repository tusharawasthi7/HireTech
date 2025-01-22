<%@ page import="hire.tech.employer.entities.Job"%>
<%@ page import="hire.tech.utils.*"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit Jobs</title>
<script src="/js/employer.js"></script>
<link href="/css/updatejob.css" rel="stylesheet" type="text/css" />
</head>
<body>
 
	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a href="/employer/dashboard"
			class="back-button">Back to Dashboard</a>
	</div>
 
	<div class="container">
		<div class="formbox">
			<div class="form-container">
				<div class="info-container">
					<h1>Edit Job</h1>
				</div>
				<form id="editJobForm" action="/employer/updateJob" method="post"
					enctype="multipart/form-data" onsubmit="return validateJobForm()">
					<%
					Job job = (Job) request.getAttribute("job");
					%>
					<input type="hidden" name="job_id" value="<%=job.getJob_id()%>">
					<div class="form-group">
						<label for="title" class="form-label">Job Title</label> <input
							type="text" class="form-control" id="title" name="title" oninput = "jobTitle()"
							value="<%=job.getTitle()%>" required><span
							id="jobTitleError" class="error-message" style="color: red"></span>
					</div>
					
					
					
					
					<div class="form-group">
						<label for="skills" class="form-label">Job Skills</label> <input
							type="text" class="form-control" id="skills" oninput="validateSkills()"
							name="skills" value="<%=job.getSkills()%>" required>
					</div>
					<div class="form-group">
						<label for="location" class="form-label">Job Location</label> <input
							type="text" class="form-control" id="location"
							oninput="location()" name="job_location"
							value="<%=job.getJob_location()%>" required>
					</div>
					<div class="form-group">
						<label for="salary" class="form-label">Salary</label> <input
							type="text" class="form-control" id="salary_offered"
							oninput="validateSalary()" name="salary_offered"
							value="<%=job.getSalary_offered()%>" required>
					</div>
					<%-- 	<div class="form-group">
						<label for="type" class="form-label">Job Type</label> <input
							type="text" class="form-control" id="job_type" name="job_type"
							value="<%=job.getJob_type()%>" required>
 
				<select name="job_type" value="<%=job.getJob_type()%>"
							id="job_type" class="form-control" oninput = "job_type()" required>
							<option value="Select">Select</option>
							<option value="Full Time">Full Time</option>
							<option value="Part Time">Part Time</option>
						</select>
 
 
 
 
					</div> --%>
 
					<div class="form-group">
						<label for="type" class="form-label">Job Type</label> <select
							name="job_type" id="job_type" class="form-control"
							oninput = "job_type()" required>
							<option value="Full Time"
								<%=job.getJob_type().equals("Full Time") ? "selected" : ""%>>Full
								Time</option>
							<option value="Part Time"
								<%=job.getJob_type().equals("Part Time") ? "selected" : ""%>>Part
								Time</option>
						</select> <span id="requirementsError" class="error-message"
							style="color: red"></span>
					</div>
 
					<div class="form-group">
						<label for="description" class="form-label">Job
							Description (PDF)</label> <input type="file" class="form-control"
							id="description" name="description" oninput="description()"
							value="<%=job.getDescription()%>" accept="application/pdf"
							required>
					</div>
					<button type="submit" class="btn btn-primary">Update Job</button>
				</form>
			</div>
		</div>
	</div>
 
</body>
</html>
