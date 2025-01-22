package hire.tech.user.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import hire.tech.employer.entities.Employer;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.user.entities.Role;
import hire.tech.user.entities.User;

public interface UserService {
	List<Role> getAllRoles();

	Optional<User> fetchUserBy(String username);

	boolean matchPassword(String pwdFromUI, User userDb);

	int updateAuthority(int userId);

	int registerEmployer(User user, Employer employer);

	int registerJobseeker(User user, Jobseeker jobseeker);

	void updateUser(User user) throws IOException;

	User getUserById(int userId);

	void saveResume(Resume resume);

	boolean updatePassword(int userId, String currentPassword, String newPassword) throws IOException;

	boolean isUsernameUnique(String username);

	boolean isEmailUnique(String email);

	List<User> fetchUser(int userId);

	int updateUserEmployer(int userId, String username, String fullname, String email, String mobileNo,
			MultipartFile profilePicture) throws SerialException, IOException, SQLException;

	void updatePassword(String email, String newPassword);

	boolean isEmailRegistered(String email);
}
