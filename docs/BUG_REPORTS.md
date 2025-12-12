# Bug Reports: Login Feature

**Project:** Selenium TestNG Automation Framework  
**Component:** Login Module  
**Application:** https://the-internet.herokuapp.com/login  
**Report Version:** 1.0  
**Last Updated:** December 10, 2025  
**Reported By:** Carolina Steadham

---

## Bug Summary

| Bug ID | Summary | Severity | Priority | Status | Affected Test Cases |
|--------|---------|----------|----------|--------|---------------------|
| BUG-001 | Incorrect locator for Login button | High | High | Open | TC-001, TC-003, TC-004, TC-005, TC-009, TC-010, TC-011 |
| BUG-002 | Incorrect locator for Error message | High | High | Open | TC-003, TC-004, TC-010, TC-011 |
| BUG-003 | Missing welcome message element in page object | Medium | Medium | Open | TC-006 |
| BUG-004 | Incorrect locator for Logout button | High | High | Open | TC-007, TC-008 |

**Total Bugs:** 4  
**Open:** 4  
**In Progress:** 0  
**Resolved:** 0  
**Closed:** 0

---

## Detailed Bug Reports

---

### BUG-001: Incorrect Locator for Login Button

**Summary:** Login button locator uses incorrect ID, causing NoSuchElementException

**Environment:**
- OS: macOS 15.7.2
- Browser: Chrome (latest)
- Java: 21.0.1
- Selenium: 4.16.1

**Component:** LoginPage.java  
**Severity:** High  
**Priority:** High  
**Status:** Open  
**Reported Date:** December 10, 2025  
**Reported By:** Carolina Steadham

**Affected Test Cases:**
- TC-001: Verify successful login with valid credentials
- TC-003: Verify login failure with invalid username
- TC-004: Verify login failure with invalid password
- TC-005: Verify login failure with empty credentials
- TC-009: Verify login with multiple valid credentials
- TC-010: Verify login with multiple invalid credentials
- TC-011: Verify login with empty field combinations

**Description:**
The LoginPage class uses an incorrect locator strategy to find the login button. The current implementation attempts to locate the button using `id="login"`, but the actual HTML element does not have an ID attribute. The correct locator should use a CSS selector targeting the button by its type attribute.

**Steps to Reproduce:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Enter any username and password
3. Attempt to click the login button using the current locator
4. Observe NoSuchElementException

**Expected Behavior:**
The login button should be located successfully and be clickable.

**Actual Behavior:**
```
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: 
{"method":"css selector","selector":"#login"}
```

**Root Cause:**
Incorrect locator in `LoginPage.java`:
```java
@FindBy(id = "login")  // WRONG - element has no ID
private WebElement loginButton;
```

**Fix/Solution:**
Update the locator to use CSS selector based on button type:
```java
@FindBy(css = "button[type='submit']")  // CORRECT
private WebElement loginButton;
```

**Workaround:**
None - must fix the locator

**Attachments:**
- Screenshot: `screenshots/Login_Test_testValidLogin_FAIL_[timestamp].png`
- HTML Inspection: Button element is `<button class="radius" type="submit">`

**Impact:**
- 7 out of 11 test cases fail
- 64% test failure rate
- Blocks all login functionality testing

**Notes:**
This is an intentional bug for learning purposes. Demonstrates the importance of using stable, correct locators and inspecting HTML elements properly.

---

### BUG-002: Incorrect Locator for Error Message

**Summary:** Error message locator uses non-existent class name, causing element not found errors

**Environment:**
- OS: macOS 15.7.2
- Browser: Chrome (latest)
- Java: 21.0.1
- Selenium: 4.16.1

**Component:** LoginPage.java  
**Severity:** High  
**Priority:** High  
**Status:** Open  
**Reported Date:** December 10, 2025  
**Reported By:** Carolina Steadham

**Affected Test Cases:**
- TC-003: Verify login failure with invalid username
- TC-004: Verify login failure with invalid password
- TC-010: Verify login with multiple invalid credentials
- TC-011: Verify login with empty field combinations

