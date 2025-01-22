package hire.tech.admin.entities;

public class Requests {

	private String employerId;
	private String fullName;
	private String companyName;
	private String industryType;
	private String title;
	private String jobType;
	private String jobLocation;
	private String status;


	public String getEmployerId() {
		return employerId;
	}

	public void setEmployerId(String employerId) {
		this.employerId = employerId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Requests(String employerId, String fullName, String companyName, String industryType, String title,
			String jobType, String jobLocation, String status) {
		super();
		this.employerId = employerId;
		this.fullName = fullName;
		this.companyName = companyName;
		this.industryType = industryType;
		this.title = title;
		this.jobType = jobType;
		this.jobLocation = jobLocation;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Admin [employerId=" + employerId + ", fullName=" + fullName + ", companyName=" + companyName
				+ ", industryType=" + industryType + ", title=" + title + ", jobType=" + jobType + ", jobLocation="
				+ jobLocation + ", status=" + status + "]";
	}

}
