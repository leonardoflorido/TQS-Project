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
import platform.backend.model.Pickup;
import platform.backend.record.Login;
import platform.backend.utils.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PickupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Pickup pickup;

    @BeforeAll
    void setUp() {
        pickup = new Pickup("Tabacaria", "tabacaria@email.com", "987654321", "tabacaria", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro", "Pending");
    }

    @AfterAll
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    @Test
    @DisplayName("Test to register a pickup with a valid input")
    void testRegisterPickupWithValidInput() throws Exception {
        mockMvc.perform(post("/pickup/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(pickup)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(pickup.getName()))
                .andExpect(jsonPath("$.email").value(pickup.getEmail()))
                .andExpect(jsonPath("$.phone").value(pickup.getPhone()))
                .andExpect(jsonPath("$.address").value(pickup.getAddress()))
                .andExpect(jsonPath("$.status").value(pickup.getStatus()));
    }

    @Test
    @DisplayName("Test to register a pickup with an invalid input")
    void testRegisterPickupWithInvalidInput() throws Exception {
        mockMvc.perform(post("/pickup/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test to update the status of a pickup")
    void testUpdatePickupStatus() throws Exception {
        pickup.setStatus("Partner");

        mockMvc.perform(put("/pickup/update-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(pickup)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pickup.getName()))
                .andExpect(jsonPath("$.email").value(pickup.getEmail()))
                .andExpect(jsonPath("$.phone").value(pickup.getPhone()))
                .andExpect(jsonPath("$.address").value(pickup.getAddress()))
                .andExpect(jsonPath("$.status").value(pickup.getStatus()));
    }

    @Test
    @DisplayName("Test to login a pickup with an valid input")
    void testLoginPickupWithInvalidInput() throws Exception {
        Login login = new Login(pickup.getEmail(), pickup.getPassword());

        mockMvc.perform(post("/pickup/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(login)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test to login a pickup with an invalid input")
    void testLoginPickupWithValidInput() throws Exception {
        Login login = new Login(pickup.getEmail(), "wrongPassword");

        mockMvc.perform(post("/pickup/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(login)))
                .andExpect(status().isUnauthorized());
    }
}
