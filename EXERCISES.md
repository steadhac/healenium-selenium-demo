# Practice Exercises - Learn by Doing

## 📚 How to Use This Guide

Each exercise builds on previous concepts. Complete them in order for best results.

- ⭐ = Beginner
- ⭐⭐ = Intermediate  
- ⭐⭐⭐ = Advanced

**For each exercise:**
1. Read the requirements
2. Try to solve it yourself first
3. Check hints if stuck
4. Compare with provided solution
5. Run tests to verify

**📂 Additional Resources:**
- [Test Plan](docs/TEST_PLAN.md) - Overall test strategy
- [Test Cases](docs/TEST_CASES.md) - Detailed test documentation
- [Traceability Matrix](docs/TRACEABILITY_MATRIX.md) - Requirements mapping
- [Bug Reports](docs/BUG_REPORTS.md) - Bug tracking
- [Clean Code Guide](docs/CLEAN_CODE_GUIDE.md) - Best practices
- [Debugging Guide](docs/DEBUGGING_GUIDE.md) - Troubleshooting tips
- [Contributing](docs/CONTRIBUTING.md) - How to contribute

---

## 🎯 Section 1: Fixing Locators (Beginner)

### Exercise 1.1: Fix the Login Button ⭐

**Goal:** Fix the failing login test by updating the locator.

**Current Problem:**
```java
@FindBy(id = "login")  // This locator doesn't work!
WebElement loginButton;
```

**Requirements:**
1. Open `src/main/java/pages/LoginPage.java`
2. Find the `loginButton` element definition
3. Update the locator to make it work with herokuapp.com
4. Run the test: `mvn test -Dtest=LoginTest#testValidLogin`

<details>
<summary>💡 Hint 1</summary>

Open https://the-internet.herokuapp.com/login in your browser, press F12, and inspect the login button. What attributes does it have?
</details>

<details>
<summary>💡 Hint 2</summary>

The button has `type="submit"`. You can use CSS selector: `button[type='submit']`
</details>

<details>
<summary>✅ Solution</summary>

```java
@FindBy(css = "button[type='submit']")
WebElement loginButton;
```

**Why this works:**
- The button doesn't have an `id` attribute
- It has `type="submit"` which is unique on the page
- CSS selector `button[type='submit']` finds it reliably
</details>

**Success Criteria:**
- [ ] Test `testValidLogin` passes
- [ ] No "Element not found" errors
- [ ] Browser successfully clicks the button

---

### Exercise 1.2: Fix the Error Message ⭐

**Goal:** Update the error message locator so invalid login tests pass.

**Current Problem:**
```java
@FindBy(className = "error-message")  // Wrong class name!
WebElement errorMessage;
```

**Requirements:**
1. In `LoginPage.java`, find the `errorMessage` element
2. Update to use the correct locator
3. Run: `mvn test -Dtest=LoginTest#testInvalidUsername`

<details>
<summary>💡 Hint</summary>

The error message has `id="flash"`. Use that instead of className.
</details>

<details>
<summary>✅ Solution</summary>

```java
@FindBy(id = "flash")
WebElement errorMessage;
```

**Alternative solutions:**
```java
// Also works:
@FindBy(css = "#flash")
@FindBy(xpath = "//div[@id='flash']")
```
</details>

**Success Criteria:**
- [ ] Test `testInvalidUsername` passes
- [ ] Error message is correctly detected
- [ ] Assertion "Error message should be displayed" passes

---

### Exercise 1.3: Fix the Logout Button ⭐

**Goal:** Fix the logout functionality in HomePage.

**Current Problem:**
```java
@FindBy(id = "logout")  // No such ID exists!
WebElement logoutButton;
```

**Requirements:**
1. Open `src/main/java/pages/HomePage.java`
2. Update the `logoutButton` locator
3. Run: `mvn test -Dtest=HomePageTest#testLogoutFunctionality`

<details>
<summary>💡 Hint</summary>

