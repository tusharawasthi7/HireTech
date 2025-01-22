package hire.tech.jobseeker.entities;
 
import java.sql.Date;
 
import org.springframework.web.multipart.MultipartFile;
 
public class Jobseeker {
	private int jobseekerId;
	private Date dateOfBirth;
	private String gender;
	private String higherEducation;
	private String skills;
	private String preferredLocation;
	private String expectedSalary;
	private MultipartFile resume;
	private String jobStatus;
	
	public Jobseeker() {
		super();
	}
 
	public int getJobseekerId() {
		return jobseekerId;
	}
 
	public void setJobseekerId(int jobseekerId) {
		this.jobseekerId = jobseekerId;
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
 
	public MultipartFile getResume() {
		return resume;
	}
 
	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}
 
	public String getJobStatus() {
		return jobStatus;
	}
 
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	public Jobseeker(int jobseekerId, Date dateOfBirth, String gender, String higherEducation, String skills,
			String preferredLocation, String expectedSalary, MultipartFile resume, String jobStatus) {
		super();
		this.jobseekerId = jobseekerId;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.higherEducation = higherEducation;
		this.skills = skills;
		this.preferredLocation = preferredLocation;
		this.expectedSalary = expectedSalary;
		this.resume = resume;
		this.jobStatus = jobStatus;
	}
 
	@Override
	public String toString() {
		return "Jobseeker [jobseekerId=" + jobseekerId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", higherEducation=" + higherEducation + ", skills=" + skills + ", preferredLocation="
				+ preferredLocation + ", expectedSalary=" + expectedSalary + ", resume=" + resume + ", jobStatus="
				+ jobStatus + "]";
	}
 
}