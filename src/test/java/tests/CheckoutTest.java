package tests;

import dto.Customer;
import io.qameta.allure.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CheckoutTest extends BaseTest {

    @Epic("Sauce Demo 2")
    @Feature("Оформление заказа (Checkout)")
    @Owner("Khomchenko E.S.")

    @Test(
            testName = "Успешный первый шаг Checkout",
            description = "Валидное заполнение данных на первом шаге оформления заказа",
            groups = {"smoke", "regression"})
    @Story("Успешное заполнение формы доставки")
    @Severity(SeverityLevel.CRITICAL)
    public void checkCheckoutWithPositiveCred() {
        log.info("Тест: Заполнение валидных данных доставки на первом шаге Checkout");
        Customer customer = new Customer("Igor", "Shustov", "111");

        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .clickCart()
                .clickCheckout()
                .checkout(customer)
                .clickContinue();

        assertEquals(checkoutPage.getTitle(), "Checkout: Overview", "Переход ко второму шагу не выполнен!");
    }

    @Test(
            testName = "Checkout с пустыми полями",
            description = "Негативный тест: проверка валидации при отправке пустой формы",
            groups = {"regression"})
    @Story("Валидация обязательных полей при отправке пустой формы")
    @Severity(SeverityLevel.NORMAL)
    public void checkCheckoutWithEmptyFields() {
        log.info("Тест: Попытка отправить пустую форму Checkout");
        Customer customer = new Customer("", "", "");

        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .clickCart()
                .clickCheckout()
                .checkout(customer)
                .clickContinue();
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "SO BAD");
    }

    @Test(
            testName = "Checkout с пустой фамилией",
            description = "Негативный тест: проверка валидации при отсутствии фамилии",
            groups = {"regression"})
    @Story("Валидация обязательных полей при отсутствии фамилии")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithEmptyLastName() {
        log.info("Тест: Оформление заказа с отсутствующей фамилией");
        Customer customer = new Customer("111", "", "111");

        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .clickCart()
                .clickCheckout()
                .checkout(customer)
                .clickContinue();
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required", "SO BAD");
    }

    @Test(
            testName = "Checkout с пустым индексом",
            description = "Негативный тест: проверка валидации при отсутствии почтового индекса",
            groups = {"regression"})
    @Story("Валидация обязательных полей при отсутствии почтового индекса")
    @Severity(SeverityLevel.NORMAL)
    public void checkCheckoutWithEmptyZipCode() {
        log.info("Тест: Оформление заказа с отсутствующим почтовым кодом");
        Customer customer = new Customer("test", "test", "");

        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .clickCart()
                .clickCheckout()
                .checkout(customer)
                .clickContinue();
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required", "SO BAD");
    }
}