package hire.tech.user.entities;

import java.lang.Integer;
import java.math.BigInteger;
import org.springframework.web.multipart.MultipartFile;

public class User {

	private int userId;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private String contactNumber;
	private MultipartFile profilePicture;
	private int roleId;
	private String passwordSalt;
	private String passwordHash;
	private boolean authorizationStatus;

	public User() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isAuthorizationStatus() {
		return authorizationStatus;
	}

	public void setAuthorizationStatus(boolean authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", fullName=" + fullName
				+ ", email=" + email + ", contactNumber=" + contactNumber + ", profilePicture=" + profilePicture
				+ ", roleId=" + roleId + ", passwordSalt=" + passwordSalt + ", passwordHash=" + passwordHash
				+ ", authorizationStatus=" + authorizationStatus + "]";
	}

	public User(int userId, String username, String password, String fullName, String email, String contactNumber,
			MultipartFile profilePicture, int roleId, String passwordSalt, String passwordHash,
			boolean authorizationStatus) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.profilePicture = profilePicture;
		this.roleId = roleId;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.authorizationStatus = authorizationStatus;
	}

}