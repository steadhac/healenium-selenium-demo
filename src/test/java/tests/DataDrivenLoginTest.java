package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Data-driven test class for Login functionality.
 * Demonstrates parameterized testing with multiple data sets using TestNG DataProvider.
 * Each test iteration validates login behavior with different credentials.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class DataDrivenLoginTest extends BaseTest {
    
    /**
     * Data provider that supplies test data for parameterized login tests.
     * Each array element represents a test iteration with username and password.
     * 
     * @return 2D array where each row contains [username, password]
     */
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"invalidUser1", "password123"},
            {"invalidUser2", "test@123"},
            {"", "password"},
            {"username", ""}
        };
    }
    
    /**
     * Test case to verify login fails with multiple invalid credential combinations.
     * This test runs 4 times with different data sets from the data provider.
     * Validates that error message is displayed for each invalid credential combination.
     * 
     * @param username the username to test
     * @param password the password to test
     */
    @Test(dataProvider = "loginData", description = "Data-driven login test")
    public void testLoginWithMultipleInvalidData(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        
        // Attempt login
        loginPage.login(username, password);
        
        // Wait for error message
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Verify error message is displayed
        boolean errorDisplayed = loginPage.isErrorMessageDisplayed();
        Assert.assertTrue(errorDisplayed, 
            "Error message not displayed for credentials: " + username + " / " + password);
        
        System.out.println("✓ Test passed for: " + username + " / " + password);
    }
}
