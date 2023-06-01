package platform.backend.pickup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class RegisterSteps {
    JavascriptExecutor js;
    private WebDriver driver;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1552, 849));
        driver.get("http://localhost:3001/");
    }

    @And("the user wants to register a pickup")
    public void the_user_wants_to_register_a_pickup() {
        driver.findElement(By.linkText("Don\'t have an account? Sign Up")).click();
        driver.findElement(By.id("Name")).click();
    }

    @When("the user enters {string} in the {string} field")
    public void when_the_user_enters_in_the_field(String input, String field) {
        switch (field) {
            case "Name" -> {
                driver.findElement(By.id("Name")).click();
                driver.findElement(By.id("Name")).sendKeys(input);
            }
            case "Email" -> {
                {
                    WebElement element = driver.findElement(By.id("email"));
                    Actions builder = new Actions(driver);
                    builder.moveToElement(element).clickAndHold().perform();
                }
                {
                    WebElement element = driver.findElement(By.id("email-label"));
                    Actions builder = new Actions(driver);
                    builder.moveToElement(element).release().perform();
                }
                driver.findElement(By.cssSelector(".MuiGrid-root:nth-child(2) > .MuiFormControl-root")).click();
                driver.findElement(By.id("email")).sendKeys(input);
            }
            case "Phone" -> driver.findElement(By.id("phone")).sendKeys(input);
            case "Password" -> {
                driver.findElement(By.id("password")).click();
                driver.findElement(By.id("password")).sendKeys(input);
            }
            case "Address" -> driver.findElement(By.id("address")).sendKeys(input);
        }
    }

    @When("the user clicks on the register button")
    public void when_the_user_clicks_on_the_register_button() {
        driver.findElement(By.cssSelector(".MuiButtonBase-root")).click();
    }

    @Then("the user should see the {string} message")
    public void then_the_user_should_see_the_message(String message) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        String actualMessage = driver.switchTo().alert().getText();
        assertThat(actualMessage.replaceAll("\\R", ""), containsString(message.replaceAll("\\R", "")));
    }
}
