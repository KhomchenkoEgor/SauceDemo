package tests;

import io.qameta.allure.*;
import org.testng.Assert;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


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

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickCart();
        assertEquals(cartPage.getTitle(), "Your Cart", "OK");
    }
}