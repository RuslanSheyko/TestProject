package base.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * A handler class for describing actions for elements on which clicks are made (buttons, links ...)
 */
public class Button extends Element {
    ElementProperties elementProperties = new ElementProperties();

    /**
     * Click on the button on the locator
     *
     * @param locator - element locator
     */
    public void click(By locator) {
        waitUntilClickable(locator).click();
    }

    /**
     * Click on a button on a web element
     *
     * @param element - element object
     */
    public void click(WebElement element) {
        waitUntilClickable(element).click();
    }

    /**
     * Check that the button is clickable (does not contain the "disabled" attribute)
     *
     * @param locator - button locator
     * @return - true - the button is enabled \ false the button is disabled
     */
    public boolean isButtonEnabled(By locator) {
        return !elementProperties.getAttribute(locator, "disabled").contains("true");
    }
}
