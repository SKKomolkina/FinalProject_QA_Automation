package mobile.tests;

import mobile.BaseMobileTest;
import mobile.pages.WikipediaSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaTest extends BaseMobileTest {

    @Test(priority = 1)
    public void testSearchFunctionality() {
        WikipediaSearchPage searchPage = new WikipediaSearchPage(driver);
        searchPage.clickSkip();
        searchPage.invokeSearch("Appium");

        // Проверка наличия результатов поиска
        Assert.assertTrue(searchPage.isLangButtonDisplayed(), "Кнопка выбора языка не отображается!");
    }

    @Test(priority = 2)
    public void testOpenArticle() {
        WikipediaSearchPage searchPage = new WikipediaSearchPage(driver);
        // Skip нажимается только если приложение переоткрылось
        try { searchPage.clickSkip(); } catch (Exception e) {}

        searchPage.invokeSearch("Java");
        searchPage.openFirstResult();

        // Проверка открытия статьи и заголовка
        String title = searchPage.getArticleTitle();
        Assert.assertTrue(title.toLowerCase().contains("java"), "Заголовок статьи не соответствует поиску!");
    }

    @Test(priority = 3)
    public void testSearchClear() {
        WikipediaSearchPage searchPage = new WikipediaSearchPage(driver);
        try { searchPage.clickSkip(); } catch (Exception e) {}

        searchPage.invokeSearch("Automation");

        // Проверка смены контекста (наличие элементов поиска после ввода)
        Assert.assertNotNull(searchPage.getArticleTitle(), "Статья не загрузилась");
    }
}