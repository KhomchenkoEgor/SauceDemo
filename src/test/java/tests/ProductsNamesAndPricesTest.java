package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProductsNamesAndPricesTest extends BaseTest {

    @DataProvider(name = "productAndPrice")
    public Object[][] getData() {
        return new Object[][]{
                {"Sauce Labs Backpack", "$29.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"}
        };
    }

    @Test(dataProvider = "getData")
    public void testProductsAndPrice(String name, String price) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(name);
        productsPage.clickCart();
        String actualName = productsPage.getProductName(name);
        Assert.assertEquals(actualName, name, "Товар не найден в корзине!");
        String actualPrice = productsPage.getProductPrice(name);
        Assert.assertEquals(actualPrice, price, "Цена товара " + name + " не совпадает!");
    }
}