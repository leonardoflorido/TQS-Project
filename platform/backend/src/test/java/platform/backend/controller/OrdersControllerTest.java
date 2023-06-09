package platform.backend.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import platform.backend.model.Orders;
import platform.backend.record.Customer;
import platform.backend.record.Product;
import platform.backend.service.OrdersService;
import platform.backend.utils.JsonUtil;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrdersService ordersService;

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
    @DisplayName("Test to create an order with valid input")
    @Order(1)
    void testCreateOrderWithValidInput() throws Exception {
        mockMvc.perform(post("/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(orders)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.customer.name").value("Leonardo"))
                .andExpect(jsonPath("$.customer.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.customer.phone").value("987654321"))
                .andExpect(jsonPath("$.customer.address").value("Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro"))
                .andExpect(jsonPath("$.eStore").value("Apple"))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.products[0].name").value("Macbook pro 14"))
                .andExpect(jsonPath("$.products[0].price").value(2399.00))
                .andExpect(jsonPath("$.products[0].quantity").value(1))
                .andExpect(jsonPath("$.products[1].name").value("iPhone 14 pro"))
                .andExpect(jsonPath("$.products[1].price").value(1200.00))
                .andExpect(jsonPath("$.products[1].quantity").value(1))
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    @DisplayName("Test to create an order with invalid input")
    @Order(2)
    void testCreateOrderWithInvalidInput() throws Exception {
        mockMvc.perform(post("/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test to get all orders")
    @Order(3)
    void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/orders/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("Test to get orders by pickup id")
    @Order(4)
    void testGetOrdersByPickupId() throws Exception {
        mockMvc.perform(get("/orders/get-by-pickup/507f1f77bcf86cd799439011")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("Test to get orders by pickup id with invalid input")
    @Order(5)
    void testGetOrdersByPickupIdWithInvalidInput() throws Exception {
        mockMvc.perform(get("/orders/get-by-pickup/null")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test to update order status")
    @Order(6)
    void testUpdateOrderStatus() throws Exception {
        Orders ordersFound = ordersService.findByPickupId("507f1f77bcf86cd799439011").get(0);
        ordersFound.setStatus("Delivered");

        mockMvc.perform(put("/orders/update-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(ordersFound)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer.name").value("Leonardo"))
                .andExpect(jsonPath("$.customer.email").value("leonardo@email.com"))
                .andExpect(jsonPath("$.customer.phone").value("987654321"))
                .andExpect(jsonPath("$.customer.address").value("Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro"))
                .andExpect(jsonPath("$.eStore").value("Apple"))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.products[0].name").value("Macbook pro 14"))
                .andExpect(jsonPath("$.products[0].price").value(2399.00))
                .andExpect(jsonPath("$.products[0].quantity").value(1))
                .andExpect(jsonPath("$.products[1].name").value("iPhone 14 pro"))
                .andExpect(jsonPath("$.products[1].price").value(1200.00))
                .andExpect(jsonPath("$.products[1].quantity").value(1))
                .andExpect(jsonPath("$.status").value("Delivered"));
    }

    @Test
    @DisplayName("Test to get orders by customer email with valid input")
    @Order(7)
    void testGetOrdersByCustomerEmail() throws Exception {
        mockMvc.perform(get("/orders/get-by-customer/leonardo@email.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    @DisplayName("Test to get orders by customer email with invalid input")
    @Order(8)
    void testGetOrdersByCustomerEmailWithInvalidInput() throws Exception {
        mockMvc.perform(get("/orders/get-by-customer/null")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test to create multiple orders with valid input")
    @Order(9)
    void testCreateMultipleOrdersWithValidInput() throws Exception {
        mockMvc.perform(post("/orders/create-many")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(List.of(orders, orders))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("Test to create multiple orders with invalid input")
    @Order(10)
    void testCreateMultipleOrdersWithInvalidInput() throws Exception {
        mockMvc.perform(post("/orders/create-many")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(null)))
                .andExpect(status().isBadRequest());
    }
}
