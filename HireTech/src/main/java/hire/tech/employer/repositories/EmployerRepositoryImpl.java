package hire.tech.employer.repositories;
 
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.rowset.serial.SerialException;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
 
import hire.tech.employer.entities.Applications;
import hire.tech.employer.entities.Employer;
import hire.tech.employer.entities.Job;
import hire.tech.utils.Utils;
import hire.tech.employer.repositories.EmployerRowMapper;
import hire.tech.user.entities.User;
import hire.tech.user.repositories.UserRowMapper;
 
@Repository
public class EmployerRepositoryImpl implements EmployerRepository {
 
	@Autowired
	private JdbcTemplate jdbcTemplate;
 
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
 
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
 
	@Override
	public List<Job> fetchAllJobs(int employerId) {
		final String GET_JOBS = "SELECT * FROM job where employer_id = ?";
		return jdbcTemplate.query(GET_JOBS, new Object[] { employerId }, new JobRowMapper());
 
	}
 
	@Override
	public int addJob(Job job, Employer employer, int employerId) throws SerialException, IOException, SQLException {
 
		final String ADD_JOB = "INSERT INTO job (job_id, title, skills, salary_offered, job_type, job_location, employer_id, job_description_pdf, company_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
		Blob description = Utils.convertToblob(job.getDescription());
		System.out.println(job.getSalary_offered()+"333333365e5y");

 
		return jdbcTemplate.update(ADD_JOB, job.getJob_id(), job.getTitle(), job.getSkills(), job.getSalary_offered(),
				job.getJob_type(), job.getJob_location(), employerId, description, employer.getCompanyName());
 
	}
 
	@Override
	public List<Employer> fetchAllEmployer() {
		final String GET_EMPLOYER = "SELECT * FROM employer where employer_id = ?";
		return jdbcTemplate.query(GET_EMPLOYER, new EmployerRowMapper());
 
	}
 
	@Override
	public List<Employer> fetchUpdateEmployer(int employerId) {
		final String UPDATE_EMPLOYER = "SELECT * FROM employer where employer_id = ?";
		return jdbcTemplate.query(UPDATE_EMPLOYER, new Object[] { employerId }, new EmployerRowMapper());
 
	}
 
	@Override
	public List<Applications> getPendingApplicantsOfEmployer(int employerId) {
 
		System.out.println(employerId);
		String GET_PENDING_APPLICANTS_BY_EMPLOYEE_ID = "SELECT \r\n" + "    a.application_id, \r\n"
				+ "    js.job_seeker_id, \r\n" + "    u.full_name, \r\n" +  " u.email, \r\n   "+ "    u.contact_no, \r\n"
				+ "    js.higher_education, \r\n" + "    js.skills, \r\n"
				+ "    js.job_status AS job_seeker_status, \r\n" + "    js.resume, \r\n"
				+ "    a.status AS application_status, \r\n" + "    a.application_date, \r\n" + "    j.title\r\n"
				+ "FROM \r\n" + "    applications a\r\n" + "JOIN \r\n" + "    job j ON a.job_id = j.job_id\r\n"
				+ "JOIN \r\n" + "    job_seeker js ON a.job_seeker_id = js.job_seeker_id\r\n" + "JOIN \r\n"
				+ "    users u ON js.job_seeker_id = u.user_id\r\n" + "WHERE \r\n" + "    j.employer_id = ?\r\n"
				+ "AND \r\n" + "    a.status = 'pending';";
 
		return jdbcTemplate.query(GET_PENDING_APPLICANTS_BY_EMPLOYEE_ID, new ApplicantsRowMapper(), employerId);
 
	}
 
