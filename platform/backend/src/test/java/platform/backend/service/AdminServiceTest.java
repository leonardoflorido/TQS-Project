package platform.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import platform.backend.model.Admin;
import platform.backend.repository.AdminRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    public AdminServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test to find admin by email")
    public void testFindByEmail() {
        // Arrange
        String email = "admin@example.com";
        Admin admin = new Admin("admin", email, "admin");
        when(adminRepository.findByEmail(email)).thenReturn(admin);

        // Act
        Admin result = adminService.findByEmail(email);

        // Assert
        assertEquals(admin, result);
        verify(adminRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Test to save admin")
    public void testSave() {
        // Arrange
        Admin admin = new Admin("admin", "admin@admin.com", "admin");
        when(adminRepository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.save(admin);

        // Assert
        assertEquals(admin, result);
        verify(adminRepository, times(1)).save(admin);
    }
}

