package platform.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import platform.backend.model.Pickup;
import platform.backend.service.PickupService;
import platform.backend.utils.JsonUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class PickupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PickupService pickupService;

    private Pickup pickup;

    @BeforeEach
    void setUp() {
        pickup = new Pickup("Tabacaria", "tabacaria@email.com", "987654321", "tabacaria", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro", "Pending");
    }

    @Test
    @DisplayName("Test to register a pickup with a valid input")
    void testRegisterPickupWithValidInput() throws Exception {
        when(pickupService.save(any(Pickup.class))).thenReturn(pickup);

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
}
