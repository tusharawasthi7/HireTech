package hire.tech.jobseeker.repositories;

import hire.tech.jobseeker.entities.*;
import hire.tech.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class JobSeekerRepositoryImpl implements JobSeekerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Job> getAllJobs() {
		final String GET_ALL_JOBS = "SELECT * FROM job";
		return jdbcTemplate.query(GET_ALL_JOBS, new JobseekerRowMapper<Job>());
	}

	@Override
	public Job findById(int jobId) {
		final String FIND_BY_ID = "SELECT * FROM job WHERE job_id = ?";
		return jdbcTemplate.queryForObject(FIND_BY_ID, new JobseekerRowMapper<Job>(), jobId);
	}

	@Override
	public List<Job> searchJobs(String skills, String location, String company) {
		final String SEARCH_JOBS = "SELECT * FROM job WHERE skills LIKE ? OR job_location LIKE ? OR company_name LIKE ?";
		return jdbcTemplate.query(SEARCH_JOBS, new JobseekerRowMapper<Job>(), "%" + skills + "%", "%" + location + "%",
				"%" + company + "%");
	}

	@Override
	public void applyForJob(Application application) {
		final String APPLY_FOR_JOB = "INSERT INTO applications (job_id, job_seeker_id, status, application_date) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(APPLY_FOR_JOB, application.getJobId(), application.getJobSeekerId(),
				application.getStatus(), application.getApplicationDate());
	}

	@Override
	public void saveJob(SavedJob savedJob) {
		final String SAVE_JOB = "INSERT INTO saved_jobs (job_id, job_seeker_id, saved_date) VALUES (?, ?, ?)";
		jdbcTemplate.update(SAVE_JOB, savedJob.getJobId(), savedJob.getJobSeekerId(), savedJob.getSaved_date());
	}

	@Override
	public List<Application> getAppliedJobs(int jobSeekerId) {
		final String GET_APPLIED_JOBS = "SELECT a.*, j.title, j.company_name, j.job_location, j.job_description_pdf, j.skills "
				+ "FROM applications a " + "JOIN job j ON a.job_id = j.job_id " + "WHERE a.job_seeker_id = ?";
		return jdbcTemplate.query(GET_APPLIED_JOBS, new JobseekerRowMapper<Application>(), jobSeekerId);
	}

	@Override
	public List<SavedJob> getSavedJobs(int jobSeekerId) {
		final String GET_SAVED_JOBS = "SELECT s.*, j.title, j.company_name, j.job_location, j.job_description_pdf, j.skills "
				+ "FROM saved_jobs s " + "JOIN job j ON s.job_id = j.job_id " + "WHERE s.job_seeker_id = ?";
		return jdbcTemplate.query(GET_SAVED_JOBS, new JobseekerRowMapper<SavedJob>(), jobSeekerId);
	}

	@Override
	public void subscribe(Subscription subscription) {
		final String CHECK_SUBSCRIPTION = "SELECT COUNT(*) FROM subscriptions WHERE user_id = ? AND status = 'active'";
		Integer count = jdbcTemplate.queryForObject(CHECK_SUBSCRIPTION, new Object[] { subscription.getUserId() },
				Integer.class);
		if (count != null && count > 0) {
			final String UPDATE_SUBSCRIPTION = "UPDATE subscriptions SET subscription_type = ?, start_date = ?, end_date = ? WHERE user_id = ? AND status = 'active'";
			jdbcTemplate.update(UPDATE_SUBSCRIPTION, subscription.getSubscriptionType(), subscription.getStartDate(),
					subscription.getEndDate(), subscription.getUserId());
		} else {
			final String SUBSCRIBE = "INSERT INTO subscriptions (user_id, job_seeker_id, subscription_type, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, 'active')";
			jdbcTemplate.update(SUBSCRIBE, subscription.getUserId(), subscription.getJobSeekerId(),
					subscription.getSubscriptionType(), subscription.getStartDate(), subscription.getEndDate());
		}
	}

	@Override
	public List<Subscription> getSubscriptions(int jobSeekerId) {
		final String GET_SUBSCRIPTIONS = "SELECT * FROM subscriptions WHERE job_seeker_id = ?";
		return jdbcTemplate.query(GET_SUBSCRIPTIONS, new JobseekerRowMapper<Subscription>(), jobSeekerId);
	}

	
	@Override
	public void deleteSavedJob(int savedJobId) {
		final String DELETE_SAVED_JOB = "DELETE FROM saved_jobs WHERE saved_job_id = ?";
		jdbcTemplate.update(DELETE_SAVED_JOB, savedJobId);
	}

	@Override
	public void deleteAppliedJob(int applicationId) {
		final String DELETE_APPLIED_JOB = "DELETE FROM applications WHERE application_id = ?";
		jdbcTemplate.update(DELETE_APPLIED_JOB, applicationId);
	}

	@Override
	public Jobseeker getJobseekerById(int jobSeekerId) throws IOException {
		String sql = "SELECT j.*, u.user_id AS user_id, u.username, u.full_name, u.email, u.contact_no, u.profile_pic "
				+ "FROM job_seeker j " + "JOIN users u ON j.job_seeker_id = u.user_id " + "WHERE j.job_seeker_id = ?";
		return jdbcTemplate.queryForObject(sql, new JobseekerRowMapper<>(), jobSeekerId);
	}

	@Override
	public User getUserById(int userId) throws IOException {
		String sql = "SELECT user_id, username, full_name, email, contact_no, profile_pic, role_id, passwordSalt, passwordHash, authorization_status "
				+ "FROM users WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, new JobseekerRowMapper<>(), userId);
	}

	@Override
	public List<Jobseeker> getAllJobseekers() throws IOException {
		String sql = "SELECT j.*, u.user_id AS user_id, u.username, u.full_name, u.email, u.contact_no "
				+ "FROM job_seeker j " + "JOIN users u ON j.job_seeker_id = u.user_id";
		return jdbcTemplate.query(sql, new JobseekerRowMapper<>());
	}

	@Override
	public void updateJobseeker(Jobseeker jobseeker) throws IOException {
		String sql = "UPDATE job_seeker SET skills = ?, date_of_birth = ?, higher_education = ?, resume = ? WHERE job_seeker_id = ?";
		byte[] resumeBytes = null;
		if (jobseeker.getResume() != null && !jobseeker.getResume().isEmpty()) {
			resumeBytes = jobseeker.getResume().getBytes();
		}
		jdbcTemplate.update(sql, jobseeker.getSkills(), jobseeker.getDateOfBirth(), jobseeker.getHigherEducation(),
				resumeBytes, jobseeker.getJobseekerId());
	}
	
	@Override
	public Resume getResumeByUserId(int userId) {
		final String GET_RESUME_BY_USER_ID = "SELECT r.resume_id, r.user_id, r.full_name, r.email, "
				+ "r.phone_number, r.address, r.education, r.skills, r.experience, u.username, "
				+ "u.full_name, u.email, u.contact_no, u.profile_pic, js.date_of_birth, "
				+ "js.gender, js.higher_education, js.skills, js.preferred_location, js.expected_salary, "
				+ "js.resume, js.job_status " + "FROM resumes r " + "JOIN users u ON r.user_id = u.user_id "
				+ "JOIN job_seeker js ON u.user_id = js.job_seeker_id " + "WHERE r.user_id = ?";
		return jdbcTemplate.queryForObject(GET_RESUME_BY_USER_ID, new JobseekerRowMapper<>(), userId);
	}
}
