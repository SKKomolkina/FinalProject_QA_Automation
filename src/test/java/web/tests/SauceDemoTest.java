package web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import web.pages.InventoryPage;
import web.pages.LoginPage;
import web.BaseWebTest;

import java.time.Duration;

public class SauceDemoTest extends BaseWebTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Заголовок страницы не совпадает!");

        Assert.assertTrue(inventoryPage.getItemsCount() > 0, "Список товаров пуст!");
    }

    @Test
    public void testSortProductsByPrice() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.selectSortOption("lohi");
    }

    @Test
    public void testAddToCart() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement removeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));

        Assert.assertEquals(inventoryPage.getCartItemsCount(), "1");
    }

    @Test
    public void testNavigationToCart() {
        loginPage.login("standard_user", "secret_sauce");

        inventoryPage.addToCart();

        inventoryPage.goToCart();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("cart.html"), "Мы не перешли в корзину!");
    }
}