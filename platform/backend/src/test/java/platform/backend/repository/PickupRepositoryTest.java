package platform.backend.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import platform.backend.model.Pickup;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PickupRepositoryTest {
    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Pickup pickup;

    @BeforeAll
    void setup() {
        pickup = new Pickup("pickup", "pickup@gmail.com", "919919191", "pickup", "Rua do Pickup", "Pending");
    }

    @AfterAll
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    @Test
    void testFindByEmail() {
        // Arrange
        pickupRepository.save(pickup);

        // Act
        Pickup result = pickupRepository.findByEmail(pickup.getEmail());

        // Assert
        assertEquals(pickup.getName(), result.getName());
        assertEquals(pickup.getEmail(), result.getEmail());
        assertEquals(pickup.getPhone(), result.getPhone());
        assertEquals(pickup.getPassword(), result.getPassword());
        assertEquals(pickup.getAddress(), result.getAddress());
        assertEquals(pickup.getStatus(), result.getStatus());
    }
}

