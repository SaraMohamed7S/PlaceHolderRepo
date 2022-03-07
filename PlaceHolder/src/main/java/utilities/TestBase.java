package utilities;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TestBase extends AbstractTestNGCucumberTests {
    public static String browserType;
    public static WebDriver driver;
    public static WebDriverWait wait;

    ChromeOptions chromeOptions = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    DesiredCapabilities firefoxCapabilities = new DesiredCapabilities();
    DesiredCapabilities ieCapabilities = new DesiredCapabilities();
//    InternetExplorerOptions ieOptions = new InternetExplorerOptions();

    public static Actions actions;

    //===================================Set Drivers Options and capabilities===========================
    //--------------------------------Driver Options------------------------------------

    public void setChromePreferences() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromeOptions.setCapability("prefs", chromePrefs);
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriverManager.chromedriver().setup();
    }

    public void getChromeDriver() {
        driver = new ChromeDriver(chromeOptions);
    }

    //------------------------------------------Firefox options---------------------------------
    public void setFirefoxCapabilities() {
        firefoxCapabilities.setCapability("RequireWindowFocus", true);
        firefoxCapabilities.setCapability("marionette", true);
        WebDriverManager.firefoxdriver().clearDriverCache();
        WebDriverManager.firefoxdriver().browserVersion("0.10").setup();
    }

    public void getFirefoxDriver() {
        firefoxOptions.merge(firefoxCapabilities);
        driver = new FirefoxDriver(firefoxOptions);
    }

    //-----------------------------------Edge Capabilities----------------------------------
    public static void setEdgeCapabilities() {
        WebDriverManager.edgedriver().clearDriverCache();
        WebDriverManager.edgedriver().setup();
    }

    public void getEdgeDriver() {
        driver = new EdgeDriver();
    }

    //=================================launch Browser================================
    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(@Optional("Chrome") String browser) {
        browserType = browser;
        if (browserType.equalsIgnoreCase("Chrome")) {
            setChromePreferences();
            getChromeDriver();
        } else if (browserType.equalsIgnoreCase("Firefox")) {
            setFirefoxCapabilities();
            getFirefoxDriver();
        } else if (browserType.equalsIgnoreCase("Edge")) {
            setEdgeCapabilities();
            getEdgeDriver();
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        actions = new Actions(getDriver());
    }

    // call this method to get the driver object and launch the browser
    public static WebDriver getDriver() {
        return driver;
    }
    @AfterMethod
    public void quitDriver() {
        getDriver().quit();
    }
}