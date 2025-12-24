package mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SearchPage {
    private AndroidDriver driver;

    // Локаторы
    private By skipButton = AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private By searchContainer = AppiumBy.accessibilityId("Search Wikipedia");
    private By searchInput = AppiumBy.id("org.wikipedia:id/search_src_text");

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickSkip() {
        driver.findElement(skipButton).click();
    }

    public void startSearch(String text) {
        driver.findElement(searchContainer).click();
        driver.findElement(searchInput).sendKeys(text);
    }
}