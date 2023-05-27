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
import platform.backend.model.Admin;
import platform.backend.record.Login;
import platform.backend.utils.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Admin admin;

    @BeforeAll
    void setUp() {
        admin = new Admin("Admin", "admin@email.com", "admin");
    }

    @AfterAll
    void tearDown() {
        mongoTemplate.getDb().drop();
    }

    @Test
    @DisplayName("Test to register an admin with a valid input")
    @Order(1)
    void testRegisterAdminWithValidInput() throws Exception {
        mockMvc.perform(post("/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(admin)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(admin.getName()))
                .andExpect(jsonPath("$.email").value(admin.getEmail()));
    }

    @Test
    @DisplayName("Test to register an admin with an invalid input")
    @Order(2)
    void testRegisterAdminWithInvalidInput() throws Exception {
        mockMvc.perform(post("/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test to login an admin with a valid input")
    @Order(3)
    void testLoginAdminWithValidInput() throws Exception {
        Login login = new Login(admin.getEmail(), admin.getPassword());

        mockMvc.perform(post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(admin.getName()))
                .andExpect(jsonPath("$.email").value(admin.getEmail()));
    }

    @Test
    @DisplayName("Test to login an admin with an invalid input")
    @Order(4)
    void testLoginAdminWithInvalidInput() throws Exception {
        Login login = new Login(admin.getEmail(), "wrong password");

        mockMvc.perform(post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(login)))
                .andExpect(status().isUnauthorized());
    }
}
