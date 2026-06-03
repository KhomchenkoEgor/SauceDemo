package pages;

import dto.Customer;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckoutPage extends BasePage {

    By FIRST_NAME_FIELD = By.id("first-name");
    By LAST_NAME_FIELD = By.id("last-name");
    By ZIP_POSTAL_FIELD = By.id("postal-code");
    By CONTINUE_BUTTON = By.cssSelector("[data-test=continue]");
    By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    By COMPLETE_MESSAGE = By.cssSelector("[data-test=complete-header]");
    By FINISH_BUTTON = By.cssSelector("[data-test=finish]");
    By TITLE_CHECKOUT = By.cssSelector("[data-test = title]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия CheckoutPage")
    public CheckoutPage isPageOpened() {
        log.info("Ожидание загрузки первого шага оформления заказа");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_CHECKOUT));
        return this;
    }

    @Override
    @Step("Открыть CheckoutPage")
    public CheckoutPage openPage() {
        log.info("Переход на страницу Checkout по URL");
        driver.get(BASE_URL + "/checkout-step-one.html");
        return isPageOpened();
    }

    @Step("Заполнить данные для оформления заказа: Имя='{firstName}', Фамилия='{lastName}', Почтовый индекс='{zipPostalCode}'")
    public CheckoutPage checkout(Customer customer) {
        log.info("Заполнение формы доставки: Имя='{}', Фамилия='{}', Код='{}'",
                customer.getFirstName(), customer.getLastName(), customer.getZipPostalCode());
        driver.findElement(FIRST_NAME_FIELD).sendKeys(customer.getFirstName());
        driver.findElement(LAST_NAME_FIELD).sendKeys(customer.getLastName());
        driver.findElement(ZIP_POSTAL_FIELD).sendKeys(customer.getZipPostalCode());
        return this;
    }

    public String getErrorMessage() {
        String errorText = driver.findElement(ERROR_MESSAGE).getText();
        log.warn("Получена ошибка заполнения полей доставки: {}", errorText);
        return errorText;
    }

    @Step("Нажать кнопку Continue")
    public CheckoutPage clickContinue() {
        log.info("Переход ко второму шагу Checkout (клик по Continue)");
        driver.findElement(CONTINUE_BUTTON).click();
        return this;
    }

    @Step("Нажать кнопку Finish")
    public CheckoutPage clickFinish() {
        log.info("Завершение заказа (клик по Finish)");
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