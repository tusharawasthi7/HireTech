package hire.tech;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.sql.rowset.serial.SerialException;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 
import hire.tech.employer.controllers.EmployerController;
import hire.tech.employer.entities.*;
import hire.tech.employer.services.EmployerService;
import hire.tech.user.entities.User;
import hire.tech.user.services.UserService;
import jakarta.servlet.http.HttpSession;
 
class EmployerTest {
 
    @Mock
    private EmployerService employerService;
 
    @Mock
    private UserService userService;
 
    @Mock
    private HttpSession session;
 
    @Mock
    private Model model;
 
    @InjectMocks
    private EmployerController employerController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testRegistration() {
        String viewName = employerController.registration();
        assertEquals("employer/employerregistration", viewName);
    }
 
    @Test
    void testEmployerDashboard() {
        ModelAndView mView = new ModelAndView();
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        Employer employer = new Employer();
        employer.setEmployerId(1);
        when(employerService.getEmployerByUserId(anyInt())).thenReturn(employer);
        when(employerService.getTotalJobsPosted(anyInt())).thenReturn(10);
        when(employerService.getTotalApplications(anyInt())).thenReturn(20);
 
        ModelAndView result = employerController.employerDashboard(mView, session);
 
        assertEquals("employer/dashboard", result.getViewName());
        assertEquals(10, result.getModel().get("totalJobs"));
        assertEquals(20, result.getModel().get("totalApplications"));
    }
 
    @Test
    void testProfile() {
        ModelAndView mView = new ModelAndView();
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        Employer employer = new Employer();
        employer.setEmployerId(1);
        when(employerService.getEmployerByUserId(anyInt())).thenReturn(employer);
 
        List<Employer> employerdetails = new ArrayList<>();
        employerdetails.add(employer);
        List<User> userdetails = new ArrayList<>();
        User user = new User();
        userdetails.add(user);
 
        when(employerService.getUpdateEmployer(anyInt())).thenReturn(employerdetails);
        when(userService.fetchUser(anyInt())).thenReturn(userdetails);
 
        ModelAndView result = employerController.profile(mView, session);
 
        assertEquals("employer/myprofile", result.getViewName());
        assertEquals(employerdetails, result.getModel().get("employerdetails"));
        assertEquals(userdetails, result.getModel().get("userdetails"));
    }
 
    @Test
    void testEditProfile() {
        ModelAndView mView = new ModelAndView();
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
 
        User user = new User();
        when(userService.getUserById(anyInt())).thenReturn(user);
 
        Employer employer = new Employer();
        employer.setEmployerId(1);
        when(employerService.getEmployerByUserId(anyInt())).thenReturn(employer);
 
        List<Employer> employerdetails = new ArrayList<>();
        employerdetails.add(employer);
        List<User> userdetails = new ArrayList<>();
        userdetails.add(user);
 
        when(employerService.getUpdateEmployer(anyInt())).thenReturn(employerdetails);
        when(userService.fetchUser(anyInt())).thenReturn(userdetails);
 
        ModelAndView result = employerController.editProfile(mView, session);
 
        assertEquals("employer/editProfile", result.getViewName());
        assertEquals(employerdetails, result.getModel().get("employerdetails"));
        assertEquals(userdetails, result.getModel().get("userdetails"));
    }
 
    @Test
    void testApproveRequest() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        String message = "Application approved";
        when(employerService.changeApplicationStatus(anyInt(), anyString(), anyInt())).thenReturn(message);
 
        String result = employerController.approveRequest(1, "approved", model, session, 1);
 
        assertEquals("redirect:/employer/viewApprovedApplicants", result);
        verify(model).addAttribute("message", message);
    }
 
    @Test
    void testRejectRequest() {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        String message = "Application rejected";
        when(employerService.changeApplicationStatus(anyInt(), anyString(), anyInt())).thenReturn(message);
 
        String result = employerController.rejectRequest(1, "rejected", model, session, 1);
 
        assertEquals("redirect:/employer/viewRejectedApplicants", result);
        verify(model).addAttribute("message", message);
    }
 
    @Test
    void testDeleteJob() {
        when(employerService.deleteJob(anyInt())).thenReturn(1);
 
        ModelAndView mView = new ModelAndView();
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        ModelAndView result = employerController.deleteJob(1, mView, session);
 
        assertEquals("redirect:/employer/viewAllYourJobs", result.getViewName());
    }
 
    @Test
    void testEditJob() {
        Job job = new Job();
        when(employerService.getJobById(anyInt())).thenReturn(job);
 
        String viewName = employerController.editJob(1, model);
 
        assertEquals("employer/editJob", viewName);
        verify(model).addAttribute("job", job);
    }
 
    @Test
    void testUpdateJob() throws SerialException, IOException, SQLException {
        when(employerService.updateJob(anyInt(), anyString(), anyString(), anyString(), anyDouble(), anyString(),
                any(MultipartFile.class))).thenReturn(1);
 
        String result = employerController.updateJob(1, "title", "skills", "job_type", 50000, "location", mock(MultipartFile.class), session);
 
        assertEquals("redirect:/employer/viewAllYourJobs", result);
    }
 
 
    @Test
    void testShowResetPasswordPage() {
        String viewName = employerController.showResetPasswordPage();
        assertEquals("employer/resetPasswordPage", viewName);
    }
 
    @Test
    void testUpdatePassword() throws IOException {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        User user = new User();
        user.setPasswordHash("currentHash");
        user.setPasswordSalt("currentSalt");
        when(userService.getUserById(anyInt())).thenReturn(user);
        when(userService.matchPassword(anyString(), any(User.class))).thenReturn(true);
 
        String result = employerController.updatePassword("currentPassword", "newPassword", "newPassword", session, model);
 
        assertEquals("employer/resetPasswordPage", result);
        verify(model).addAttribute("successMessage", "Password updated successfully!");
    }
 
    @Test
    void testInsertJob() throws SerialException, IOException, SQLException {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        Employer employer = new Employer();
        employer.setEmployerId(1);
        when(employerService.getEmployerByUserId(anyInt())).thenReturn(employer);
        when(employerService.addJob(any(Job.class), any(Employer.class), anyInt())).thenReturn(1);
 
        ModelAndView mView = new ModelAndView();
        ModelAndView result = employerController.insertJob(new Job(), mView, session);
 
        assertEquals("redirect:/employer/viewAllYourJobs", result.getViewName());
    }
}