The logout is an `<a>` tag (link) with text "Logout". Use `linkText` locator.
</details>

<details>
<summary>✅ Solution</summary>

```java
@FindBy(linkText = "Logout")
WebElement logoutButton;

// Alternative:
@FindBy(css = "a[href='/logout']")
WebElement logoutButton;
```
</details>

**Success Criteria:**
- [ ] Logout test passes
- [ ] User is redirected back to login page
- [ ] No "NoSuchElementException" errors

---

## 🔧 Section 2: Creating New Page Objects (Intermediate)

### Exercise 2.1: Create a Registration Page Object ⭐⭐

**Goal:** Practice building a complete page object from scratch.

**Scenario:** Create a page object for a registration form with:
- Username field (id="username")
- Email field (id="email")
- Password field (id="password")
- Confirm password field (id="confirm-password")
- Register button (type="submit")

**Requirements:**
1. Create new file: `src/main/java/pages/RegistrationPage.java`
2. Define all elements using `@FindBy`
3. Create methods:
   - `enterUsername(String username)`
   - `enterEmail(String email)`
   - `enterPassword(String password)`
   - `enterConfirmPassword(String password)`
   - `clickRegister()`
   - `register(String username, String email, String password)`

<details>
<summary>✅ Solution</summary>

```java
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    
    private WebDriver driver;
    
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "confirm-password")
    private WebElement confirmPasswordField;
    
    @FindBy(css = "button[type='submit']")
    private WebElement registerButton;
    
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    
    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }
    
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    public void enterConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }
    
    public void clickRegister() {
        registerButton.click();
    }
    
    public void register(String username, String email, String password) {
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
    }
}
```
</details>

---

### Exercise 2.2: Add Dynamic Dropdown Handling ⭐⭐

**Goal:** Learn to handle dynamic dropdowns.

**Requirements:**
Add a method to `LoginPage.java` to select country from a dropdown:

```html
<select id="country">
    <option value="us">United States</option>
    <option value="uk">United Kingdom</option>
    <option value="ca">Canada</option>
</select>
```

**Tasks:**
1. Add the dropdown element to LoginPage
2. Create method `selectCountry(String countryValue)`
3. Use Selenium's `Select` class

<details>
<summary>✅ Solution</summary>

```java
import org.openqa.selenium.support.ui.Select;

// In LoginPage class:
@FindBy(id = "country")
private WebElement countryDropdown;

public void selectCountry(String countryValue) {
    Select select = new Select(countryDropdown);
    select.selectByValue(countryValue);
}

// Usage:
loginPage.selectCountry("us");
```
</details>

---

## ✍️ Section 3: Writing Test Cases (Intermediate)

### Exercise 3.1: Write a Negative Test Case ⭐⭐

**Goal:** Practice writing comprehensive test cases.

**Requirements:**
Create a new test in `LoginTest.java` that verifies:
1. User enters valid username but leaves password empty
2. Click login
3. Verify appropriate error message is displayed
4. Verify user remains on login page

<details>
<summary>✅ Solution</summary>

```java
@Test(priority = 5, description = "Verify login fails with empty password")
public void testLoginWithEmptyPassword() {
    // Arrange
    LoginPage loginPage = new LoginPage(driver);
    String validUsername = config.getUsername();
    
    // Act
    loginPage.enterUsername(validUsername);
    loginPage.enterPassword("");
    loginPage.clickLoginButton();
    
    // Wait for error
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
    // Assert
    Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
        "Error message should be displayed when password is empty");
    
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("login"), 
        "User should remain on login page when password is empty");
}
```
</details>

---

### Exercise 3.2: Implement Data-Driven Testing ⭐⭐

**Goal:** Create a data-driven test with multiple scenarios.

**Requirements:**
1. Create a new test class: `PasswordValidationTest.java`
2. Use `@DataProvider` to test multiple invalid passwords
3. Test at least 5 invalid password scenarios

