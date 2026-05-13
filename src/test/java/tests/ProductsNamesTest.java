package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductsNamesTest extends BaseTest {

    @DataProvider(name = "productsData")
    public Object[][] getProducts() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Test.allTheThings() T-Shirt (Red)"},
                {"Sauce Labs Bike Light"}
        };
    }

    @Test(dataProvider = "productsData")
    public void checkEachProduct(String productName) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(productName);
        productsPage.clickCart();
        String actualName = productsPage.getProductName(productName);
        Assert.assertEquals(actualName, productName, "Товар не найден в корзине!");
    }
}