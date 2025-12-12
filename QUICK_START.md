# 🚀 Quick Start Guide

This guide helps you run the project and experience Healenium self-healing in action!

## Prerequisites

- **Java 11+** installed
- **Maven** installed
- **Firefox browser** installed (tests run on Firefox by default)
- **Docker** installed and running (for Healenium backend)

Check your setup:
```bash
java -version
mvn -version
docker --version
```

---

## Step 1: Start Healenium Backend 🐳

```bash
cd infra
docker-compose up -d
```

Check status:
```bash
docker-compose ps
```

You should see 3 containers running:
- healenium-backend (port 7878)
- postgres (port 5432)
- hlm-selector-imitator (port 8000)

---

## Step 2: Run Tests Without Healing 🐛

See the intentional broken locators fail:

```bash
mvn clean test
```

**Expected Result:** Tests fail due to broken locators (`id="login"`, `className="error-message"`)

Edit `src/test/resources/healenium.properties`:

```properties
heal-enabled = true  # Change from false to true
```

### Step 4: Fix Locators and Run Tests

First, fix the broken locators to establish a baseline:

1. Edit `src/main/java/pages/LoginPage.java`:
   - Change `@FindBy(id = "login")` to `@FindBy(xpath = "//button[@type='submit']")`
   - Change `@FindBy(className = "error-message")` to `@FindBy(id = "flash")`

2. Edit `src/main/java/pages/HomePage.java`:
   - Change `@FindBy(id = "logout")` to `@FindBy(linkText = "Logout")`

3. Run tests to create baseline:
```bash
mvn clean test
```

**Expected Result:** All 11 tests pass, Healenium records the working locators

---

## Step 4: See Healenium Heal Broken Locators 🎯

Now break a locator intentionally and watch Healenium fix it:

1. Edit `src/main/java/pages/LoginPage.java`:
   - Change `@FindBy(xpath = "//button[@type='submit']")` to `@FindBy(id = "wrong-id")`

2. Run tests with healing enabled:
```bash
mvn test
```

**Expected Result:** Healenium automatically finds the correct button using its ML model!

---

## Step 5: View Healenium Reports 📊

### Run Traditional Tests First
```bash
mvn clean test > traditional-results.txt
```

### Enable Self-Healing (follow Option 2 steps)

Open your browser and visit:

- **Healenium Report**: http://localhost:7878/healenium/report
- **Selector List**: http://localhost:7878/healenium/selectors
- **API Docs**: http://localhost:7878/swagger-ui.html

You'll see which locators were healed and how!

---

## What You'll Learn 💡

1. **Without Healing**: Tests fail when locators break
2. **With Healing**: Healenium ML model finds elements automatically
3. **Baseline Creation**: First successful run records locators
4. **Self-Healing**: Subsequent runs heal broken locators

---

## Next Steps 📚

1. Complete the [Exercises](EXERCISES.md) to practice
2. Read the [Healenium Setup Summary](HEALENIUM_SETUP_SUMMARY.md)
3. Explore the [docs folder](docs/) for detailed guides
4. Check [README.md](README.md) for full documentation

---

## Troubleshooting 🔧

**Docker containers not starting?**
```bash
cd infra
docker-compose down
docker-compose up -d
```

**Tests still failing?**
- Check Firefox is installed
- Verify Docker containers are running: `docker-compose ps`
- Check Healenium backend: `curl http://localhost:7878/healenium/health`

---

## Resources 🔗

- [Healenium Documentation](https://healenium.io)
- [GitHub Repository](https://github.com/healenium/healenium-web)
- [Test Documentation](docs/)

### Run in headless mode:
Update `src/test/java/tests/BaseTest.java`:
```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");
driver = new ChromeDriver(options);
```

---

## Exercises Mode 🎓

Want to learn by implementing yourself?

1. **Keep solutions commented** in `SelfHealingElement.java`
2. Open `EXERCISES.md`
3. Follow Exercise 6 with gradually detailed hints
4. Implement each method step-by-step
5. Run tests to verify your implementation

```bash
# After implementing each method, test it:
mvn test -Dtest=LoginTest#successfulLoginTest
```

---

## Troubleshooting

### ChromeDriver version mismatch
```bash
# Update Chrome driver version in pom.xml to match your Chrome browser
# Or let WebDriverManager handle it automatically (already configured)
```

### All tests failing
```bash
# Make sure the Practice Automation Testing site is accessible
curl -I http://practice.automationtesting.in/my-account/
```

### Compilation errors after uncommenting solutions
```bash
# Clean and recompile
mvn clean compile
```

---

## Next Steps

1. ✅ Run traditional tests to see failures
2. ✅ Enable self-healing and see improvement
3. ✅ Read `SELF_HEALING_GUIDE.md` for deep dive
4. ✅ Try implementing `SelfHealingElement` yourself
5. ✅ Check `BUG_REPORTS.md` to understand each bug
6. ✅ Explore the blog post: `blog-post.html`

---

## Demo Video Recording

Want to record a demo? Run with visible browser:

```bash
# Make sure headless mode is OFF in BaseTest.java
mvn clean test

# Tests will run in visible Chrome browser
# Record your screen to show self-healing in action!
```

---

**Questions?** Check the comprehensive guides:
- `README.md` - Project overview
- `SELF_HEALING_GUIDE.md` - Complete implementation guide
- `EXERCISES.md` - Hands-on practice with hints
- `BUG_REPORTS.md` - Bug details and solutions
