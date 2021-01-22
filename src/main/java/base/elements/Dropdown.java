package base.elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A handler class for describing actions for items with a dropdown list
 */
@Slf4j
public class Dropdown extends Element {

    /**
     * Get the selected dropdown item
     *
     * @param locator - element locator by which the drop-down list is found
     * @return string with selected dropdown option
     */
    public String getSelected(By locator) {
        Select select = new Select(waitUntilClickable(locator));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Select item dropdown list
     *
     * @param locator - element locator by which the drop-down list is found
     * @param value - a string with an option to be selected in the drop-down list
     */
    public void selectByVisibleText(By locator, String value) {
        Select select = new Select(waitUntilClickable(locator));
        select.selectByVisibleText(value);
    }

    /**
     * Selecting a drop-down list item by index
     *
     * @param locator - element locator by which the drop-down list is found
     * @param index - the index of the option to be selected in the dropdown list
     */
    public void selectByIndex(By locator, int index) {
        Select select = new Select(waitUntilClickable(locator));
        select.selectByIndex(index);
    }

    /**
     * Select item dropdown list
     *
     * @param locator - element locator by which the drop-down list is found
     * @param value - substring with an option to be selected in the drop-down list
     */
    public void selectByVisibleSubText(By locator, String value) {
        Select select = new Select(waitUntilClickable(locator));
        List<String> listOfOptions = select.getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        int indexOfElement = -1;
        for (String option : listOfOptions) {
            if (option.contains(value)) {
                indexOfElement = listOfOptions.indexOf(option);
                break;
            }
        }
        if (indexOfElement != -1) {
            select.selectByIndex(indexOfElement);
        } else {
            log.info("Element " + value + " not found in list with options " + listOfOptions);
        }
    }

    /**
     * Check if dropdown contains an option
     *
     * @param locator - element locator by which the drop-down list is found
     * @param option - the option we expect in the dropdown options list
     * @return true - the option is present, false - not
     */
    public boolean isDropdownHasOption(By locator, String option) {
        Select select = new Select(waitUntilClickable(locator));
        return select.getOptions().stream().map(WebElement::getText).collect(Collectors.toSet()).contains(option);
    }
}
