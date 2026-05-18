package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class ProductsNamesAndPricesTest extends BaseTest {

    @DataProvider(name = "productAndPrice")
    public Object[][] getData() {
        return new Object[][]{
                {"Sauce Labs Backpack", "$29.99"},
                {"Test.allTheThings() T-Shirt (Red)", "$15.99"}
        };
    }

    @Test(
            testName = "Проверка цен в корзине",
            description = "Проверка соответствия цен конкретных товаров после добавления в корзину",
            groups = {"regression"},
            dataProvider = "productAndPrice")
    public void testProductsAndPrice(String name, String price) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart(name);
        productsPage.clickCart();
        String actualName = productsPage.getProductName(name);
        softAssert.assertEquals(actualName, name, "Товар не найден в корзине!");
        String actualPrice = productsPage.getProductPrice(name);
        softAssert.assertEquals(actualPrice, price, "Цена товара " + name + " не совпадает!");
        softAssert.assertAll();
    }
}
