package hire.tech.employer.services;
import hire.tech.employer.entities.Applications;
import hire.tech.employer.entities.Employer;
import hire.tech.employer.entities.Job;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;
import org.springframework.web.multipart.MultipartFile;
public interface EmployerService {
	List<Job> getAllJobs(int employerId);
	List<Employer> getAllEmployer();
	List<Employer> getUpdateEmployer(int employerId);
	Employer getEmployerByUserId(int userId);
	int addJob(Job job, Employer employer, int employerId) throws SerialException, IOException, SQLException;
	List<Applications> getPendingApplicantsOfEmployer(int employerId);
	List<Applications> getRejectedApplicantsOfEmployer(int employerId);
	List<Applications> getApprovedApplicantsOfEmployer(int employerId);
	String changeApplicationStatus(int application_id, String status, int condition);
	public int getTotalApplications(int employerId);
	public int getTotalJobsPosted(int employerId);
	int deleteJob(int job_id);
	int updateJob(int job_id, String title, String skills, String job_type, double salary_offered, String job_location,
			MultipartFile description);
	Job getJobById(int job_id);
}