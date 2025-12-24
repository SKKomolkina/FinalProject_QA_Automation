package mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WikipediaSearchPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    private By skipButton = AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By searchContainer = AppiumBy.accessibilityId("Search Wikipedia");
    private By searchInput = AppiumBy.id("org.wikipedia:id/search_src_text");
    private By firstResult = AppiumBy.id("org.wikipedia:id/page_list_item_title");
    private By articleTitle = AppiumBy.id("org.wikipedia:id/view_page_title_text");
    private By langButton = AppiumBy.id("org.wikipedia:id/search_lang_button");

    public WikipediaSearchPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickSkip() {
        wait.until(ExpectedConditions.elementToBeClickable(skipButton)).click();
    }

    public void invokeSearch(String text) {
        wait.until(ExpectedConditions.elementToBeClickable(searchContainer)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput)).sendKeys(text);
    }

    public void openFirstResult() {
        wait.until(ExpectedConditions.elementToBeClickable(firstResult)).click();
    }

    public String getArticleTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(articleTitle)).getText();
    }

    public boolean isLangButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(langButton)).isDisplayed();
    }
}