package tests;

import io.qameta.allure.*;
import org.testng.Assert;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class CartTest extends BaseTest {

    @Test(
            testName = "Добавление в корзину и переход",
            description = "Проверка успешного перехода в корзину после добавления товаров",
            groups = {"smoke", "regression"})
    @Owner("Khomchenko E.S.")
    @Epic("Sauce Demo 2")
    @Feature("Log in")
    @Story("Log in with positive credentials")
    @Description("Проверка логина с валидными кредами")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Link(name = "Аналитика", url = "https://www.saucedemo.com/")
    @TmsLink("SD-T02")
    @Issue("BUG-02")
    public void checkCart() {

        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .clickCart();
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "SO BAAAAAAD");
        assertEquals(cartPage.getProductNameFromCart(0), "Sauce Labs Backpack", "SO BAAAAAD");
        assertEquals(cartPage.getProductNameFromCart(1), "Test.allTheThings() T-Shirt (Red)", "SO BAAAAAD");
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"), "SO BAAAAAD");
    }
}