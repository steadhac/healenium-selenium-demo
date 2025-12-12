package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Model for Home Page (Secure Area).
 * Represents the page displayed after successful login.
 * Contains methods to interact with home page elements.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class HomePage {
    
    /** WebDriver instance */
    private WebDriver driver;
    
    /** Logout link element */
    @FindBy(linkText = "Logout")
    WebElement logoutButton;
    
    /**
     * Constructor to initialize page elements using PageFactory.
     * 
     * @param driver WebDriver instance to interact with the browser
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Clicks the logout link to log out from the application.
     */
    public void clickLogout() {
        logoutButton.click();
    }
    
    /**
     * Gets the current page title.
     * 
     * @return the page title as a String
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Gets the current page URL.
     * 
     * @return the current URL as a String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