**Test Data:**
- Empty password: ""
- Too short: "123"
- No numbers: "Password"
- No uppercase: "password123"
- No special chars: "Password123"

<details>
<summary>✅ Solution</summary>

```java
package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class PasswordValidationTest extends BaseTest {
    
    @DataProvider(name = "invalidPasswords")
    public Object[][] getInvalidPasswords() {
        return new Object[][] {
            {"", "Password cannot be empty"},
            {"123", "Password too short"},
            {"Password", "Password must contain numbers"},
            {"password123", "Password must contain uppercase"},
            {"Password123", "Password must contain special characters"}
        };
    }
    
    @Test(dataProvider = "invalidPasswords", 
          description = "Verify password validation rules")
    public void testInvalidPasswordValidation(String password, String errorReason) {
        LoginPage loginPage = new LoginPage(driver);
        
        // Act
        loginPage.login(config.getUsername(), password);
        
        // Wait
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Assert
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), 
            "Error should be displayed for: " + errorReason);
        
        System.out.println("✓ Test passed for: " + errorReason);
    }
}
```
</details>

---

## 🚀 Section 4: Advanced Concepts (Advanced)

### Exercise 4.1: Implement Explicit Waits ⭐⭐⭐

**Goal:** Replace Thread.sleep with proper explicit waits.

**Current Problem:**
```java
Thread.sleep(2000);  // Bad practice!
```

**Requirements:**
1. Open `WaitHelper.java`
2. Create a new method `waitForElementToBeClickable(WebElement element)`
3. Update `LoginTest.java` to use explicit waits instead of Thread.sleep

<details>
<summary>✅ Solution</summary>

```java
// In WaitHelper.java
public void waitForElementToBeClickable(WebElement element, int timeoutSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    wait.until(ExpectedConditions.elementToBeClickable(element));
}

public void waitForElementToBeVisible(WebElement element, int timeoutSeconds) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    wait.until(ExpectedConditions.visibilityOf(element));
}

// In LoginTest.java - Replace Thread.sleep with:
WaitHelper waitHelper = new WaitHelper(driver);
waitHelper.waitForElementToBeVisible(loginPage.getErrorMessageElement(), 10);
```
</details>

---

### Exercise 4.2: Create a Fluent Page Object ⭐⭐⭐

**Goal:** Implement method chaining for cleaner test code.

**Requirements:**
Modify `LoginPage.java` methods to return `this` for chaining.

**Before:**
```java
loginPage.enterUsername("user");
loginPage.enterPassword("pass");
loginPage.clickLoginButton();
```

**After:**
```java
loginPage.enterUsername("user")
        .enterPassword("pass")
        .clickLoginButton();
```

<details>
<summary>✅ Solution</summary>

```java
// Modify methods in LoginPage.java to return 'this'
public LoginPage enterUsername(String username) {
    usernameField.clear();
    usernameField.sendKeys(username);
    return this;
}

public LoginPage enterPassword(String password) {
    passwordField.clear();
    passwordField.sendKeys(password);
    return this;
}

public HomePage clickLoginButton() {
    loginButton.click();
    return new HomePage(driver);  // Returns next page object
}

// Usage in test:
HomePage homePage = loginPage
    .enterUsername(config.getUsername())
    .enterPassword(config.getPassword())
    .clickLoginButton();
```
</details>

---

### Exercise 4.3: Implement Screenshot on Failure ⭐⭐⭐

**Goal:** Enhance the screenshot utility to capture full page screenshots.

**Requirements:**
1. Modify `DriverManager.takeScreenshot()` to support full page screenshots
2. Add a method to capture element screenshots
3. Integrate with test failure listener

<details>
<summary>✅ Solution</summary>

