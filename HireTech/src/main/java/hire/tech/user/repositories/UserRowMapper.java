package hire.tech.user.repositories;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.user.entities.User;
import hire.tech.utils.Utils;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {

		int userId = rs.getInt("user_id");
		String username = rs.getString("username");
		String fullName = rs.getString("full_name");
		String email = rs.getString("email");
		String contactNumber = rs.getString("contact_no");
		Blob blobprofilePicture = rs.getBlob("profile_pic");
		int roleId = rs.getInt("role_id");
		String passwordSalt = rs.getString("passwordSalt");
		String passwordHash = rs.getString("passwordHash");
		boolean isAuthorized = rs.getBoolean("authorization_status");
		MultipartFile profilePicture = Utils.convertToMultipart(blobprofilePicture);

		return new User(userId, username, passwordHash, fullName, email, contactNumber, profilePicture, roleId,
				passwordSalt, passwordHash, isAuthorized);
	}
}
