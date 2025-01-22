package hire.tech.admin.repositories;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import hire.tech.admin.entities.Subscription;

public class SubscriptionRowMapper implements RowMapper<Subscription> {
	@Override
	public Subscription mapRow(ResultSet rs, int rowNum) throws SQLException {
		int subscriptionId = rs.getInt("subscription_id");
		int jobseekerId = rs.getInt("job_seeker_id");

		String subscriptionType = rs.getString("subscription_type");
		Date startDate = rs.getDate("start_date");
		Date endDate = rs.getDate("end_date");
		String status = rs.getString("status");

		return new Subscription(subscriptionId, jobseekerId, subscriptionType, startDate, endDate, status);
	}
}
