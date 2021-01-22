package base.elements;

import base.driver.InitialDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static base.util.BaseConstants.DELAY;
import static base.util.BaseConstants.TIME_OUT;

/**
 * Handler class for describing actions for web elements
 * It is necessary to inherit from this class in case of creating a handler class for a web entity
 */
public class Element {
    protected WebDriver driver = InitialDriver.getInstance().getDriver();

    /**
     * Waiting for a web element, with a timeout from the TIME_OUT variable
     *
     * @return WebDriverWait object waiting for a web element and ignoring a number of exceptions that prevent the element from waiting
     */
    private WebDriverWait waitElement() {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        wait.pollingEvery(Duration.ofMillis(DELAY));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(InterruptedException.class);
        wait.ignoring(UnknownError.class);
        return wait;
    }

    /**
     * Получение веб-элемента с условием
     *
     * @param webElementExpectedCondition - условие
     * @return WebElement-обьект полученного веб-элемента
     */
    public WebElement getWebElement(ExpectedCondition<WebElement> webElementExpectedCondition) {
        return waitElement().until(webElementExpectedCondition);
    }

    /**
     * Get the state of a web item with a state condition
     *
     * @param stateElementExpectedCondition - condition of the state of the web element
     * @return web item state
     */
    public boolean getWebStateOfElement(ExpectedCondition<Boolean> stateElementExpectedCondition) {
        return waitElement().until(stateElementExpectedCondition);
    }

    /**
     * Waiting for a web element to click on its locator
     *
     * @param locator - web element locator
     * @return the retrieved web element
     */
    public WebElement waitUntilClickable(By locator) {
        return getWebElement(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waiting for a web element to click on its object
     *
     * @param element - web element object
     * @return the retrieved web element
     */
    public WebElement waitUntilClickable(WebElement element) {
        return getWebElement(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waiting for the visibility of a web element by its object
     *
     * @param element - web element object
     * @return the retrieved web element
     */
    public WebElement waitUntilVisible(WebElement element) {
        return getWebElement(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waiting for a web element to be visible by its locator
     *
     * @param locator - web element locator
     * @return the retrieved web element
     */
    public WebElement waitUntilVisible(By locator) {
        return getWebElement(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waiting for a web element in the DOM by its object
     *
     * @param locator - web element locator
     * @return the retrieved web element
     */
    public WebElement waitUntilExist(By locator) {
        return getWebElement(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Getting the value of the visibility of a web element by its locator
     * It consists in the fact that we first expect the element to be visible, and then return its visibility
     *
     * @param locator - element locator
     * @return - returns true - the element is visible or disappears with an execution timeout
     */
    public boolean isVisibility(By locator) {
        return getWebElement(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    /**
     * Retrieving a web item without waiting or conditions
     * NOT RECOMMENDED TO USE PURE IN PAGES CLASSES
     * Used to check if an element is in a class
     *
     * @param locator - locator to find an element
     * @return - Returns the web element object, if found
     * @see ElementProperties
     */
    public WebElement getWebElementWithoutWaitAndCondition(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Getting a list of web elements with an expectation of clickability (necessary to get options for custom dropdowns
     * as well as for getting visible elements for further processing, for example - getting a list of texts)
     *
     * @param locator - locator for finding web elements
     * @return - returns a list of found web items
     */
    public List<WebElement> selectFromList(By locator) {
        waitUntilVisible(locator);
        return driver.findElements(locator);
    }
}
