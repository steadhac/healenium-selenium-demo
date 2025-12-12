package utils;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WebDriver management utility class.
 * Manages WebDriver lifecycle including initialization, configuration, and cleanup.
 * Integrates Healenium self-healing capabilities by wrapping drivers.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class DriverManager {
    
    /** Singleton WebDriver instance wrapped with Healenium */
    private static WebDriver driver;
    
    /**
     * Initializes and returns a WebDriver instance for specified browser.
     * Creates browser-specific driver with appropriate options and wraps it with Healenium.
     * Implements singleton pattern to reuse same driver instance.
     * 
     * @param browser name of browser (chrome, firefox, edge)
     * @return WebDriver instance wrapped with Healenium self-healing capabilities
     */
    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            WebDriver delegate;
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-notifications");
                    delegate = new ChromeDriver(options);
                    break;
                    
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    delegate = new FirefoxDriver();
                    delegate.manage().window().maximize();
                    break;
                    
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    delegate = new EdgeDriver();
                    delegate.manage().window().maximize();
                    break;
                    
                default:
                    System.out.println("Browser not supported. Launching Chrome...");
                    WebDriverManager.chromedriver().setup();
                    delegate = new ChromeDriver();
                    delegate.manage().window().maximize();
            }
            
            // Wrap with Healenium SelfHealingDriver
            driver = SelfHealingDriver.create(delegate);
        }
        return driver;
    }
    
    /**
     * Quits the browser and closes all windows.
     * Resets the driver instance to null for clean state.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    /**
     * Captures screenshot and saves to screenshots directory.
     * Handles SelfHealingDriver by extracting delegate for screenshot capability.
     * 
     * @param testName name of the test for screenshot filename
     * @return path where screenshot is saved
     */
    public static String takeScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";
        
        // Handle SelfHealingDriver - get the delegate driver for screenshots
        WebDriver screenshotDriver = driver;
        if (driver instanceof SelfHealingDriver) {
            screenshotDriver = ((SelfHealingDriver) driver).getDelegate();
        }
        
        File srcFile = ((TakesScreenshot) screenshotDriver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
        
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
        
        return screenshotPath;
    }
}
