package hire.tech.jobseeker.entities;

import java.sql.Date;

public class Application {
    private int applicationId;
    private int jobId;
    private int jobSeekerId;
    private String status;
    private Date applicationDate;

   
    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }
    
    public Application(int jobId, int jobSeekerId, String status, Date applicationDate) {
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.status = status;
        this.applicationDate = applicationDate;
    }
  
    public Application(int applicationId, int jobId, int jobSeekerId, String status, Date applicationDate) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.status = status;
        this.applicationDate = applicationDate;
    }

}
