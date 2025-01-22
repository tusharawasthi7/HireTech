package hire.tech.admin.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import hire.tech.admin.entities.Subscription;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.repositories.JobseekerRowMapper;
import hire.tech.user.entities.User;
import hire.tech.user.repositories.UserRowMapper;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;// Automatically injects the JdbcTemplate bean.

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Map<String, Object>> fetchAllEmployers() {
		final String GET_ALL_EMPLOYER = "select u.user_id,u.full_name,u.email,u.contact_no,u.authorization_status,\r\n"
				+ "e.company_name,e.industry_type\r\n" + " from users u\r\n" + " inner join \r\n" + " employer e\r\n"
				+ " on\r\n" + " u.user_id = e.employer_id;";
		return jdbcTemplate.queryForList(GET_ALL_EMPLOYER);
	}

	public int updateEmployerStatus(int userId) {
		boolean userStatus;
		String UPDATE_EMPLOYER_STATUS = "";
		final String FETCH_USER = "SELECT * FROM users WHERE user_id=?";
		userStatus = jdbcTemplate.queryForObject(FETCH_USER, new UserRowMapper(), userId).isAuthorizationStatus();
		if (userStatus == true) {
			UPDATE_EMPLOYER_STATUS = "update Employer set status= 'approved' where employer_id=?";
		} else {
			UPDATE_EMPLOYER_STATUS = "update Employer set status= 'rejected' where employer_id=?";
		}
		return jdbcTemplate.update(UPDATE_EMPLOYER_STATUS, userId);
	}

	public List<Map<String, Object>> fetchAllJobSeekers() {
		final String GET_ALL_JOBSEEKERS = "select  u.user_id, u.full_name,u.email,u.contact_no,\r\n"
				+ "js.date_of_birth,js.gender,js.higher_education,js.skills,js.preferred_location,js.expected_salary,js.resume,js.job_status\r\n"
				+ "from users u\r\n" + "inner join\r\n" + "job_seeker js\r\n" + "on\r\n"
				+ "u.user_id = js.job_seeker_id;";
		return jdbcTemplate.queryForList(GET_ALL_JOBSEEKERS);
	}

	public List<Map<String, Object>> fetchAllJobsInfo() {
		final String GET_ALL_JOBS = "SELECT \r\n" + "e.employer_id,\r\n" + "u.full_name,\r\n" + "e.company_name,\r\n"
				+ "j.title,\r\n" + "j.skills,\r\n" + "j.salary_offered,\r\n" + "j.job_type,\r\n" + "j.job_location\r\n"
				+ "from  \r\n" + "users u  \r\n" + "inner join  \r\n" + "employer e  \r\n"
				+ "on u.user_id = e.employer_id \r\n" + "inner join \r\n" + "job j  \r\n"
				+ "on e.employer_id = j.employer_id;";
		return jdbcTemplate.queryForList(GET_ALL_JOBS);
	}

	@Override
	public List<Map<String, Object>> fetchPendingEmployersWithDetails() {
		String query = "SELECT e.employer_id, u.full_name, e.company_name, e.industry_type " + "FROM employer e "
				+ "JOIN users u ON u.user_id = e.employer_id " + "WHERE e.status = 'pending'";
		return jdbcTemplate.queryForList(query);
	}

	@Override
	public List<Map<String, Object>> fetchApprovedEmployersWithDetails() {
		String query = "SELECT e.employer_id, u.full_name, e.company_name, e.industry_type " + "FROM employer e "
				+ "JOIN users u ON u.user_id = e.employer_id " + "WHERE e.status = 'approved'";
		return jdbcTemplate.queryForList(query);
	}

	@Override
	public List<Map<String, Object>> fetchRejectEmployersWithDetails() {
		String query = "SELECT e.employer_id, u.full_name, e.company_name, e.industry_type " + "FROM employer e "
				+ "JOIN users u ON u.user_id = e.employer_id " + "WHERE e.status = 'rejected'";
		return jdbcTemplate.queryForList(query);
	}

	@Override
	public List<User> getAdminById(int userId) {
		final String sql = "SELECT * FROM users WHERE user_id = ?";
		return jdbcTemplate.query(sql, new Object[] { userId }, new UserRowMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<User> findById(Integer userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		try {
			return jdbcTemplate.query(sql, new Object[] { userId }, rs -> {
				if (rs.next()) {
					return Optional.of(new UserRowMapper().mapRow(rs, rs.getRow()));
				} else {
					return Optional.empty();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public void updateUser(User user) {
		String sql = "UPDATE users SET username =?, full_name = ?, email = ?, contact_no = ?, profile_pic = ? WHERE user_id = ?";
		try (Connection connection = jdbcTemplate.getDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getFullName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getContactNumber());
			preparedStatement.setBytes(5,
					user.getProfilePicture() != null ? user.getProfilePicture().getBytes() : null);
			preparedStatement.setInt(6, user.getUserId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error updating user profile in database: " + e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Subscription> findSubscriptionsByjobseekerId() {
		String sql = "SELECT * FROM subscriptions";
		return jdbcTemplate.query(sql, new SubscriptionRowMapper());
	}

}
