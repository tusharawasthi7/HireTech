<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jobs List</title>
<link href="../css/rejectedrequests.css" rel="stylesheet" type="text/css" />
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
		<h1>Jobs List</h1>
		<!-- Header for the table -->
		<%
		List<Map<String, Object>> jobsInfo = (List<Map<String, Object>>) request.getAttribute("jobsInfo");

		if (jobsInfo == null || jobsInfo.isEmpty()) {
			out.println("No jobs information available.");
		} else {
		%>
		<table>
			<tr>
				<th>S. No</th>
				<th>Employer Id</th>
				<th>FullName</th>
				<th>Company Name</th>
				<th>Title</th>
				<th>Skills</th>
				<th>Salary Offered</th>
				<th>JobType</th>
				<th>Job Location</th>
			</tr>
			<%
			int sNo = 0;
			for (Map<String, Object> info : jobsInfo) {
			%>
			<tr>
				<td><%=++sNo%></td>
				<td><%=info.get("employer_id")%></td>
				<td><%=info.get("full_name")%></td>
				<td><%=info.get("company_name")%></td>
				<td><%=info.get("title")%></td>
				<td><%=info.get("skills")%></td>
				<td><%=info.get("salary_offered")%></td>
				<td><%=info.get("job_type")%></td>
				<td><%=info.get("job_location")%></td>
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