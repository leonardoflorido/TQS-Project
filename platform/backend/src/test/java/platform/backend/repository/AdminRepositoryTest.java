package platform.backend.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import platform.backend.model.Admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class AdminRepositoryTest {

    @Autowired
    private AdminRepository adminRepository;

    @BeforeEach
    public void setup() {
        // Clear the repository before each test
        adminRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        // Clear the repository after each test
        adminRepository.deleteAll();
    }

    @Test
    @DisplayName("Test to find admin by email")
    public void testFindByEmail() {
        // Arrange
        String email = "admin@example.com";
        String name = "admin";
        String password = "admin";
        Admin admin = new Admin(name, email, password);
        adminRepository.save(admin);

        // Act
        Admin result = adminRepository.findByEmail(email);

        // Assert email
        assertEquals(admin.getEmail(), result.getEmail());
        // Assert name
        assertEquals(admin.getName(), result.getName());
        // Assert password
        assertEquals(admin.getPassword(), result.getPassword());
    }
}

