package base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Handler class for describing actions for text field elements
 */
public class Field extends Element {
    /**
     * Method for getting the text of an element by locator
     *
     * @param locator - element locator
     * @return string - element text
     */
    public String getText(By locator) {
        return waitUntilVisible(locator).getText();
    }

    /**
     * Method for getting the text of an element by object
     *
     * @param element - an object of an element
     * @return string - text of an element
     */
    public String getText(WebElement element) {
        return waitUntilVisible(element).getText();
    }

    /**
     * Method of writing text in the element field by locator after waiting for the element to exist
     *
     * @param locator - element object
     * @param text - text
     */
    public void typeTextOnExist(By locator, String text) {
        waitUntilExist(locator).clear();
        waitUntilExist(locator).sendKeys(text);
    }

    /**
     * The method of writing text in the element field by locator after waiting for the element to be clickable
     *
     * @param locator - element object
     * @param text - text
     */
    public void typeText(By locator, String text) {
        waitUntilClickable(locator).clear();
        waitUntilClickable(locator).sendKeys(text);
    }

    /**
     * Method of clearing text in the element field by locator after waiting for the element to be clickable
     *
     * @param locator - element object
     */
    public void clearText(By locator) {
        waitUntilClickable(locator).clear();
    }
}
