package hire.tech.user.repositories;

import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.employer.entities.Employer;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.user.entities.Role;
import hire.tech.user.entities.User;
import hire.tech.utils.Utils;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Role> fetchAllRoles() {
		final String GET_ALL_ROLES = "SELECT * FROM roles";
		return jdbcTemplate.query(GET_ALL_ROLES, (rs, rowNum) -> {
			int roleId = rs.getInt("role_id");
			String roleName = rs.getString("role_name");
			return new Role(roleId, roleName);
		});
	}

	@Override
	public Optional<User> getUserBy(String username) {
		User user = null;
		final String FETCH_ALL_USERS = "SELECT * FROM users WHERE username=?";
		try {
			user = jdbcTemplate.queryForObject(FETCH_ALL_USERS, new UserRowMapper(), username);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

	@Override
	public int insertemployer(User user, Employer employer) {
		Blob profilePicture = Utils.convertToblob(user.getProfilePicture());
		Blob companyLogo = Utils.convertToblob(employer.getCompanyLogo());
		int result = 0;
		String userSql = "INSERT INTO users (username, full_name, email, contact_no, profile_pic, role_id, passwordSalt, passwordHash, authorization_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			int userRows = jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getFullName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getContactNumber());
				ps.setBlob(5, profilePicture);
				ps.setInt(6, user.getRoleId());
				ps.setString(7, user.getPasswordSalt());
				ps.setString(8, user.getPasswordHash());
				ps.setBoolean(9, user.isAuthorizationStatus());
				return ps;
			}, keyHolder);

			if (userRows > 0) {
				int employer_id = keyHolder.getKey().intValue();
				String employerSql = "INSERT INTO employer (employer_id, company_name, industry_type, status, company_logo) VALUES (?, ?, ?, ?, ?)";
				int rowsAffectedEmployer = jdbcTemplate.update(employerSql, employer_id, employer.getCompanyName(),
						employer.getIndustryType(), employer.getStatus(), companyLogo);
				if (rowsAffectedEmployer > 0) {
					result = 1;
				}
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public int insertjobseeker(User user, Jobseeker jobseeker) {
	    Blob profilePicture = Utils.convertToblob(user.getProfilePicture());
	    Blob resume = Utils.convertToblob(jobseeker.getResume());
	    int result = 0;
	    String userSql = "INSERT INTO users (username, full_name, email, contact_no, profile_pic, role_id, passwordSalt, passwordHash, authorization_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    try {
	        int userRows = jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
	            ps.setString(1, user.getUsername());
	            ps.setString(2, user.getFullName());
	            ps.setString(3, user.getEmail());
	            ps.setString(4, user.getContactNumber());
	            ps.setBlob(5, profilePicture);
	            ps.setInt(6, user.getRoleId());
	            ps.setString(7, user.getPasswordSalt());
	            ps.setString(8, user.getPasswordHash());
	            ps.setBoolean(9, user.isAuthorizationStatus());
	            return ps;
	        }, keyHolder);
 
	        if (userRows > 0) {
	            int job_seeker_id = keyHolder.getKey().intValue();
	            String jobseekerSql = "INSERT INTO job_seeker (job_seeker_id, date_of_birth, gender, higher_education, skills, preferred_location, expected_salary, resume, job_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            int rowsAffectedJobseeker = jdbcTemplate.update(jobseekerSql, job_seeker_id, jobseeker.getDateOfBirth(),
	                    jobseeker.getGender(), jobseeker.getHigherEducation(), jobseeker.getSkills(),
	                    jobseeker.getPreferredLocation(), jobseeker.getExpectedSalary(), resume,
	                    jobseeker.getJobStatus());
 
	            if (rowsAffectedJobseeker > 0) {
	                // Insert details into resumes table
	                String resumeSql = "INSERT INTO resumes (user_id, full_name, email, phone_number, address, education, skills, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	                int rowsAffectedResume = jdbcTemplate.update(resumeSql, job_seeker_id, user.getFullName(),
	                        user.getEmail(), user.getContactNumber(), "Chennai", jobseeker.getHigherEducation(),
	                        jobseeker.getSkills(), "3");
 
	                if (rowsAffectedResume > 0) {
	                    result = 1;
	                }
	            }
	        }
	    } catch (DataAccessException e) {
	        System.err.println("Data access exception: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return result;
	}
 
	@Override
	public int saveResume(Resume resume) {
	    String resumeSql = "UPDATE resumes SET full_name = ?, email = ?, phone_number = ?, address = ?, education = ?, skills = ?, experience = ? WHERE user_id = ?";
	    return jdbcTemplate.update(resumeSql, resume.getFullName(), resume.getEmail(), resume.getPhoneNumber(),
	            resume.getAddress(), resume.getEducation(), resume.getSkills(), resume.getExperience(), resume.getUserId());
	}
 
	@Override
	public List<Resume> findResumesByUserId(int userId) {
	    String sql = "SELECT r.resume_id, r.user_id, r.full_name, r.email, r.phone_number, r.address, r.education, r.skills, r.experience, u.username, u.full_name, u.email, u.contact_no, u.profile_pic, js.date_of_birth, js.gender, js.higher_education, js.skills, js.preferred_location, js.expected_salary, js.resume, js.job_status FROM resumes r JOIN users u ON r.user_id = u.user_id JOIN job_seeker js ON u.user_id = js.job_seeker_id WHERE r.user_id = ?";
	    return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
	        Resume resume = new Resume();
	        resume.setResumeId(rs.getInt("resume_id"));
	        resume.setUserId(rs.getInt("user_id"));
	        resume.setFullName(rs.getString("full_name"));
	        resume.setEmail(rs.getString("email"));
	        resume.setPhoneNumber(rs.getString("phone_number"));
	        resume.setAddress(rs.getString("address"));
	        resume.setEducation(rs.getString("education"));
	        resume.setSkills(rs.getString("skills"));
	        resume.setExperience(rs.getString("experience"));
	        return resume;
	    });
	}
 


	@Override
	public int toogleAuthority(int userId) {
		final String UPDATE_AUTHORITY = "UPDATE users SET authorization_status = !authorization_status WHERE user_id = ?";
		return jdbcTemplate.update(UPDATE_AUTHORITY, userId);
	}

	@Override
	public void updateUser(User user) throws IOException {
		String sql = "UPDATE users SET username = ?, full_name = ?, email = ?, contact_no = ?, profile_pic = ?, passwordSalt = ?, passwordHash = ? WHERE user_id = ?";
		byte[] profilePictureBytes = null;
		if (user.getProfilePicture() != null && !user.getProfilePicture().isEmpty()) {
			profilePictureBytes = user.getProfilePicture().getBytes();
		}
		int rowsAffected = jdbcTemplate.update(sql, user.getUsername(), user.getFullName(), user.getEmail(),
				user.getContactNumber(), profilePictureBytes, user.getPasswordSalt(), user.getPasswordHash(),
				user.getUserId());
		System.out.println("Rows affected: " + rowsAffected);
	}

	@Override
	public User findById(int userId) {
		String sql = "SELECT user_id, username, full_name, email, contact_no, profile_pic, role_id, passwordSalt, passwordHash, authorization_status FROM users WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
	}

	@Override
	public int findByUsername(String username) {
		String USERNAME = "select count(*) from users where username=?";
		return jdbcTemplate.queryForObject(USERNAME, new Object[] { username }, Integer.class);
	}

	@Override
	public int findByEmail(String email) {
		String EMAIL = "select count(*) from users where email=?";
		return jdbcTemplate.queryForObject(EMAIL, new Object[] { email }, Integer.class);
	}

	@Override
	public List<User> fetchUser(int userId) {
		final String SQL = "SELECT * FROM users where user_id = ?";
		return jdbcTemplate.query(SQL, new Object[] { userId }, new UserRowMapper());

	}

	@Override
	public int updateUserEmployer(int userId, String username, String fullname, String email, String mobileNo,
			MultipartFile profilePicture) throws SerialException, IOException, SQLException {
		String UPDATE_USER;
		Object[] params;

		if (profilePicture != null && !profilePicture.isEmpty()) {
			byte[] profilePictureBytes = profilePicture.getBytes();
			UPDATE_USER = "UPDATE users SET username = ?, full_name = ?, email = ?, contact_no = ?, profile_pic = ? WHERE user_id = ?;";
			params = new Object[] { username, fullname, email, mobileNo, profilePictureBytes, userId };
		} else {
			UPDATE_USER = "UPDATE users SET username = ?, full_name = ?, email = ?, contact_no = ? WHERE user_id = ?;";
			params = new Object[] { username, fullname, email, mobileNo, userId };
		}
		int rowsAffected = jdbcTemplate.update(UPDATE_USER, params);
		return rowsAffected;
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		User user = null;

		final String FETCH_ALL_USERS = "SELECT * FROM users WHERE email=?";

		try {
			user = jdbcTemplate.queryForObject(FETCH_ALL_USERS, new UserRowMapper(), email);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return Optional.ofNullable(user);

	}

	@Override
	public int updateUsers(User user) {
		final String UPDATE_USER = "UPDATE users SET passwordSalt = ?, passwordHash = ? WHERE user_id = ?";
		return jdbcTemplate.update(UPDATE_USER, user.getPasswordSalt(), user.getPasswordHash(), user.getUserId());
	}

	@Override
	public boolean isEmailRegistered(String email) {
		String query = "SELECT COUNT(*) FROM users WHERE email = ?";
		Integer count = jdbcTemplate.queryForObject(query, new Object[] { email }, Integer.class);
		return count != null && count > 0;
	}
}
