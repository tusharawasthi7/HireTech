package hire.tech.user.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hire.tech.employer.entities.Employer;
import hire.tech.jobseeker.entities.Jobseeker;
import hire.tech.jobseeker.entities.Resume;
import hire.tech.user.entities.Role;
import hire.tech.user.entities.User;
import hire.tech.user.repositories.UserRepository;
import hire.tech.utils.Utils;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Role> getAllRoles() {
		return userRepository.fetchAllRoles();
	}

	@Override
	public Optional<User> fetchUserBy(String username) {
		return userRepository.getUserBy(username);
	}

	@Override
	public boolean matchPassword(String pwdFromUI, User userDb) {
		String modifiedPwd = pwdFromUI + userDb.getPasswordSalt();
		String newHash = Utils.generateHash(modifiedPwd);
		String oldHash = userDb.getPasswordHash();
		return newHash.equals(oldHash);
	}

	@Override
	public int updateAuthority(int userId) {
		return userRepository.toogleAuthority(userId);
	}

	@Override
	public int registerEmployer(User user, Employer employer) {
		String pwdFromUI = user.getPassword();
		String pwdSalt = Utils.generateSalt();
		String modifiedPwd = pwdFromUI + pwdSalt;
		String pwdHash = Utils.generateHash(modifiedPwd);
		user.setPasswordSalt(pwdSalt);
		user.setPasswordHash(pwdHash);
		if (user.getRoleId() == 1)
			user.setAuthorizationStatus(true);
		return userRepository.insertemployer(user, employer);
	}

	@Override
	@Transactional
	public int registerJobseeker(User user, Jobseeker jobseeker) {
		String pwdFromUI = user.getPassword();
		String pwdSalt = Utils.generateSalt();
		String modifiedPwd = pwdFromUI + pwdSalt;
		String pwdHash = Utils.generateHash(modifiedPwd);
		user.setPasswordSalt(pwdSalt);
		user.setPasswordHash(pwdHash);
		if (user.getRoleId() == 1)
			user.setAuthorizationStatus(true);
		int result = userRepository.insertjobseeker(user, jobseeker);
		if (result > 0) {
			Optional<User> savedUser = userRepository.getUserBy(user.getUsername());

			if (savedUser.isPresent()) {
				Resume resume = new Resume();
				resume.setUserId(savedUser.get().getUserId());
				resume.setFullName(savedUser.get().getFullName());
				resume.setEmail(savedUser.get().getEmail());
				resume.setPhoneNumber(savedUser.get().getContactNumber());
				resume.setAddress("Default Address"); // Adjust as needed
				resume.setEducation(jobseeker.getHigherEducation());
				resume.setSkills(jobseeker.getSkills());
				resume.setExperience("Default Experience"); // Adjust as needed
				userRepository.saveResume(resume);
			}
		}

		return result;
	}

	@Override
	public void updateUser(User user) throws IOException {
		userRepository.updateUser(user);
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId);
	}

	
	@Override
	public void saveResume(Resume resume) {
		userRepository.saveResume(resume);
	}

	@Override
	public boolean updatePassword(int userId, String currentPassword, String newPassword) throws IOException {
		User user = userRepository.findById(userId);
		String modifiedCurrentPwd = currentPassword + user.getPasswordSalt();
		String hashedCurrentPwd = Utils.generateHash(modifiedCurrentPwd);

		if (!hashedCurrentPwd.equals(user.getPasswordHash())) {
			return false; // Current password is incorrect
		}

		String newSalt = Utils.generateSalt();
		String modifiedNewPwd = newPassword + newSalt;
		String hashedNewPwd = Utils.generateHash(modifiedNewPwd);

		user.setPasswordSalt(newSalt);
		user.setPasswordHash(hashedNewPwd);

		userRepository.updateUser(user);
		return true;
	}

	@Override
	public boolean isUsernameUnique(String username) {
		return userRepository.findByUsername(username) == 0;
	}

	@Override
	public boolean isEmailUnique(String email) {
		return userRepository.findByEmail(email) == 0;
	}

	@Override
	public List<User> fetchUser(int userId) {

		return userRepository.fetchUser(userId);
	}

	@Override
	public int updateUserEmployer(int userId, String username, String fullname, String email, String mobileNo,
			MultipartFile profilePicture) throws SerialException, IOException, SQLException {
		return userRepository.updateUserEmployer(userId, username, fullname, email, mobileNo, profilePicture);
	}

	@Override
	public void updatePassword(String email, String newPassword) {
		Optional<User> userOptional = userRepository.findUserByEmail(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String pwdSalt = Utils.generateSalt();
			String modifiedPwd = newPassword + pwdSalt;
			String pwdHash = Utils.generateHash(modifiedPwd);
			user.setPasswordSalt(pwdSalt);
			user.setPasswordHash(pwdHash);
			userRepository.updateUsers(user);
		} else {
			throw new RuntimeException("User not found for email: " + email);
		}
	}

	@Override
	public boolean isEmailRegistered(String email) {
		return userRepository.isEmailRegistered(email);

	}
}
