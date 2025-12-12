package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

/**
 * Test class for Home Page functionality.
 * Contains test cases to verify home page elements and logout functionality.
 * 
 * @author Carolina Steadham
 * @version 1.0
 */
public class HomePageTest extends BaseTest {
    
    /**
     * Helper method to perform login before testing home page.
     * Logs in with valid credentials.
     */
    private void performLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());
    }
    
    /**
     * Test case to verify home page displays correctly after login.
     * Validates page title and URL contain expected values.
     */
    @Test(priority = 1, description = "Verify home page displays correctly")
    public void testHomePageDisplay() {
        performLogin();
        HomePage homePage = new HomePage(driver);
        
        String pageTitle = homePage.getPageTitle();
        Assert.assertNotNull(pageTitle, "Page title is null");
        
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("secure"), 
            "Home page URL does not contain 'secure'");
        
        System.out.println("✓ Home page display test passed");
    }
    
    /**
     * Test case to verify logout functionality.
     * Validates that user is redirected away from secure area after logout.
     */
    @Test(priority = 2, description = "Verify logout functionality")
    public void testLogoutFunctionality() {
        performLogin();
        HomePage homePage = new HomePage(driver);
        
        homePage.clickLogout();
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertFalse(currentUrl.contains("secure"), 
            "Logout failed - User still on secure page");
        
        System.out.println("✓ Logout functionality test passed");
    }
    
    /**
     * Test case to verify page title after successful login.
     * Validates that page title is not empty.
     */
    @Test(priority = 3, description = "Verify page title after login")
    public void testPageTitle() {
        performLogin();
        HomePage homePage = new HomePage(driver);
        
        String title = homePage.getPageTitle();
        Assert.assertFalse(title.isEmpty(), "Page title is empty");
        
        System.out.println("Current Page Title: " + title);
        System.out.println("✓ Page title test passed");
    }
}
