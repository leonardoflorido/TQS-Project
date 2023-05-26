package platform.backend.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import platform.backend.model.Pickup;
import platform.backend.repository.PickupRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class PickupRepositoryTest {

    @Autowired
    private PickupRepository pickupRepository;

    @BeforeEach
    public void setup() {
        // Clear the repository before each test
        pickupRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        // Clear the repository after each test
        pickupRepository.deleteAll();
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "pickup@gmail.com";
        String name = "pickup";
        String phone = "919919191";
        String password = "pickup";
        String address = "Rua do Pickup";
        String status = "Pending";
        Pickup pickup = new Pickup(name, email, phone, password, address, status);
        pickupRepository.save(pickup);

        // Act
        Pickup result = pickupRepository.findByEmail(email);

        // Assert
        assertEquals(pickup.getEmail(), result.getEmail());
        assertEquals(pickup.getName(), result.getName());
        assertEquals(pickup.getPhone(), result.getPhone());
        assertEquals(pickup.getPassword(), result.getPassword());
        assertEquals(pickup.getAddress(), result.getAddress());
        assertEquals(pickup.getStatus(), result.getStatus());
    }
}

