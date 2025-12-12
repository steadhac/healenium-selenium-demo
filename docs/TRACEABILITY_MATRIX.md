# Traceability Matrix: Login Feature

**Project:** Selenium TestNG Automation Framework  
**Feature:** Login  
**Version:** 1.0  
**Last Updated:** December 10, 2025  
**Prepared By:** Carolina Steadham

---

## Purpose

This traceability matrix establishes bidirectional links between:
- **Requirements** → **Test Cases** → **Automated Tests** → **Defects**

It ensures:
✅ All requirements are tested  
✅ All tests map to requirements  
✅ All automation covers test cases  
✅ All defects trace to failed tests  

---

## Complete Traceability Matrix

| Requirement ID | Requirement | Test Case ID | Automated Test | Test Class | Test Method | Status | Defect ID |
|----------------|-------------|--------------|----------------|------------|-------------|--------|-----------|
| REQ-001 | User can login with valid credentials | TC-001 | ✅ Yes | LoginTest | testValidLogin() | ❌ Fail | BUG-001 |
| REQ-001 | User can login with valid credentials | TC-009 | ✅ Yes | DataDrivenLoginTest | testLoginWithValidCredentials() | ❌ Fail | BUG-001 |
| REQ-002 | Login page displays correct title | TC-002 | ✅ Yes | LoginTest | testPageTitle() | ✅ Pass | - |
| REQ-003 | System rejects invalid username | TC-003 | ✅ Yes | LoginTest | testInvalidUsername() | ❌ Fail | BUG-001, BUG-002 |
| REQ-003 | System rejects invalid username | TC-010 | ✅ Yes | DataDrivenLoginTest | testLoginWithInvalidCredentials() | ❌ Fail | BUG-001, BUG-002 |
| REQ-004 | System rejects invalid password | TC-004 | ✅ Yes | LoginTest | testInvalidPassword() | ❌ Fail | BUG-001, BUG-002 |
| REQ-004 | System rejects invalid password | TC-010 | ✅ Yes | DataDrivenLoginTest | testLoginWithInvalidCredentials() | ❌ Fail | BUG-001, BUG-002 |
| REQ-005 | System rejects empty credentials | TC-005 | ✅ Yes | LoginTest | testEmptyCredentials() | ❌ Fail | BUG-001 |
| REQ-005 | System rejects empty credentials | TC-011 | ✅ Yes | DataDrivenLoginTest | testLoginWithEmptyCredentials() | ❌ Fail | BUG-001, BUG-002 |
| REQ-006 | Welcome message displayed after login | TC-006 | ✅ Yes | HomePageTest | testWelcomeMessage() | ❌ Fail | BUG-003 |
| REQ-007 | Logout button visible after login | TC-007 | ✅ Yes | HomePageTest | testLogoutButtonDisplayed() | ❌ Fail | BUG-004 |
| REQ-008 | User can logout from application | TC-008 | ✅ Yes | HomePageTest | testLogout() | ❌ Fail | BUG-004 |

---

## Requirements Coverage

### Functional Requirements

| Requirement ID | Requirement Description | Priority | Test Cases | Coverage | Status |
|----------------|------------------------|----------|------------|----------|--------|
| REQ-001 | User can login with valid credentials | High | TC-001, TC-009 | 100% | ❌ Blocked |
| REQ-002 | Login page displays correct title | High | TC-002 | 100% | ✅ Passing |
| REQ-003 | System rejects invalid username | High | TC-003, TC-010 | 100% | ❌ Blocked |
| REQ-004 | System rejects invalid password | High | TC-004, TC-010 | 100% | ❌ Blocked |
| REQ-005 | System rejects empty credentials | High | TC-005, TC-011 | 100% | ❌ Blocked |
| REQ-006 | Welcome message displayed after login | Medium | TC-006 | 100% | ❌ Blocked |
| REQ-007 | Logout button visible after login | Medium | TC-007 | 100% | ❌ Blocked |
| REQ-008 | User can logout from application | Medium | TC-008 | 100% | ❌ Blocked |

**Total Requirements:** 8  
**Requirements Covered:** 8 (100%)  
**Requirements Passing:** 1 (12.5%)  
**Requirements Blocked:** 7 (87.5%)

---

## Test Case to Automation Mapping

