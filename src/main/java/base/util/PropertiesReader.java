package base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Class for reading properties from configuration files
 *
 * @see BaseConstants - use here
 */
@Slf4j
public class PropertiesReader {
    /**
     * Object properties
     */
    private Properties properties;
    /**
     * Configuration file for config elements
     */
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
    /**
     * Configuration file for config application
     */
    private static final String APPLICATION_PROPERTIES = "src/main/resources/application.properties";
    /**
     * Configuration file for browser config
     */
    private static final String BROWSER_PROPERTIES = "target/classes/browser.properties";

    public PropertiesReader() {
        properties = readPropertiesFromFile(CONFIG_FILE_PATH);
    }

    /**
     * @return getting application properties
     */
    public Properties application() {
        return readPropertiesFromFile(APPLICATION_PROPERTIES);
    }

    /**
     * @return getting browser properties
     */
    public Properties browserProperties() {
        return readPropertiesFromFile(BROWSER_PROPERTIES);
    }

    /**
     * Method for reading properties from a file along the path
     *
     * @param filePath - path to the file with properties
     * @return - property object
     */
    private Properties readPropertiesFromFile(String filePath) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new InputStreamReader(new FileInputStream(new File(filePath)), StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
        return properties;
    }

    /**
     * Getting the configuration property of an element by key
     */
    public String get(String key) {
        return properties.getProperty(key);
    }
}

