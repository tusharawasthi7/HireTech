package hire.tech.employer.entities;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Job {

	private int job_id;
	private String title;
	private String skills;
	private String job_type;
	private double salary_offered;
	private String job_location;
	private Employer employer_id;	
	private MultipartFile description;
	
	
	

	@Override
	public String toString() {
		return "Job [job_id=" + job_id + ", title=" + title + ", skills=" + skills + ", job_type=" + job_type
				+ ", salary_offered=" + salary_offered + ", job_location=" + job_location + ", employer_id="
				+ employer_id + ", description=" + description + "]";
	}




	public Job() {
		super();
		}
	
	

	
	public Job(int job_id, String title, String skills, String job_type, double salary_offered, String job_location) {
		super();
		this.job_id = job_id;
		this.title = title;
		this.skills = skills;
		this.job_type = job_type;
		this.salary_offered = salary_offered;
		this.job_location = job_location;
	}
	
	




	public Job(int job_id, String title, String skills, String job_type, double salary_offered, String job_location,
			MultipartFile description) {
		super();
		this.job_id = job_id;
		this.title = title;
		this.skills = skills;
		this.job_type = job_type;
		this.salary_offered = salary_offered;
		this.job_location = job_location;
		this.description = description;
	}

	



	public Job(int job_id, String title, String skills, String job_type, double salary_offered, String job_location,
			Employer employer_id, MultipartFile description) {
		super();
		this.job_id = job_id;
		this.title = title;
		this.skills = skills;
		this.job_type = job_type;
		this.salary_offered = salary_offered;
		this.job_location = job_location;
		this.employer_id = employer_id;
		this.description = description;
	}




	public int getJob_id() {
		return job_id;
	}

	public void setJob_id(int job_id) {
		this.job_id = job_id;
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

	public String getJob_type() {
		return job_type;
	}

	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}

	public double getSalary_offered() {
		return salary_offered;
	}

	public void setSalary_offered(double salary_offered) {
		this.salary_offered = salary_offered;
	}

	public String getJob_location() {
		return job_location;
	}

	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}

	public Employer getEmployer_id() {
		return employer_id;
	}

	public void setEmployer_id(Employer employer_id) {
		this.employer_id = employer_id;
	}

	public MultipartFile getDescription() {
		return description;
	}

	public void setDescription(MultipartFile description) {
		this.description = description;
	}
	
	
	
}
	
	