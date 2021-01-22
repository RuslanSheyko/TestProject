package base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Handler class for describing actions to be processed through JS scripts
 */
public class JSExecutor extends Element {
    /**
     * Scroll down the page until the web element is visible on the object
     *
     * @param element - web element object
     */
    public void scrollToNecessaryElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    /**
     *  Scroll to your locator
     * @param locator
     */
    public void scrollToNecessaryElement(By locator) {
        scrollToNecessaryElement(waitUntilVisible(locator));
    }

    /**
     * Scroll the page to the bottom
     */
    public void scrollToPageBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Scroll the page to the top
     */
    public void scrollToPageTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }

    /**
     * New browser tab opening
     */
    public void openNewBrowserTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }
}
