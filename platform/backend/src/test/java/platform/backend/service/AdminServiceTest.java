package platform.backend.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import platform.backend.model.Admin;
import platform.backend.repository.AdminRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    private Admin admin;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        admin = new Admin("admin", "admin@email.com", "admin");
    }

    @Test
    @DisplayName("Test to save admin")
    @Order(1)
    public void testSave() {
        // Arrange
        when(adminRepository.save(admin)).thenReturn(admin);

        // Act
        Admin result = adminService.save(admin);

        // Assert
        assertEquals(admin, result);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("Test to find admin by email")
    @Order(2)
    public void testFindByEmail() {
        // Arrange
        String email = "admin@email.com";
        when(adminRepository.findByEmail(email)).thenReturn(admin);

        // Act
        Admin result = adminService.findByEmail(email);

        // Assert
        assertEquals(admin, result);
        verify(adminRepository, times(1)).findByEmail(email);
    }
}

