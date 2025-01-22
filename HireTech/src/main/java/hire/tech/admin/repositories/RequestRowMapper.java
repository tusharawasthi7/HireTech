package hire.tech.admin.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import hire.tech.admin.entities.EmployerInfo;
import hire.tech.admin.entities.Requests;

public class RequestRowMapper implements RowMapper<Requests> {

	@Override
	public Requests mapRow(ResultSet rs, int rowNum) throws SQLException {

		int employerId = rs.getInt("employer_id");
		String fullName = rs.getString("full_name");
		String companyName = rs.getString("company_name");
		String title = rs.getString("title");
		String industryType = rs.getString("industry_type");
		String jobType = rs.getString("job_type");
		String jobLocation = rs.getString("job_location");
		String status = rs.getString("status");

		return new Requests(status, fullName, companyName, industryType, title, jobType, jobLocation, status);

	}
}
