package hire.tech.employer.entities;
 
import java.sql.Date;
import java.util.List;
 
import org.springframework.web.multipart.MultipartFile;
 
public class Applications {
	private int applicationId;
	private int jobSeekerId;
	private String fullName;
	private String email;
	private String mobileNo;
	private String higherEducation;
	private String job_seeker_status;
	private String skills;
	private MultipartFile resume;
	private String applicationStatus;
	private Date applicationDate;
	private String title;
    private List<Applications> applications;
	public Applications() {
		super();
	}
    public List<Applications> getApplications() {
		return applications;
	}
 
	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

 
	
	public Applications(int applicationId, int jobSeekerId, String fullName, String email, String mobileNo, String higherEducation,
			String job_seeker_status, String skills, String applicationStatus, Date applicationDate, String title) {
		super();
		this.applicationId = applicationId;
		this.jobSeekerId = jobSeekerId;
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.higherEducation = higherEducation;
		this.job_seeker_status = job_seeker_status;
		this.skills = skills;
		this.applicationStatus = applicationStatus;
		this.applicationDate = applicationDate;
		this.title = title;
	}
	public Applications(int applicationId, int jobSeekerId, String fullName, String email, String mobileNo, String higherEducation,
			String job_seeker_status, String skills, MultipartFile resume, String applicationStatus,
			Date applicationDate, String title) {
		super();
		this.applicationId = applicationId;
		this.jobSeekerId = jobSeekerId;
		this.fullName = fullName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.higherEducation = higherEducation;
		this.job_seeker_status = job_seeker_status;
		this.skills = skills;
		this.resume = resume;
		this.applicationStatus = applicationStatus;
		this.applicationDate = applicationDate;
		this.title = title;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getHigherEducation() {
		return higherEducation;
	}
	public void setHigherEducation(String higherEducation) {
		this.higherEducation = higherEducation;
	}
	public String getJob_seeker_status() {
		return job_seeker_status;
	}
	public void setJob_seeker_status(String job_seeker_status) {
		this.job_seeker_status = job_seeker_status;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public MultipartFile getResume() {
		return resume;
	}
	public void setResume(MultipartFile resume) {
		this.resume = resume;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Applications [applicationId=" + applicationId + ", jobSeekerId=" + jobSeekerId + ", fullName="
				+ fullName + ", mobileNo=" + mobileNo + ", email=" + email + ", higherEducation=" + higherEducation + ", job_seeker_status="
				+ job_seeker_status + ", skills=" + skills + ", resume=" + resume + ", applicationStatus="
				+ applicationStatus + ", applicationDate=" + applicationDate + ", title=" + title +  "]";
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
}
