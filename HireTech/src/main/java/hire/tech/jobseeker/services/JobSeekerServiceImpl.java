package hire.tech.jobseeker.services;

import hire.tech.jobseeker.entities.Application;
import hire.tech.jobseeker.entities.Job;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.jobseeker.entities.SavedJob;
import hire.tech.jobseeker.entities.Subscription;
import hire.tech.jobseeker.repositories.JobSeekerRepository;
import hire.tech.user.entities.User;
import hire.tech.user.repositories.UserRepository;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Job> getAllJobs() {
		return jobSeekerRepository.getAllJobs();
	}

	@Override
	public Job findById(int jobId) {
		return jobSeekerRepository.findById(jobId);
	}

	@Override
	public List<Job> searchJobs(String skills, String location, String company) {
		return jobSeekerRepository.searchJobs(skills, location, company);
	}

	@Override
	public void applyForJob(Application application) {
		jobSeekerRepository.applyForJob(application);
	}

	@Override
	public void saveJob(SavedJob savedJob) {
		jobSeekerRepository.saveJob(savedJob);
	}

	@Override
	public List<Application> getAppliedJobs(int jobSeekerId) {
		return jobSeekerRepository.getAppliedJobs(jobSeekerId);
	}

	@Override
	public List<SavedJob> getSavedJobs(int jobSeekerId) {
		return jobSeekerRepository.getSavedJobs(jobSeekerId);
	}

	@Override
	public void subscribe(Subscription subscription) {
		jobSeekerRepository.subscribe(subscription);
	}

	@Override
	public List<Subscription> getSubscriptions(int jobSeekerId) {
		return jobSeekerRepository.getSubscriptions(jobSeekerId);
	}

	
	@Override
    public void saveResume(Resume resume) throws IOException {
        userRepository.saveResume(resume);
    }
 
    @Override
    public Resume getResumeByUserId(int userId) {
        return jobSeekerRepository.getResumeByUserId(userId);
    }
    
    
	@Override
	public void downloadJobDescriptionPdf(HttpServletResponse response, MultipartFile jobDescriptionPdf)
			throws IOException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=" + jobDescriptionPdf.getOriginalFilename());

		try (InputStream inputStream = jobDescriptionPdf.getInputStream()) {
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
		}
	}

	@Override
	public void deleteSavedJob(int savedJobId) {
		jobSeekerRepository.deleteSavedJob(savedJobId);
	}

	@Override
	public void deleteAppliedJob(int applicationId) {
		jobSeekerRepository.deleteAppliedJob(applicationId);
	}

	public List<Jobseeker> getAllJobseekers() throws IOException {
		return jobSeekerRepository.getAllJobseekers();
	}

	public Jobseeker getJobseekerById(int jobSeekerId) throws IOException {
		return jobSeekerRepository.getJobseekerById(jobSeekerId);
	}

	public User getUserById(int userId) throws IOException {
		return jobSeekerRepository.getUserById(userId);
	}

	@Override
	public void updateUser(User user) throws IOException {
		userRepository.updateUser(user);
	}

	@Override
	public void updateJobseeker(Jobseeker jobseeker) throws IOException {
		jobSeekerRepository.updateJobseeker(jobseeker);
	}

}
