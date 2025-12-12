package base;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;

/**
 * Base Test class
 * All test classes should extend this class
 * Contains setup and teardown methods
 */
public class BaseTest {
    
    protected WebDriver driver;
    protected ConfigReader config;
    
    /**
     * Setup method - runs before each test
     * Initializes browser and navigates to application
     */
    @BeforeMethod
    public void setUp() {
        // Create screenshots directory if it doesn't exist
        File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        
        // Initialize configuration
        config = new ConfigReader();
        
        // Initialize browser
        String browser = config.getBrowser();
        driver = DriverManager.getDriver(browser);
        
        // Navigate to application URL
        String url = config.getUrl();
        driver.get(url);
        
        System.out.println("Test Started - Browser: " + browser);
    }
    
    /**
     * Teardown method - runs after each test
     * Takes screenshot on failure and closes browser
     */
    @AfterMethod
   public void tearDown(ITestResult result) {
        // Take screenshot if test fails
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName();
            DriverManager.takeScreenshot(testName);
            System.out.println("Test Failed: " + testName);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test Passed: " + result.getName());
        }
        
        // Optional: Pause to see results (uncomment for debugging)
        // try { Thread.sleep(3000); } catch (InterruptedException e) { }
        
        // Close browser
        DriverManager.quitDriver();
        System.out.println("Browser Closed");
    }
}
