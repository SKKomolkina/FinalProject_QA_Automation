package web.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import web.pages.InventoryPage;
import web.pages.LoginPage;
import web.BaseWebTest;

import java.time.Duration;

public class SauceDemoTest extends BaseWebTest {

    @Test
    public void testSuccessfulLogin() {
        // 1. Открываем сайт
        driver.get("https://www.saucedemo.com/");

        // 2. Выполняем логин
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // 3. Проверяем результат
        InventoryPage inventoryPage = new InventoryPage(driver);

        // Проверка заголовка страницы
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Заголовок страницы не совпадает!");

        // Проверка, что товары отобразились (их должно быть 6)
        Assert.assertTrue(inventoryPage.getItemsCount() > 0, "Список товаров пуст!");
    }

    @Test
    public void testSortProductsByPrice() {
        driver.get("https://www.saucedemo.com/");
        new LoginPage(driver).login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectSortOption("lohi"); // Сортировка по цене Low to High
    }

    @Test
    public void testAddToCart() {
        driver.get("https://www.saucedemo.com/");
        new LoginPage(driver).login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addToCart();

        // Ждем, пока кнопка "Remove" появится, вместо прямого поиска
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement removeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("remove-sauce-labs-backpack")));

        System.out.println("Текст кнопки после клика: " + removeBtn.getText());

        Assert.assertEquals(inventoryPage.getCartItemsCount(), "1");
    }
}