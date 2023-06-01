package platform.backend.repository;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import platform.backend.model.Orders;
import platform.backend.record.Customer;
import platform.backend.record.Product;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Orders orders;

    @BeforeAll
    void setUp() {
        Customer customer = new Customer("Leonardo", "leonardo@email.com", "987654321", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro");

        Product macbook = new Product("Macbook pro 14", 2399.00, 1);
        Product iphone = new Product("iPhone 14 pro", 1200.00, 1);

        orders = new Orders("507f1f77bcf86cd799439011", customer, "Apple", new Date(), List.of(macbook, iphone), "Pending");
    }

    @AfterAll
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    @Test
    @DisplayName("Test to find orders by pickup id")
    void testFindByPickupId() {
        // Arrange
        ordersRepository.save(orders);

        // Act
        List<Orders> result = ordersRepository.findByPickupId(orders.getPickupId());

        // Assert pickup id
        assertEquals(orders.getPickupId(), result.get(0).getPickupId());
        // Assert customer
        assertEquals(orders.getCustomer(), result.get(0).getCustomer());
        // Assert estore
        assertEquals(orders.geteStore(), result.get(0).geteStore());
        // Assert date
        assertEquals(orders.getDate(), result.get(0).getDate());
        // Assert products
        assertEquals(orders.getProducts(), result.get(0).getProducts());
        // Assert status
        assertEquals(orders.getStatus(), result.get(0).getStatus());
    }

    @Test
    @DisplayName("Test to find orders by customer email")
    void testFindByCustomerEmail() {
        // Arrange
        ordersRepository.save(orders);

        // Act
        List<Orders> result = ordersRepository.findByCustomerEmail(orders.getCustomer().email());

        // Assert pickup id
        assertEquals(orders.getPickupId(), result.get(0).getPickupId());
        // Assert customer
        assertEquals(orders.getCustomer(), result.get(0).getCustomer());
        // Assert estore
        assertEquals(orders.geteStore(), result.get(0).geteStore());
        // Assert date
        assertEquals(orders.getDate(), result.get(0).getDate());
        // Assert products
        assertEquals(orders.getProducts(), result.get(0).getProducts());
        // Assert status
        assertEquals(orders.getStatus(), result.get(0).getStatus());
    }
}
