package bindings;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

import utilities.BaseTest;

public class Basket {

    private WebDriver driver;
    private BaseTest Selenium = new BaseTest();

    @Before
    public void setup(Scenario scenario){
        System.out.println("Starting - " + scenario.getName());
        this.driver = Selenium.setup();
    }

    @After
    public void teardown(Scenario scenario){
        System.out.println("Finishing - " + scenario.getName());
        driver.quit();
    }

    @When("^i add an item to the basket$")
    public void i_add_an_item_to_the_basket() throws Throwable {
        driver.findElement(By.cssSelector("#masthead [type='search']")).click();
        driver.findElement(By.cssSelector("#masthead [type='search']")).sendKeys("cap");
        driver.findElement(By.cssSelector("#masthead [type='search']")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".entry-summary button")).click();
    }

    @Then("^i can view the item in my basket$")
    public void i_can_view_the_item_in_my_basket() throws Throwable {
        driver.findElement(By.linkText("Cart")).click();
        assertTrue(driver.findElement(By.linkText("Cap")).isDisplayed());
        driver.findElement(By.linkText("×")).click();
        // explicit wait for item to be removed as may use AJAX
        WebDriverWait wait=new WebDriverWait(driver, 20); //explicit wait if we want to use
        WebElement returnToShopLink;
        returnToShopLink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Return to shop")));
        returnToShopLink.click();
    }


}