| Test Case ID | Test Case Name | Automation Status | Test Class | Test Method | Page Objects Used |
|--------------|----------------|-------------------|------------|-------------|-------------------|
| TC-001 | Verify successful login with valid credentials | ✅ Automated | LoginTest | testValidLogin() | LoginPage, HomePage |
| TC-002 | Verify page title on login page | ✅ Automated | LoginTest | testPageTitle() | LoginPage |
| TC-003 | Verify login failure with invalid username | ✅ Automated | LoginTest | testInvalidUsername() | LoginPage |
| TC-004 | Verify login failure with invalid password | ✅ Automated | LoginTest | testInvalidPassword() | LoginPage |
| TC-005 | Verify login failure with empty credentials | ✅ Automated | LoginTest | testEmptyCredentials() | LoginPage |
| TC-006 | Verify welcome message after login | ✅ Automated | HomePageTest | testWelcomeMessage() | HomePage |
| TC-007 | Verify logout button is displayed | ✅ Automated | HomePageTest | testLogoutButtonDisplayed() | HomePage |
| TC-008 | Verify logout functionality | ✅ Automated | HomePageTest | testLogout() | HomePage |
| TC-009 | Verify login with multiple valid credentials | ✅ Automated | DataDrivenLoginTest | testLoginWithValidCredentials() | LoginPage, HomePage |
| TC-010 | Verify login with multiple invalid credentials | ✅ Automated | DataDrivenLoginTest | testLoginWithInvalidCredentials() | LoginPage |
| TC-011 | Verify login with empty field combinations | ✅ Automated | DataDrivenLoginTest | testLoginWithEmptyCredentials() | LoginPage |

**Total Test Cases:** 11  
**Automated:** 11 (100%)  
**Manual Only:** 0 (0%)

---

## Defect Impact Analysis

| Defect ID | Defect Summary | Severity | Affected Requirements | Affected Test Cases | Blocked Tests |
|-----------|----------------|----------|----------------------|---------------------|---------------|
| BUG-001 | Incorrect locator for Login button | High | REQ-001, REQ-003, REQ-004, REQ-005 | TC-001, TC-003, TC-004, TC-005, TC-009, TC-010, TC-011 | 7 tests |
| BUG-002 | Incorrect locator for Error message | High | REQ-003, REQ-004, REQ-005 | TC-003, TC-004, TC-010, TC-011 | 4 tests |
| BUG-003 | Missing welcome message element | Medium | REQ-006 | TC-006 | 1 test |
| BUG-004 | Incorrect locator for Logout button | High | REQ-007, REQ-008 | TC-007, TC-008 | 2 tests |

**Total Defects:** 4  
**Requirements Impacted:** 7 (87.5%)  
**Test Cases Impacted:** 10 (91%)

---

## Automation Code Structure

### Test Classes and Their Coverage

| Test Class | File Path | Test Methods | Requirements Covered | Test Cases Covered |
|------------|-----------|--------------|----------------------|-------------------|
| LoginTest | src/test/java/tests/LoginTest.java | 4 | REQ-001, REQ-002, REQ-003, REQ-004, REQ-005 | TC-001, TC-002, TC-003, TC-004, TC-005 |
| HomePageTest | src/test/java/tests/HomePageTest.java | 3 | REQ-006, REQ-007, REQ-008 | TC-006, TC-007, TC-008 |
| DataDrivenLoginTest | src/test/java/tests/DataDrivenLoginTest.java | 3 | REQ-001, REQ-003, REQ-004, REQ-005 | TC-009, TC-010, TC-011 |

### Page Objects and Their Usage

| Page Object | File Path | Elements | Used By Test Classes | Requirements Supported |
|-------------|-----------|----------|---------------------|----------------------|
| LoginPage | src/main/java/pages/LoginPage.java | 4 | LoginTest, DataDrivenLoginTest, HomePageTest | REQ-001, REQ-002, REQ-003, REQ-004, REQ-005 |
| HomePage | src/main/java/pages/HomePage.java | 3 | LoginTest, HomePageTest, DataDrivenLoginTest | REQ-001, REQ-006, REQ-007, REQ-008 |

### Utility Classes Support

| Utility Class | File Path | Purpose | Used By |
|---------------|-----------|---------|---------|
| BaseTest | src/test/java/base/BaseTest.java | Setup/Teardown | All test classes |
| DriverManager | src/main/java/utils/DriverManager.java | Browser management | BaseTest |
| WaitHelper | src/main/java/utils/WaitHelper.java | Explicit waits | Page objects (if needed) |
| ConfigReader | src/main/java/utils/ConfigReader.java | Configuration | BaseTest |

---

## Coverage Metrics

### Overall Coverage

| Metric | Count | Percentage |
|--------|-------|------------|
| **Requirements Defined** | 8 | 100% |
| **Requirements with Test Cases** | 8 | 100% |
| **Test Cases Created** | 11 | - |
| **Test Cases Automated** | 11 | 100% |
| **Test Cases Passing** | 1 | 9% |
| **Test Cases Failing** | 10 | 91% |

