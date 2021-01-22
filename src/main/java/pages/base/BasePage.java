package pages.base;

import base.elements.Alert;
import base.elements.Window;

public abstract class BasePage {
    /**
     * Elements
     */
    protected Window window = new Window();
    protected Alert alert = new Alert();

    /**
     * Default refresh page realization
     */
    public BasePage refreshPage() {
        window.refresh();
        alert.accept();
        return this;
    }

    /**
     * Standard implementation of returning page context
     */
    public BasePage switchToOriginalPageContext() {
        window.returnToDefaultWindow();
        alert.accept();
        return this;
    }

    /**
     * Standard implementation of returning to the previous page
     */
    public void goBackPage() {
        window.goBackPage();
    }
}
