package hire.tech.admin.entities;
 
import java.sql.Date;
 
public class Subscription {
 
    private int subscriptionId;
    private int jobseekerId;
    private String subscriptionType;
    private Date startDate;
    private Date endDate;
    private String status;
	public Subscription(int subscriptionId, int jobseekerId, String subscriptionType, Date startDate, Date endDate,
			String status) {
		super();
		this.subscriptionId = subscriptionId;
		this.jobseekerId = jobseekerId;
		this.subscriptionType = subscriptionType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	public int getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public int getJobseekerId() {
		return jobseekerId;
	}
	public void setJobseekerId(int jobseekerId) {
		this.jobseekerId = jobseekerId;
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
	@Override
	public String toString() {
		return "Subscription [subscriptionId=" + subscriptionId + ", jobseekerId=" + jobseekerId + ", subscriptionType="
				+ subscriptionType + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}
}