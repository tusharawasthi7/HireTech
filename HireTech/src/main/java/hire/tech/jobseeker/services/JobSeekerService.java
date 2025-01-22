package hire.tech.jobseeker.services;

import hire.tech.jobseeker.entities.Application;
import hire.tech.jobseeker.entities.Job;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.jobseeker.entities.SavedJob;
import hire.tech.jobseeker.entities.Subscription;
import hire.tech.user.entities.User;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

public interface JobSeekerService {

	List<Job> getAllJobs();

	Job findById(int jobId);

	List<Job> searchJobs(String skills, String location, String company);

	void applyForJob(Application application);

	void saveJob(SavedJob savedJob);

	List<Application> getAppliedJobs(int jobSeekerId);

	List<SavedJob> getSavedJobs(int jobSeekerId);

	void subscribe(Subscription subscription);

	List<Subscription> getSubscriptions(int jobSeekerId);

	void saveResume(Resume resume) throws IOException;

	Resume getResumeByUserId(int userId);

	void downloadJobDescriptionPdf(HttpServletResponse response, MultipartFile jobDescriptionPdf) throws IOException;

	void deleteSavedJob(int savedJobId);

	void deleteAppliedJob(int applicationId);

	List<Jobseeker> getAllJobseekers() throws IOException;

	Jobseeker getJobseekerById(int jobSeekerId) throws IOException;

	User getUserById(int userId) throws IOException;

	void updateUser(User user) throws IOException;

	void updateJobseeker(Jobseeker jobseeker) throws IOException;
}
