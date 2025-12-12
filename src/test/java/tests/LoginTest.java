package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    
    /**
     * Test case to verify successful login with valid credentials.
     * Validates that user is redirected to secure page after login.
     */
    @Test(priority = 1, description = "Verify login with valid credentials")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("secure"), 
            "Login failed - User not redirected to secure page");
        System.out.println("✓ Valid login test passed");
    }
    
    /**
     * Test case to verify login fails with invalid username.
     * Validates that error message is displayed for invalid username.
     */
    @Test(priority = 2, description = "Verify login with invalid username")
    public void testInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidUser", config.getPassword());
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message not displayed for invalid username");
        System.out.println("✓ Invalid username test passed");
    }
    
    /**
     * Test case to verify login fails with invalid password.
     * Validates that error message is displayed for wrong password.
     */
    @Test(priority = 3, description = "Verify login with invalid password")
    public void testInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), "wrongPassword");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message not displayed for invalid password");
        System.out.println("✓ Invalid password test passed");
    }
    
    /**
     * Test case to verify login fails with empty credentials.
     * Validates that error message is displayed when both fields are empty.
     */
    @Test(priority = 4, description = "Verify login with empty credentials")
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error message not displayed for empty credentials");
        System.out.println("✓ Empty credentials test passed");
    }
}
