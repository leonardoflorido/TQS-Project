package platform.backend.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import platform.backend.model.Orders;
import platform.backend.record.Customer;
import platform.backend.record.Product;
import platform.backend.repository.OrdersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersServiceTest {
    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrdersService ordersService;

    private Orders orders;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Customer customer = new Customer("Leonardo", "leonardo@email.com", "987654321", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro");

        Product macbook = new Product("Macbook pro 14", 2399.00, 1);
        Product iphone = new Product("iPhone 14 pro", 1200.00, 1);

        orders = new Orders("507f1f77bcf86cd799439011", customer, "Apple", new Date(), List.of(macbook, iphone), "Pending");
    }

    @Test
    @DisplayName("Test to create an order")
    @Order(1)
    void testSave() {
        // Arrange
        when(ordersRepository.save(orders)).thenReturn(orders);

        // Act
        Orders result = ordersService.save(orders);

        // Assert
        Assertions.assertEquals(orders, result);
        verify(ordersRepository, times(1)).save(orders);
    }

    @Test
    @DisplayName("Test to find all orders")
    @Order(2)
    void testFindAll() {
        // Arrange
        when(ordersRepository.findAll()).thenReturn(List.of(orders));

        // Act
        List<Orders> result = ordersService.findAll();

        // Assert
        Assertions.assertEquals(List.of(orders), result);
        verify(ordersRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test to find an order by valid id")
    @Order(3)
    void testFindById() {
        // Arrange
        String id = "507f1f77bcf86cd799439011";
        when(ordersRepository.findById(id)).thenReturn(Optional.ofNullable(orders));

        // Act
        Orders result = ordersService.findById(id);

        // Assert
        Assertions.assertEquals(orders, result);
        verify(ordersRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test to find an order by invalid id")
    @Order(4)
    void testFindByIdInvalid() {
        // Arrange
        String id = "507f1f77bcf86cd799439011";
        when(ordersRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Orders result = ordersService.findById(id);

        // Assert
        Assertions.assertNull(result);
        verify(ordersRepository, times(2)).findById(id);
    }

    @Test
    @DisplayName("Test to find orders by valid pickup id")
    @Order(5)
    void testFindByPickupId() {
        // Arrange
        String pickupId = "507f1f77bcf86cd799439011";
        when(ordersRepository.findByPickupId(pickupId)).thenReturn(List.of(orders));

        // Act
        List<Orders> result = ordersService.findByPickupId(pickupId);

        // Assert
        Assertions.assertEquals(List.of(orders), result);
        verify(ordersRepository, times(1)).findByPickupId(pickupId);
    }

    @Test
    @DisplayName("Test to find orders by invalid pickup id")
    @Order(6)
    void testFindByPickupIdInvalid() {
        // Arrange
        String pickupId = "507f1f77bcf86cd799439011";
        when(ordersRepository.findByPickupId(pickupId)).thenReturn(List.of());

        // Act
        List<Orders> result = ordersService.findByPickupId(pickupId);

        // Assert
        Assertions.assertEquals(List.of(), result);
        verify(ordersRepository, times(2)).findByPickupId(pickupId);
    }

    @Test
    @DisplayName("Test to find orders by valid customer email")
    @Order(7)
    void testFindByCustomerEmail() {
        // Arrange
        String email = "leonardo@email.com";
        when(ordersRepository.findByCustomerEmail(email)).thenReturn(List.of(orders));

        // Act
        List<Orders> result = ordersService.findByCustomerEmail(email);

        // Assert
        Assertions.assertEquals(List.of(orders), result);
        verify(ordersRepository, times(1)).findByCustomerEmail(email);
    }

    @Test
    @DisplayName("Test to find orders by invalid customer email")
    @Order(8)
    void testFindByCustomerEmailInvalid() {
        // Arrange
        String email = "invalid";
        when(ordersRepository.findByCustomerEmail(email)).thenReturn(List.of());

        // Act
        List<Orders> result = ordersService.findByCustomerEmail(email);

        // Assert
        Assertions.assertEquals(List.of(), result);
        verify(ordersRepository, times(1)).findByCustomerEmail(email);
    }
}
