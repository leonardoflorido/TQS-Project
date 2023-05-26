package platform.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import platform.backend.model.Pickup;
import platform.backend.utils.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@TestPropertySource(locations = "classpath:application-test.properties")
class PickupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private Pickup pickup;

    @BeforeEach
    void setUp() {
        pickup = new Pickup("Tabacaria", "tabacaria@email.com", "987654321", "tabacaria", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro", "Pending");
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
    @DisplayName("Test to update the status of the pickup")
    void testUpdatePickupStatus() throws Exception {
        pickup.setStatus("Partner");

        mockMvc.perform(post("/pickup/update-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(pickup)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(pickup.getName()))
                .andExpect(jsonPath("$.email").value(pickup.getEmail()))
                .andExpect(jsonPath("$.phone").value(pickup.getPhone()))
                .andExpect(jsonPath("$.address").value(pickup.getAddress()))
                .andExpect(jsonPath("$.status").value(pickup.getStatus()));
    }
}