```java
// In DriverManager.java
public static String takeFullPageScreenshot(String testName) {
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String screenshotName = testName + "_fullpage_" + timestamp + ".png";
    
    // For Chrome
    ChromeDriver chromeDriver = (ChromeDriver) driver;
    String base64Screenshot = chromeDriver.getScreenshotAs(OutputType.BASE64);
    
    String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
    
    try {
        byte[] decodedImg = Base64.getDecoder().decode(base64Screenshot);
        FileUtils.writeByteArrayToFile(new File(screenshotPath), decodedImg);
        System.out.println("Full page screenshot saved: " + screenshotPath);
    } catch (IOException e) {
        System.out.println("Failed to save screenshot: " + e.getMessage());
    }
    
    return screenshotPath;
}

public static String takeElementScreenshot(WebElement element, String testName) {
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String screenshotName = testName + "_element_" + timestamp + ".png";
    
    File srcFile = element.getScreenshotAs(OutputType.FILE);
    String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
    
    try {
        FileUtils.copyFile(srcFile, new File(screenshotPath));
        System.out.println("Element screenshot saved: " + screenshotPath);
    } catch (IOException e) {
        System.out.println("Failed to save screenshot: " + e.getMessage());
    }
    
    return screenshotPath;
}
```
</details>

---

## 🎓 Section 5: Challenge Projects (Advanced)

### Challenge 5.1: Parallel Test Execution ⭐⭐⭐

**Goal:** Configure tests to run in parallel.

**Requirements:**
1. Update `testng.xml` to enable parallel execution
2. Make tests thread-safe
3. Run tests on multiple threads

<details>
<summary>✅ Solution</summary>

```xml
<!-- testng.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Parallel Test Suite" parallel="methods" thread-count="3">
    
    <test name="Login Tests">
        <classes>
            <class name="tests.LoginTest"/>
        </classes>
    </test>
    
</suite>
```

**Make BaseTest thread-safe:**
```java
public class BaseTest {
    
    protected ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    protected ConfigReader config;
    
    @BeforeMethod
    public void setUp() {
        config = new ConfigReader();
        String browser = config.getBrowser();
        
        WebDriver driver = DriverManager.getDriver(browser);
        threadDriver.set(driver);
        
        getDriver().get(config.getUrl());
    }
    
    protected WebDriver getDriver() {
        return threadDriver.get();
    }
    
    @AfterMethod
    public void tearDown() {
        getDriver().quit();
        threadDriver.remove();
    }
}
```
</details>

---

### Challenge 5.2: API Testing Integration ⭐⭐⭐

**Goal:** Add REST API testing to your framework.

**Requirements:**
1. Add RestAssured dependency to pom.xml
2. Create an API test class
3. Validate API response before UI test

<details>
<summary>✅ Solution</summary>

```xml
<!-- Add to pom.xml -->
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.2</version>
    <scope>test</scope>
</dependency>
```

```java
// Create ApiTest.java
package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    
    @Test
    public void testApiEndpoint() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        
        Response response = given()
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .body("name", notNullValue())
            .extract().response();
        
        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
```
</details>

---

---

## 🔧 Section 6: Self-Healing Locators (Advanced) ⭐⭐⭐

### Exercise 6.1: Implement Self-Healing Element Finder ⭐⭐⭐

**Goal:** Implement the `findElement()` method in `SelfHealingElement.java` to automatically try fallback locators when the primary locator fails.

**Why This Matters:**
- Real-world UI changes constantly
- Self-healing = tests auto-recover
- Production-grade automation pattern
- Shows advanced Selenium skills

**Current Problem:**
```java
// When locator breaks, test fails immediately
driver.findElement(By.id("login")).click();  // ❌ NoSuchElementException
```

**Your Solution:**
```java
// Self-healing tries multiple locators automatically
healer.findElement("Login Button",
    By.id("login"),                      // Primary (might fail)
    By.cssSelector("button[type='submit']"),  // Fallback 1
    By.xpath("//button[@type='submit']")      // Fallback 2
);
// ✅ Auto-recovers if any locator works!
```

**Requirements:**
1. Open `src/main/java/utils/SelfHealingElement.java`
2. Find the `findElement()` method with TODOs
3. Implement the self-healing logic (loop through locators with try-catch)
4. Add appropriate logging (success, failure, warnings)
5. Test with: `mvn test -Dtest=LoginTest#testSelfHealingLoginButton`