### Automation Coverage by Priority

| Priority | Requirements | Test Cases | Automated | Coverage |
|----------|--------------|------------|-----------|----------|
| High | 5 | 7 | 7 | 100% |
| Medium | 3 | 4 | 4 | 100% |
| Low | 0 | 0 | 0 | - |
| **Total** | **8** | **11** | **11** | **100%** |

### Test Execution Summary

| Status | Test Cases | Percentage |
|--------|------------|------------|
| ✅ Pass | 1 | 9% |
| ❌ Fail | 10 | 91% |
| ⏭️ Skip | 0 | 0% |
| **Total** | **11** | **100%** |

---

## Bidirectional Traceability

### Forward Traceability (Requirements → Tests → Code)

```
REQ-001 (Valid Login)
  ├── TC-001 (Verify successful login)
  │   └── LoginTest.testValidLogin()
  │       ├── Uses: LoginPage.java
  │       ├── Uses: HomePage.java
  │       └── Status: ❌ FAIL (BUG-001)
  │
  └── TC-009 (Data-driven valid login)
      └── DataDrivenLoginTest.testLoginWithValidCredentials()
          ├── Uses: LoginPage.java
          ├── Uses: HomePage.java
          └── Status: ❌ FAIL (BUG-001)
```

### Backward Traceability (Code → Tests → Requirements)

```
LoginPage.java
  ├── Used by: LoginTest
  │   ├── testValidLogin() → TC-001 → REQ-001
  │   ├── testPageTitle() → TC-002 → REQ-002
  │   ├── testInvalidUsername() → TC-003 → REQ-003
  │   ├── testInvalidPassword() → TC-004 → REQ-004
  │   └── testEmptyCredentials() → TC-005 → REQ-005
  │
  └── Used by: DataDrivenLoginTest
      ├── testLoginWithValidCredentials() → TC-009 → REQ-001
      ├── testLoginWithInvalidCredentials() → TC-010 → REQ-003, REQ-004
      └── testLoginWithEmptyCredentials() → TC-011 → REQ-005
```

---

## Gap Analysis

### Requirements Without Tests
**Result:** ✅ None - 100% requirements coverage

### Tests Without Requirements
**Result:** ✅ None - All tests trace to requirements

### Automation Gaps
**Result:** ✅ None - 100% automation coverage

### Known Issues
1. **High failure rate (91%)** - Due to intentional locator bugs for learning
2. **4 open defects** - Blocking 10 out of 11 tests
3. **87.5% requirements blocked** - Need bug fixes to unblock

---

## Quality Metrics

### Test Coverage Quality

| Metric | Target | Actual | Status |
|--------|--------|--------|--------|
| Requirements Coverage | 100% | 100% | ✅ Met |
| Automation Coverage | 90% | 100% | ✅ Exceeded |
| Test Pass Rate | 90% | 9% | ❌ Below Target |
| Defect Density | <5 bugs | 4 bugs | ✅ Within Limit |

### Test Effectiveness

| Metric | Value |
|--------|-------|
| Defects Found by Tests | 4 |
| Tests per Requirement | 1.4 avg |
| Requirements per Test | 0.73 avg |
| Automation ROI | 100% (vs manual) |

---

## Recommendations

### Immediate Actions
1. **Fix BUG-001** - Highest priority, blocks 7 tests (64%)
2. **Fix BUG-002** - Blocks 4 additional tests (36%)
3. **Fix BUG-004** - Blocks logout functionality (18%)
4. **Address BUG-003** - Remove or adjust welcome message test (9%)

### Long-term Improvements
1. **Increase test data variations** - Add more edge cases
2. **Add cross-browser testing** - Firefox, Edge
3. **Implement retry logic** - Handle flaky tests
4. **Add performance tests** - Response time validation
5. **Enhance reporting** - More detailed test reports

---

## References

- **Test Plan:** [TEST_PLAN.md](TEST_PLAN.md)
- **Test Cases:** [TEST_CASES.md](TEST_CASES.md)
- **Bug Reports:** [BUG_REPORTS.md](BUG_REPORTS.md)
- **Debugging Guide:** [DEBUGGING_GUIDE.md](DEBUGGING_GUIDE.md)
- **Source Code:** `src/` directory

---

## Document Maintenance

### Update Triggers
This document should be updated when:
- New requirements are added
- Test cases are created/modified
- Automation scripts are updated
- Defects are resolved
- Coverage gaps are identified

### Version History

| Version | Date | Author | Changes |
|---------|------|--------|---------|
| 1.0 | Dec 10, 2025 | Carolina Steadham | Initial traceability matrix |

---

**End of Traceability Matrix Document**
