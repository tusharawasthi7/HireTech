package hire.tech.employer.entities;
 
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
 
public class Employer {
	private int employerId;
	private String companyName;
	private String industryType;
	private String status="pending";
	private MultipartFile companyLogo;
	public Employer() {
		super();
	}
	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MultipartFile getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(MultipartFile companyLogo) {
		this.companyLogo = companyLogo;
	}
	public Employer(int employerId, String companyName, String industryType, String status, MultipartFile companyLogo) {
		super();
		this.employerId = employerId;
		this.companyName = companyName;
		this.industryType = industryType;
		this.status = status;
		this.companyLogo = companyLogo;
	}
	@Override
	public String toString() {
		return "Employer [employerId=" + employerId + ", companyName=" + companyName + ", industryType=" + industryType
				+ ", status=" + status + ", companyLogo=" + companyLogo + "]";
	}
}