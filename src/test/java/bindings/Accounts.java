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

import static java.lang.Thread.*;
import static org.junit.Assert.assertTrue;
import utilities.BaseTest;

public class Accounts {

    private WebDriver driver;
    private BaseTest Selenium = new BaseTest();
    private String email;

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

    @When("^i register as a new user with '(.*)' and '(.*)'$")
    public void i_register_as_a_new_user(String email, String pwd) throws Throwable {
        this.email = email;

        driver.findElement(By.linkText("My account")).click();

        driver.findElement(By.id("reg_email")).click();
        driver.findElement(By.id("reg_email")).clear();
        driver.findElement(By.id("reg_email")).sendKeys(email);
        driver.findElement(By.id("reg_password")).click();
        driver.findElement(By.id("reg_password")).clear();
        driver.findElement(By.id("reg_password")).sendKeys(pwd);
        driver.findElement(By.id("reg_password")).sendKeys(Keys.TAB);
        sleep(1000);
        //driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[2]/form/p[3]/button")).click();
        driver.findElement(By.name("register")).click();
    }
    @Then("^i get a welcome message$")
    public void i_get_a_welcome_message() throws Throwable {
        String[] parts = email.split("@");
        String uname = parts[0];

        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Hello " + uname));
    }
}
