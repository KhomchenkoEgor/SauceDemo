package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test(
            testName = "Успешный первый шаг Checkout",
            description = "Валидное заполнение данных на первом шаге оформления заказа",
            groups = {"smoke", "regression"})
    public void checkCheckoutWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("Igor", "Shustov", "111");
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getTitle(), "Checkout: Overview", "OK");
    }

    @Test(
            testName = "Checkout с пустыми полями",
            description = "Негативный тест: проверка валидации при отправке пустой формы",
            groups = {"regression"})
    public void checkCheckoutWithEmptyFields() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("", "", "");
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "SO BAD");
    }

    @Test(
            testName = "Checkout с пустой фамилией",
            description = "Негативный тест: проверка валидации при отсутствии фамилии",
            groups = {"regression"})
    public void checkLoginWithEmptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("111", "", "111");
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required", "SO BAD");
    }

    @Test(
            testName = "Checkout с пустым индексом",
            description = "Негативный тест: проверка валидации при отсутствии почтового индекса",
            groups = {"regression"})
    public void checkCheckoutWithEmptyZipCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("test", "test", "");
        checkoutPage.clickContinue();
        assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required", "SO BAD");
    }
}