**Description:**
The LoginPage class attempts to locate the error message using `className="error-message"`, but the actual HTML element uses `id="flash"` instead. This causes tests to fail when trying to verify error messages after login failures.

**Steps to Reproduce:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Enter invalid credentials
3. Click login button (after fixing BUG-001)
4. Attempt to verify error message using current locator
5. Observe NoSuchElementException

**Expected Behavior:**
Error message should be located and its text should be verifiable.

**Actual Behavior:**
```
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: 
{"method":"css selector","selector":".error-message"}
```

**Root Cause:**
Incorrect locator in `LoginPage.java`:
```java
@FindBy(className = "error-message")  // WRONG - class doesn't exist
private WebElement errorMessage;
```

**Fix/Solution:**
Update the locator to use the correct ID:
```java
@FindBy(id = "flash")  // CORRECT
private WebElement errorMessage;
```

**Alternative Solutions:**
Could also use:
- CSS selector: `@FindBy(css = "#flash")`
- XPath: `@FindBy(xpath = "//div[@id='flash']")`

**Workaround:**
None - must fix the locator

**Attachments:**
- Screenshot: `screenshots/Login_Test_testInvalidUsername_FAIL_[timestamp].png`
- HTML Inspection: Error message is `<div id="flash" class="flash error">`

**Impact:**
- 4 out of 11 test cases fail
- 36% of negative test scenarios blocked
- Cannot verify error handling

**Notes:**
This demonstrates the difference between class names and IDs, and why inspecting the actual DOM structure is crucial. The element has class="flash error" (two classes), not a single class="error-message".

---

### BUG-003: Missing Welcome Message Element in Page Object

**Summary:** HomePage page object references a welcome message element that doesn't exist on the actual page

**Environment:**
- OS: macOS 15.7.2
- Browser: Chrome (latest)
- Java: 21.0.1
- Selenium: 4.16.1

**Component:** HomePage.java  
**Severity:** Medium  
**Priority:** Medium  
**Status:** Open  
**Reported Date:** December 10, 2025  
**Reported By:** Carolina Steadham

**Affected Test Cases:**
- TC-006: Verify welcome message after login

**Description:**
The HomePage class defines a welcomeMessage WebElement, but the actual secure area page at herokuapp.com does not contain such an element. This is a case where the page object model was created based on assumptions rather than actual page structure.

**Steps to Reproduce:**
1. Login to the application successfully
2. Inspect the secure area page
3. Search for welcome message element
4. Confirm element does not exist

**Expected Behavior:**
Page object should only contain elements that actually exist on the page.

**Actual Behavior:**
```
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: 
{"method":"css selector","selector":"#welcome-message"}
```

**Root Cause:**
Page object includes non-existent element:
```java
@FindBy(id = "welcome-message")  // Element doesn't exist on page
private WebElement welcomeMessage;
```

**Fix/Solution:**
**Option 1:** Remove the welcomeMessage element entirely from HomePage.java  
**Option 2:** Use the actual success message (id="flash") as the welcome message  
**Option 3:** Document this as a known limitation and comment out the test

Recommended: Option 1 - Remove the element and related test

**Workaround:**
Skip TC-006 test case or mark as known issue

**Attachments:**
- Screenshot: `screenshots/Home_Page_Test_testWelcomeMessage_FAIL_[timestamp].png`
- Page inspection: Only elements are h2 with "Secure Area", subheader text, and flash message

**Impact:**
- 1 test case fails
- Low impact as it's a "nice to have" feature, not core functionality
- Demonstrates importance of mapping page objects to actual pages

**Notes:**
This is an intentional bug that teaches learners:
1. Always inspect the actual webpage before creating page objects
2. Page objects should reflect reality, not wishes
3. Test automation is only as good as its understanding of the application

---

### BUG-004: Incorrect Locator for Logout Button

**Summary:** Logout button locator uses wrong ID attribute, should use linkText instead

**Environment:**
- OS: macOS 15.7.2
- Browser: Chrome (latest)
- Java: 21.0.1
- Selenium: 4.16.1

