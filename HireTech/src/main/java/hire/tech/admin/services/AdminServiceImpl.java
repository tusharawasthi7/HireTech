package hire.tech.admin.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hire.tech.admin.entities.Subscription;
import hire.tech.admin.repositories.AdminRepository;
import hire.tech.user.entities.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Override
	public List<Map<String, Object>> getAllEmployers() {
		return adminRepository.fetchAllEmployers();
	}

	public int updateEmployerStatus(int userId) {
		return adminRepository.updateEmployerStatus(userId);
	}

	@Override
	public List<Map<String, Object>> getAllJobSeekers() {
		return adminRepository.fetchAllJobSeekers();
	}

	@Override
	public List<Map<String, Object>> getAllJobsInfo() {
		return adminRepository.fetchAllJobsInfo();
	}

	@Override

	public List<Map<String, Object>> getPendingEmployerDetails() {
		return adminRepository.fetchPendingEmployersWithDetails();

	}

	public List<Map<String, Object>> getApprovedEmployerDetails() {
		return adminRepository.fetchApprovedEmployersWithDetails();

	}

	public List<Map<String, Object>> getRejectEmployerDetails() {
		return adminRepository.fetchRejectEmployersWithDetails();

	}

	@Override
	public List<User> getAdminById(int userId) throws IOException {
		return adminRepository.getAdminById(userId);
	}

	@Override
	public Optional<User> getUserById(Integer userId) {
		return adminRepository.findById(userId);
	}

	@Override

	public void updateUser(User user) {
		if (user.getProfilePicture() != null && !user.getProfilePicture().isEmpty()) {
			user.setProfilePicture(user.getProfilePicture());
		}
		adminRepository.updateUser(user);

	}

	@Override
	public List<Subscription> getSubscriptionsByjobseekerId() {
		return adminRepository.findSubscriptionsByjobseekerId();
	}
}
