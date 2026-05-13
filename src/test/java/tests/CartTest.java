package tests;

import org.testng.Assert;

import org.testng.annotations.Test;


public class CartTest extends BaseTest {

    @Test
    public void checkCart() {

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");
        productsPage.clickCart();
        Assert.assertEquals(cartPage.getTitle(), "Your Cart", "OK");
    }
}