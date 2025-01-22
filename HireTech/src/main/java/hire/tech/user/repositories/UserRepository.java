package hire.tech.user.repositories;

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

public interface UserRepository {

	List<Role> fetchAllRoles();

	Optional<User> getUserBy(String username);

	int insertemployer(User user, Employer employer);

	int insertjobseeker(User user, Jobseeker jobseeker);

	int toogleAuthority(int userId);

	void updateUser(User user) throws IOException;

	User findById(int userId);

	int saveResume(Resume resume);

	List<Resume> findResumesByUserId(int userId);

	int findByUsername(String username);

	int findByEmail(String email);

	List<User> fetchUser(int userId);

	public int updateUserEmployer(int userId, String username, String fullname, String email, String mobileNo,
			MultipartFile profilePicture) throws SerialException, IOException, SQLException;

	Optional<User> findUserByEmail(String email);

	int updateUsers(User user);

	boolean isEmailRegistered(String email);

}
