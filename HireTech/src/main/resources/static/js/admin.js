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

function validateRequiredFields() {
    const requiredFields = ['profilePhoto'];
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
    validateFullName();
    validateEmail();
    validateContactNumber();
    const areRequiredFieldsValid = validateRequiredFields();

    // Prevent form submission if any field is invalid
    return document.getElementById('username').checkValidity() &&
           document.getElementById('fullName').checkValidity() &&
           document.getElementById('email').checkValidity() &&
           document.getElementById('contactNumber').checkValidity() &&
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


function confirmApproval(event) {    
	var confirmed = confirm("Are you sure, Do you want to Approve ?");             
	   if (!confirmed) {  
				event.preventDefault();             
			}         
	}  
	
	
function confirmRejection(event) {    
     var confirmed = confirm("Are you sure, Do you want to Reject ?");             
		if (!confirmed) {  
					event.preventDefault();             
				}         
		} 	
		
