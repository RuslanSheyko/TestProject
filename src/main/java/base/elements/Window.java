package base.elements;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class handler for describing actions associated with the browser window
 */
@Slf4j
public class Window extends Element {

    /**
     * Navigating a web page by URL
     *
     * @param url - the address to which the transition is made
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Getting the page address
     *
     * @return page address string
     */
    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Change browser window by title
     */
    @Step("Browser window opening \"{0}\"")
    public void switchToWindowByTitle(String title) {
        Set<String> handle = driver.getWindowHandles();
        for (String myWindows : handle) {
            String windowTitle = driver.switchTo().window(myWindows).getTitle();
            if (windowTitle.equals(title)) {
                log.info("Switch to tab with title:\"" + title + "\"");
                driver.switchTo().window(myWindows);
            }
        }
    }

    /**
     * Changing the browser window to standard
     */
    public void returnToDefaultWindow() {
        changeBrowserTabById(0);
        log.info("Switch to original browser tab");
    }

    /**
     * Opening a tab by id
     *
     * @param id - tab index
     */
    public void changeBrowserTabById(int id) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(id));
        log.info("Switch to tab with id:\"" + id + "\" and name: \"" + driver.getTitle() + "\"");
    }

    /**
     * Create new browser tab
     */
    public void openNewBrowserTab() {
        new JSExecutor().openNewBrowserTab();
        log.info("Open new blank browser tab");
    }

    /**
     * Create a new browser tab, go to it and go to the page by url
     */
    public void openNewTabAndNavigateToUrl(String url) {
        openNewBrowserTab();
        changeBrowserTabById(driver.getWindowHandles().size() - 1);
        navigateTo(url);
    }

    /**
     * Refresh page
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * @return string title of page
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * return to back page
     */
    public void goBackPage() {
        driver.navigate().back();
    }
}
