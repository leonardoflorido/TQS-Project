package platform.backend.pickup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class PickupSteps {
    JavascriptExecutor js;
    private WebDriver driver;

    @Given("the pickup is on the login page")
    public void the_pickup_is_on_the_login_page() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1552, 849));
        driver.get("http://localhost:3001/");
    }

    @When("the pickup enters {string} in the {string} field")
    public void when_the_pickup_enters_in_the_field(String input, String field) {
        switch (field) {
            case "Email" -> {
                driver.findElement(By.id("email")).click();
                driver.findElement(By.id("email")).sendKeys(input);
            }
            case "Password" -> {
                {
                    WebElement element = driver.findElement(By.cssSelector(".MuiButtonBase-root"));
                    Actions builder = new Actions(driver);
                    builder.moveToElement(element).perform();
                }
                driver.findElement(By.id("password")).sendKeys(input);
            }
        }
    }

    @Then("the pickup clicks on the login button")
    public void the_pickup_clicks_the_login_button() {
        driver.findElement(By.cssSelector(".MuiButtonBase-root")).click();
    }

    @Then("the pickup change the status of the order to {string}")
    public void the_pickup_change_the_status_of_the_order_to(String status) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.cssSelector(".MuiSvgIcon-fontSizeInherit")).click();
        driver.findElement(By.cssSelector(".MuiDataGrid-cell--editable > .MuiDataGrid-cellContent")).click();
        driver.findElement(By.cssSelector(".MuiDataGrid-cell--editable > .MuiDataGrid-cellContent")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiDataGrid-cell--editable > .MuiDataGrid-cellContent"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        switch (status) {
            case "Processing" -> driver.findElement(By.cssSelector(".MuiMenuItem-root:nth-child(1)")).click();
            case "In Transit" -> driver.findElement(By.cssSelector(".MuiMenuItem-root:nth-child(2)")).click();
            case "Waiting for collection" ->
                    driver.findElement(By.cssSelector(".MuiMenuItem-root:nth-child(3)")).click();
            case "Completed" -> driver.findElement(By.cssSelector(".MuiMenuItem-root:nth-child(4)")).click();
        }
        driver.findElement(By.cssSelector(".MuiStack-root > .MuiButtonBase-root")).click();
    }

    @Then("the pickup clicks on the logout button")
    public void the_pickup_clicks_on_the_logout_button() {
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(2) .MuiTypography-root")).click();
    }
}
