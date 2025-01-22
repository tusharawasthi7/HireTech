<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employer List</title>
<link href="../css/rejectedrequests.css" rel="stylesheet" type="text/css" />
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
		<h1>Employer's List</h1>
		<!-- Header for the table -->
		<%
		List<Map<String, Object>> employerInfo = (List<Map<String, Object>>) request.getAttribute("employerInfo");

		if (employerInfo == null) {
			out.println("<p>No employer information available.</p>");
		} else {
		%>
		<table>
			<tr>
				<th>S. No</th>
				<th>FullName</th>
				<th>Email</th>
				<th>Contact Number</th>
				<th>CompanyName</th>
				<th>IndustryType</th>
				<th>Status</th>
				<th>Approve/Reject</th>
			</tr>
			<%
			int sNo = 0;
			for (Map<String, Object> info : employerInfo) {
				int userId = (Integer) info.get("userId");
				Boolean statusObj = (Boolean) info.get("authorization_status");
				boolean status = (statusObj != null && statusObj);
			%>
			<tr>
				<td><%=++sNo%></td>
				<td><%=info.get("full_name")%></td>
				<td><%=info.get("email")%></td>
				<td><%=info.get("contact_no")%></td>
				<td><%=info.get("company_name")%></td>
				<td><%=info.get("industry_type")%></td>
				<td><%=status ? "Employer is approved" : "Authorization is pending"%></td>
				<td><a href="/admin/toggleAuthority/<%=info.get("user_id")%>"
					class="button <%=status ? "reject" : "approve"%>"
					onclick="<%=status ? "confirmRejection(event)" : "confirmApproval(event)"%>"><%=status ? "Reject" : "Approve"%></a>
				</td>
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
