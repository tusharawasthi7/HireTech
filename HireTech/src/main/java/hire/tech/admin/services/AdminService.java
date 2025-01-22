package hire.tech.admin.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;

import hire.tech.admin.entities.Subscription;
import hire.tech.user.entities.User;

public interface AdminService {

	List<Map<String, Object>> getAllEmployers();

	public int updateEmployerStatus(int userId);

	List<Map<String, Object>> getAllJobSeekers();

	List<Map<String, Object>> getAllJobsInfo();

	List<Map<String, Object>> getPendingEmployerDetails();

	List<Map<String, Object>> getApprovedEmployerDetails();

	List<Map<String, Object>> getRejectEmployerDetails();

	List<User> getAdminById(int userId) throws IOException;

	Optional<User> getUserById(Integer userId);

	void updateUser(User user);

	List<Subscription> getSubscriptionsByjobseekerId();

}