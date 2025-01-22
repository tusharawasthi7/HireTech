<%@ page import="java.util.List" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>View Job Applicants - HireTech</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" />
    <link href="../css/viewJobApplicants.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="header">
        <img src="https://static.wixstatic.com/media/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg/v1/fit/w_2500,h_1330,al_c/b4a0b1_bb7d241efa4d405baaf0b775682349f2~mv2.jpg" alt="HireTech Logo" class="logo">
        <a href="/employer/dashboard" class="back-button">Back to Dashboard</a>
    </div>

    <div class="container">
        <h2 class="text-center mb-4">All Applicants</h2>
        <div class="row card-container">
            <div class="col-md-4">
                <a href="/employer/viewPendingApplicants">
                    <div class="card info-card">
                        <div class="card-body text-center">
                            <div class="info-icon">
                                <i class="fas fa-users fa-3x"></i>
                            </div>
                            <h3 class="card-title mt-3">Pending Applicants</h3>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-4">
                <a href="/employer/viewApprovedApplicants">
                    <div class="card info-card">
                        <div class="card-body text-center">
                            <div class="info-icon">
                                <i class="fas fa-users fa-3x"></i>
                            </div>
                            <h3 class="card-title mt-3">Approved Applicants</h3>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-4">
                <a href="/employer/viewRejectedApplicants">
                    <div class="card info-card">
                        <div class="card-body text-center">
                            <div class="info-icon">
                                <i class="fas fa-users fa-3x"></i>
                            </div>
                            <h3 class="card-title mt-3">Rejected Applicants</h3>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</body>
</html>
