package base.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import static base.elements.FileDownloadWindow.getFileDownloadOptions;
import static base.elements.FileDownloadWindow.getGeckoDownloadProfile;

/**
 * The class describes properties for different types of browsers
 */
abstract class Options {
    /**
     * Returns standard options for Firefox browser
     *
     * @return an object of the Options class containing the required properties
     */
    FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        /* Adding settings for file download */
        options.setProfile(getGeckoDownloadProfile());
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        return options;
    }

    /**
     * Returns standard options for Chrome browser
     *
     * @return an object of the Options class containing the required properties
     */
    ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        /* Adding settings for file download */
        options.setExperimentalOption("prefs", getFileDownloadOptions());
        return options;
    }
}
