<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Approved Employer List</title>
<link href="../css/rejectedrequests.css" rel="stylesheet"
	type="text/css" />
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
		<h1>Approved Employer List</h1>
		<!-- Header for the table -->
		<%
		List<Map<String, Object>> approvedEmployers = (List<Map<String, Object>>) request.getAttribute("approvedEmployers");
		if (approvedEmployers == null || approvedEmployers.isEmpty()) {
		%>
		<p>No approved employer requests available.</p>
		<%
		} else {
		%>
		<table>
			<tr>
				<th>S. No</th>
				<th>Employer Id</th>
				<th>Full Name</th>
				<th>Company Name</th>
				<th>Industry Type</th>
				<!--  <th>Job Title</th> -->
				<!-- <th>Job Type</th>
                <th>Job Location</th> -->
				<!--     <th>Status</th> -->
			</tr>
			<%
			int sNo = 0;
			for (Map<String, Object> info : approvedEmployers) {
				int employerId = (Integer) info.get("employer_id");
			%>
			<tr>
				<td><%=++sNo%></td>
				<td><%=info.get("employer_id")%></td>
				<td><%=info.get("full_name")%></td>
				<td><%=info.get("company_name")%></td>
				<td><%=info.get("industry_type")%></td>
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
