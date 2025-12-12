# Test Plan: The Internet - Login Feature

**Project:** Selenium TestNG Automation Framework  
**Application Under Test:** https://the-internet.herokuapp.com/login  
**Test Plan Version:** 1.0  
**Date:** December 10, 2025  
**Prepared By:** Carolina Steadham  

---

## 1. Introduction

### 1.1 Purpose
This test plan outlines the testing strategy for the login functionality of The Internet application. The goal is to ensure the login feature works correctly across different scenarios and browsers.

### 1.2 Scope
**In Scope:**
- Valid login scenarios
- Invalid login scenarios (wrong username, wrong password, empty fields)
- Error message validation
- Post-login page verification
- Logout functionality
- Cross-browser testing (Chrome, Firefox, Edge)

**Out of Scope:**
- Password reset functionality (not available in test app)
- "Remember Me" functionality (not available in test app)
- Session timeout testing
- Performance testing
- Security testing (SQL injection, XSS)

---

## 2. Test Strategy

### 2.1 Test Levels
- **Functional Testing:** Verify all login features work as expected
- **UI Testing:** Validate page elements display correctly
- **Regression Testing:** Ensure existing functionality isn't broken

### 2.2 Test Types
- **Positive Testing:** Valid credentials, successful login
- **Negative Testing:** Invalid credentials, error handling
- **Boundary Testing:** Empty fields, special characters
- **Data-Driven Testing:** Multiple credential combinations

### 2.3 Testing Approach
- **Manual Testing:** Initial exploratory testing (completed)
- **Automated Testing:** Selenium WebDriver with TestNG framework
- **Design Pattern:** Page Object Model (POM)
- **Automation Coverage Target:** 90% of test cases

---

## 3. Test Environment

### 3.1 Application Environment
- **URL:** https://the-internet.herokuapp.com/login
- **Application Type:** Web Application
- **Environment:** Production (public test site)

### 3.2 Test Infrastructure
| Component | Details |
|-----------|---------|
| **Operating System** | macOS 15.7.2 |
| **Programming Language** | Java 21.0.1 |
| **Build Tool** | Maven 3.x |
| **Test Framework** | TestNG 7.8.0 |
| **Automation Tool** | Selenium WebDriver 4.16.1 |
| **Browsers** | Chrome (latest), Firefox (latest), Edge (latest) |
| **IDE** | IntelliJ IDEA / Eclipse |

### 3.3 Test Data
| Data Type | Username | Password |
|-----------|----------|----------|
| Valid | tomsmith | SuperSecretPassword! |
| Invalid Username | invalidUser | SuperSecretPassword! |
| Invalid Password | tomsmith | wrongPassword |
| Empty Credentials | (empty) | (empty) |

---

## 4. Test Objectives

### 4.1 Primary Objectives
1. Verify users can log in with valid credentials
2. Verify appropriate error messages for invalid credentials
3. Verify page navigation after successful login
4. Verify logout functionality works correctly

### 4.2 Quality Goals
- **Functional Correctness:** 100% of critical scenarios pass
- **Test Coverage:** Minimum 90% of identified test cases automated
- **Test Execution Time:** Complete regression suite in < 5 minutes
- **Defect Detection:** Identify critical bugs before manual testing

---

## 5. Entry and Exit Criteria

### 5.1 Entry Criteria
✅ Test environment is set up and accessible  
✅ Application URL is reachable  
✅ Test automation framework is configured  
✅ Test data is prepared  
✅ All dependencies (Java, Maven, Selenium) are installed  

### 5.2 Exit Criteria
✅ All planned test cases are executed  
✅ 90% or more test cases pass  
✅ All critical and high-priority defects are logged  
✅ Test execution report is generated  
✅ Regression suite is stable and repeatable  

---

## 6. Test Deliverables

### 6.1 Test Documentation
- [x] Test Plan (this document)
- [x] Test Cases (TEST_CASES.md)
- [x] Traceability Matrix (TRACEABILITY_MATRIX.md)
- [x] Bug Reports (BUG_REPORTS.md)

### 6.2 Automation Artifacts
- [x] Page Object classes (LoginPage.java, HomePage.java)
- [x] Test classes (LoginTest.java, HomePageTest.java)
- [x] Utility classes (DriverManager, WaitHelper, ConfigReader)
- [x] TestNG suite configuration (testng.xml)

### 6.3 Test Reports
- [x] TestNG HTML reports
- [x] Screenshots on test failure
- [ ] Test execution summary (generated after run)

---

## 7. Test Schedule

| Phase | Activities | Duration | Status |
|-------|-----------|----------|--------|
| **Phase 1: Planning** | Define scope, strategy, test cases | 1 day | ✅ Complete |
| **Phase 2: Framework Setup** | Create POM structure, utilities | 2 days | ✅ Complete |
| **Phase 3: Test Development** | Write automated tests | 2 days | ✅ Complete |
| **Phase 4: Test Execution** | Run tests, log defects | 1 day | 🔄 In Progress |
| **Phase 5: Reporting** | Generate reports, document findings | 1 day | ⏳ Pending |

