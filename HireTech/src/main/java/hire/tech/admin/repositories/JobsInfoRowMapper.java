package hire.tech.admin.repositories;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.admin.entities.JobsInfo;
import hire.tech.utils.Utils;

public class JobsInfoRowMapper implements RowMapper<JobsInfo> {

	@Override
	public JobsInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int employerId = rs.getInt("employer_id");
		String fullName = rs.getString("full_name");
		String title = rs.getString("title");
		String skills = rs.getString("skills");
		Double salaryOffered = rs.getDouble("salary_offered");
		String jobType = rs.getString("job_type");
		String jobLocation = rs.getString("job_location");

		return new JobsInfo(employerId, fullName, title, skills, salaryOffered, jobType, jobLocation);

	}
}
