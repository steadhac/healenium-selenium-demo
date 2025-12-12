# Test Cases: Login Functionality

**Project:** Selenium TestNG Automation Framework  
**Feature:** Login  
**Application:** https://the-internet.herokuapp.com/login  
**Version:** 1.0  
**Last Updated:** December 10, 2025  
**Prepared By:** Carolina Steadham

---

## Test Case Index

| TC ID | Test Case Name | Priority | Status | Automated |
|-------|---------------|----------|--------|-----------|
| TC-001 | Verify successful login with valid credentials | High | ❌ Fail | Yes |
| TC-002 | Verify page title on login page | High | ✅ Pass | Yes |
| TC-003 | Verify login failure with invalid username | High | ❌ Fail | Yes |
| TC-004 | Verify login failure with invalid password | High | ❌ Fail | Yes |
| TC-005 | Verify login failure with empty credentials | High | ❌ Fail | Yes |
| TC-006 | Verify welcome message after login | Medium | ❌ Fail | Yes |
| TC-007 | Verify logout button is displayed | Medium | ❌ Fail | Yes |
| TC-008 | Verify logout functionality | Medium | ❌ Fail | Yes |
| TC-009 | Verify login with multiple valid credentials | Medium | ❌ Fail | Yes |
| TC-010 | Verify login with multiple invalid credentials | Medium | ❌ Fail | Yes |
| TC-011 | Verify login with empty field combinations | Medium | ❌ Fail | Yes |

**Total Test Cases:** 11  
**Passed:** 1  
**Failed:** 10  
**Automation Coverage:** 100%

---

## Test Cases - Detailed Specifications

---

### TC-001: Verify Successful Login with Valid Credentials

**Objective:** Verify that a user can successfully log in with valid username and password.

**Priority:** High  
**Category:** Positive Testing  
**Automated:** Yes (`LoginTest.testValidLogin()`)

**Preconditions:**
- Application is accessible at https://the-internet.herokuapp.com/login
- Valid test credentials are available (tomsmith / SuperSecretPassword!)
- Browser is installed and configured

**Test Data:**
- Username: `tomsmith`
- Password: `SuperSecretPassword!`

**Test Steps:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Enter valid username "tomsmith" in the username field
3. Enter valid password "SuperSecretPassword!" in the password field
4. Click the "Login" button
5. Observe the result

**Expected Results:**
- User is redirected to secure area page
- URL changes to https://the-internet.herokuapp.com/secure
- Success message "You logged into a secure area!" is displayed
- Logout button is visible

**Actual Results:** ❌ **FAIL**
- Login button locator is incorrect (uses id="login", actual uses css selector button[type='submit'])
- Test fails with NoSuchElementException

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001

---

### TC-002: Verify Page Title on Login Page

**Objective:** Verify that the login page displays the correct title.

**Priority:** High  
**Category:** UI Validation  
**Automated:** Yes (`LoginTest.testPageTitle()`)

**Preconditions:**
- Application is accessible

**Test Steps:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Get the page title
3. Verify the title

**Expected Results:**
- Page title should be "The Internet"

**Actual Results:** ✅ **PASS**
- Page title is correctly "The Internet"

**Test Status:** ✅ PASS

---

### TC-003: Verify Login Failure with Invalid Username

**Objective:** Verify that login fails with invalid username and displays appropriate error message.

**Priority:** High  
**Category:** Negative Testing  
**Automated:** Yes (`LoginTest.testInvalidUsername()`)

**Preconditions:**
- Application is accessible

**Test Data:**
- Username: `invalidUser`
- Password: `SuperSecretPassword!`

**Test Steps:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Enter invalid username "invalidUser"
3. Enter valid password "SuperSecretPassword!"
4. Click the "Login" button
5. Verify error message is displayed

**Expected Results:**
- User remains on login page
- Error message "Your username is invalid!" is displayed
- Error message has red background
- Close button (×) is present

**Actual Results:** ❌ **FAIL**
- Login button locator is incorrect
- Error message locator is incorrect (uses className="error-message", actual uses id="flash")
- Test fails with NoSuchElementException

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001, BUG-002

---

### TC-004: Verify Login Failure with Invalid Password

**Objective:** Verify that login fails with invalid password and displays appropriate error message.

**Priority:** High  
**Category:** Negative Testing  
**Automated:** Yes (`LoginTest.testInvalidPassword()`)

**Preconditions:**
- Application is accessible

**Test Data:**
- Username: `tomsmith`
- Password: `wrongPassword`