**Total Estimated Effort:** 7 days  
**Actual Effort:** 7 days  

---

## 8. Test Cases Summary

| Category | Total Cases | Automated | Manual | Priority |
|----------|-------------|-----------|--------|----------|
| Login - Positive | 2 | 2 | 0 | High |
| Login - Negative | 3 | 3 | 0 | High |
| Post-Login | 3 | 3 | 0 | Medium |
| Data-Driven | 3 | 3 | 0 | Medium |
| **Total** | **11** | **11** | **0** | - |

**Automation Coverage:** 100%

See [TEST_CASES.md](TEST_CASES.md) for detailed test case specifications.

---

## 9. Risk Assessment

### 9.1 Risks and Mitigation

| Risk | Impact | Probability | Mitigation Strategy |
|------|--------|-------------|---------------------|
| Test site unavailable | High | Low | Use local test environment backup |
| Locator changes in UI | High | Medium | Use stable locators (ID/name over XPath) |
| Browser compatibility | Medium | Low | Test on multiple browsers manually |
| Flaky tests (timing) | Medium | Medium | Implement explicit waits, retry mechanism |
| Test data changes | Low | Low | Use configurable test data from properties |

### 9.2 Assumptions
- The Internet (herokuapp.com) remains accessible
- Valid credentials remain: tomsmith / SuperSecretPassword!
- Page structure doesn't change significantly
- CI/CD environment has internet access

### 9.3 Dependencies
- Java JDK 11+ installed
- Maven installed and configured
- Browsers (Chrome/Firefox/Edge) installed
- Internet connectivity

---

## 10. Defect Management

### 10.1 Defect Reporting Process
1. **Identify:** Test fails or unexpected behavior occurs
2. **Reproduce:** Verify defect is consistent
3. **Document:** Log in BUG_REPORTS.md with full details
4. **Classify:** Assign severity and priority
5. **Track:** Monitor until resolution

### 10.2 Severity Classification
- **Critical:** Application crashes, cannot login with valid credentials
- **High:** Error messages incorrect, major functionality broken
- **Medium:** UI issues, inconsistent behavior
- **Low:** Cosmetic issues, minor text errors

### 10.3 Defect Metrics (Target)
- **Defect Detection Rate:** > 95% of bugs found in testing
- **Defect Leakage:** < 5% of bugs found in production
- **Defect Resolution Time:** Critical (1 day), High (3 days), Medium (1 week)

See [BUG_REPORTS.md](BUG_REPORTS.md) for actual defects found.

---

## 11. Test Execution

### 11.1 Execution Process
1. **Setup:** Configure environment (Java, Maven, browsers)
2. **Run:** Execute TestNG suite (`mvn clean test`)
3. **Monitor:** Watch test execution in real-time
4. **Capture:** Screenshots on failure
5. **Report:** Review TestNG reports
6. **Log:** Document defects in BUG_REPORTS.md

### 11.2 Execution Commands
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=LoginTest

# Run with specific browser
mvn test -Dbrowser=firefox

# Run in headless mode
mvn test -Dheadless=true
```

---

## 12. Roles and Responsibilities

| Role | Name | Responsibilities |
|------|------|------------------|
| **Test Lead** | Carolina Steadham | Overall test strategy, planning, reporting |
| **Automation Engineer** | Carolina Steadham | Framework development, test automation |
| **QA Analyst** | Carolina Steadham | Test case design, execution, defect logging |
| **DevOps Engineer** | Carolina Steadham | CI/CD setup, environment management |

---

## 13. Communication Plan

### 13.1 Status Reporting
- **Daily:** Test execution status (pass/fail count)
- **Weekly:** Test progress report, defect summary
- **End of Cycle:** Final test report with metrics

### 13.2 Reporting Format
- TestNG HTML reports: `target/surefire-reports/index.html`
- Screenshots: `screenshots/` directory
- Documentation: All markdown files in repository

---

## 14. Approval

| Role | Name | Signature | Date |
|------|------|-----------|------|
| Test Lead | Carolina Steadham | _Approved_ | Dec 10, 2025 |
| Project Manager | TBD | _Pending_ | - |
| Stakeholder | TBD | _Pending_ | - |

---

## 15. References

- **Framework Documentation:** [README.md](README.md)
- **Clean Code Guide:** [CLEAN_CODE_GUIDE.md](CLEAN_CODE_GUIDE.md)
- **Debugging Guide:** [DEBUGGING_GUIDE.md](DEBUGGING_GUIDE.md)
- **Practice Exercises:** [EXERCISES.md](EXERCISES.md)
- **Contributing Guide:** [CONTRIBUTING.md](CONTRIBUTING.md)
- **Test Cases:** [TEST_CASES.md](TEST_CASES.md)
- **Bug Reports:** [BUG_REPORTS.md](BUG_REPORTS.md)

---

**Document Version History:**

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | Dec 10, 2025 | Carolina Steadham | Initial test plan creation |

---

**End of Test Plan**
