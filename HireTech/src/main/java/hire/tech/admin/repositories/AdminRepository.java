package hire.tech.admin.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import hire.tech.admin.entities.Subscription;
import hire.tech.user.entities.User;

public interface AdminRepository {

	List<Map<String, Object>> fetchAllEmployers();

	int updateEmployerStatus(int userId);

	List<Map<String, Object>> fetchAllJobSeekers();

	List<Map<String, Object>> fetchAllJobsInfo();

	List<Map<String, Object>> fetchPendingEmployersWithDetails();

	List<Map<String, Object>> fetchApprovedEmployersWithDetails();

	List<Map<String, Object>> fetchRejectEmployersWithDetails();

	List<User> getAdminById(int userId);

	Optional<User> findById(Integer userId);

	void updateUser(User user);

	List<Subscription> findSubscriptionsByjobseekerId();

}