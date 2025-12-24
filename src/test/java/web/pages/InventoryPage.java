package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    private By title = By.className("title");
    private By inventoryItems = By.className("inventory_item");

    // Локаторы
    private By sortContainer = By.className("product_sort_container");
    private By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(title).getText();
    }

    public int getItemsCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void selectSortOption(String value) {
        driver.findElement(sortContainer).sendKeys(value);
    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

        try {
            button.click();
        } catch (Exception e) {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", button);
        }
    }

    public String getCartItemsCount() {
        // Создаем объект ожидания
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Ждем появления элемента в DOM и его видимости
        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));

        return badge.getText();
    }
}