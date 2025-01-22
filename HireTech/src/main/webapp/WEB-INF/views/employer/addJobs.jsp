<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Add Jobs</title>
<link href="../css/addJob.css" rel="stylesheet" type="text/css" />
<script src="/js/employer.js"></script>
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
					<h1>Add Job</h1>
				</div>
				<form id="addJobForm" action="/employer/insertJob"
					enctype="multipart/form-data" method="post"
					onsubmit="return validateJobForm()">
					<div class="form-group">
						<label for="title">Job Title</label> <input type="text"
							name="title" id="title" class="form-control" oninput="jobTitle()"
							required /> <span id="jobTitleError" class="error-message"
							style="color: red"></span>
					</div>
					<div class="form-group">
						<label for="job_type">Job Type</label> <select name="job_type"
							id="job_type" class="form-control" oninput="job_type()" required>
							<option value="Select">Select</option>
							<option value="Full Time">Full Time</option>
							<option value="Part Time">Part Time</option>
						</select> <span id="requirementsError" class="error-message"
							style="color: red"></span>
					</div>
					<div class="form-group">
						<label for="salary">Salary (per month)</label> <input type="text"
							name="salary_offered" id="salary_offered" class="form-control"
							oninput="validateSalary()" required /> <span id="salaryError"
							class="error-message" style="color: red"></span>
					</div>
					<div class="form-group">
						<label for="job_location">Location</label> <input type="text"
							name="job_location" id="job_location" class="form-control"
							oninput="location()" required /> <span id="locationError"
							class="error-message" style="color: red"></span>
					</div>
					<div class="form-group">
						<label for="skills">Skills</label>
						<textarea id="skills" name="skills" class="form-control" rows="1"
							oninput="validateSkills()" required></textarea>
						<span id="skillsError" class="error-message" style="color: red"></span>
					</div>
					<!-- 	<div class="form-group">
						<label for="description">Job Requirements PDF</label> <input
							type="file" name="description" id="description" oninput = "description()"
							class="form-control" accept=".pdf" required />
					</div> -->
 
 
					<div class="form-group">
						<label for="description">Job Requirements PDF</label> <input
							type="file" name="description" id="description"
							oninput="description()" class="form-control" accept=".pdf"
							required />
						<span id="error-message" style="color: red;"></span>
					</div>
 
					<div class="form-group btn-container">
						<button type="submit" class="btn-custom my-2">Add Job</button>
					</div>
					<%
					System.out.println();
					%>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
 