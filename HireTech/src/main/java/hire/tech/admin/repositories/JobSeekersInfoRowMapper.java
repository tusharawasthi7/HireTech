package hire.tech.admin.repositories;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.admin.entities.EmployerInfo;
import hire.tech.admin.entities.JobSeekersInfo;
import hire.tech.utils.Utils;

public class JobSeekersInfoRowMapper implements RowMapper<JobSeekersInfo> {

	@Override
	public JobSeekersInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int userId = rs.getInt("user_id");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String contactNumber = rs.getString("contact_no");
		Date dateOfBirth = rs.getDate("date_of_birth");
		String gender = rs.getString("gender");
		String higherEducation = rs.getString("higher_education");
		String skills = rs.getString("skills");
		String preferredLocation = rs.getString("preferred_location");
		String expectedSalary = rs.getString("expected_salary");
		String jobStatus = rs.getString("job_status");

		return new JobSeekersInfo(userId, fullName, email, contactNumber, dateOfBirth, gender, higherEducation, skills,
				preferredLocation, expectedSalary, jobStatus);

	}
}
