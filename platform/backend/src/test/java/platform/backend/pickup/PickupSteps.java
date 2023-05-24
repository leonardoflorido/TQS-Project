package platform.backend.pickup;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import platform.backend.controller.PickupController;
import platform.backend.model.Pickup;
import platform.backend.service.PickupService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(PickupController.class)
public class PickupSteps {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PickupService service;
    private int responseStatusCode;
    private String url;

    @Given("I have a valid pickup registration endpoint at {string}")
    public void setApiUrl(String endpointUrl) {
        url = endpointUrl;
        System.out.println("url: " + url);
    }

    @When("I send a POST request with the following payload:")
    public void sendPostRequest(Map<String, String> requestData) throws Exception {
        Pickup pickup = new Pickup(requestData.get("name"), requestData.get("email"), requestData.get("phone"), requestData.get("password"), requestData.get("address"), requestData.get("status"));
        when(service.save(pickup)).thenReturn(pickup);
        MvcResult result = mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pickup)))
                .andReturn();
        responseStatusCode = result.getResponse().getStatus();
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, responseStatusCode);
    }
}
