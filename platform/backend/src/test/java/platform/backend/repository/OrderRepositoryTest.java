package platform.backend.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import platform.backend.model.Order;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        // Clear the repository before each test
        orderRepository.deleteAll();
    }

    @AfterEach
    public void cleanup() {
        // Clear the repository after each test
        orderRepository.deleteAll();
    }

    @Test
    public void testFindByPickupId() {
        // Arrange
        String pickupId = "123";
        List<Order> orders = Arrays.asList(
                new Order(pickupId, "customerId1", "eStore1", new Date(), null, "status1"),
                new Order(pickupId, "customerId2", "eStore2", new Date(), null, "status2")
        );
        orderRepository.saveAll(orders);

        // Act
        List<Order> result = orderRepository.findByPickupId(pickupId);

        // Assert
        for (Order order : orders) {
            assertEquals(order.getPickupId(), result.get(orders.indexOf(order)).getPickupId());
            assertEquals(order.getCustomerId(), result.get(orders.indexOf(order)).getCustomerId());
            assertEquals(order.geteStore(), result.get(orders.indexOf(order)).geteStore());
            assertEquals(order.getDate(), result.get(orders.indexOf(order)).getDate());
            assertEquals(order.getProducts(), result.get(orders.indexOf(order)).getProducts());
            assertEquals(order.getStatus(), result.get(orders.indexOf(order)).getStatus());
        }
    }

    @Test
    public void testFindByCustomerId() {
        // Arrange
        String customerId = "456";
        List<Order> orders = Arrays.asList(
                new Order("pickupId1", customerId, "eStore1", new Date(), null, "status1"),
                new Order("pickupId2", customerId, "eStore2", new Date(), null, "status2")
        );
        orderRepository.saveAll(orders);

        // Act
        List<Order> result = orderRepository.findByCustomerId(customerId);

        // Assert
        for (Order order : orders) {
            assertEquals(order.getPickupId(), result.get(orders.indexOf(order)).getPickupId());
            assertEquals(order.getCustomerId(), result.get(orders.indexOf(order)).getCustomerId());
            assertEquals(order.geteStore(), result.get(orders.indexOf(order)).geteStore());
            assertEquals(order.getDate(), result.get(orders.indexOf(order)).getDate());
            assertEquals(order.getProducts(), result.get(orders.indexOf(order)).getProducts());
            assertEquals(order.getStatus(), result.get(orders.indexOf(order)).getStatus());
        }
    }

    @Test
    public void testFindByEStore() {
        // Arrange
        String eStore = "exampleStore";
        Order order = new Order("pickupId1", "customerId1", eStore, new Date(), null, "status1");
        orderRepository.save(order);

        // Act
        Order result = orderRepository.findByEStore(eStore);

        // Assert
        assertEquals(order.getPickupId(), result.getPickupId());
        assertEquals(order.getCustomerId(), result.getCustomerId());
        assertEquals(order.geteStore(), result.geteStore());
        assertEquals(order.getDate(), result.getDate());
        assertEquals(order.getProducts(), result.getProducts());
        assertEquals(order.getStatus(), result.getStatus());
    }
}

