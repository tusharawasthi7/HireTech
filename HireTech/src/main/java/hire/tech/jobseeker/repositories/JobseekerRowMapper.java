package hire.tech.jobseeker.repositories;

import hire.tech.jobseeker.entities.*;
import hire.tech.user.entities.User;
import hire.tech.utils.Utils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Component
public class JobseekerRowMapper<T> implements RowMapper<T> {

	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		String tableName = rs.getMetaData().getTableName(1).toLowerCase();

		try {
			switch (tableName) {
			case "job":
				return (T) mapRowToJob(rs);
			case "job_seeker":
				return (T) mapRowToJobseeker(rs);
			case "applications":
				return (T) mapRowToApplication(rs);
			case "saved_jobs":
				return (T) mapRowToSavedJob(rs);
			case "subscriptions":
				return (T) mapRowToSubscription(rs);
			case "resumes":
				return (T) mapRowToResume(rs);
			case "users":
				return (T) mapRowToUser(rs);
			default:
				throw new SQLException("Unknown table: " + tableName);
			}
		} catch (IOException e) {
			throw new SQLException("Error mapping row", e);
		}
	}

	private Job mapRowToJob(ResultSet rs) throws SQLException, IOException {
		int jobId = rs.getInt("job_id");
		String title = rs.getString("title");
		String skills = rs.getString("skills");
		double salaryOffered = rs.getDouble("salary_offered");
		String jobType = rs.getString("job_type");
		String jobLocation = rs.getString("job_location");
		int employerId = rs.getInt("employer_id");
		String companyName = rs.getString("company_name");
		Blob jobDescriptionBlob = rs.getBlob("job_description_pdf");

		String descriptionContent = "";
		MultipartFile jobDescriptionPdf = null;
		if (jobDescriptionBlob != null) {
			jobDescriptionPdf = Utils.convertToMultipart(jobDescriptionBlob);
			try (InputStream is = jobDescriptionBlob.getBinaryStream()) {
				PDFTextStripper pdfStripper = new PDFTextStripper();
				try (PDDocument document = PDDocument.load(is)) {
					descriptionContent = pdfStripper.getText(document);
				}
			}
		}

		Job job = new Job(jobId, title, skills, salaryOffered, jobType, jobLocation, employerId, jobDescriptionPdf);
		job.setCompanyName(companyName);
		job.setDescriptionContent(descriptionContent);
		return job;
	}

	private Jobseeker mapRowToJobseeker(ResultSet rs) throws SQLException, IOException {
		int jobseekerId = rs.getInt("job_seeker_id");
		java.sql.Date dateOfBirth = rs.getDate("date_of_birth");
		String gender = rs.getString("gender");
		String higherEducation = rs.getString("higher_education");
		String skills = rs.getString("skills");
		String preferredLocation = rs.getString("preferred_location");
		double expectedSalary = rs.getDouble("expected_salary");
		Blob resumeBlob = rs.getBlob("resume");
		String jobStatus = rs.getString("job_status");

		MultipartFile resume = Utils.convertToMultipart(resumeBlob);

		Jobseeker jobseeker = new Jobseeker(jobseekerId, dateOfBirth, gender, higherEducation, skills,
				preferredLocation, preferredLocation, resume, jobStatus);
		User user = new User();
		user.setUserId(rs.getInt("user_id"));
		user.setUsername(rs.getString("username"));
		user.setFullName(rs.getString("full_name"));
		user.setEmail(rs.getString("email"));
		user.setContactNumber(rs.getString("contact_no"));
		Blob profilePictureBlob = rs.getBlob("profile_pic");
		MultipartFile profilePicture = Utils.convertToMultipart(profilePictureBlob);
		user.setProfilePicture(profilePicture);

		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());

		return jobseeker;

	}

	private Application mapRowToApplication(ResultSet rs) throws SQLException {
		return new Application(rs.getInt("application_id"), rs.getInt("job_id"), rs.getInt("job_seeker_id"),
				rs.getString("status"), rs.getDate("application_date"));
	}

	private SavedJob mapRowToSavedJob(ResultSet rs) throws SQLException {
		return new SavedJob(rs.getInt("saved_job_id"), rs.getInt("job_id"), rs.getInt("job_seeker_id"),
				rs.getDate("saved_date"));
	}

	private Subscription mapRowToSubscription(ResultSet rs) throws SQLException {
		return new Subscription(rs.getInt("subscription_id"), rs.getInt("user_id"), rs.getInt("job_seeker_id"),
				rs.getString("subscription_type"), rs.getDate("start_date"), rs.getDate("end_date"),
				rs.getString("status"));
	}

	public Resume mapRowToResume(ResultSet rs) throws SQLException {
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
	}

	private User mapRowToUser(ResultSet rs) throws SQLException, IOException {
		int userId = rs.getInt("user_id");
		String username = rs.getString("username");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String contactNumber = rs.getString("contact_no");
		Blob profilePictureBlob = rs.getBlob("profile_pic");
		MultipartFile profilePicture = Utils.convertToMultipart(profilePictureBlob);

		User user = new User(userId, username, null, fullName, email, contactNumber, profilePicture,
				rs.getInt("role_id"), rs.getString("passwordSalt"), rs.getString("passwordHash"),
				rs.getBoolean("authorization_status"));

		return user;
	}

}