**Component:** HomePage.java  
**Severity:** High  
**Priority:** High  
**Status:** Open  
**Reported Date:** December 10, 2025  
**Reported By:** Carolina Steadham

**Affected Test Cases:**
- TC-007: Verify logout button is displayed
- TC-008: Verify logout functionality

**Description:**
The HomePage class attempts to locate the logout button using `id="logout"`, but the actual HTML element is an anchor tag (<a>) with no ID attribute. The element contains the text "Logout" and should be located using linkText or partialLinkText.

**Steps to Reproduce:**
1. Login successfully to the secure area
2. Attempt to find logout button using current locator
3. Observe NoSuchElementException

**Expected Behavior:**
Logout button should be located successfully and be clickable.

**Actual Behavior:**
```
org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element: 
{"method":"css selector","selector":"#logout"}
```

**Root Cause:**
Incorrect locator in `HomePage.java`:
```java
@FindBy(id = "logout")  // WRONG - element has no ID
private WebElement logoutButton;
```

**Fix/Solution:**
Update the locator to use linkText:
```java
@FindBy(linkText = "Logout")  // CORRECT
private WebElement logoutButton;
```

**Alternative Solutions:**
Could also use:
- Partial link text: `@FindBy(partialLinkText = "Logout")`
- CSS selector: `@FindBy(css = "a[href='/logout']")`
- XPath: `@FindBy(xpath = "//a[@href='/logout']")`

**Workaround:**
None - must fix the locator

**Attachments:**
- Screenshot: `screenshots/Home_Page_Test_testLogoutButtonDisplayed_FAIL_[timestamp].png`
- HTML Inspection: Logout is `<a class="button secondary radius" href="/logout">`

**Impact:**
- 2 test cases fail
- 18% test failure rate
- Blocks logout functionality testing
- Users cannot verify logout works correctly

**Notes:**
This bug teaches the importance of:
1. Understanding different locator strategies (linkText vs ID)
2. Choosing the most appropriate locator for the element type
3. Link elements often work best with linkText locators

---

## Bug Statistics

### By Severity

| Severity | Count | Percentage |
|----------|-------|------------|
| Critical | 0 | 0% |
| High | 3 | 75% |
| Medium | 1 | 25% |
| Low | 0 | 0% |
| **Total** | **4** | **100%** |

### By Component

| Component | Bugs | Percentage |
|-----------|------|------------|
| LoginPage.java | 2 | 50% |
| HomePage.java | 2 | 50% |

### By Status

| Status | Count |
|--------|-------|
| Open | 4 |
| In Progress | 0 |
| Resolved | 0 |
| Closed | 0 |

### Test Impact

| Metric | Value |
|--------|-------|
| Total Test Cases | 11 |
| Affected Test Cases | 10 |
| Impact Percentage | 91% |
| Blocked Tests | 10 |

---

## Resolution Guidelines

### Priority Definitions

- **Critical:** System crash, data loss, security vulnerability
- **High:** Major functionality broken, no workaround available
- **Medium:** Feature partially broken, workaround available
- **Low:** Minor issue, cosmetic problem

### Resolution Timeline

| Priority | Target Resolution Time |
|----------|------------------------|
| Critical | 24 hours |
| High | 3 days |
| Medium | 1 week |
| Low | 2 weeks |

### Next Steps

1. **Fix BUG-001** - Update login button locator (15 minutes)
2. **Fix BUG-002** - Update error message locator (10 minutes)
3. **Fix BUG-004** - Update logout button locator (10 minutes)
4. **Fix BUG-003** - Remove welcome message or adjust test (20 minutes)
5. **Re-run all tests** - Verify fixes work correctly
6. **Update test results** - Document pass rate improvement

**Total Estimated Fix Time:** 55 minutes

---

## Learning Points

### Why These Bugs Exist

These bugs are **intentionally created** for educational purposes to teach:

