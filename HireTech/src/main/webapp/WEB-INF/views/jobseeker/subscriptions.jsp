<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Subscription Page</title>
<link href="${pageContext.request.contextPath}/css/subscriptions.css"
    rel="stylesheet" type="text/css" />
<script src="../js/jobseeker.js"></script>
<!-- Link to the new CSS file -->
</head>
<body>
    <!-- Header -->
    <div class="header">
        <img
            src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg"
            alt="HireTech Logo" class="logo"> <a
            href="/jobseeker/openIndexPage" class="back-button">Back to
            Dashboard</a>
    </div>
 
    <div class="page-title">
        <h1>Subscription Plans</h1>
    </div>
 
    <div class="container">
        <div id="subscription-plans" class="plans">
            <div id="basic-plan" class="subscription">
                <h2>Basic Plan</h2>
                <p>6 months: 499 INR</p>
                <ul>
                    <li>Access to Job Listings</li>
                    <li>Search jobs</li>
                    <li>Job Alerts</li>
                    <li>Resume Builder</li>
                    <li>Basic Customer Support</li>
                </ul>
                <button onclick="subscribe('basic')">Subscribe</button>
            </div>
            <div id="premium-plan" class="subscription">
                <h2>Premium Plan</h2>
                <p>1 year: 999 INR</p>
                <ul>
                    <li>Everything in the Basic Plan</li>
                    <li>Resume Builder</li>
                    <li>Certifications</li>
                    <li>Exclusive Job Listings</li>
                    <li>Priority Customer Support</li>
                </ul>
                <button onclick="subscribe('premium')">Subscribe</button>
            </div>
        </div>
        <div id="subscriptionFormModal" class="modal">
            <div class="modal-content form-container">
                <span class="close" onclick="closeModal()">&times;</span>
                <h2>Subscription Form</h2>
                <form id="subscriptionForm"
                    action="${pageContext.request.contextPath}/jobseeker/subscriptions"
                    method="post" onsubmit="return validateJobSeekerForm()">
                    <div class="input-box">
                        <label for="fullName">Full Name:</label> <input type="text" id="fullName"
                            name="name" oninput="validateFullName()" required> <span id="nameError"
                            class="error-message"></span>
                    </div>
                    <div class="input-box">
                        <label for="gender">Gender:</label> <select id="gender"
                            name="gender" required>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </select> <span id="genderError" class="error-message"></span>
                    </div>
                    <div class="input-box">
                        <label for="subscriptionType">Subscription Type:</label> <input
                            type="text" id="subscriptionType" name="subscriptionType"
                            readonly> <span id="subscriptionTypeError"
                            class="error-message"></span>
                    </div>
                    <div class="input-box">
                        <label for="startDate">Start Date:</label> <input type="date"
                            id="startDate" name="startDate" oninput="validateStartDate()" required> <span
                            id="startDateError" class="error-message"></span>
                    </div>
                    <div class="input-box">
                        <label for="endDate">End Date:</label> <input type="date"
                            id="endDate" name="endDate" readonly> <span
                            id="endDateError" class="error-message"></span>
                    </div>
                    <div class="input-box">
                        <label for="paymentMode">Payment Mode:</label> <select
                            id="paymentMode" name="paymentMode" required>
                            <option value="Credit Card">Credit Card</option>
                            <option value="Debit Card">Debit Card</option>
                            <option value="Net Banking">Net Banking</option>
                            <option value="UPI">UPI</option>
                        </select> <span id="paymentModeError" class="error-message"></span>
                    </div>
                    <div class="input-box">
                        <label for="paymentId">Payment ID/Number:</label> <input
                            type="text" id="paymentId" name="paymentId" required> <span
                            id="paymentIdError" class="error-message"></span>
                    </div>
                    <button type="submit" class="btn">Pay</button>
                </form>
            </div>
        </div>
        <div class="register-link"></div>
    </div>
    <script>
        function subscribe(plan) {
            const subscriptionType = plan.charAt(0).toUpperCase()
                    + plan.slice(1) + " Plan";
            document.getElementById('subscriptionType').value = subscriptionType;
            document.getElementById('subscriptionFormModal').style.display = 'block';
        }
 
        function closeModal() {
            document.getElementById('subscriptionFormModal').style.display = 'none';
        }
 
        function calculateEndDate(startDate, months) {
            let date = new Date(startDate);
            date.setMonth(date.getMonth() + months);
            return date.toISOString().split('T')[0];
        }
 
        document.getElementById('startDate')
                .addEventListener(
                        'change',
                        function() {
                            let plan = document
                                    .getElementById('subscriptionType').value
                                    .toLowerCase().includes('basic') ? 6 : 12;
                            let endDate = calculateEndDate(this.value, plan);
                            document.getElementById('endDate').value = endDate;
                        });
    </script>
</body>
</html>
