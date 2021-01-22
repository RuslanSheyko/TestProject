package base.elements;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoAlertPresentException;

/**
 * Class-handler of actions with Alerts
 */
@Slf4j
public class Alert extends Element {
    /**
     * Accept alert
     */
    public Alert accept() {
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
            log.info("Alert is present and accepted");
        }
        return this;
    }

    /**
     * @return result of checking if an alert exists
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }
}
