package base.elements;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Wrapper class for file uploading
 */
@Slf4j
public class FileUploadWindow extends Element {
    /**
     * *! IMPORTANT! * Works only if the element is <input type = 'file'>
     *
     * @param locator    - element locator by tap, to which the file is loaded
     * @param pathToFile - path to file
     */
    public void uploadFileFullPath(By locator, String pathToFile) {
        try {
            WebElement addFile = driver.findElement(locator);
            LocalFileDetector detector = new LocalFileDetector();
            File file = detector.getLocalFile(pathToFile);
            ((RemoteWebElement) addFile).setFileDetector(detector);
            addFile.sendKeys(file.getAbsolutePath());
            attachXlsx(pathToFile);
        } catch (Exception e) {
            log.error("Exception while file uploading" + e.getMessage());
        }
    }

    @Attachment(value = "uploaded_file", type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", fileExtension = "xlsx")
    public byte[] attachXlsx(String path) throws IOException {
        File file = new File(path);
        return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }
}