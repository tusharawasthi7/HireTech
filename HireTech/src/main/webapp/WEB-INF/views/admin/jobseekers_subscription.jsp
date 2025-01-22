<%@page import="hire.tech.admin.entities.Subscription"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jobseeker Subscriptions</title>
    <link href="../css/rejectedrequests.css" rel="stylesheet" type="text/css" />
</head>
<body>

    <div class="header">
        <img src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg" alt="HireTech Logo" class="logo">
        <a href="/admin/backToDashboard" class="back-button">Back to Dashboard</a>
    </div>

    <div class="container">
        <h1>Jobseeker Subscriptions</h1>
        <!-- Header for the table -->
        <table>
            <thead>
                <tr>
                    <th>Subscription ID</th>
                    <th>Jobseeker ID</th>
                    <th>Subscription Type</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Subscription> subscriptions = (List<Subscription>) request.getAttribute("subscriptions");
                    if (subscriptions == null || subscriptions.isEmpty()) {
                        out.println("No subscriptions information available.");
                    } else {
                        for (Subscription subscription : subscriptions) {
                %>
                <tr>
                    <td><%= subscription.getSubscriptionId() %></td>
                    <td><%= subscription.getJobseekerId() %></td>
                    <td><%= subscription.getSubscriptionType() %></td>
                    <td><%= subscription.getStartDate() %></td>
                    <td><%= subscription.getEndDate() %></td>
                    <td><%= subscription.getStatus() %></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
