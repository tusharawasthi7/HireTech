package hire.tech.employer.repositories;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.employer.entities.Employer;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.utils.Utils;

public class EmployerRowMapper implements RowMapper<Employer>  {

	@Override
	public Employer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int employerId=rs.getInt("employer_id");
		String companyName=rs.getString("company_name");
		String industryType=rs.getString("industry_type");
		String status=rs.getString("status");
		Blob blobcompanyLogo=rs.getBlob("company_logo");
		
		MultipartFile companyLogo = Utils.convertToMultipart(blobcompanyLogo);
		
		return new Employer(employerId,companyName,industryType, status,companyLogo);

 
	}

	
}
