package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Model for Login Page.
 * Contains locators and methods to interact with the login page elements.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class LoginPage {
    
    /** Username input field */
    @FindBy(id = "username")
    WebElement usernameField;
    
    /** Password input field */
    @FindBy(id = "password")
    WebElement passwordField;
    
    /** Login submit button */
    @FindBy(id = "login")
    WebElement loginButton;
    
    /** Error message element displayed on login failure */
    @FindBy(className = "error-message")
    WebElement errorMessage;
    
    /**
     * Constructor to initialize page elements using PageFactory.
     * 
     * @param driver WebDriver instance to interact with the browser
     */
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Performs login operation with provided credentials.
     * Clears fields, enters username and password, then clicks login button.
     * 
     * @param username the username to enter
     * @param password the password to enter
     */
    public void login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
    
    /**
     * Checks if error message is displayed on the page.
     * 
     * @return true if error message is visible, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
