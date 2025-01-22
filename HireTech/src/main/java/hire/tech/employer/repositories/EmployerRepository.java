package hire.tech.employer.repositories;
import hire.tech.employer.entities.Applications;
import hire.tech.employer.entities.Employer;
import hire.tech.employer.entities.Job;
import hire.tech.user.entities.User;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;
import org.springframework.web.multipart.MultipartFile;
public interface EmployerRepository {
	List<Job> fetchAllJobs(int employerId);
	List<Employer> fetchAllEmployer();
	List<Employer> fetchUpdateEmployer(int employerId);
	int addJob(Job job, Employer employer, int employerId) throws SerialException, IOException, SQLException;
	List<Applications> getPendingApplicantsOfEmployer(int employerId);
	List<Applications> getRejectedApplicantsOfEmployer(int employerId);
	List<Applications> getApprovedApplicantsOfEmployer(int employerId);
	Employer getEmployerByUserId(int userId);
	boolean changeApplicationStatus(int application_id, String status);
	int getTotalApplications(int employerId);
	public int getTotalJobsPosted(int employerId);
	int deleteJob(int job_id);
	int updateJob(int job_id, String title, String skills,  String job_type, double salary_offered, String job_location,
			MultipartFile description);
	Job findById(int job_id);
 
	
}
