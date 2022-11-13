package base.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static base.util.BaseConstants.DRIVER_NAME;
import static base.util.BaseConstants.useWebdriverManager;

/**
 * A class for initializing and getting a webdriver - an object for controlling the browser
 */
@Slf4j
public class InitialDriver extends Options {
    /**
     * Driver (profile) name for subsequent selection of settings for initialization
     */
    private static final String driverName;
    /**
     * ThreadLocal object for video driver synchronization (for future multithreading)
     */
    private static final ThreadLocal<InitialDriver> driverThread = new ThreadLocal<>();

    static {
        driverName = DRIVER_NAME;
    }

    /**
     * Web Driver Object
     */
    private WebDriver driver;

    /**
     * Getting an instance of a class
     */
    public static InitialDriver getInstance() {
        if (driverThread.get() == null) {
            synchronized (InitialDriver.class) {
                driverThread.set(new InitialDriver());
            }
        }
        return driverThread.get();
    }

    /**
     * Getting a driver instance with initialization (if the driver is not initialized)
     *
     * @return instance of webdriver
     */
    public WebDriver getDriver() {
        if (driver == null) {
            driver = initialDriver();
        }
        return driver;
    }

    /**
     * Method which depending on the value in browser.name in main / resources / browser.properties
     * initializes the browser instance
     * CHROME_WIN10 - an instance of the remote driver for Chrome on Windows obtained from the Selenium Grid hub
     * FIREFOX_WIN10 - an instance of the remote driver for Firefox on Windows obtained from the Selenium Grid hub
     * <p>
     * CHROME-LOCAL - a local driver instance for Chrome obtained from src / main / resources / chromedriver.exe
     * default - an instance of the local driver for Firefox obtained from src / main / resources / geckodriver.exe
     */
    private synchronized WebDriver initialDriver() {
        switch (driverName) {
            case "CHROME_LOCAL": {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                log.info("Creation driver instance: Chrome local...");
                if (useWebdriverManager)
                    WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions());
                break;
            }
            case "FIREFOX_LOCAL": {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                log.info("Creation driver instance: Firefox local...");
                if (useWebdriverManager)
                    WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions());
            }
            default: {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                log.info("Creation driver instance: Firefox local...");
                if (useWebdriverManager)
                    driver = new FirefoxDriver(firefoxOptions());
                driver.manage().window().maximize();
            }
        }
        log.info("Browser initialised: " + getBrowserExecuted());
        log.info("Browser options: " + ((RemoteWebDriver) driver).getCapabilities());
        return driver;
    }

    /**
     * Returns the name of the web driver browser
     *
     * @return driver name string
     */
    public String getBrowserExecuted() {
        return ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase();
    }

    /**
     * Destroying a driver instance after a test
     */
    public void destroy() {
        if (driver != null) {
            driver.quit();
            this.driver = null;
            log.info("Driver destroy...");
        } else {
            log.info("Driver destroy with no driver present...");
        }
    }
}