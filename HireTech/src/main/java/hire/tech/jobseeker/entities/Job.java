package hire.tech.jobseeker.entities;

import org.springframework.web.multipart.MultipartFile;

public class Job {
    private int jobId;
    private String title;
    private String skills;
    private double salaryOffered;
    private String jobType;
    private String jobLocation;
    private int employerId;
    private String companyName;
    private MultipartFile jobDescriptionPdf;  
    private String descriptionContent;  

    
    public Job() {
		super();
		
	}
    
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
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

    public double getSalaryOffered() {
        return salaryOffered;
    }

    public void setSalaryOffered(double salaryOffered) {
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

    public MultipartFile getJobDescriptionPdf() {
        return jobDescriptionPdf;
    }

    public void setJobDescriptionPdf(MultipartFile jobDescriptionPdf) {
        this.jobDescriptionPdf = jobDescriptionPdf;
    }

    public String getDescriptionContent() {
        return descriptionContent;
    }

    public void setDescriptionContent(String descriptionContent) {
        this.descriptionContent = descriptionContent;
    }
    
 
    public Job(int jobId, String title, String skills, double salaryOffered, String jobType, String jobLocation, int employerId, MultipartFile jobDescriptionPdf) {
        this.jobId = jobId;
        this.title = title;
        this.skills = skills;
        this.salaryOffered = salaryOffered;
        this.jobType = jobType;
        this.jobLocation = jobLocation;
        this.employerId = employerId;
        this.jobDescriptionPdf = jobDescriptionPdf;  
    }

}
