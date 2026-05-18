package tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class CartTest extends BaseTest {

    @Test(
            testName = "Добавление в корзину и переход",
            description = "Проверка успешного перехода в корзину после добавления товаров",
            groups = {"smoke", "regression"})
    public void checkCart() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickCart();
        assertEquals(cartPage.getTitle(), "Your Cart", "OK");
    }
}