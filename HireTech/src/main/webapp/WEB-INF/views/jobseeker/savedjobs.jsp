<%@ page import="hire.tech.jobseeker.entities.Job"%>
<%@ page import="hire.tech.jobseeker.entities.SavedJob"%>
<%@ page import="hire.tech.utils.*"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Saved Jobs</title>
<link href="../css/savedjobs.css" rel="stylesheet" type="text/css" />
<script src="../js/jobseeker.js"></script>
<style>
.success-message {
	color: green;
}
</style>
</head>
<body>
	<div class="navbar">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/jobseeker/openIndexPage" class="dashboard-button">Back to
			Dashboard</a>
	</div>
	<h1>Saved Jobs</h1>
	<%
	List<SavedJob> savedJobs = (List<SavedJob>) request.getAttribute("savedJobs");
	List<Job> allJobs = (List<Job>) request.getAttribute("allJobs"); // Fetch all jobs from attribute
	Integer jobSeekerId = (Integer) request.getAttribute("jobSeekerId");
	
	System.out.println(savedJobs + "################################3");// Ensure jobSeekerId is retrieved
	System.out.println(allJobs + "################################3");// 
	%>
	<%
	String message = (String) request.getAttribute("message");
	if (message != null) {
	%>
	<div class="success-message"><%=message%></div>
	<%
	}
	%>
	<%
	if (savedJobs != null && !savedJobs.isEmpty()) {
	%>
	<div class="job-container">
		<%
		for (SavedJob savedJob : savedJobs) {
			Job job = null;
			for (Job j : allJobs) {
		
				if (j.getJobId() == savedJob.getJobId()) {
			job = j;
			break;
				}
			}
			if (job != null) {
				System.out.println(job.getJobDescriptionPdf() + "################################3");// 
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
				<strong>Saved Date:</strong>
				<%=savedJob.getSaved_date()%>
			</p>
			<p>
				
				<%-- <strong>Description:</strong> <a
					href="/jobseeker/downloadJobDescription?jobId=<%=job.getJobId()%>"
					class="description-button">Download</a>  --%>
				
		    <%
					if (job.getJobDescriptionPdf() != null) {
						String base64Link = Utils.getBase64PdfDownloadLink(job.getJobDescriptionPdf());
						if (base64Link != null) {
					%>
					<a href="<%=base64Link%>" download="description.pdf"><i
						class="fas fa-download mx-2"></i>Download Description</a>
 
                 <%}
					}
                 
                 %>
					
					
			</p>
			<div class="button-container">
				<button class="apply-button"
					onclick="showConfirmationPopup(<%=job.getJobId()%>)">Apply</button>
				<form action="/jobseeker/deleteSavedJob"
					onclick="confirmDelete(event)" method="get" class="delete-form">
					<input type="hidden" name="savedJobId"
						value="<%=savedJob.getSavedJobId()%>" />
					<button type="submit" class="delete-button">Delete</button>
				</form>
			</div>
		</div>
		<%
		}
		}
		%>
	</div>
	<%
	} else {
	%>
	<p>No saved jobs found.</p>
	<%
	}
	%>
 
   <!-- Application form container displayed in the center -->
    <div id="applyFormContainer" class="form-container">
        <button type="button" class="close-btn" onclick="closeForm()">Close</button>
        <h2>Apply for a Job</h2>
        <form action="/jobseeker/submitApplication" method="post" enctype="multipart/form-data" onsubmit="return validateJobSeekerForm()">
            <input type="hidden" id="jobIdInput" name="jobId" value="">
            <input type="hidden" id="jobSeekerIdInput" name="jobSeekerId" value="<%=jobSeekerId != null ? jobSeekerId : ""%>">
            <div class="form-group">
                <label for="fullName">Full Name</label>
                <input type="text" id="fullName" name="name" oninput="validateFullName()" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender</label>
                <select id="gender" name="gender" required>
                    <option value="Select">Select</option>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dateOfBirth">Date of Birth</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" oninput="validateDateOfBirth()" required>
            </div>
            <div class="form-group">
                <label for="skills">Skills</label>
                <textarea id="skills" name="skills" rows="4" required></textarea>
            </div>
            <div class="form-group">
                <label for="resume">Resume</label>
                <input type="file" id="resume" name="resume" accept=".pdf,.doc,.docx" required>
            </div>
            <div class="form-group">
                <label for="availability">Availability:</label>
                <select id="availability" name="availability" required>
                    <option value="Select">Select</option>
                    <option value="Immediate">Immediate</option>
                    <option value="After 6 months">After 6 months</option>
                    <option value="After 1 year">After 1 year</option>
                </select>
            </div>

            <div class="form-group">
                <button type="submit">Submit Application</button>
            </div>
        </form>
    </div>

    <button onclick="topFunction()" id="scrollTopBtn" title="Go to top">Top</button>

    <script>
        function showConfirmationPopup(jobId) {
            const userConfirmed = confirm("Would you like to apply?");
            if (userConfirmed) {
                showForm(jobId);
            }
        }

        function showForm(jobId) {
            document.getElementById("applyFormContainer").style.display = "block";
            document.getElementById("jobIdInput").value = jobId;
            var jobSeekerId = "<%=jobSeekerId%>";
            document.getElementById("jobSeekerIdInput").value = jobSeekerId;
        }

        function closeForm() {
            document.getElementById("applyFormContainer").style.display = "none";
        }

        window.onscroll = function() { scrollFunction() };

        function scrollFunction() {
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                document.getElementById("scrollTopBtn").style.display = "block";
            } else {
                document.getElementById("scrollTopBtn").style.display = "none";
            }
        }

        function topFunction() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
    </script>
</body>
</html>
 