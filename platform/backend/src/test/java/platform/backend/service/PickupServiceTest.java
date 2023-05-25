package platform.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import platform.backend.model.Pickup;
import platform.backend.repository.PickupRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PickupServiceTest {

    @Mock
    private PickupRepository pickupRepository;

    @InjectMocks
    private PickupService pickupService;

    public PickupServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test to find all pickups")
    public void testFindAll() {
        // Arrange
        Pickup p1 = new Pickup("pickup", "pickup@gmail.com", "919919191", "pickup", "Rua do Pickup", "Pending");
        Pickup p2 = new Pickup("pickup2", "pickup2@gmail.com", "929929292", "pickup2", "Rua do Pickup2", "Partener");
        List<Pickup> pickups = Arrays.asList(p1, p2);
        when(pickupRepository.findAll()).thenReturn(pickups);

        // Act
        List<Pickup> result = pickupService.findAll();

        // Assert
        assertEquals(pickups, result);
        verify(pickupRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test to find pickup by id")
    public void testFindById() {
        // Arrange
        String id = "123";
        Pickup pickup = new Pickup("pickup", "pickup@gmail.com", "919919191", "pickup", "Rua do Pickup", "Pending");
        when(pickupRepository.findById(id)).thenReturn(Optional.of(pickup));

        // Act
        Pickup result = pickupService.findById(id);

        // Assert
        assertEquals(pickup, result);
        verify(pickupRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test to find pickup by email")
    public void testFindByEmail() {
        // Arrange
        String email = "pickup@gmail.com";
        Pickup pickup = new Pickup("pickup", "pickup@gmail.com", "919919191", "pickup", "Rua do Pickup", "Pending");
        when(pickupRepository.findByEmail(email)).thenReturn(pickup);

        // Act
        Pickup result = pickupService.findByEmail(email);

        // Assert
        assertEquals(pickup, result);
        verify(pickupRepository, times(1)).findByEmail(email);
    }

    @Test
    @DisplayName("Test to save pickup")
    public void testSave() {
        // Arrange
        Pickup pickup = new Pickup("pickup", "pickup@gmail.com", "919919191", "pickup", "Rua do Pickup", "Pending");
        when(pickupRepository.save(pickup)).thenReturn(pickup);

        // Act
        Pickup result = pickupService.save(pickup);

        // Assert
        assertEquals(pickup, result);
        verify(pickupRepository, times(1)).save(pickup);
    }
}

