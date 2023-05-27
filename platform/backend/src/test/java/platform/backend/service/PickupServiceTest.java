package platform.backend.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import platform.backend.model.Pickup;
import platform.backend.repository.PickupRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PickupServiceTest {
    @Mock
    private PickupRepository pickupRepository;

    @InjectMocks
    private PickupService pickupService;

    private Pickup pickup1, pickup2;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pickup1 = new Pickup("pickup1", "pickup1@gmail.com", "919919191", "pickup1", "Rua do Pickup1", "Pending");
        pickup2 = new Pickup("pickup2", "pickup2@gmail.com", "929929292", "pickup2", "Rua do Pickup2", "Partener");
    }

    @Test
    @DisplayName("Test to save pickup")
    @Order(1)
    public void testSave() {
        // Arrange
        when(pickupRepository.save(pickup1)).thenReturn(pickup1);

        // Act
        Pickup result = pickupService.save(pickup1);

        // Assert
        assertEquals(pickup1, result);
        verify(pickupRepository, times(1)).save(pickup1);
    }

    @Test
    @DisplayName("Test to find all pickups")
    @Order(1)
    public void testFindAll() {
        // Arrange
        List<Pickup> pickups = Arrays.asList(pickup1, pickup2);
        when(pickupRepository.findAll()).thenReturn(pickups);

        // Act
        List<Pickup> result = pickupService.findAll();

        // Assert
        assertEquals(pickups, result);
        verify(pickupRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test to find pickup by id")
    @Order(2)
    public void testFindById() {
        // Arrange
        String id = "507f1f77bcf86cd799439314";
        when(pickupRepository.findById(id)).thenReturn(Optional.ofNullable(pickup1));

        // Act
        Pickup result = pickupService.findById(id);

        // Assert
        assertEquals(pickup1, result);
        verify(pickupRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test to find pickup by email")
    @Order(3)
    public void testFindByEmail() {
        // Arrange
        String email = "pickup1@gmail.com";
        when(pickupRepository.findByEmail(email)).thenReturn(pickup1);

        // Act
        Pickup result = pickupService.findByEmail(email);

        // Assert
        assertEquals(pickup1, result);
        verify(pickupRepository, times(1)).findByEmail(email);
    }
}