---

#### 🔵 Level 1: Conceptual Hints (Gradually More Detailed)

<details>
<summary>💡 Hint 1: What's the core problem we're solving?</summary>

When `driver.findElement(By.id("login"))` fails, Selenium throws `NoSuchElementException`.

**Your task:** Catch this exception and try a different locator instead of letting the test fail.

**Think about:**
- How do you catch exceptions in Java?
- How do you try multiple things in sequence?
- When should you give up and throw an exception?
</details>

<details>
<summary>💡 Hint 2: What data structure are we working with?</summary>

The method signature uses **varargs**:
```java
public WebElement findElement(String elementName, By... locators)
```

In Java, `By... locators` is treated as an **array** inside the method.

**Think about:**
- How do you loop through an array in Java?
- How can you track which array index you're on?
- How do you know if you're on the last element?
</details>

<details>
<summary>💡 Hint 3: What's the success vs failure logic?</summary>

**On Success:**
- Element found → Return it immediately
- Log which locator worked
- Warn if it was a fallback (not the primary locator)

**On Failure:**
- Catch `NoSuchElementException`
- Log that this locator failed
- Try next locator
- If all locators fail → Throw descriptive exception

**Think about:** What Java construct handles "try this, if it fails do that"?
</details>

---

#### 🟢 Level 2: Directional Hints

<details>
<summary>💡 Hint 4: Show me the basic loop structure</summary>

```java
for (By locator : locators) {
    // Try to find element using this locator
    // If successful, return the element
    // If failed, continue to next locator
}

// If we're here, all locators failed
// Throw exception
```

**Next question:** What goes inside the loop to "try" finding the element?
</details>

<details>
<summary>💡 Hint 5: Show me the try-catch pattern</summary>

```java
for (By locator : locators) {
    try {
        // Attempt to find element
        WebElement element = driver.findElement(locator);
        // If we reach here, it worked!
        return element;
    } catch (NoSuchElementException e) {
        // This locator failed, try next one
        // Don't throw yet - we have more locators to try
    }
}
```

**Next question:** How do you know which attempt number you're on for logging?
</details>

<details>
<summary>💡 Hint 6: How do I track the loop index?</summary>

Use a traditional `for` loop with an index:

```java
for (int i = 0; i < locators.length; i++) {
    By locator = locators[i];
    
    // Now you can log: 
    // "Trying locator " + (i+1) + " of " + locators.length
    
    // You can also detect if this is the last locator:
    // if (i == locators.length - 1) { ... }
}
```

**Next question:** When should you log a WARNING about self-healing activation?
</details>

<details>
<summary>💡 Hint 7: When is self-healing "activated"?</summary>

Self-healing is activated when a **fallback locator** works, meaning the primary locator failed.

```java
if (i > 0) {
    // i > 0 means we're NOT on the first (primary) locator
    // This means self-healing kicked in!
    logger.warn("⚠️ Self-healing activated! Using fallback locator #{}", i + 1);
} else {
    // i == 0 means primary locator worked normally
    logger.debug("✅ Primary locator worked");
}
```
</details>

---

#### 🟡 Level 3: Implementation Hints

<details>
<summary>💡 Hint 8: Complete method skeleton</summary>

```java
public WebElement findElement(String elementName, By... locators) {
    logger.info("Attempting to find element: {}", elementName);
    
    for (int i = 0; i < locators.length; i++) {
        By locator = locators[i];
        
        try {
            logger.debug("Trying locator {}/{}: {}", i + 1, locators.length, locator);
            WebElement element = driver.findElement(locator);
            
            // TODO: Log success message
            // TODO: If i > 0, log WARNING about self-healing
            // TODO: Return the element
            
        } catch (NoSuchElementException e) {
            logger.debug("❌ Locator #{} failed", i + 1);
            
            // TODO: If this is the last locator (i == locators.length - 1),
            //       throw a descriptive NoSuchElementException
        }
    }
    
    // TODO: Throw exception if somehow we get here
}
```

