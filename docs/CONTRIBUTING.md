# Contributing to Selenium TestNG Automation Framework

Thank you for your interest in contributing! This project aims to help manual testers transition to automation, and your contributions can help make that journey easier for others.

---

## 🎯 How You Can Contribute

### 1. **As a Learner**
- **Fork this repository** and practice fixing the intentional bugs
- **Complete the exercises** in EXERCISES.md
- **Share your experience** - What was confusing? What helped you learn?
- **Ask questions** by creating GitHub Issues

### 2. **As a Contributor**
- **Improve documentation** - Fix typos, clarify explanations, add examples
- **Suggest new exercises** - What would help learners practice more?
- **Report bugs** - Found actual bugs (not the intentional ones)? Let us know!
- **Share resources** - Know great Selenium tutorials? Share them!

---

## 🍴 How to Fork and Practice

### Step 1: Fork the Repository
1. Click the **"Fork"** button at the top-right of this GitHub page
2. This creates your own copy of the project

### Step 2: Clone Your Fork
```bash
git clone https://github.com/YOUR_USERNAME/healenium-selenium-demo.git
cd healenium-selenium-demo
```

### Step 3: Start Learning
1. Follow the **[README.md](README.md)** to set up the project
2. Run the tests and see them fail (this is intentional!)
3. Follow **[DEBUGGING_GUIDE.md](DEBUGGING_GUIDE.md)** to fix the bugs
4. Complete exercises in **[EXERCISES.md](EXERCISES.md)**

### Step 4: Track Your Progress
- Create a new branch for your fixes:
```bash
git checkout -b my-fixes
```
- Commit your changes as you fix bugs:
```bash
git add .
git commit -m "Fixed login button locator"
git push origin my-fixes
```

### Step 5: Compare with Solutions (Optional)
- Once available, check the `solutions` branch to compare your fixes:
```bash
git fetch origin
git diff origin/solutions
```

---

## 💡 Suggesting Improvements

### Found Something to Improve?

**For Documentation:**
1. Click the pencil icon on any .md file in GitHub
2. Make your edits
3. Submit a Pull Request with a clear description

**For Code:**
1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-improvement`
3. Make your changes
4. Test that everything still works: `mvn clean test`
5. Commit with a clear message: `git commit -m "Add explicit wait helper for dropdowns"`
6. Push: `git push origin feature/your-improvement`
7. Create a Pull Request

---

## 🐛 Reporting Issues

### Before Creating an Issue:
1. Check existing issues to avoid duplicates
2. Make sure it's not an **intentional bug** for learning (see DEBUGGING_GUIDE.md)
3. Try the troubleshooting steps in README.md

### When Creating an Issue:
Use our issue templates and include:
- **Clear title**: e.g., "Maven build fails on Windows"
- **Environment**: OS, Java version, Maven version
- **Steps to reproduce**: What did you do?
- **Expected vs Actual**: What should happen vs what happened?
- **Screenshots/Logs**: If applicable

**Issue Categories:**
- 🐛 **Bug Report**: Actual bugs (not intentional learning bugs)
- 📚 **Documentation**: Unclear explanations, typos, missing info
- 💡 **Enhancement**: New feature or exercise suggestions
- ❓ **Question**: Need help understanding something?

---

## 📝 Pull Request Guidelines

### Good PR Checklist:
- [ ] **Clear title** - Describes what the PR does
- [ ] **Description** - Explains why the change is needed
- [ ] **Tests pass** - Run `mvn clean test` locally
- [ ] **Documentation updated** - If you changed behavior, update docs
- [ ] **Clean commits** - Meaningful commit messages
- [ ] **One thing at a time** - Don't mix unrelated changes

### Example Good PR:
```
Title: Add dropdown handling example to EXERCISES.md

Description:
Many learners struggle with dropdown elements. This PR adds:
- Exercise 6: Working with Select dropdowns
- Example code snippet
- Common pitfalls section

This addresses issue #123.
```

---

## 🌟 Code of Conduct

### Our Standards:
✅ **Be welcoming** - This project is for learners; everyone starts somewhere  
✅ **Be respectful** - Disagree professionally  
✅ **Be helpful** - Share knowledge generously  
✅ **Be patient** - Learning takes time  

❌ **Don't:** Mock beginners, submit AI-generated spam, be dismissive of questions

---

## 🎓 Recognition

Contributors will be recognized in:
- **README.md** acknowledgments section
- Commit history with your GitHub profile
- Special thanks in release notes for significant contributions

---

## 💬 Need Help Contributing?

**New to GitHub?** Check out:
- [GitHub's First Contributions Guide](https://github.com/firstcontributions/first-contributions)
- [How to Create a Pull Request](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request)

**Questions about this project?**
- Create an issue with the "Question" label
- Tag it with `good-first-issue` if you're new to contributing

---

## 📞 Contact

**Project Maintainer:** Carolina Steadham ([@steadhac](https://github.com/steadhac))  
**Questions?** Open an issue with the "Question" label  
**Purpose:** Help manual testers transition to automation testing

---

Thank you for helping make test automation more accessible! 🚀
