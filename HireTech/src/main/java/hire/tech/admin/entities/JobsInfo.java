package hire.tech.admin.entities;

import org.springframework.web.multipart.MultipartFile;

public class JobsInfo {

	private int employerId;
	private String fullName;
	private String title;
	private String skills;
	private Double salaryOffered;
	private String jobType;
	private String jobLocation;

	public int getEmployerId() {
		return employerId;
	}

	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Double getSalaryOffered() {
		return salaryOffered;
	}

	public void setSalaryOffered(Double salaryOffered) {
		this.salaryOffered = salaryOffered;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public JobsInfo(int employerId, String fullName, String title, String skills, Double salaryOffered, String jobType,
			String jobLocation) {
		super();
		this.employerId = employerId;
		this.fullName = fullName;
		this.title = title;
		this.skills = skills;
		this.salaryOffered = salaryOffered;
		this.jobType = jobType;
		this.jobLocation = jobLocation;
	}

	@Override
	public String toString() {
		return "JobsInfo [employerId=" + employerId + ", fullName=" + fullName + ", title=" + title + ", skills="
				+ skills + ", salaryOffered=" + salaryOffered + ", jobType=" + jobType + ", jobLocation=" + jobLocation
				+ "]";
	}
}