**Fill in the TODOs!**
</details>

<details>
<summary>💡 Hint 9: How to handle the last locator failing?</summary>

Inside the `catch` block:

```java
catch (NoSuchElementException e) {
    logger.debug("❌ Locator #{} failed: {}", i + 1, locator);
    
    if (i == locators.length - 1) {
        // This was the last locator - all failed!
        logger.error("🚨 All {} locators failed for element '{}'", 
                     locators.length, elementName);
        
        throw new NoSuchElementException(
            String.format("Element '%s' not found after trying %d locators: %s", 
                         elementName, locators.length, Arrays.toString(locators))
        );
    }
    // Otherwise, loop continues to next iteration
}
```

**Note:** Need to import `java.util.Arrays` for `Arrays.toString()`
</details>

<details>
<summary>💡 Hint 10: Complete solution (try before viewing!)</summary>

```java
public WebElement findElement(String elementName, By... locators) {
    logger.info("Attempting to find element: {}", elementName);
    
    for (int i = 0; i < locators.length; i++) {
        By locator = locators[i];
        try {
            logger.debug("Trying locator {}/{}: {}", i + 1, locators.length, locator);
            WebElement element = driver.findElement(locator);
            
            if (i > 0) {
                logger.warn("⚠️ Self-healing activated! Element '{}' found using fallback locator #{}: {}", 
                           elementName, i + 1, locator);
                logger.warn("💡 Consider updating the primary locator in your Page Object");
            } else {
                logger.debug("✅ Element '{}' found using primary locator: {}", elementName, locator);
            }
            
            return element;
            
        } catch (NoSuchElementException e) {
            logger.debug("❌ Locator #{} failed: {}", i + 1, locator);
            if (i == locators.length - 1) {
                logger.error("🚨 Self-healing failed! Element '{}' not found using any of {} locators", 
                            elementName, locators.length);
                throw new NoSuchElementException(
                    String.format("Element '%s' not found after trying %d locator strategies: %s", 
                                 elementName, locators.length, Arrays.toString(locators))
                );
            }
        }
    }
    
    throw new NoSuchElementException("Unexpected error in self-healing logic");
}
```

**Now uncomment the solution in the actual file and test it!**
</details>

---

#### Testing Your Implementation

**Quick Test:**
```bash
# Create a simple test in LoginTest.java
@Test
public void testSelfHealingLoginButton() {
    SelfHealingElement healer = new SelfHealingElement(driver);
    
    WebElement button = healer.findElement("Login Button",
        By.id("login"),                      // ❌ Will fail (wrong)
        By.cssSelector("button[type='submit']")  // ✅ Will work!
    );
    
    Assert.assertNotNull(button);
}
```

**Expected Console Output:**
```
[INFO] Attempting to find element: Login Button
[DEBUG] Trying locator 1/2: By.id: login
[DEBUG] ❌ Locator #1 failed: By.id: login
[DEBUG] Trying locator 2/2: By.cssSelector: button[type='submit']
[WARN] ⚠️ Self-healing activated! Element 'Login Button' found using fallback locator #2
```

---

### Exercise 6.2: Implement clickElement() (Bonus) ⭐⭐⭐

**Goal:** Implement the `clickElement()` method with retry logic for `StaleElementReferenceException`.

**Requirements:**
1. Use `findElement()` to get the element
2. Click it
3. If `StaleElementReferenceException` occurs, retry up to 3 times
4. Add appropriate logging

<details>
<summary>💡 Hint: Click with retry pattern</summary>

