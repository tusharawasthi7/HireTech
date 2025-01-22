function validateUsername() {
	const usernameInput = document.getElementById('username');
	const username = usernameInput.value;
 
	if (username.length < 8) {
		usernameInput.style.borderColor = "red";
		usernameInput.style.color = "red";
		usernameInput.setCustomValidity("Username must be at least 8 characters long.");
		usernameInput.reportValidity();
	} else if (/\s/.test(username)) {
		usernameInput.style.borderColor = "red";
		usernameInput.style.color = "red";
		usernameInput.setCustomValidity("Username should not contain spaces.");
		usernameInput.reportValidity();
	} else if (/^\d/.test(username)) {
		usernameInput.style.borderColor = "red";
		usernameInput.style.color = "red";
		usernameInput.setCustomValidity("Username should not start with a number.");
		usernameInput.reportValidity();
	} else {
		usernameInput.style.borderColor = "green";
		usernameInput.style.color = "black";
		usernameInput.setCustomValidity("");
	}
}
 
function validatePassword() {
	const passwordInput = document.getElementById('password');
	const password = passwordInput.value;
 
	const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
	if (!passwordRegex.test(password)) {
		passwordInput.style.borderColor = "red";
		passwordInput.style.color = "red";
		passwordInput.setCustomValidity("Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.");
		passwordInput.reportValidity();
	} else {
		passwordInput.style.borderColor = "green";
		passwordInput.style.backgroundColor = "#d4edda";
		passwordInput.style.color = "black";
		passwordInput.setCustomValidity("");
	}
}
 
function validateConfirmPassword() {
	const confirmPasswordInput = document.getElementById('confirmPassword');
	const confirmPassword = confirmPasswordInput.value;
	const password = document.getElementById('password').value;
 
	if (confirmPassword !== password) {
		confirmPasswordInput.style.borderColor = "red";
		confirmPasswordInput.style.color = "red";
		confirmPasswordInput.setCustomValidity("Passwords do not match.");
		confirmPasswordInput.reportValidity();
	} else {
		confirmPasswordInput.style.borderColor = "green";
		confirmPasswordInput.style.backgroundColor = "#d4edda";
		confirmPasswordInput.style.color = "black";
		confirmPasswordInput.setCustomValidity("");
	}
}
 
function validateFullName() {
	const fullNameInput = document.getElementById('fullName');
	const fullName = fullNameInput.value;
 
	if (fullName.trim().length < 5) {
		fullNameInput.style.borderColor = "red";
		fullNameInput.style.color = "red";
		fullNameInput.setCustomValidity("Full Name must be at least 5 characters long.");
		fullNameInput.reportValidity();
	} else {
		fullNameInput.style.borderColor = "green";
		fullNameInput.style.backgroundColor = "#d4edda";
		fullNameInput.style.color = "black";
		fullNameInput.setCustomValidity("");
	}
}
 
function validateEmail() {
	const emailInput = document.getElementById('email');
	const email = emailInput.value;
 
	const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	if (!emailRegex.test(email)) {
		emailInput.style.borderColor = "red";
		emailInput.style.color = "red";
		emailInput.setCustomValidity("Please enter a valid email address.");
		emailInput.reportValidity();
	} else {
		emailInput.style.borderColor = "green";
		emailInput.style.backgroundColor = "#d4edda";
		emailInput.style.color = "black";
		emailInput.setCustomValidity("");
	}
}
 
function validateContactNumber() {
	const contactNoInput = document.getElementById('contactNumber');
	const contactNo = contactNoInput.value;
 
	const contactNoRegex = /^[6789][0-9]{9}$/;
	if (!contactNoRegex.test(contactNo)) {
		contactNoInput.style.borderColor = "red";
		contactNoInput.style.color = "red";
		contactNoInput.setCustomValidity("Please enter a valid 10-digit contact number starting with 6, 7, 8, or 9.");
		contactNoInput.reportValidity();
	} else {
		contactNoInput.style.borderColor = "green";
		contactNoInput.style.backgroundColor = "#d4edda";
		contactNoInput.style.color = "black";
		contactNoInput.setCustomValidity("");
	}
}
 
function validateSalary() {
	const salaryInput = document.getElementById('salary_offered');
	const salary = salaryInput.value;
 
	if (!/^\d+$/.test(salary)) {
		salaryInput.style.borderColor = "red";
		salaryInput.style.color = "red";
		salaryInput.setCustomValidity("Salary should be in digits.");
		salaryInput.reportValidity();
	} else if (salary <= 5000 || salary >= 500000) {
		salaryInput.style.borderColor = "red";
		salaryInput.style.color = "red";
		salaryInput.setCustomValidity("Salary should be greater than 5000 and less than 5,00,000.");
		salaryInput.reportValidity();
	} else {
		salaryInput.style.borderColor = "green";
		salaryInput.style.backgroundColor = "#d4edda";
		salaryInput.style.color = "black";
		salaryInput.setCustomValidity("");
	}
}
 
 
function validateRequiredFields() {
	const requiredFields = ['profilePhoto', 'companyName', 'industryType', 'industryLogo'];
	let allValid = true;
 
	requiredFields.forEach(fieldId => {
		const inputElement = document.getElementById(fieldId);
		if (!inputElement.value) {
			inputElement.style.borderColor = "red";
			inputElement.style.color = "red";
			inputElement.setCustomValidity("This field is required.");
			inputElement.reportValidity();
			allValid = false;
		} else {
			inputElement.style.borderColor = "green";
			inputElement.style.backgroundColor = "#d4edda";
			inputElement.style.color = "black";
			inputElement.setCustomValidity("");
		}
	});
 
	return allValid;
}
 
