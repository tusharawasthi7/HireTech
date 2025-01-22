package hire.tech.admin.repositories;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.admin.entities.EmployerInfo;

public class EmployerInfoRowMapper implements RowMapper<EmployerInfo> {

	@Override
	public EmployerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int userId = rs.getInt("user_id");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String contactNumber = rs.getString("contact_no");
		String companyName = rs.getString("company_name");
		String industryType = rs.getString("industry_type");
		boolean isAuthorized = rs.getBoolean("authorization_status");
		String status = rs.getString("status");

		return new EmployerInfo(userId, fullName, email, contactNumber, companyName, industryType, isAuthorized,
				status);

	}
}
