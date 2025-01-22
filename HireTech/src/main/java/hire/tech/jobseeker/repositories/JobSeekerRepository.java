package hire.tech.jobseeker.repositories;

import hire.tech.jobseeker.entities.Application;
import hire.tech.jobseeker.entities.Job;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.jobseeker.entities.SavedJob;
import hire.tech.jobseeker.entities.Subscription;
import hire.tech.user.entities.User;
 
import java.io.IOException;
import java.util.List;
import java.util.Map;
 
public interface JobSeekerRepository {
	List<Job> getAllJobs();
 
	Job findById(int jobId);
 
	List<Job> searchJobs(String skills, String location, String company);
 
	void applyForJob(Application application);
 
	void saveJob(SavedJob savedJob);
 
	List<Application> getAppliedJobs(int jobSeekerId);
 
	List<SavedJob> getSavedJobs(int jobSeekerId);
 
	void subscribe(Subscription subscription);
 
	List<Subscription> getSubscriptions(int jobSeekerId);
 
	Resume getResumeByUserId(int userId);
 
	void deleteSavedJob(int savedJobId);
 
	void deleteAppliedJob(int applicationId);
 
	Jobseeker getJobseekerById(int jobSeekerId) throws IOException;
 
	User getUserById(int userId) throws IOException;
 
	List<Jobseeker> getAllJobseekers() throws IOException;
 
	void updateJobseeker(Jobseeker jobseeker) throws IOException;
 
}

