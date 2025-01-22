package hire.tech.employer.repositories;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.employer.entities.Job;
import hire.tech.utils.Utils;
import hire.tech.employer.entities.Employer;


		public class JobRowMapper implements RowMapper<Job> {
		 
		@Override
		public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			int jobId = rs.getInt("job_id");
			String title = rs.getString("title");
			String skills = rs.getString("skills");
			double salary = rs.getDouble("salary_offered");
			String jobType = rs.getString("job_type");
			String jobLocation = rs.getString("job_location");
			Blob blobJobDescriptionPdf = rs.getBlob("job_description_pdf");
			
			
			//Blob to Multipartfile
			MultipartFile description = Utils.convertToMultipart(blobJobDescriptionPdf);
			
			return new Job(jobId, title, skills, jobType, salary, jobLocation, description);
			
			
			
			
		
		}
	 
	}
	
	