	@Override
	public List<Applications> getRejectedApplicantsOfEmployer(int employerId) {
 
		String GET_REJECTED_APPLICANTS_BY_EMPLOYEE_ID = "SELECT \r\n" + "    a.application_id, \r\n"
				+ "    js.job_seeker_id, \r\n" + "    u.full_name, \r\n" +  " u.email, \r\n   "+"    u.contact_no, \r\n"
				+ "    js.higher_education, \r\n" + "    js.skills, \r\n"
				+ "    js.job_status AS job_seeker_status, \r\n" + "    js.resume, \r\n"
				+ "    a.status AS application_status, \r\n" + "    a.application_date, \r\n" + "    j.title\r\n"
				+ "FROM \r\n" + "    applications a\r\n" + "JOIN \r\n" + "    job j ON a.job_id = j.job_id\r\n"
				+ "JOIN \r\n" + "    job_seeker js ON a.job_seeker_id = js.job_seeker_id\r\n" + "JOIN \r\n"
				+ "    users u ON js.job_seeker_id = u.user_id\r\n" + "WHERE \r\n" + "    j.employer_id = ?\r\n"
				+ "AND \r\n" + "    a.status = 'rejected';";
 
		return jdbcTemplate.query(GET_REJECTED_APPLICANTS_BY_EMPLOYEE_ID, new ApplicantsRowMapper(), employerId); // employerid
	}

 
	@Override
	public List<Applications> getApprovedApplicantsOfEmployer(int employerId) {
 
		System.out.println(employerId + "approved");
		String GET_APPROVED_APPLICANTS_BY_EMPLOYEE_ID = "SELECT \r\n" + "    a.application_id, \r\n"
				+ "    js.job_seeker_id, \r\n" + "    u.full_name, \r\n"+ " u.email, \r\n   " + "    u.contact_no, \r\n"
				+ "    js.higher_education, \r\n" + "    js.skills, \r\n"
				+ "    js.job_status AS job_seeker_status, \r\n" + "    js.resume, \r\n"
				+ "    a.status AS application_status, \r\n" + "    a.application_date, \r\n" + "    j.title\r\n"
				+ "FROM \r\n" + "    applications a\r\n" + "JOIN \r\n" + "    job j ON a.job_id = j.job_id\r\n"
				+ "JOIN \r\n" + "    job_seeker js ON a.job_seeker_id = js.job_seeker_id\r\n" + "JOIN \r\n"
				+ "    users u ON js.job_seeker_id = u.user_id\r\n" + "WHERE \r\n" + "    j.employer_id = ?\r\n"
				+ "AND \r\n" + "    a.status = 'approved';";
 
		return jdbcTemplate.query(GET_APPROVED_APPLICANTS_BY_EMPLOYEE_ID, new ApplicantsRowMapper(), employerId); // employerid
	}
 
	@Override
	public Employer getEmployerByUserId(int userId) {
		String sql = "SELECT u.user_id, e.employer_id, e.company_name, e.industry_type, e.status, e.company_logo "
				+ "FROM users u " + "LEFT JOIN employer e ON u.user_id = e.employer_id " + "WHERE u.user_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userId }, new EmployerRowMapper());
 
	}
 
	@Override
	public boolean changeApplicationStatus(int application_id, String status) {
		System.out.println("application_id: " + application_id + ", status: " + status);
		final String CHANGE_STATUS = "UPDATE applications SET status = ? WHERE application_id = ?";
		int result = jdbcTemplate.update(CHANGE_STATUS, status, application_id);
		return result > 0;
	}
 
	public int getTotalJobsPosted(int employerId) {
		System.out.println(employerId + "employerIdTotalJobsPosted");
		String sql = "SELECT COUNT(*) FROM Job WHERE employer_id = ?";
		int jobs = jdbcTemplate.queryForObject(sql, new Object[] { employerId }, Integer.class);
 
		return jobs;
	}
 
	public int getTotalApplications(int employerId) {
		String sql = "SELECT COUNT(*) FROM Applications a JOIN Job j ON a.job_id = j.job_id WHERE j.employer_id = ?";
		int app = jdbcTemplate.queryForObject(sql, new Object[] { employerId }, Integer.class);
		return app;
	}
 
	@Override
	public int deleteJob(int job_id) {
		System.out.println("Deleting job with job_id: " + job_id);
		final String DELETE_JOB = "DELETE FROM Job WHERE job_id = ?";
		int rowsAffected = jdbcTemplate.update(DELETE_JOB, job_id);
		return rowsAffected;
	}
 
	@Override
	public int updateJob(int job_id, String title, String skills, String job_type, double salary_offered,
			String job_location, MultipartFile description) {
		System.out.println(title + skills + salary_offered + job_type + job_location + description);
		System.out.println("Updating job with job_id: " + job_id);
 
		final String UPDATE_JOB = "UPDATE Job SET title = ?, skills = ?, job_type = ?, salary_offered = ?, job_location = ?, job_description_pdf = ? WHERE job_id = ?;";
		Blob description1 = Utils.convertToblob(description);
		int rowsAffected = jdbcTemplate.update(UPDATE_JOB, title, skills, job_type, salary_offered, job_location,
				description1, job_id);
		return rowsAffected;
	}
 
	@Override
	public Job findById(int job_id) {
		final String FIND_JOB = "SELECT * FROM Job WHERE job_id = ?";
		return jdbcTemplate.queryForObject(FIND_JOB, new Object[] { job_id }, new JobRowMapper());
	}
 
	
	}
 