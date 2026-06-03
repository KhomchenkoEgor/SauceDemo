package pages;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginPage extends BasePage {

    By USERNAME_FIELD = By.id("user-name");
    By PASSWORD_FIELD = By.id("password");
    By LOGIN_BUTTON = By.id("login-button");
    By ERROR_MESSAGE = By.cssSelector("[data-test=error]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия LoginPage")
    public LoginPage isPageOpened() {
        log.info("Ожидание видимости кнопки Login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Override
    @Step("Открытие страницы LoginPage")
    public LoginPage openPage() {
        log.info("Открытие страницы: {}", BASE_URL);
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вход в систему с именем пользователя: '{user}' и паролем '{password}'")
    public ProductsPage login(String user, String password) {
        log.info("Попытка входа под пользователем: {}", user);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

//    public String getErrorMessage() {
//        String errorText = driver.findElement(ERROR_MESSAGE).getText();
//        log.warn("Получена ошибка валидации при логине: {}", errorText);
//        return errorText;
//    }
    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}