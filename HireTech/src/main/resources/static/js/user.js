function logout(event) {
	var confirmed = confirm("Are you sure, Do you want to Logout?");             
		if (!confirmed) {  
		   event.preventDefault();             
	} 
}