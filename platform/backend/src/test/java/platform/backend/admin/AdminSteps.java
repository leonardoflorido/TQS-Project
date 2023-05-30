package platform.backend.admin;

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

public class AdminSteps {
    JavascriptExecutor js;
    private WebDriver driver;

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1552, 849));
        driver.get("http://localhost:3000/");
    }

    @And("the user is an admin")
    public void the_user_is_an_admin() {
        driver.findElement(By.linkText("Admin?")).click();
    }

    @When("the admin enters {string} in the {string} field")
    public void when_the_admin_enters_in_the_field(String input, String field) {
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

    @Then("the admin clicks on the login button")
    public void the_admin_clicks_the_login_button() {
        driver.findElement(By.cssSelector(".MuiButtonBase-root")).click();
    }

    @Then("the admin see the orders")
    public void the_admin_see_the_orders() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(By.cssSelector(".MuiDataGrid-row:nth-child(1) .MuiDataGrid-groupingCriteriaCell .MuiSvgIcon-root")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiDataGrid-row:nth-child(1) .MuiDataGrid-groupingCriteriaCell .MuiSvgIcon-root"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = driver.findElement(By.tagName("body"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element, 0, 0).perform();
        }
        driver.findElement(By.cssSelector(".MuiDataGrid-cell--withRenderer:nth-child(2) .MuiButtonBase-root")).click();
    }

    @Then("the admin change the status of the pickup to {string}")
    public void the_admin_change_the_status_of_the_pickup_to(String status) {
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiDataGrid-row:nth-child(1) .MuiDataGrid-groupingCriteriaCell .MuiSvgIcon-root"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        driver.findElement(By.cssSelector(".MuiDataGrid-row:nth-child(1) .MuiDataGrid-groupingCriteriaCell .MuiSvgIcon-root")).click();
        driver.findElement(By.cssSelector(".MuiDataGrid-row--lastVisible > .MuiDataGrid-cell--editable > .MuiDataGrid-cellContent")).click();
        driver.findElement(By.cssSelector(".Mui-selected > .MuiDataGrid-cell--editable > .MuiDataGrid-cellContent")).click();
        {
            WebElement element = driver.findElement(By.cssSelector(".MuiDataGrid-row--lastVisible > .MuiDataGrid-cell--editable > .MuiDataGrid-cellContent"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
        driver.findElement(By.cssSelector(".MuiInputBase-input")).sendKeys(status);
        driver.findElement(By.cssSelector(".MuiStack-root > .MuiButtonBase-root:nth-child(1)")).click();
    }

    @Then("the admin logout")
    public void the_admin_logout() {
        driver.findElement(By.cssSelector(".MuiButtonBase-root:nth-child(2) .MuiTypography-root")).click();
    }
}
