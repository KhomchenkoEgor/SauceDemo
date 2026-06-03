package pages;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class BasePage {

    final WebDriver driver;
    final WebDriverWait wait;
    public final String BASE_URL = "https://saucedemo.com";

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void jSclick(WebElement element) {
        log.info("Клик через JavaScript по элементу: {}", element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    @Step("Ожидание полной загрузки DOM-структуры страницы")
    public void waitForPageLoaded() {
        log.info("Ожидание полной загрузки document.readyState");
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").toString().equals("complete")
        );
    }

    public abstract BasePage isPageOpened();

    public abstract BasePage openPage();
}