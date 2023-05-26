package platform.backend.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import platform.backend.BackendApplication;
import platform.backend.model.Admin;
import platform.backend.service.AdminService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BackendApplication.class)
@AutoConfigureMockMvc
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @Test
    @DisplayName("Test to Register admin")
    void testAdminRegister() throws Exception {
        String requestBody = "{\"name\":\"admin\", \"email\":\"admin@admin.com\", \"password\":\"admin\"}";

        Admin admin = new Admin("admin", "admin@admin.com", "admin");

        when(adminService.save(admin)).thenReturn(admin);

        mockMvc.perform(post("/admin/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test to Login admin")
    @Disabled
    void testAdminLogin() throws Exception {

        String requestBody = "{\"email\":\"admin@admin.com\", \"password\":\"admin\"}";

        Admin admin = new Admin("admin", "admin@admin.com", "admin");

        when(adminService.findByEmail("admin@admin.com")).thenReturn(admin);

        mockMvc.perform(post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test to Login admin with wrong credentials")
    void testAdminLoginWrongPassword() throws Exception {

        String requestBody = "{\"email\":\"admin@admin.com\", \"password\":\"passasasa\"}";

        Admin admin = new Admin("admin", "admin@admin.com", "admin");

        when(adminService.findByEmail("admin@admin.com")).thenReturn(admin);

        mockMvc.perform(post("/admin/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

    }
}