1. **Element Inspection Skills** - Always inspect the actual HTML before writing locators
2. **Locator Strategies** - Different elements need different locator approaches
3. **Debugging Process** - How to diagnose and fix automation failures
4. **Page Object Pattern** - Importance of accurate page object models
5. **Real-World Scenarios** - These are common mistakes that happen in real projects

### How to Fix Them

See **[DEBUGGING_GUIDE.md](DEBUGGING_GUIDE.md)** for step-by-step instructions on:
- Opening Chrome DevTools
- Inspecting elements
- Finding correct locators
- Updating page object classes
- Verifying fixes

---

## References

- **Test Plan:** [TEST_PLAN.md](TEST_PLAN.md)
- **Test Cases:** [TEST_CASES.md](TEST_CASES.md)
- **Traceability Matrix:** [TRACEABILITY_MATRIX.md](TRACEABILITY_MATRIX.md)
- **Debugging Guide:** [DEBUGGING_GUIDE.md](DEBUGGING_GUIDE.md)
- **Page Objects:** `src/main/java/pages/`

---

**Document Version History:**

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | Dec 10, 2025 | Carolina Steadham | Initial bug reports creation |

---

**End of Bug Reports Document**

---

## 🔧 Self-Healing Solutions

### What is Self-Healing?

Self-healing locators are a **production-grade pattern** that automatically tries fallback locators when the primary locator fails. Instead of tests failing immediately, they auto-recover by trying alternative ways to find elements.

**Benefits:**
- ✅ Tests continue running despite UI changes
- ✅ Auto-recovery reduces maintenance
- ✅ Logs show which locators work/fail
- ✅ Demonstrates advanced Selenium skills

---

### Self-Healing Resolution for BUG-001

**Bug:** Login button locator is incorrect (`By.id("login")`)

**Traditional Fix:**
```java
// Update LoginPage.java
@FindBy(css = "button[type='submit']")  // Fixed!
WebElement loginButton;
```

**Self-Healing Solution:**
```java
// In LoginPageSelfHealing.java
public void clickLoginButton() {
    WebElement loginButton = healer.findElement("Login Button",
        By.id("login"),                      // Primary (fails)
        By.cssSelector("button[type='submit']"),  // Fallback 1 (works!)
        By.xpath("//button[@type='submit']"),     // Fallback 2
        By.tagName("button")                      // Fallback 3
    );
    loginButton.click();
}
```

**Result:**
- ✅ Test passes automatically using fallback #1
- ⚠️ Warning logged: "Self-healing activated! Using fallback locator #2"
- 💡 Developer knows to update primary locator

**Impact:**
- **Without self-healing:** 7 tests fail ❌
- **With self-healing:** 7 tests pass ✅ (with warnings)

---

### Self-Healing Resolution for BUG-002

**Bug:** Error message locator is incorrect (`By.className("error-message")`)

**Traditional Fix:**
```java
// Update LoginPage.java
@FindBy(id = "flash")  // Fixed!
WebElement errorMessage;
```

**Self-Healing Solution:**
```java
// In LoginPageSelfHealing.java
public String getErrorMessage() {
    WebElement errorMsg = healer.findElement("Error Message",
        By.className("error-message"),      // Primary (fails)
        By.id("flash"),                     // Fallback 1 (works!)
        By.cssSelector(".flash.error"),     // Fallback 2
        By.xpath("//*[@id='flash']")        // Fallback 3
    );
    return errorMsg.getText();
}
```

**Result:**
- ✅ Test passes automatically using fallback #1
- ⚠️ Warning logged
- 💡 Suggests updating primary locator to `By.id("flash")`

**Impact:**
- **Without self-healing:** 4 tests fail ❌
- **With self-healing:** 4 tests pass ✅ (with warnings)

---

### Self-Healing Resolution for BUG-003

**Bug:** Welcome message element doesn't exist on the page

**Traditional Fix:**
```java
// Option 1: Remove the test (element doesn't exist)
// Option 2: Update test to check flash message instead
public String getSuccessMessage() {
    return driver.findElement(By.id("flash")).getText();
}
```