**Test Steps:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Enter valid username "tomsmith"
3. Enter invalid password "wrongPassword"
4. Click the "Login" button
5. Verify error message is displayed

**Expected Results:**
- User remains on login page
- Error message "Your password is invalid!" is displayed
- Error message has red background

**Actual Results:** ❌ **FAIL**
- Login button locator is incorrect
- Error message locator is incorrect

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001, BUG-002

---

### TC-005: Verify Login Failure with Empty Credentials

**Objective:** Verify that login fails when both username and password are empty.

**Priority:** High  
**Category:** Boundary Testing  
**Automated:** Yes (`LoginTest.testEmptyCredentials()`)

**Preconditions:**
- Application is accessible

**Test Data:**
- Username: (empty)
- Password: (empty)

**Test Steps:**
1. Navigate to https://the-internet.herokuapp.com/login
2. Leave username field empty
3. Leave password field empty
4. Click the "Login" button
5. Verify error message is displayed

**Expected Results:**
- User remains on login page
- Error message "Your username is invalid!" is displayed

**Actual Results:** ❌ **FAIL**
- Login button locator is incorrect

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001

---

### TC-006: Verify Welcome Message After Login

**Objective:** Verify that welcome message is displayed after successful login.

**Priority:** Medium  
**Category:** Post-Login Validation  
**Automated:** Yes (`HomePageTest.testWelcomeMessage()`)

**Preconditions:**
- User has successfully logged in

**Test Steps:**
1. Login with valid credentials
2. Observe the secure area page
3. Verify welcome message

**Expected Results:**
- Welcome message is displayed
- Message text should be present

**Actual Results:** ❌ **FAIL**
- Welcome message element doesn't exist on the page
- Test fails with NoSuchElementException

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-003

**Note:** This is an intentional learning bug - the herokuapp.com site doesn't have a welcome message element

---

### TC-007: Verify Logout Button is Displayed

**Objective:** Verify that logout button is visible after successful login.

**Priority:** Medium  
**Category:** Post-Login Validation  
**Automated:** Yes (`HomePageTest.testLogoutButtonDisplayed()`)

**Preconditions:**
- User has successfully logged in

**Test Steps:**
1. Login with valid credentials
2. Observe the secure area page
3. Verify logout button is present and displayed

**Expected Results:**
- Logout button should be visible
- Button text should be "Logout"

**Actual Results:** ❌ **FAIL**
- Logout button locator is incorrect (uses id="logout", actual uses linkText="Logout")
- Test fails with NoSuchElementException

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-004

---

### TC-008: Verify Logout Functionality

**Objective:** Verify that user can successfully logout from the application.

**Priority:** Medium  
**Category:** Logout Testing  
**Automated:** Yes (`HomePageTest.testLogout()`)

**Preconditions:**
- User has successfully logged in

**Test Steps:**
1. Login with valid credentials
2. Click the logout button
3. Verify redirection to login page
4. Verify logout success message

**Expected Results:**
- User is redirected to login page
- URL should be https://the-internet.herokuapp.com/login
- Success message "You logged out of the secure area!" is displayed

**Actual Results:** ❌ **FAIL**
- Logout button locator is incorrect
- Cannot proceed with test

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-004

---

### TC-009: Verify Login with Multiple Valid Credentials

**Objective:** Verify login functionality using data-driven approach with valid credentials.

**Priority:** Medium  
**Category:** Data-Driven Testing  
**Automated:** Yes (`DataDrivenLoginTest.testLoginWithValidCredentials()`)

**Test Data:**
| Username | Password | Expected Result |
|----------|----------|-----------------|
| tomsmith | SuperSecretPassword! | Login Success |

**Test Steps:**
1. For each data set, navigate to login page
2. Enter username and password
3. Click login button
4. Verify successful login

**Expected Results:**
- All valid credential sets should allow successful login

**Actual Results:** ❌ **FAIL**
- Login button locator issues prevent successful execution

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001

---

### TC-010: Verify Login with Multiple Invalid Credentials

**Objective:** Verify login failure with multiple invalid credential combinations using data-driven approach.

**Priority:** Medium  
**Category:** Data-Driven Testing  
**Automated:** Yes (`DataDrivenLoginTest.testLoginWithInvalidCredentials()`)

**Test Data:**
| Username | Password | Expected Error |
|----------|----------|----------------|
| invalidUser | SuperSecretPassword! | Your username is invalid! |
| tomsmith | wrongPassword | Your password is invalid! |

**Test Steps:**
1. For each data set, navigate to login page
2. Enter username and password
3. Click login button
4. Verify error message

