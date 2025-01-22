package hire.tech;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
 
import hire.tech.jobseeker.controllers.JobseekerController;
import hire.tech.jobseeker.entities.*;
import hire.tech.jobseeker.services.JobSeekerService;
import hire.tech.user.entities.User;
import hire.tech.user.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
 
class JobseekerTest {
 
    @Mock
    private JobSeekerService jobSeekerService;
 
    @Mock
    private UserService userService;
 
    @Mock
    private HttpSession session;
 
    @Mock
    private Model model;
 
    @Mock
    private HttpServletRequest response;
 
    @InjectMocks
    private JobseekerController jobseekerController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testRegistration() {
        String viewName = jobseekerController.registration();
        assertEquals("jobseeker/jobseekerregistration", viewName);
    }
 
    @Test
    void testGetJobseekerProfile() throws IOException {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        Jobseeker jobseeker = new Jobseeker();
        User user = new User();
        when(jobSeekerService.getJobseekerById(anyInt())).thenReturn(jobseeker);
        when(jobSeekerService.getUserById(anyInt())).thenReturn(user);
 
        String viewName = jobseekerController.getJobseekerProfile(model, session);
 
        assertEquals("jobseeker/myprofile", viewName);
        verify(model).addAttribute("jobseeker", jobseeker);
        verify(model).addAttribute("user", user);
    }
 
    @Test
    void testOpenIndexPage() {
        List<Job> jobs = new ArrayList<>();
        when(jobSeekerService.getAllJobs()).thenReturn(jobs);
 
        String viewName = jobseekerController.openIndexPage(model);
 
        assertEquals("jobseeker/jobseekerdashboard", viewName);
        verify(model).addAttribute("jobs", jobs);
    }
 
    @Test
    void testShowApplyJobs() {
        List<Job> jobs = new ArrayList<>();
        Job job = new Job();
        when(jobSeekerService.getAllJobs()).thenReturn(jobs);
        when(jobSeekerService.findById(anyInt())).thenReturn(job);
 
        String viewName = jobseekerController.showApplyJobs(model, 1, 1, session);
 
        assertEquals("jobseeker/apply", viewName);
        verify(model).addAttribute("jobs", jobs);
        verify(model).addAttribute("selectedJob", job);
    }
 
    @Test
    void testGetAppliedJobs() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        List<Application> appliedJobs = new ArrayList<>();
        List<Job> allJobs = new ArrayList<>();
        when(jobSeekerService.getAppliedJobs(anyInt())).thenReturn(appliedJobs);
        when(jobSeekerService.getAllJobs()).thenReturn(allJobs);
 
        String viewName = jobseekerController.getAppliedJobs(model, session);
 
        assertEquals("jobseeker/appliedjob", viewName);
        verify(model).addAttribute("appliedJobs", appliedJobs);
        verify(model).addAttribute("allJobs", allJobs);
    }
 
    @Test
    void testSubmitApplication() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        String result = jobseekerController.submitApplication(1, session, "name", "gender", new Date(System.currentTimeMillis()),
                "skills", mock(MultipartFile.class), "availability", model);
 
        assertEquals("redirect:/jobseeker/appliedjob", result);
    }
 
    @Test
    void testSaveJob() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        String result = jobseekerController.saveJob(1, session, model);
 
        assertEquals("redirect:/jobseeker/savedjobs", result);
    }
 
    @Test
    void testGetSavedJobs() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        List<SavedJob> savedJobs = new ArrayList<>();
        List<Job> allJobs = new ArrayList<>();
        when(jobSeekerService.getSavedJobs(anyInt())).thenReturn(savedJobs);
        when(jobSeekerService.getAllJobs()).thenReturn(allJobs);
 
        String viewName = jobseekerController.getSavedJobs(model, session);
 
        assertEquals("jobseeker/savedjobs", viewName);
        verify(model).addAttribute("savedJobs", savedJobs);
        verify(model).addAttribute("allJobs", allJobs);
        verify(model).addAttribute("jobSeekerId", 1);
    }
 
 
    @Test
    void testProcessPayment() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        String result = jobseekerController.processPayment(session, "name", "gender", "subscriptionType", new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), "paymentMode", "paymentId", model);
 
        assertEquals("jobseeker/subscriptionConfirmation", result);
    }
 
    @Test
    void testSearchJobs() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        List<Job> searchResults = new ArrayList<>();
        when(jobSeekerService.searchJobs(anyString(), anyString(), anyString())).thenReturn(searchResults);
 
        String viewName = jobseekerController.searchJobs("skills", "location", "company", session, model);
 
        assertEquals("jobseeker/search", viewName);
        verify(model).addAttribute("searchResults", searchResults);
        verify(model).addAttribute("jobSeekerId", 1);
    }
 
 
    @Test
    void testShowResumeForm() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1, 1, 1, "active", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "active"));
        when(jobSeekerService.getSubscriptions(anyInt())).thenReturn(subscriptions);
        Resume resume = new Resume();
        when(jobSeekerService.getResumeByUserId(anyInt())).thenReturn(resume);
 
        String viewName = jobseekerController.showResumeForm(session, model);
 
        assertEquals("jobseeker/resumeBuilder", viewName);
        verify(model).addAttribute("resume", resume);
    }
 
    @Test
    void testSaveResume() throws IOException {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        Resume resume = new Resume();
        String result = jobseekerController.saveResume(resume, session);
 
        assertEquals("redirect:/jobseeker/resumeBuilder", result);
    }
    
 
    @Test
    void testCertifications() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1, 1, 1, "Premium Plan", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), "active"));
        when(jobSeekerService.getSubscriptions(anyInt())).thenReturn(subscriptions);
 
        String viewName = jobseekerController.certifications(session, model);
 
        assertEquals("jobseeker/certifications", viewName);
        verify(model).addAttribute("subscribed", true);
    }
 
 
    @Test
    void testHelpPage() {
        String viewName = jobseekerController.helppage();
        assertEquals("jobseeker/help", viewName);
    }
 
    @Test
    void testDeleteSavedJob() {
        String result = jobseekerController.deleteSavedJob(1);
        assertEquals("redirect:/jobseeker/savedjobs", result);
    }
}