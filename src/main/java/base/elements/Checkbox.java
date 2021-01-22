package base.elements;

import org.openqa.selenium.By;

/**
 * Handler class for handling actions with the Checkbox element
 */
public class Checkbox extends Element {
    Button button = new Button();

    /**
     * Click on checkbox`y
     */
    public void checkCheckBox(By locator) {
        button.click(locator);
    }

    /**
     * Check if checkbox is selected
     *
     * @param locator - checkbox locator
     * @return - true if the checkbox is selected
     */
    public boolean isCheckboxChecked(By locator) {
        return waitUntilClickable(locator).isSelected();
    }

}