**Expected Results:**
- All invalid credential sets should show appropriate error messages

**Actual Results:** ❌ **FAIL**
- Locator issues prevent validation

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001, BUG-002

---

### TC-011: Verify Login with Empty Field Combinations

**Objective:** Verify login failure with empty username/password combinations using data-driven approach.

**Priority:** Medium  
**Category:** Data-Driven Testing  
**Automated:** Yes (`DataDrivenLoginTest.testLoginWithEmptyCredentials()`)

**Test Data:**
| Username | Password | Expected Error |
|----------|----------|----------------|
| (empty) | (empty) | Your username is invalid! |
| (empty) | SuperSecretPassword! | Your username is invalid! |
| tomsmith | (empty) | Your password is invalid! |

**Test Steps:**
1. For each data set, navigate to login page
2. Enter username and password (or leave empty)
3. Click login button
4. Verify error message

**Expected Results:**
- Empty fields should trigger appropriate error messages

**Actual Results:** ❌ **FAIL**
- Cannot complete due to locator issues

**Test Status:** ❌ FAIL  
**Defect ID:** BUG-001, BUG-002

---

## Test Execution Summary

**Execution Date:** December 10, 2025  
**Executed By:** Carolina Steadham  
**Environment:** macOS 15.7.2, Chrome Browser  
**Execution Mode:** Automated (Maven + TestNG)

### Results Overview

| Status | Count | Percentage |
|--------|-------|------------|
| ✅ Pass | 1 | 9% |
| ❌ Fail | 10 | 91% |
| ⏭️ Skip | 0 | 0% |
| **Total** | **11** | **100%** |

### Pass Rate: 9% (1/11)

### Failure Analysis

**Root Causes:**
1. **Incorrect Locators (BUG-001, BUG-002, BUG-004)** - 90% of failures
   - Login button uses wrong locator strategy
   - Error message uses wrong class name
   - Logout button uses wrong ID

2. **Missing Elements (BUG-003)** - 10% of failures
   - Welcome message element doesn't exist on test site

**Action Items:**
- Fix all locators in LoginPage.java and HomePage.java
- See DEBUGGING_GUIDE.md for step-by-step fix instructions
- See BUG_REPORTS.md for detailed bug information

---

## Test Coverage Analysis

### Functional Coverage

| Functionality | Coverage | Test Cases |
|---------------|----------|------------|
| Valid Login | 100% | TC-001, TC-009 |
| Invalid Login | 100% | TC-003, TC-004, TC-005, TC-010, TC-011 |
| Post-Login Validation | 100% | TC-006, TC-007 |
| Logout | 100% | TC-008 |
| UI Validation | 100% | TC-002 |

### Browser Coverage

| Browser | Status | Notes |
|---------|--------|-------|
| Chrome | ✅ Tested | Primary browser for testing |
| Firefox | ⏳ Pending | Can be configured via config.properties |
| Edge | ⏳ Pending | Can be configured via config.properties |

### Automation vs Manual

- **Automated:** 11 test cases (100%)
- **Manual:** 0 test cases (0%)
- **Automation Goal:** ✅ Achieved 100% coverage

---

## Notes for Manual Testers

### How This Maps to Manual Testing

Each automated test case above can be executed manually by:

1. **Following the Test Steps** - The steps are written in manual testing format
2. **Using the Test Data** - All test data is clearly specified
3. **Verifying Expected Results** - Clear acceptance criteria provided
4. **Documenting Actual Results** - Space to record what actually happens

### Transition to Automation

**What Changed:**
- **Manual:** You click elements in the browser
- **Automated:** Code clicks elements for you

**What Stayed the Same:**
- Test case logic and flow
- Expected results
- Test data
- Validation points

**Benefits:**
- Tests run faster (seconds vs minutes)
- No human error in test execution
- Can run overnight or repeatedly
- Consistent execution every time

---

## References

- **Test Plan:** [TEST_PLAN.md](TEST_PLAN.md)
- **Bug Reports:** [BUG_REPORTS.md](BUG_REPORTS.md)
- **Traceability Matrix:** [TRACEABILITY_MATRIX.md](TRACEABILITY_MATRIX.md)
- **Debugging Guide:** [DEBUGGING_GUIDE.md](DEBUGGING_GUIDE.md)
- **Automation Framework:** [README.md](README.md)

---

**Document Version History:**

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | Dec 10, 2025 | Carolina Steadham | Initial test cases creation |

---

**End of Test Cases Document**
