#!/bin/bash

# 🚀 Self-Healing Locators Demo Script
# This script demonstrates the before/after comparison

set -e  # Exit on error

echo "════════════════════════════════════════════════════════════"
echo "  🔧 Self-Healing Locators Demo"
echo "  Author: Carolina Steadham"
echo "════════════════════════════════════════════════════════════"
echo ""

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Check prerequisites
echo -e "${BLUE}📋 Checking prerequisites...${NC}"
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Java not found. Please install Java 11+${NC}"
    exit 1
fi

if ! command -v mvn &> /dev/null; then
    echo -e "${RED}❌ Maven not found. Please install Maven${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Java: $(java -version 2>&1 | head -n 1)${NC}"
echo -e "${GREEN}✅ Maven: $(mvn -version | head -n 1)${NC}"
echo ""

# Function to run tests and capture results
run_tests() {
    local mode=$1
    echo -e "${BLUE}Running tests in ${mode} mode...${NC}"
    
    mvn clean test > test-output-${mode}.txt 2>&1 || true
    
    # Extract results
    TESTS_RUN=$(grep -oP "Tests run: \K\d+" test-output-${mode}.txt | tail -1)
    FAILURES=$(grep -oP "Failures: \K\d+" test-output-${mode}.txt | tail -1)
    ERRORS=$(grep -oP "Errors: \K\d+" test-output-${mode}.txt | tail -1)
    PASSED=$((TESTS_RUN - FAILURES - ERRORS))
    
    if [ "$TESTS_RUN" -gt 0 ]; then
        PASS_RATE=$((PASSED * 100 / TESTS_RUN))
    else
        PASS_RATE=0
    fi
    
    echo "$TESTS_RUN,$PASSED,$FAILURES,$ERRORS,$PASS_RATE"
}

# Ask user which mode to run
echo -e "${YELLOW}Choose demo mode:${NC}"
echo "  1) Run traditional tests only (with bugs)"
echo "  2) Run self-healing tests only (bugs auto-fixed)"
echo "  3) Compare both (recommended for demo)"
echo ""
read -p "Enter choice (1-3): " choice

case $choice in
    1)
        echo ""
        echo -e "${YELLOW}════════════════════════════════════════════════════════════${NC}"
        echo -e "${YELLOW}  🐛 Running TRADITIONAL tests (with intentional bugs)${NC}"
        echo -e "${YELLOW}════════════════════════════════════════════════════════════${NC}"
        echo ""
        
        mvn clean test
        
        echo ""
        echo -e "${RED}As you can see, most tests fail due to wrong locators!${NC}"
        echo -e "${YELLOW}💡 Next step: Enable self-healing to fix these failures${NC}"
        ;;
        
    2)
        echo ""
        echo -e "${YELLOW}⚠️  Make sure you've uncommented solutions in SelfHealingElement.java${NC}"
        echo -e "${YELLOW}⚠️  And updated LoginTest.java to use *SelfHealing page objects${NC}"
        read -p "Have you done this? (y/n): " ready
        
        if [ "$ready" != "y" ]; then
            echo -e "${RED}Please follow QUICK_START.md Option 2 first!${NC}"
            exit 1
        fi
        
        echo ""
        echo -e "${YELLOW}════════════════════════════════════════════════════════════${NC}"
        echo -e "${YELLOW}  ✨ Running SELF-HEALING tests (auto-fixing bugs)${NC}"
        echo -e "${YELLOW}════════════════════════════════════════════════════════════${NC}"
        echo ""
        
        mvn clean test
        
        echo ""
        echo -e "${GREEN}✅ Most tests now pass thanks to self-healing locators!${NC}"
        echo -e "${YELLOW}💡 Check the logs for '⚠️ Self-healing activated!' messages${NC}"
        ;;
        
    3)
        echo ""
        echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
        echo -e "${BLUE}  📊 COMPARISON MODE${NC}"
        echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
        echo ""
        
        # Phase 1: Traditional
        echo -e "${YELLOW}Phase 1: Running traditional tests...${NC}"
        TRADITIONAL_RESULTS=$(run_tests "traditional")
        
        echo ""
        echo -e "${YELLOW}Phase 2: Ready to run self-healing tests?${NC}"
        echo -e "${YELLOW}Please ensure:${NC}"
        echo "  1. Solutions are uncommented in SelfHealingElement.java"
        echo "  2. LoginTest.java uses *SelfHealing page objects"
        echo ""
        read -p "Ready to continue? (y/n): " ready
        
        if [ "$ready" != "y" ]; then
            echo -e "${RED}Demo stopped. Complete setup and try again.${NC}"
            exit 1
        fi
        
        echo ""
        echo -e "${YELLOW}Phase 2: Running self-healing tests...${NC}"
        SELFHEALING_RESULTS=$(run_tests "selfhealing")
        
        # Parse results
        IFS=',' read -r T_TESTS T_PASSED T_FAILED T_ERRORS T_RATE <<< "$TRADITIONAL_RESULTS"
        IFS=',' read -r S_TESTS S_PASSED S_FAILED S_ERRORS S_RATE <<< "$SELFHEALING_RESULTS"
        
        # Display comparison
        echo ""
        echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
        echo -e "${BLUE}  📊 RESULTS COMPARISON${NC}"
        echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
        echo ""
        
        printf "%-30s %15s %15s\n" "Metric" "Traditional" "Self-Healing"
        echo "────────────────────────────────────────────────────────────"
        printf "%-30s %15s %15s\n" "Tests Run" "$T_TESTS" "$S_TESTS"
        printf "%-30s ${GREEN}%15s${NC} ${GREEN}%15s${NC}\n" "Passed" "$T_PASSED" "$S_PASSED"
        printf "%-30s ${RED}%15s${NC} ${RED}%15s${NC}\n" "Failed" "$T_FAILED" "$S_FAILED"
        printf "%-30s %15s %15s\n" "Errors" "$T_ERRORS" "$S_ERRORS"
        printf "%-30s ${YELLOW}%14s%%${NC} ${GREEN}%14s%%${NC}\n" "Pass Rate" "$T_RATE" "$S_RATE"
        echo "────────────────────────────────────────────────────────────"
        echo ""
        
        IMPROVEMENT=$((S_RATE - T_RATE))
        BUGS_FIXED=$((S_PASSED - T_PASSED))
        
        echo -e "${GREEN}✨ Improvement: +${IMPROVEMENT}% pass rate${NC}"
        echo -e "${GREEN}🐛 Bugs Auto-Fixed: ${BUGS_FIXED} tests recovered${NC}"
        echo ""
        
        echo -e "${BLUE}📄 Detailed logs saved to:${NC}"
        echo "  • test-output-traditional.txt"
        echo "  • test-output-selfhealing.txt"
        echo ""
        
        echo -e "${YELLOW}💡 Search for '⚠️ Self-healing activated!' in selfhealing logs${NC}"
        echo -e "${YELLOW}   to see which locators were automatically recovered${NC}"
        ;;
        
    *)
        echo -e "${RED}Invalid choice. Exiting.${NC}"
        exit 1
        ;;
esac

echo ""
echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
echo -e "${GREEN}✅ Demo complete!${NC}"
echo ""
echo "Next steps:"
echo "  • Read SELF_HEALING_GUIDE.md for implementation details"
echo "  • Try EXERCISES.md to implement it yourself"
echo "  • Check BUG_REPORTS.md for bug analysis"
echo "  • Open blog-post.html for interactive learning"
echo -e "${BLUE}════════════════════════════════════════════════════════════${NC}"
