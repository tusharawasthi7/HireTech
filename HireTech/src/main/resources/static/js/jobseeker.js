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

function validateNewPassword() {
    const passwordInput = document.getElementById('newPassword');
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
        contactNoInput.setCustomValidity("Please enter a valid 10-digit contact number starting with 6,7, 8, or 9.");
        contactNoInput.reportValidity();
    } else {
        contactNoInput.style.borderColor = "green";
        contactNoInput.style.backgroundColor = "#d4edda";
        contactNoInput.style.color = "black";
        contactNoInput.setCustomValidity("");
    }
}


function validatePhoneNumber() {
    const phoneNumberInput = document.getElementById('phoneNumber');
    const phoneNumber = phoneNumberInput.value;

    const phoneNumberRegex = /^[6789][0-9]{9}$/;
    if (!phoneNumberRegex.test(phoneNumber)) {
        phoneNumberInput.style.borderColor = "red";
        phoneNumberInput.style.color = "red";
        phoneNumberInput.setCustomValidity("Please enter a valid 10-digit contact number starting with 6,7, 8, or 9.");
        phoneNumberInput.reportValidity();
    } else {
        phoneNumberInput.style.borderColor = "green";
        phoneNumberInput.style.backgroundColor = "#d4edda";
        phoneNumberInput.style.color = "black";
        phoneNumberInput.setCustomValidity("");
    }
}


function validateDateOfBirth() {
    const dateOfBirthInput = document.getElementById('dateOfBirth');
    const dateOfBirth = new Date(dateOfBirthInput.value);
    const maxDate = new Date('2004-12-31');

    if (dateOfBirth > maxDate) {
        dateOfBirthInput.style.borderColor = "red";
        dateOfBirthInput.style.color = "red";
        dateOfBirthInput.setCustomValidity("Please enter valid Date of Birth, jobseeker must be atleast 20 years old.");
        dateOfBirthInput.reportValidity();
    } else {
        dateOfBirthInput.style.borderColor = "green";
        dateOfBirthInput.style.backgroundColor = "#d4edda";
        dateOfBirthInput.style.color = "black";
        dateOfBirthInput.setCustomValidity("");
    }
}

function validateStartDate() {
    const startDateInput = document.getElementById('startDate');
    const startDate = new Date(startDateInput.value);
    const targetDate = new Date('2025-01-10');

    // Set the hours to zero to avoid time discrepancies
    targetDate.setHours(0, 0, 0, 0);
    startDate.setHours(0, 0, 0, 0);

    if (startDate.getTime() !== targetDate.getTime()) {
        startDateInput.style.borderColor = "red";
        startDateInput.style.color = "red";
        startDateInput.setCustomValidity("Please enter today's date.");
        startDateInput.reportValidity();
    } else {
        startDateInput.style.borderColor = "green";
        startDateInput.style.backgroundColor = "#d4edda";
        startDateInput.style.color = "black";
        startDateInput.setCustomValidity("");
    }
}




function validateExpectedSalary() {
    const expectedSalaryInput = document.getElementById('expectedSalary');
    const expectedSalary = expectedSalaryInput.value;

    if (!/^\d+$/.test(expectedSalary)) {
        expectedSalaryInput.style.borderColor = "red";
        expectedSalaryInput.style.color = "red";
        expectedSalaryInput.setCustomValidity("Expected salary should be in digits.");
        expectedSalaryInput.reportValidity();
    } else {
        expectedSalaryInput.style.borderColor = "green";
        expectedSalaryInput.style.backgroundColor = "#d4edda";
        expectedSalaryInput.style.color = "black";
        expectedSalaryInput.setCustomValidity("");
    }
}

function validateRequiredFields() {
    const requiredFields = ['gender', 'profilePicture', 'higherEducation', 'skills',  'preferredLocation',  'resume',  'jobStatus'];
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
	validateNewPassword();
    validateConfirmPassword();
    validateFullName();
    validateEmail();
    validateContactNumber();
	validatePhoneNumber();
    validateDateOfBirth();
    validateExpectedSalary();
    const areRequiredFieldsValid = validateRequiredFields();

    // Prevent form submission if any field is invalid
    return document.getElementById('username').checkValidity() &&
           document.getElementById('password').checkValidity() &&
		   document.getElementById('newPassword').checkValidity() &&
           document.getElementById('confirmPassword').checkValidity() &&
           document.getElementById('fullName').checkValidity() &&
           document.getElementById('email').checkValidity() &&
           document.getElementById('contactNumber').checkValidity() &&
		   document.getElementById('phoneNumber').checkValidity() &&
           document.getElementById('dateOfBirth').checkValidity() &&
           document.getElementById('expectedSalary').checkValidity() &&
           areRequiredFieldsValid;
}

// Add event listener to validate the form on submit
document.getElementById('registrationForm').addEventListener('submit', function(event) {
    if (!validateForm()) {
        event.preventDefault();
    }
});




function confirmDelete(event) {    
	var confirmed = confirm("Are you sure, Do you want to Delete this Job ?");             
		if (!confirmed) {  
		   event.preventDefault();             
	}         
}  
				  	
				  	
function logout(event) {
    var confirmed = confirm("Are you sure, Do you want to Logout?");             
	    if (!confirmed) {  
		   event.preventDefault();             
	} 
}
			 
function confirmSave(event) {
     event.preventDefault();
	    if (confirm("Would you like to save?")) {
		   event.target.submit();
	}
}

function confirmApplication() {
	return confirm("Application submitted successfully!");
}

			
document.getElementById('dateForm').addEventListener('submit', function(event) {
            event.preventDefault(); // Prevent the default form submission
            const dateInput = document.getElementById('dateOfBirth');
            const dateValue = dateInput.value;
            if (dateValue) {
                const dateObj = new Date(dateValue);
                const year = dateObj.getFullYear();
                const month = ("0" + (dateObj.getMonth() + 1)).slice(-2); // Ensure two digits
                const day = ("0" + dateObj.getDate()).slice(-2); // Ensure two digits
 
                const formattedDate = `${year}-${month}-${day}`;
                dateInput.value = formattedDate; // Update the input value with the formatted date
            }
            console.log('Formatted Date:', dateInput.value);
        });
		
		
		