package hire.tech.admin.entities;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class JobSeekersInfo {
	private int userId;
	private String fullName;
	private String email;
	private String contactNumber;
	private Date dateOfBirth;
	private String gender;
	private String higherEducation;
	private String skills;
	private String preferredLocation;
	private String expectedSalary;
	private String job_status;

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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHigherEducation() {
		return higherEducation;
	}

	public void setHigherEducation(String higherEducation) {
		this.higherEducation = higherEducation;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public JobSeekersInfo(int userId, String fullName, String email, String contactNumber, Date dateOfBirth,
			String gender, String higherEducation, String skills, String preferredLocation, String expectedSalary,
			String job_status) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.higherEducation = higherEducation;
		this.skills = skills;
		this.preferredLocation = preferredLocation;
		this.expectedSalary = expectedSalary;
		this.job_status = job_status;
	}

	@Override
	public String toString() {
		return "JobSeekersInfo [userId=" + userId + ", fullName=" + fullName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", higherEducation="
				+ higherEducation + ", skills=" + skills + ", preferredLocation=" + preferredLocation
				+ ", expectedSalary=" + expectedSalary + ", job_status=" + job_status + "]";
	}

}