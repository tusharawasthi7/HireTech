package hire.tech.jobseeker.entities;

import java.sql.Date;

public class SavedJob {
    private int savedJobId;
    private int jobId;
    private int jobSeekerId;
    private Date saved_date;
	
	public int getSavedJobId() {
		return savedJobId;
	}
	public void setSavedJobId(int savedJobId) {
		this.savedJobId = savedJobId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	
	public Date getSaved_date() {
		return saved_date;
	}
	public void setSaved_date(Date saved_date) {
		this.saved_date = saved_date;
	}
	
	
	public SavedJob(int savedJobId, int jobId, int jobSeekerId, Date saved_date) {
		super();
		this.savedJobId = savedJobId;
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
		this.saved_date = saved_date;
	}
	public SavedJob(int savedJobId, int jobId, int jobSeekerId) {
		super();
		this.savedJobId = savedJobId;
		this.jobId = jobId;
		this.jobSeekerId = jobSeekerId;
		this.saved_date = saved_date;
	}
	
	@Override
	public String toString() {
		return "SavedJob [savedJobId=" + savedJobId + ", jobId=" + jobId + ", jobSeekerId=" + jobSeekerId + "]";
	}

    
}
