package platform.backend.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import platform.backend.model.Admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Admin admin;

    @BeforeAll
    void setup() {
        admin = new Admin("Admin", "admin@email.com", "admin");
    }

    @AfterAll
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    @Test
    @DisplayName("Test to find admin by email")
    void testFindByEmail() {
        // Arrange
        adminRepository.save(admin);

        // Act
        Admin result = adminRepository.findByEmail(admin.getEmail());

        // Assert email
        assertEquals(admin.getEmail(), result.getEmail());
        // Assert name
        assertEquals(admin.getName(), result.getName());
        // Assert password
        assertEquals(admin.getPassword(), result.getPassword());
    }
}

