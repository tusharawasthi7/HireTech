package hire.tech.employer.services;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import hire.tech.employer.entities.Applications;
import hire.tech.employer.entities.Employer;
import hire.tech.employer.entities.Job;
import hire.tech.employer.repositories.EmployerRepository;
@Service
public class EmployerServicesImpl implements EmployerService {
	@Autowired
	EmployerRepository employerRepository;
	@Override
	public List<Job> getAllJobs(int employerId) {
		return employerRepository.fetchAllJobs(employerId);
	}
	@Override
	public int addJob(Job job, Employer employer, int employerId) throws SerialException, IOException, SQLException {
		return employerRepository.addJob(job, employer, employerId);
	}
	@Override
	public List<Employer> getAllEmployer() {
		return employerRepository.fetchAllEmployer();
	}
	@Override
	public List<Employer> getUpdateEmployer(int employerId) {
		return employerRepository.fetchUpdateEmployer(employerId);
	}
	@Override
	public List<Applications> getPendingApplicantsOfEmployer(int employerId) {
		return employerRepository.getPendingApplicantsOfEmployer(employerId);
	}
	@Override
	public List<Applications> getRejectedApplicantsOfEmployer(int employerId) {
		return employerRepository.getRejectedApplicantsOfEmployer(employerId);
	}
	@Override
	public List<Applications> getApprovedApplicantsOfEmployer(int employerId) {
		return employerRepository.getApprovedApplicantsOfEmployer(employerId);
	}
	@Override
	public Employer getEmployerByUserId(int userId) {
		return employerRepository.getEmployerByUserId(userId);
	}
	@Override
	public String changeApplicationStatus(int application_id, String status, int condition) {
		System.out.println(status + "service layer");
		String updatedStatus = "pending";
		if (status.equalsIgnoreCase("approved")) {
			updatedStatus = "rejected";
			System.out.println(updatedStatus + " inside approved");
		} else if (status.equalsIgnoreCase("rejected")) {
			updatedStatus = "approved";
			System.out.println(updatedStatus + " inside rejected");
		} else if (status.equalsIgnoreCase("pending")) {
			if (condition == 0) {
				updatedStatus = "rejected";
				System.out.println(updatedStatus + " inside pending");
			} else if (condition == 1) {
				updatedStatus = "approved";
				System.out.println(updatedStatus + " inside pending");
			}
		}
		System.out.println(updatedStatus + "outside loop");
		boolean isUpdated = employerRepository.changeApplicationStatus(application_id, updatedStatus);
		return isUpdated ? "Application status updated " + status + " successfully." : "Failed to update status.";
	}
	@Override
	public int getTotalApplications(int employerId) {
		return employerRepository.getTotalApplications(employerId);
	}
	@Override
	public int getTotalJobsPosted(int employerId) {
		int jobs = employerRepository.getTotalJobsPosted(employerId);
		System.out.println(jobs);
		return jobs;
	}
	 @Override
	    public int deleteJob(int job_id) {
	        return employerRepository.deleteJob(job_id);
	    }
 

	    @Override
	    public Job getJobById(int job_id) {
	        return employerRepository.findById(job_id);
	    }
		@Override
		public int updateJob(int job_id, String title, String skills, String job_type, double salary_offered, 
				String job_location, MultipartFile description) {
			 return employerRepository.updateJob(job_id, title, skills, job_type, salary_offered, job_location, description);
		}

}
