package base.elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static base.util.BaseConstants.TIME_OUT;

/**
 * Handler class for methods associated with properties of web elements
 */
@Slf4j
public class ElementProperties extends Element {
    /**
     * Getting the attribute of the web element found by the locator
     *
     * @param locator   - locator
     * @param attribute - attribute
     * @return string with attribute value
     */
    public String getAttribute(By locator, String attribute) {
        return waitUntilVisible(locator).getAttribute(attribute);
    }

    /**
     * Getting the attribute of the web element found by the locator
     *
     * @param element   - element
     * @param attribute - attribute
     * @return string with attribute value
     */
    public String getAttribute(WebElement element, String attribute) {
        return waitUntilVisible(element).getAttribute(attribute);
    }

    /**
     * Getting the CSS property found by the locator
     *
     * @param locator  - locator
     * @param cssValue - CSS property
     * @return CSS property value string
     */
    public String getCSSValue(By locator, String cssValue) {
        return waitUntilClickable(locator).getCssValue(cssValue);
    }

    /**
     * Getting the class of an element
     *
     * @param locator - element locator
     * @return element class
     */
    public String getAttributeClass(By locator) {
        return getAttribute(locator, "class");
    }

    /**
     *
     */
    public String getTextExistedElement(By locator) {
        return waitUntilExist(locator).getText();
    }

    /**
     * Getting the text of an element existing in the DOM tree
     *
     * @param locator - element locator
     * @return text of found element
     */
    public List<String> getElementsTexts(By locator) {
        return selectFromList(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * Get the value of web elements
     *
     * @param locator locator for finding a list of web elements
     * @return list of texts
     */
    public List<String> getElementsValues(By locator) {
        return selectFromList(locator).stream().map(element -> {
            return getAttribute(element, "value");
        }).collect(Collectors.toList());
    }

    /**
     * Getting the "value" attribute of a web element
     *
     * @param locator - element locator
     * @return attribute value
     */
    public String getAttributeValue(By locator) {
        return waitUntilExist(locator).getAttribute("value");
    }


    /**
     * Getting the "value" attribute of a web element
     *
     * @param element - the object of the element
     * @return attribute value
     */
    public String getAttributeValue(WebElement element) {
        return waitUntilVisible(element).getAttribute("value");
    }

    /**
     * Get the color of a web element
     *
     * @param locator - element locator
     * @return string with color value
     */
    public String getColor(By locator) {
        return getCSSValue(locator, "color");
    }

    /**
     * Returns the value of the element's presence state
     *
     * @param locator - element locator
     * @return - true - the element exists, false - the element does not exist
     */
    public boolean isElementPresent(By locator) {
        try {
            getWebElementWithoutWaitAndCondition(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /**
     * Getting the value of the state of the presence of a web element attribute
     *
     * @param locator   - locator
     * @param attribute - attribute
     * @return - true - the attribute exists, false - the attribute does not exist
     */
    public boolean isAttributePresent(By locator, String attribute) {
        boolean result = false;
        try {
            String value = getAttribute(locator, attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return result;
    }

    /**
     * Getting the value of the state of the presence of a web element attribute
     * Method with the condition of waiting for the element to have an attribute
     *
     * @param locator   - locator
     * @param attribute - attribute
     * @return - true - the attribute exists, false - the attribute does not exist
     */
    public boolean isAttributePresentWithWait(By locator, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        boolean result = false;
        wait.until((ExpectedCondition<Boolean>) driver -> {
            try {
                if (getAttribute(locator, attribute) != null) {
                    return Boolean.TRUE;
                }
            } catch (Exception ex) {
                log.warn("Element attribute not found, wait more");
            }
            return null;
        });
        try {
            if (getAttribute(locator, attribute) != null) {
                result = true;
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return result;
    }

    /**
     * Get the value of the state of the absence of a web element attribute
     * Method with the condition of waiting for the element to have no attribute
     *
     * @param locator   - locator
     * @param attribute - attribute
     * @return - true - the attribute is absent, false - the attribute is present
     */
    public boolean isAttributeNotPresentWithWait(By locator, String attribute) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        boolean result = false;
        wait.until((ExpectedCondition<Boolean>) driver -> {
            try {
                if (getAttribute(locator, attribute) == null) {
                    return Boolean.TRUE;
                }
            } catch (Exception ex) {
                log.warn("Element attribute found, wait more");
            }
            return null;
        });
        try {
            if (getAttribute(locator, attribute) == null) {
                result = true;
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return result;
    }
}