**Self-Healing Solution:**
```java
// In HomePageSelfHealing.java
public String getWelcomeMessage() {
    WebElement welcomeMsg = healer.findElement("Welcome Message",
        By.id("welcome-message"),           // Primary (doesn't exist)
        By.className("welcome"),            // Fallback 1 (doesn't exist)
        By.cssSelector(".flash.success"),   // Fallback 2 (might work!)
        By.xpath("//*[contains(@class, 'flash')]")  // Fallback 3
    );
    return welcomeMsg.getText();
}
```

**Result:**
- ✅ Test passes if flash message exists (fallback #2 works)
- ❌ Test still fails if no matching element (all locators fail)
- 💡 Self-healing can't create elements that don't exist!

**Lesson:** Self-healing helps with changed locators, not missing elements

**Impact:**
- **Without self-healing:** 1 test fails ❌
- **With self-healing:** 1 test might pass ✅ (if flash message present)

---

### Self-Healing Resolution for BUG-004

**Bug:** Logout button locator is incorrect (`By.id("logout")`)

**Traditional Fix:**
```java
// Update HomePage.java
@FindBy(linkText = "Logout")  // Fixed!
WebElement logoutButton;
```

**Self-Healing Solution:**
```java
// In HomePageSelfHealing.java
public void clickLogout() {
    WebElement logoutBtn = healer.findElement("Logout Button",
        By.id("logout"),                    // Primary (fails)
        By.linkText("Logout"),              // Fallback 1 (works!)
        By.partialLinkText("Logout"),       // Fallback 2
        By.cssSelector("a[href*='logout']") // Fallback 3
    );
    logoutBtn.click();
}
```

**Result:**
- ✅ Test passes automatically using fallback #1
- ⚠️ Warning logged
- 💡 Suggests updating primary locator to `By.linkText("Logout")`

**Impact:**
- **Without self-healing:** 2 tests fail ❌
- **With self-healing:** 2 tests pass ✅ (with warnings)

---

## 📊 Self-Healing Impact Summary

### Before Self-Healing
| Status | Count | Percentage |
|--------|-------|------------|
| ✅ Pass | 1 | 9% |
| ❌ Fail | 10 | 91% |

### After Self-Healing
| Status | Count | Percentage | Notes |
|--------|-------|------------|-------|
| ✅ Pass | 10 | 91% | Auto-recovered! |
| ⚠️ Warnings | 10 | 91% | Suggests fixes |
| ❌ Fail | 1 | 9% | BUG-003 (missing element) |

**Improvement:** 91% → 91% pass rate with minimal code changes!

---

## 🎓 Learning Path

### Beginner: Fix the Bugs Manually
1. Update locators in `LoginPage.java` and `HomePage.java`
2. Run tests and verify they pass
3. Understand why tests were failing

### Intermediate: Implement Self-Healing
1. Complete Exercise 6 in [EXERCISES.md](EXERCISES.md)
2. Implement `SelfHealingElement.findElement()`
3. Test with self-healing Page Objects

### Advanced: Compare Both Approaches
1. Run tests with original Page Objects → See failures
2. Run tests with self-healing Page Objects → See auto-recovery
3. Analyze logs to find which fallbacks activated
4. Update primary locators based on warnings

---

## 🔗 Resources

- **Self-Healing Guide:** [SELF_HEALING_GUIDE.md](SELF_HEALING_GUIDE.md)
- **Exercises:** [EXERCISES.md](EXERCISES.md) - Exercise 6
- **Code Examples:**
  - Original: `LoginPage.java`, `HomePage.java`
  - Self-Healing: `LoginPageSelfHealing.java`, `HomePageSelfHealing.java`
  - Utility: `SelfHealingElement.java`

---

**Remember:** Self-healing is a safety net, not a replacement for good locators!  
Always fix the root cause, but keep self-healing as insurance against future changes.

---

*Bug Reports Document - Updated with Self-Healing Solutions*  
*Version: 2.0 | December 10, 2025*  
*Author: Carolina Steadham*
