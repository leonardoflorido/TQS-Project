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
import platform.backend.utils.JsonUtil;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


    private Orders orders;

    @BeforeAll
    void setUp() {
        Customer customer = new Customer("Leonardo", "leonardo@email.com", "987654321", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro");

        Product macbook = new Product("Macbook pro 14", 2399.00, 1);
        Product iphone = new Product("iPhone 14 pro", 1200.00, 1);
        Product airpods = new Product("AirPods", 140.00, 3);

        orders = new Orders("507f1f77bcf86cd799439011", customer, "Apple", new Date(), List.of(macbook, iphone), "Pending");
    }

    @AfterAll
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    @Test
    @DisplayName("Test to create an order with valid input")
    @Order(1)
    void createOrderWithValidInput() throws Exception {
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
}
