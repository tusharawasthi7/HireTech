package hire.tech.jobseeker.entities;

import java.sql.Date;

public class Subscription {

	private int subscriptionId;
	private int userId;
	private int jobSeekerId;
	private String subscriptionType;
	private Date startDate;
	private Date endDate;
	private String status;

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Subscription() {
	}

	public Subscription(int subscriptionId, int userId, int jobSeekerId, String subscriptionType, Date startDate,
			Date endDate, String status) {
		this.subscriptionId = subscriptionId;
		this.userId = userId;
		this.jobSeekerId = jobSeekerId;
		this.subscriptionType = subscriptionType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Subscription(int userId, int jobSeekerId, String subscriptionType, Date startDate, Date endDate,
			String status) {
		this.userId = userId;
		this.jobSeekerId = jobSeekerId;
		this.subscriptionType = subscriptionType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

}