```java
public void clickElement(String elementName, By... locators) {
    int maxRetries = 3;
    
    for (int attempt = 1; attempt <= maxRetries; attempt++) {
        try {
            WebElement element = findElement(elementName, locators);
            element.click();
            logger.info("✅ Clicked on element: {}", elementName);
            return;  // Success!
            
        } catch (StaleElementReferenceException e) {
            if (attempt == maxRetries) {
                throw new RuntimeException("Failed to click after " + maxRetries + " attempts", e);
            }
            logger.warn("⚠️ Stale element, retrying... ({}/{})", attempt, maxRetries);
            Thread.sleep(500);  // Wait before retry
        }
    }
}
```
</details>

---

### Exercise 6.3: Implement sendKeys() (Bonus) ⭐⭐⭐

**Goal:** Similar to `clickElement()`, but for entering text.

**Requirements:**
1. Find element with self-healing
2. Clear existing text
3. Send keys
4. Retry on stale element

<details>
<summary>💡 Hint: SendKeys implementation</summary>

```java
public void sendKeys(String elementName, String text, By... locators) {
    int maxRetries = 3;
    
    for (int attempt = 1; attempt <= maxRetries; attempt++) {
        try {
            WebElement element = findElement(elementName, locators);
            element.clear();
            element.sendKeys(text);
            logger.info("✅ Entered text into element: {}", elementName);
            return;
            
        } catch (StaleElementReferenceException e) {
            if (attempt == maxRetries) {
                throw new RuntimeException("Failed to send keys after " + maxRetries + " attempts", e);
            }
            logger.warn("⚠️ Stale element, retrying... ({}/{})", attempt, maxRetries);
            Thread.sleep(500);
        }
    }
}
```
</details>

---

### 📖 Further Learning

**Read the complete guide:** [SELF_HEALING_GUIDE.md](SELF_HEALING_GUIDE.md)

Topics covered:
- Why self-healing locators?
- Real-world examples
- Best practices
- Common mistakes
- Advanced patterns
- Performance optimization

---

## 📊 Progress Tracker

Track your completed exercises:

### Beginner ⭐
- [ ] Exercise 1.1: Fix Login Button
- [ ] Exercise 1.2: Fix Error Message
- [ ] Exercise 1.3: Fix Logout Button

### Intermediate ⭐⭐
- [ ] Exercise 2.1: Create Registration Page Object
- [ ] Exercise 2.2: Add Dropdown Handling
- [ ] Exercise 3.1: Write Negative Test Case
- [ ] Exercise 3.2: Data-Driven Testing

### Advanced ⭐⭐⭐
- [ ] Exercise 4.1: Implement Explicit Waits
- [ ] Exercise 4.2: Create Fluent Page Object
- [ ] Exercise 4.3: Enhanced Screenshots
- [ ] Challenge 5.1: Parallel Execution
- [ ] Challenge 5.2: API Testing Integration

### Expert Level 🔥
- [ ] Exercise 6.1: Implement Self-Healing findElement()
- [ ] Exercise 6.2: Implement clickElement() with retry
- [ ] Exercise 6.3: Implement sendKeys() with retry
- [ ] Exercise 6.4: Add performance caching (optional)

---

## 🏆 Certification Project

**Final Challenge:** Build a complete test suite for an e-commerce website

**Requirements:**
- 3+ page objects
- 10+ test cases
- Data-driven tests
- Parallel execution
- Full documentation

---

## 💡 Tips for Success

1. **Start Small:** Master basics before advanced concepts
2. **Test Your Code:** Run tests after each change
3. **Read Error Messages:** They tell you exactly what's wrong
4. **Use Debugger:** Step through code to understand flow
5. **Ask Questions:** Check DEBUGGING_GUIDE.md when stuck

---

## 📚 Additional Learning Resources

- **Selenium Documentation:** https://www.selenium.dev/documentation/
- **TestNG Tutorial:** https://testng.org/doc/documentation-main.html
- **Page Object Model:** https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/
- **Design Patterns:** https://refactoring.guru/design-patterns

---

**Remember:** Making mistakes is part of learning! Each error teaches you something new.

*Practice Exercises - Selenium TestNG Automation Framework*  
*Version: 1.0 | December 2025*
