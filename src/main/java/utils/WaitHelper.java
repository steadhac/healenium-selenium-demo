package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Wait utility class for handling WebDriver synchronization.
 * Provides reusable explicit wait methods to handle dynamic web elements.
 * Improves test reliability by properly synchronizing with page loads and element states.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class WaitHelper {
    
    /** WebDriver instance for browser control */
    private final WebDriver driver;
    
    /** WebDriverWait instance with default 10 second timeout */
    private final WebDriverWait wait;
    
    /**
     * Constructor that initializes WaitHelper with default timeout.
     * 
     * @param driver WebDriver instance to apply waits to
     */
    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Waits for element to become visible on the page.
     * 
     * @param element WebElement to wait for
     */
    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Waits for element to become clickable (visible and enabled).
     * 
     * @param element WebElement to wait for
     */
    public void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Waits for page title to contain specific text.
     * 
     * @param title expected title text
     */
    public void waitForTitle(String title) {
        wait.until(ExpectedConditions.titleContains(title));
    }
    
    /**
     * Waits for element with custom timeout.
     * 
     * @param element WebElement to wait for
     * @param timeoutInSeconds custom wait timeout in seconds
     */
    public void waitForElement(WebElement element, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Sets implicit wait timeout for all element searches.
     * 
     * @param timeoutInSeconds implicit wait timeout in seconds
     */
    public void setImplicitWait(int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
    }
}
