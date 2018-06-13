package bindings;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import static java.lang.Thread.*;
import static org.junit.Assert.assertTrue;
import utilities.Hooks;



public class Accounts {

    private WebDriver driver;
    private String email;

    public Accounts(){
        driver = Hooks.driver;
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

    @When("^i try to register with an existing user '(.*)' and '(.*)'$")
    public void i_try_to_register_with_an_existing_user_user_edgewords_co_uk_and_password_Q(String email, String pwd) throws Throwable {
        driver.findElement(By.linkText("My account")).click();

        driver.findElement(By.id("reg_email")).click();
        driver.findElement(By.id("reg_email")).clear();
        driver.findElement(By.id("reg_email")).sendKeys(email);
        driver.findElement(By.id("reg_password")).click();
        driver.findElement(By.id("reg_password")).clear();
        driver.findElement(By.id("reg_password")).sendKeys(pwd);
        driver.findElement(By.id("reg_password")).sendKeys(Keys.TAB);
        sleep(1000);
        driver.findElement(By.name("register")).click();
    }

    @Then("^i get an error message that the user is already registered$")
    public void i_get_an_error_message_that_the_user_is_already_registered() throws Throwable {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue(bodyText.contains("Error: An account is already registered with your email address"));
    }

    @When("^i login with valid credentials '(.*)' and '(.*)'$")
    public void i_login_with_valid_credentials_user_edgewords_co_uk_and_password_Q(String email, String pwd) throws Throwable {
        driver.findElement(By.linkText("My account")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(email);
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(pwd);
        driver.findElement(By.name("login")).click();
    }

    @Then("^i get access to My Account$")
    public void i_get_access_to_My_Account() throws Throwable {    // Write code here that turns the phrase above into concrete actions
            assertTrue(driver.findElement(By.linkText("Dashboard")).isDisplayed());
    }

}
