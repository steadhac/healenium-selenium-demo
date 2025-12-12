package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for Product Page (Example E-commerce page)
 * Demonstrates working with lists and dynamic elements
 */
public class ProductPage {
    
    private WebDriver driver;
    
    // Page Elements
    @FindBy(id = "search-box")
    WebElement searchBox;
    
    @FindBy(id = "search-button")
    WebElement searchButton;
    
    @FindBy(className = "product-title")
    WebElement productTitle;
    
    @FindBy(id = "add-to-cart")
    WebElement addToCartButton;
    
    @FindBy(className = "price")
    WebElement productPrice;
    
    @FindBy(id = "cart-icon")
    WebElement cartIcon;
    
    @FindBy(className = "cart-count")
    WebElement cartCount;
    
    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Page Actions
    
    /**
     * Search for a product
     * @param productName - name of product to search
     */
    public void searchProduct(String productName) {
        searchBox.clear();
        searchBox.sendKeys(productName);
        searchButton.click();
    }
    
    /**
     * Get product title
     * @return product title text
     */
    public String getProductTitle() {
        return productTitle.getText();
    }
    
    /**
     * Get product price
     * @return product price text
     */
    public String getProductPrice() {
        return productPrice.getText();
    }
    
    /**
     * Click add to cart button
     */
    public void addToCart() {
        addToCartButton.click();
    }
    
    /**
     * Get cart count
     * @return number of items in cart
     */
    public String getCartCount() {
        return cartCount.getText();
    }
    
    /**
     * Click on cart icon
     */
    public void goToCart() {
        cartIcon.click();
    }
    
    /**
     * Select a product from search results by name
     * @param productName - name of product to select
     */
    public void selectProduct(String productName) {
        WebElement product = driver.findElement(
            By.xpath("//div[contains(text(),'" + productName + "')]")
        );
        product.click();
    }
    
    /**
     * Check if product is displayed
     * @return true if product is visible
     */
    public boolean isProductDisplayed() {
        try {
            return productTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
