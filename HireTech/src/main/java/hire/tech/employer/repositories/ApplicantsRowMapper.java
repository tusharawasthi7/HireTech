package hire.tech.employer.repositories;
 
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;
 
import hire.tech.employer.entities.*;
import hire.tech.utils.Utils;
 
public class ApplicantsRowMapper implements RowMapper<Applications> {
    @Override
    public Applications mapRow(ResultSet rs, int rowNum) throws SQLException {

    	int applicationId = rs.getInt("application_id");
    	int jobSeeker = rs.getInt("job_seeker_id");
    	String mobileNo = rs.getString("contact_no");
    	Blob resumePdf=rs.getBlob("resume");
    	String skills = rs.getString("skills");
    	String fullName = rs.getString("full_name");
    	String email = rs.getString("email");
    	String job_seeker_status= rs.getString("job_seeker_status");
    	String application_status = rs.getString("application_status");
    	Date application_date = rs.getDate("application_date");
    	String title = rs.getString("title");
    	String higherEducation = rs.getString("higher_education");

		//Blob to Multipartfile
		MultipartFile resume = Utils.convertToMultipart(resumePdf);

    	//application_id, job_seeker_id, full_name, contact_no, higher_education, skills, job_seeker_status, resume, application_status, application_date, title

    	return new Applications(applicationId, jobSeeker, fullName, email, mobileNo, higherEducation, job_seeker_status, skills, resume, application_status, application_date, title);
 
        
         }
}