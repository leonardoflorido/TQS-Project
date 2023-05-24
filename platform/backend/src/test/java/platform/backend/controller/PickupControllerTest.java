package platform.backend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import platform.backend.model.Pickup;
import platform.backend.service.PickupService;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PickupController.class)
public class PickupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PickupService pickupService;

    @Test
    @DisplayName("Test to register a pickup with a valid input")
    void testRegisterPickupWithValidInput() throws Exception {
        Pickup pickup = new Pickup("Tabacaria", "tabacaria@emai.com", "987654321", "tabacaria", "Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro", "Pending");

        mockMvc.perform(get("/pickup/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(pickup)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Tabacaria")))
                .andExpect(jsonPath("$.email", is("tabacaria@emai.com")))
                .andExpect(jsonPath("$.phone", is("987654321")))
                .andExpect(jsonPath("$.password", is("tabacaria")))
                .andExpect(jsonPath("$.address", is("Avenida Doutor Lourenço Peixinho, 3810-123, Aveiro")))
                .andExpect(jsonPath("$.status", is("Pending")));
    }
}
