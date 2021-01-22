package base.elements;

import base.driver.InitialDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

import static base.util.BaseConstants.LOADER_TIME_WAIT_FOR_DOWNLOAD;

/**
 * A class that handles downloading files using Selenium and saves along the way
 * which is stored in the PROJECT_DOWNLOAD_DIRECTORY variable
 * Also contains methods whether the given file exists
 */
@Slf4j
public class FileDownloadWindow extends Element {
    /**
     * Instance driver to wait for file download
     */
    private final WebDriver driver = InitialDriver.getInstance().getDriver();
    /**
     * Field containing the path to the project in the system
     */
    public static final String PROJECT_DIR_PATH = System.getProperty("user.dir");
    /**
     * Field containing the path to the target folder in the project root
     */
    public static final String PROJECT_BUILD_DIRECTORY = PROJECT_DIR_PATH + File.separator + "target";
    /**
     * Field containing the path to the folder where files are downloaded inside the target folder
     */
    public static final String PROJECT_DOWNLOAD_DIRECTORY = PROJECT_BUILD_DIRECTORY + File.separator + "downloaded";

    /**
     * Method for downloading a file to a folder along the PROJECT_DOWNLOAD_DIRECTORY path
     *
     * @param locator - element locator, by tap on which the file download window will pop up
     */
    public void downloadFile(By locator) {
        createDownloadFolder();
        new CustomWaiters().waitForLoaderNotToBePresent();
        new Button().click(locator);
        new CustomWaiters().waitForLoaderForFileDownload();
        waitForFileToBeDownloaded();
    }

    /**
     * Method to customize Chrome profile download file
     *
     * @return mapOfPreferences - map with customized properties
     */
    public static HashMap<String, Object> getFileDownloadOptions() {
        HashMap<String, Object> mapOfPreferences = new HashMap<>();
        mapOfPreferences.put("download.default_directory", PROJECT_DOWNLOAD_DIRECTORY);
        mapOfPreferences.put("profile.default_content_settings_popups", 0);
        return mapOfPreferences;
    }

    /**
     * Method for configuring Firefox profile of file download
     *
     * @return firefoxProfile - configured profile
     */
    public static FirefoxProfile getGeckoDownloadProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", PROJECT_DOWNLOAD_DIRECTORY);
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        profile.setPreference("browser.helperApps.neverAsk.openFile", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.openFile", "");
        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", true);
        return profile;
    }

    /**
     * Create a folder along the path PROJECT_DOWNLOAD_DIRECTORY to store files
     */
    public void createDownloadFolder() {
        log.info("Project folder path:\"" + PROJECT_DIR_PATH + "\"");
        log.info("Project build folder path:\"" + PROJECT_BUILD_DIRECTORY + "\"");
        log.info("Project download folder path:\"" + PROJECT_DOWNLOAD_DIRECTORY + "\"");
        if (!isDownloadFolderExists()) {
            try {
                Files.createDirectories(Paths.get(PROJECT_DOWNLOAD_DIRECTORY));
                log.info("Folder for downloaded files created. Folder path:\"" + PROJECT_DOWNLOAD_DIRECTORY + "\"");
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        }
    }

    /**
     * Проверка наличия папки по пути PROJECT_DOWNLOAD_DIRECTORY для хранения файлов
     * Проверка необходима, чтобы лишний раз не создавать папку
     */
    private boolean isDownloadFolderExists() {
        return new File(PROJECT_DOWNLOAD_DIRECTORY).exists();
    }

    /**
     * Clearing the PROJECT_DOWNLOAD_DIRECTORY folder from files
     *
     * @return returns true if the folder is empty, false if not (shouldn't be)
     */
    public boolean clearDownloadFolder() {
        log.info("Directory purge: " + PROJECT_DOWNLOAD_DIRECTORY);
        File downloadDirectory = new File(PROJECT_DOWNLOAD_DIRECTORY);
        if (isDownloadFolderExists()) {
            purgeDirectory(downloadDirectory);
        }
        return Objects.requireNonNull(downloadDirectory.listFiles()).length <= 0;
    }

    /**
     * File cleaning directory passed as parameter
     *
     * @param dir directory where you want to delete all files
     */
    private void purgeDirectory(File dir) {
        log.info("Directory purge: " + dir.getAbsolutePath());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory())
                purgeDirectory(file);
            log.info("File delete: " + file.getName());
            file.delete();
        }
        log.info("Directory is purged: " + dir.getAbsolutePath());
    }

    /**
     * File upload pending
     */
    private void waitForFileToBeDownloaded() {
        log.info("Wait for file to be downloaded...");
        if (isDownloadFolderExists()) {
            WebDriverWait wait = new WebDriverWait(driver, LOADER_TIME_WAIT_FOR_DOWNLOAD);
            wait.until((ExpectedCondition<Boolean>) driver -> {
                if (isDownloadFolderNotEmpty()) {
                    log.info("Download directory: " + PROJECT_DOWNLOAD_DIRECTORY + "is not empty");
                    return Boolean.TRUE;
                }
                return null;
            });
        } else log.warn("Try to check file in UNCREATED download directory");
    }

    /**
     * Check that the download folder is not empty
     */
    public boolean isDownloadFolderNotEmpty() {
        File downloadDir = new File(PROJECT_DOWNLOAD_DIRECTORY);
        log.info("Check if download folder is not empty... " + (Objects.requireNonNull(downloadDir.listFiles()).length > 0));
        return Objects.requireNonNull(downloadDir.listFiles()).length > 0;
    }
}
