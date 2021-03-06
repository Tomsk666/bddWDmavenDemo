package utilities;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void openBrowser(Scenario scenario) throws MalformedURLException {
        System.out.println("Called openBrowser in " + scenario.getName());
        driver = new ChromeDriver();
        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://www.edgewordstraining.co.uk/demo-site/");
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Dismiss")).click();
    }
    @After
    public void embedScreenshot(Scenario scenario) {
        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + driver.getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
        driver.quit();
    }
}

