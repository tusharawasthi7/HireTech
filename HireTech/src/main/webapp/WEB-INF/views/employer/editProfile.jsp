<%@ page import="hire.tech.employer.entities.Job"%>
<%@ page import="hire.tech.employer.entities.Employer"%>
<%@ page import="hire.tech.user.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page import="hire.tech.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Edit Job - HireTech</title>
<link href="../css/editprofilee.css" rel="stylesheet" type="text/css" />
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
        <%
        List<User> userdetails = (List<User>) request.getAttribute("userdetails");
        if (userdetails != null) {
            for (User details : userdetails) {
        %>
        <form action="/employer/updateUserProfile" method="post"
            enctype="multipart/form-data" onsubmit="return validateForm()">
            <div class="form-container">
                <h2 class="form-title">Edit Profile</h2>
                <input type="hidden" name="userId" value="<%=details.getUserId()%>">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username"
                        value="<%=details.getUsername()%>" oninput="validateUsername()"
                        required>
                </div>
                <div class="mb-3">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName"
                        value="<%=details.getFullName()%>" oninput="validateFullName()"
                        required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email"
                        value="<%=details.getEmail()%>" oninput="validateEmail()" required>
                </div>
                <div class="mb-3">
                    <label for="contactNumber" class="form-label">Contact Number</label>
                    <input type="text" class="form-control" id="contactNumber" name="contactNumber"
                        value="<%=details.getContactNumber()%>" oninput="validateContactNumber()" required>
                </div>
                <div class="mb-3">
                    <label for="profilePicture" class="form-label">Profile Picture</label>
                    <input type="file" class="form-control" id="profilePicture" name="profilePicture" accept="image/*">
                </div>
                <button type="submit" class="btn btn-primary">Update Profile</button>
            </div>
        </form>
        <hr>
        <%
            }
        } else {
        %>
        <p>No user details available.</p>
        <%
        }
        %>
    </div>
</body>
</html>
