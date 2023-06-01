package platform.backend.estore;

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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EStoreSteps {
    JavascriptExecutor js;
    private WebDriver driver;

    @Given("I am on the eStore home page")
    public void i_am_on_the_eStore_home_page() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1552, 849));
        driver.get("http://localhost:3000/");
    }

    @And("I want to buy a {string}")
    public void i_want_to_buy_a(String string) {
        switch (string) {
            case "MacBook Pro 14" ->
                    driver.findElement(By.cssSelector(".product:nth-child(1) > .addToCartBttn")).click();
            case "iPhone 14 Pro" ->
                    driver.findElement(By.cssSelector(".product:nth-child(2) > .addToCartBttn")).click();
            case "AirPods" -> {
                driver.findElement(By.cssSelector(".product:nth-child(3) > .addToCartBttn")).click();
            }
        }
    }

    @Then("I want to checkout")
    public void i_want_to_checkout() throws InterruptedException {
        driver.findElement(By.cssSelector("svg")).click();
        TimeUnit.SECONDS.sleep(2);
        {
            WebElement element = driver.findElement(By.cssSelector("svg"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".cartItem:nth-child(3) button:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".checkout > button:nth-child(3)")).click();
        js.executeScript("window.scrollTo(0,0)");
        driver.findElement(By.cssSelector(".button-master-container:nth-child(5) > .button-container")).click();
    }

    @Then("I want to enter {string} in the {string} field")
    public void i_want_to_enter_in_the_field(String input, String field) {
        switch (field) {
            case "First Name" -> {
                driver.findElement(By.id("first-name")).click();
                driver.findElement(By.id("first-name")).sendKeys(input);
            }
            case "Last Name" -> {
                driver.findElement(By.id("last-name")).click();
                driver.findElement(By.id("last-name")).sendKeys(input);
            }
            case "Email" -> {
                driver.findElement(By.id("email")).click();
                driver.findElement(By.id("email")).sendKeys(input);
            }
            case "Phone" -> {
                driver.findElement(By.id("phone")).click();
                driver.findElement(By.id("phone")).sendKeys(input);
            }
            case "Address" -> {
                driver.findElement(By.id("address")).click();
                driver.findElement(By.id("address")).sendKeys(input);
            }
            case "City" -> {
                driver.findElement(By.id("city")).click();
                driver.findElement(By.id("city")).sendKeys(input);
            }
            case "State" -> {
                driver.findElement(By.id("state")).click();
                driver.findElement(By.id("state")).sendKeys(input);
            }
            case "Zip Code" -> {
                driver.findElement(By.id("zip-code")).click();
                driver.findElement(By.id("zip-code")).sendKeys(input);
            }
            case "Card Name" -> {
                driver.findElement(By.cssSelector(".payment-form > input:nth-child(2)")).click();
                driver.findElement(By.cssSelector(".payment-form > input:nth-child(2)")).sendKeys(input);
            }
            case "Card Number" -> {
                driver.findElement(By.cssSelector(".payment-form > input:nth-child(3)")).click();
                driver.findElement(By.cssSelector(".payment-form > input:nth-child(3)")).sendKeys(input);
            }
            case "CVV" -> {
                driver.findElement(By.cssSelector("input:nth-child(4)")).click();
                driver.findElement(By.cssSelector("input:nth-child(4)")).sendKeys(input);
            }
            case "Expiration Date" -> {
                driver.findElement(By.cssSelector(".payment-form > input:nth-child(5)")).sendKeys("10/24");
            }
        }
    }

    @Then("I submit the personal information")
    public void i_submit_the_personal_information() {
        driver.findElement(By.cssSelector("button:nth-child(9)")).click();
        driver.findElement(By.cssSelector("select")).click();
    }

    @Then("I pick the pickup closest to me")
    public void i_pick_the_pickup_closest_to_me() {
        {
            WebElement dropdown = driver.findElement(By.cssSelector("select"));
            dropdown.findElement(By.xpath("//option[. = 'Papelaria']")).click();
        }
        driver.findElement(By.cssSelector("select")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("select"));
            dropdown.findElement(By.xpath("//option[. = 'Fitness Studio']")).click();
        }
        driver.findElement(By.cssSelector("select")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("select"));
            dropdown.findElement(By.xpath("//option[. = 'Tabacaria']")).click();
        }
        driver.findElement(By.cssSelector(".button-master-container:nth-child(2) > .button-container")).click();
    }

    @When("I submit the payment information")
    public void i_submit_the_payment_information() {
        driver.findElement(By.cssSelector(".button-full")).click();
    }

    @Then("I receive a confirmation message")
    public void i_receive_a_confirmation_message() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertThat(alertText, is("Order created successfully"));
        alert.accept();
    }

    @Then("I want to see my orders")
    public void i_want_to_see_my_orders() {
        driver.findElement(By.linkText("Check Orders")).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("leonardo@email.com");
        driver.findElement(By.cssSelector(".submit-btn")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".submit-btn"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
    }
}
