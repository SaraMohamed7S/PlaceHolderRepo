package utilities;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters("browserType")
    public void lanuchBrowser(@Optional ("Chrome") String browserType)
    {
        switch (browserType) {
            case "Chrome" :
                WebDriverManager.chromedriver().setup();
            case "Firefox" :
                WebDriverManager.firefoxdriver().setup();
            case "Edge" :
                WebDriverManager.edgedriver().setup();
            case "Safari" :
                WebDriverManager.safaridriver().setup();
            case "IE" :
                WebDriverManager.iedriver().setup();
        }
    }

    @AfterMethod
    public void tearDownDriver()
    {
    }
}
