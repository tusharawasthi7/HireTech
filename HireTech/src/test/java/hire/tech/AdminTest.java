package hire.tech;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.io.IOException;
import java.util.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
 
import hire.tech.admin.controllers.AdminController;
import hire.tech.admin.entities.Subscription;
import hire.tech.admin.services.AdminService;
import hire.tech.user.entities.User;
import hire.tech.user.services.UserService;
import jakarta.servlet.http.HttpSession;
 
class AdminTest {
 
    @Mock
    private AdminService adminService;
 
    @Mock
    private UserService userService;
 
    @Mock
    private Model model;
 
    @Mock
    private HttpSession session;
 
    @InjectMocks
    private AdminController adminController;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testShowAdminDashboard() {
        String viewName = adminController.showAdminDashboard();
        assertEquals("admin/adminDashboard", viewName);
    }
 
    @Test
    void testGetAdminProfile() throws IOException {
        int userId = 1;
        List<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);
        when(adminService.getAdminById(userId)).thenReturn(users);
 
        String viewName = adminController.getAdminProfile(model);
 
        assertEquals("admin/myprofile", viewName);
        verify(model).addAttribute("user", user);
    }
 
    @Test
    void testOpenUpdateProfilePage() {
        int ADMIN_USER_ID = 1;
        User user = new User();
        when(adminService.getUserById(ADMIN_USER_ID)).thenReturn(Optional.of(user));
 
        String viewName = adminController.openUpdateProfilePage(model);
 
        assertEquals("admin/updateprofile", viewName);
        verify(model).addAttribute("user", user);
    }
 
    @Test
    void testUpdateProfile() {
        User user = new User();
        MultipartFile file = mock(MultipartFile.class);
 
        when(file.isEmpty()).thenReturn(false);
        doNothing().when(adminService).updateUser(any(User.class));
 
        String viewName = adminController.updateProfile(user, file, model);
 
        assertEquals("admin/myprofile", viewName);
        verify(adminService).updateUser(any(User.class));
        verify(model).addAttribute("message", "Profile updated successfully.");
    }
 
    @Test
    void testGetEmployerInfo() {
        List<Map<String, Object>> employerInfo = new ArrayList<>();
        when(adminService.getAllEmployers()).thenReturn(employerInfo);
 
        String viewName = adminController.getemployerinfo(model);
 
        assertEquals("admin/employerinfo_list", viewName);
        verify(model).addAttribute("employerInfo", employerInfo);
    }
 

    @Test
    void testGetJobseekersInfo() {
        List<Map<String, Object>> jobseekersInfo = new ArrayList<>();
        when(adminService.getAllJobSeekers()).thenReturn(jobseekersInfo);
 
        String viewName = adminController.getjobseekersinfo(model);
 
        assertEquals("admin/jobseekersinfo_list", viewName);
        verify(model).addAttribute("jobseekersInfo", jobseekersInfo);
    }
 
    @Test
    void testGetJobsInfo() {
        List<Map<String, Object>> jobsInfo = new ArrayList<>();
        when(adminService.getAllJobsInfo()).thenReturn(jobsInfo);
 
        String viewName = adminController.getjobsinfo(model);
 
        assertEquals("admin/jobs_list", viewName);
        verify(model).addAttribute("jobsInfo", jobsInfo);
    }
 
    @Test
    void testViewPendingRequests() {
        List<Map<String, Object>> pendingEmployers = new ArrayList<>();
        when(adminService.getPendingEmployerDetails()).thenReturn(pendingEmployers);
 
        String viewName = adminController.viewPendingRequests(model);
 
        assertEquals("admin/pending_requests", viewName);
        verify(model).addAttribute("pendingEmployers", pendingEmployers);
    }
 
    @Test
    void testViewApprovedRequests() {
        List<Map<String, Object>> approvedEmployers = new ArrayList<>();
        when(adminService.getApprovedEmployerDetails()).thenReturn(approvedEmployers);
 
        String viewName = adminController.viewApprovedRequests(model);
 
        assertEquals("admin/approved_requests", viewName);
        verify(model).addAttribute("approvedEmployers", approvedEmployers);
    }
 
    @Test
    void testViewRejectRequests() {
        List<Map<String, Object>> rejectEmployers = new ArrayList<>();
        when(adminService.getRejectEmployerDetails()).thenReturn(rejectEmployers);
 
        String viewName = adminController.viewRejectRequests(model);
 
        assertEquals("admin/reject_requests", viewName);
        verify(model).addAttribute("rejectEmployers", rejectEmployers);
    }
 
    @Test
    void testLogoutPage() {
        String viewName = adminController.logoutPage();
        assertEquals("user/login", viewName);
    }
 
    @Test
    void testBackToDashboard() {
        String viewName = adminController.backToDashboard();
        assertEquals("admin/admindashboard", viewName);
    }
 
    @Test
    void testViewJobseekerSubscriptions() {
        List<Subscription> jobseekerSubscriptions = new ArrayList<>();
        when(adminService.getSubscriptionsByjobseekerId()).thenReturn(jobseekerSubscriptions);
 
        String viewName = adminController.viewJobseekerSubscriptions(model);
 
        assertEquals("admin/jobseekers_subscription", viewName);
        verify(model).addAttribute("subscriptions", jobseekerSubscriptions);
    }
 
    @Test
    void testShowResetPasswordPage() {
        String viewName = adminController.showResetPasswordPage();
        assertEquals("admin/resetPasswordPage", viewName);
    }
 
    @Test
    void testUpdatePassword() throws IOException {
        when(session.getAttribute("loggedInUserId")).thenReturn(1);
        User user = new User();
        when(userService.getUserById(anyInt())).thenReturn(user);
        when(userService.matchPassword(anyString(), any(User.class))).thenReturn(true);
 
        String viewName = adminController.updatePassword("currentPassword", "newPassword", "newPassword", session, model);
 
        assertEquals("redirect:/admin/profile", viewName);
        verify(model).addAttribute("successMessage", "Password updated successfully!");
    }
}