function validateForm() {
	validateUsername();
	validatePassword();
	validateConfirmPassword();
	validateFullName();
	validateEmail();
	validateContactNumber();
	validateSalary();
	const areRequiredFieldsValid = validateRequiredFields();
 
	// Prevent form submission if any field is invalid
	return document.getElementById('username').checkValidity() &&
		document.getElementById('password').checkValidity() &&
		document.getElementById('confirmPassword').checkValidity() &&
		document.getElementById('fullName').checkValidity() &&
		document.getElementById('email').checkValidity() &&
		document.getElementById('contactNumber').checkValidity() &&
		document.getElementById('salary').checkValidity() &&
		areRequiredFieldsValid;
}
 
// Add event listener to validate the form on submit
document.getElementById('registrationForm').addEventListener('submit', function(event) {
	if (!validateForm()) {
		event.preventDefault();
	}
});
 
 
function logout(event) {
	var confirmed = confirm("Are you sure, Do you want to Logout?");
	if (!confirmed) {
		event.preventDefault();
	}
}
 
 
function deleteJob(event) {
	var confirmed = confirm("Are you sure, Do you want to Delete this Job?");
	if (!confirmed) {
		event.preventDefault();
	}
}
 
 
function confirmApproval(event) {
	var confirmed = confirm("Are you sure, Do you want to Select Applicant ?");
	if (!confirmed) {
		event.preventDefault();
	}
}
 
 
function confirmRejection(event) {
	var confirmed = confirm("Are you sure, Do you want to Reject Applicant ?");
	if (!confirmed) {
		event.preventDefault();
	}
}
 
	
	function jobTitle() {
	    var jobTitleValue = document.getElementById("title").value;
	    var isValid = true;
 
	    if (jobTitleValue === "") {
	        document.getElementById("jobTitleError").textContent = "Job title is required.";
	        isValid = false;
	    } else if (jobTitleValue.length <= 5) {
	        document.getElementById("jobTitleError").textContent = "Job title must be greater than 5 characters.";
	        isValid = false;
	    } else {
	        document.getElementById("jobTitleError").textContent = "";
	    }
 
	    return isValid;
	}
 
	// Add an event listener to clear the error message while typing
	document.getElementById("jobTitle").addEventListener("input", function() {
	    document.getElementById("jobTitleError").textContent = "";
	});
	
	
	
	// Validate Location
	function location() {
		const location = document.getElementById("job_location").value.trim();
		if (location === "") {
			document.getElementById("locationError").textContent = "Location is required.";
			isValid = false;
		} else {
			document.getElementById("locationError").textContent = "";
		}
	}
	function job_type(){
		const jobTypeSelect = document.getElementById('job_type');
			const requirementsError = document.getElementById('requirementsError');
 
			if (jobTypeSelect.value === "Select") {
				requirementsError.textContent = "Please select a valid job type.";
				jobTypeSelect.style.borderColor = "red";
				event.preventDefault(); // Prevent form submission
			} else {
				requirementsError.textContent = "";
				jobTypeSelect.style.borderColor = "";
			}
			}
	
 
	// Validate Skills
/*	function skills() {
		const skills = document.getElementById("skills").value.trim();
		if (skills === "") {
			document.getElementById("descriptionError").textContent = "Skills are required.";
			isValid = false;
		} else {
			document.getElementById("descriptionError").textContent = "";
		}
		
		}
 
			*/
			
			function validateSkills() {
			    var jobTitleValue = document.getElementById("skills").value;
			    var isValid = true;
 
			    if (jobTitleValue === "") {
			        document.getElementById("skillsError").textContent = "Skill is required.";
			        isValid = false;
			    } else if (jobTitleValue.length <= 3) {
			        document.getElementById("skillsError").textContent = "Must add at least one skill";
			        isValid = false;
			    } else {
			        document.getElementById("skillsError").textContent = "";
			    }
 
			    return isValid;
			}
 
			// Add an event listener to clear the error message while typing
			document.getElementById("skills").addEventListener("input", function() {
				    document.getElementById("skillsError").textContent = "";
				});
				
		
		
/*	function documents() {
		const documentInput = document.getElementById("description");
		const documentFile = documentInput.files[0];
		if (documentFile && documentFile.type !== "application/pdf") {
			alert("Please upload a PDF document.");
			isValid = false;
		}
	}*/
	
	
	
	function description() {
	    const documentInput = document.getElementById("description");
	    const documentFile = documentInput.files[0];
	    const errorMessage = document.getElementById("error-message");
	    let isValid = true;
 
	    errorMessage.textContent = ""; // Clear previous error message
 
	    if (documentFile) {
	        if (documentFile.type !== "application/pdf") {
	            errorMessage.textContent = "Please upload a PDF document.";
	            isValid = false;
	        } else if (documentFile.size > 5 * 1024 * 1024) { // 5 MB in bytes
	            errorMessage.textContent = "File size should be less than 5 MB.";
	            isValid = false;
	        }
	    }
 
	    return isValid;
	}
	
	
	function validateJobForm() {
		validateSalary();
		location();
		validateSkills();
		description();
		validateSalary();
		job_type();
		const areRequiredFieldsValid = validateRequiredFields();
 
 
		return document.getElementById('salary_offered').checkValidity() &&
			document.getElementById('job_location').checkValidity() &&
			document.getElementById('title').checkValidity() &&
			document.getElementById('fullName').checkValidity() &&
			document.getElementById('description').checkValidity() &&
			document.getElementById('job_type').checkValidity() &&
			document.getElementById('skills').checkValidity() &&
			areRequiredFieldsValid;
	}
	document.getElementById('addJobForm').addEventListener('submit', function(event) {
		if (!validateJobForm()) {
			event.preventDefault();
		}
	});
 
 
 
 