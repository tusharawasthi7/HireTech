package hire.tech.admin.entities;

public class EmployerInfo {

	private int userId;
	private String fullName;
	private String email;
	private String contactNumber;
	private String companyName;
	private String industryType;
	private boolean authorizationStatus;
	private String status;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public boolean isAuthorizationStatus() {
		return authorizationStatus;
	}

	public void setAuthorizationStatus(boolean authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public EmployerInfo(int userId, String fullName, String email, String contactNumber, String companyName,
			String industryType, boolean authorizationStatus, String status) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.companyName = companyName;
		this.industryType = industryType;
		this.authorizationStatus = authorizationStatus;
		this.status = status;
	}

	@Override
	public String toString() {
		return "EmployerInfo [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", companyName=" + companyName + ", industryType=" + industryType
				+ ", authorizationStatus=" + authorizationStatus + ", status=" + status + "]";
	}

}