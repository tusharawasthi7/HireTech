<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Seekers List</title>
<link href="../css/rejectedrequests.css" rel="stylesheet"
	type="text/css" />
<script src="../js/admin.js"></script>
</head>
<body>

	<div class="header">
		<img
			src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
			alt="HireTech Logo" class="logo"> <a
			href="/admin/backToDashboard" class="back-button">Back To
			Dashboard</a>
	</div>
	<div class="container">
		<h1>Job Seekers List</h1>
		<!-- Header for the table -->
		<%
		List<Map<String, Object>> jobseekersInfo = (List<Map<String, Object>>) request.getAttribute("jobseekersInfo");

		if (jobseekersInfo == null) {
			out.println("<p>No job seekers information available.</p>");
		} else {
		%>
		<table>
			<tr>
				<th>S.No</th>
				<th>FullName</th>
				<th>Email</th>
				<th>Contact Number</th>
				<th>Date Of Birth</th>
				<th>Gender</th>
				<th>Higher Education</th>
				<th>Preferred Location</th>
				<th>Expected Salary</th>
				<th>Job Status</th>
			</tr>
			<%
			int sNo = 0;
			for (Map<String, Object> jsinfo : jobseekersInfo) {
			%>
			<tr>
				<td><%=++sNo%></td>
				<td><%=jsinfo.get("full_name")%></td>
				<td><%=jsinfo.get("email")%></td>
				<td><%=jsinfo.get("contact_no")%></td>
				<td><%=jsinfo.get("date_of_birth")%></td>
				<td><%=jsinfo.get("gender")%></td>
				<td><%=jsinfo.get("higher_education")%></td>
				<td><%=jsinfo.get("preferred_location")%></td>
				<td><%=jsinfo.get("expected_salary")%></td>
				<td><%=jsinfo.get("job_status")%></td>
			</tr>
			<%
			}
			%>
		</table>
		<%
		}
		%>
	</div>
</body>
</html>
