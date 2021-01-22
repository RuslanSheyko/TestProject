package base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * A handler class for describing actions that simulate user actions
 */
public class MouseActions extends Element {
    /**
     * Mouse hover over element by locator
     *
     * @param locator - element locator
     */
    public void mouseHover(By locator) {
        mouseHover(waitUntilVisible(locator));
    }

    /**
     * Mouse hover over an element
     *
     * @param element - element object
     */
    public void mouseHover(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /**
     * Hovering the mouse over the element over the object and clicking
     *
     * @param element - the object of the element
     */
    public void mouseHoverAndClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).click().build().perform();
    }

    /**
     * Mouse hover over element by locator and click
     *
     * @param locator - element locator
     */
    public void mouseHoverAndClick(By locator) {
        mouseHoverAndClick(waitUntilClickable(locator));
    }
}
