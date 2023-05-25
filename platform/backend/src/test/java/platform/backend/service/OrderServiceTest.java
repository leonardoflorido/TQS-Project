package platform.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import platform.backend.model.Order;
import platform.backend.repository.OrderRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test to find orders by pickupId")
    public void testFindByPickupId() {
        // Arrange
        String pickupId = "123";
        List<Order> orders = Arrays.asList(
                new Order(pickupId, "customerId1", "eStore1", new Date(), null, "status1"),
                new Order(pickupId, "customerId2", "eStore2", new Date(), null, "status2")
        );
        when(orderRepository.findByPickupId(pickupId)).thenReturn(orders);

        // Act
        List<Order> result = orderService.findByPickupId(pickupId);

        // Assert
        assertEquals(orders, result);
        verify(orderRepository, times(1)).findByPickupId(pickupId);
    }

    @Test
    @DisplayName("Test to find orders by customerId")
    public void testFindByCustomerId() {
        // Arrange
        String customerId = "456";
        List<Order> orders = Arrays.asList(
                new Order("pickupId1", customerId, "eStore1", new Date(), null, "status1"),
                new Order("pickupId2", customerId, "eStore2", new Date(), null, "status2")
        );
        when(orderRepository.findByCustomerId(customerId)).thenReturn(orders);

        // Act
        List<Order> result = orderService.findByCustomerId(customerId);

        // Assert
        assertEquals(orders, result);
        verify(orderRepository, times(1)).findByCustomerId(customerId);
    }

    @Test
    @DisplayName("Test to find order by eStore")
    public void testFindByEStore() {
        // Arrange
        String eStore = "exampleStore";
        Order order = new Order("pickupId1", "customerId1", eStore, new Date(), null, "status1");
        when(orderRepository.findByEStore(eStore)).thenReturn(order);

        // Act
        Order result = orderService.findByEStore(eStore);

        // Assert
        assertEquals(order, result);
        verify(orderRepository, times(1)).findByEStore(eStore);
    }
}

