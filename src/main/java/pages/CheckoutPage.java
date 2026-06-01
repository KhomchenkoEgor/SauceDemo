package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By ZIP_POSTAL_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.cssSelector("[data-test=continue]");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    private final By COMPLETE_MESSAGE = By.cssSelector("[data-test=complete-header]");
    private final By FINISH_BUTTON = By.cssSelector("[data-test=finish]");
    private final By TITLE_CHECKOUT = By.cssSelector("[data-test = title]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия CheckoutPage")
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_CHECKOUT));
        return this;
    }

    @Override
    @Step("Открыть CheckoutPage")
    public CheckoutPage openPage() {
        driver.get(BASE_URL + "/checkout-step-one.html");
        return isPageOpened();
    }

    @Step("Заполнить данные для оформления заказа: Имя='{firstName}', Фамилия='{lastName}', Почтовый индекс='{zipPostalCode}'")
    public CheckoutPage checkout(String firstName, String lastName, String zipPostalCode) {
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_POSTAL_FIELD).sendKeys(zipPostalCode);
        return this;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    @Step("Нажать кнопку Continue")
    public CheckoutPage clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Нажать кнопку Finish")
    public CheckoutPage clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
        return this;
    }

    public String getCompletedMessage() {
        return driver.findElement(COMPLETE_MESSAGE).getText();
    }

    public String getTitle() {
        return driver.findElement(TITLE_CHECKOUT).getText();
    }
}