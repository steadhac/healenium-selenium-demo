package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration reader utility class.
 * Reads and provides access to test configuration from properties file.
 * Centralizes configuration management for test execution parameters.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class ConfigReader {
    
    /** Properties object holding configuration key-value pairs */
    private final Properties properties;
    
    /**
     * Constructor that loads configuration from config.properties file.
     * Properties file is located at src/test/resources/config.properties.
     */
    public ConfigReader() {
        properties = new Properties();
        try {
            String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println("Config file not found: " + e.getMessage());
        }
    }
    
    /**
     * Retrieves property value by key.
     * 
     * @param key property key
     * @return property value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Retrieves browser name from configuration.
     * 
     * @return browser name (chrome, firefox, edge)
     */
    public String getBrowser() {
        return properties.getProperty("browser");
    }
    
    /**
     * Retrieves application URL from configuration.
     * 
     * @return application URL
     */
    public String getUrl() {
        return properties.getProperty("url");
    }
    
    /**
     * Retrieves username from configuration.
     * 
     * @return username for login
     */
    public String getUsername() {
        return properties.getProperty("username");
    }
    
    /**
     * Retrieves password from configuration.
     * 
     * @return password for login
     */
    public String getPassword() {
        return properties.getProperty("password");
    }
}
