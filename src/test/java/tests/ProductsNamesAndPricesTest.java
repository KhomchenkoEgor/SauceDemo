package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProductsNamesAndPricesTest extends BaseTest {

    @Epic("Sauce Demo 2")
    @Feature("Каталог и цены")
    @Owner("Khomchenko E.S.")

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
    @Story("Проверка метаданных товаров в корзине")
    @Severity(SeverityLevel.NORMAL)
    public void testProductsAndPrice(String name, String price) {
        log.info("Тест: Проверка соответствия цены товара [{}] значению [{}]", name, price);
        SoftAssert softAssert = new SoftAssert();
        loginPage.openPage()
                .login("standard_user", "secret_sauce");

        productsPage.isPageOpened()
                .addToCart(name);

        productsPage.clickCart();
        String actualName = productsPage.getProductName(name);
        softAssert.assertEquals(actualName, name, "Товар не найден в корзине!");
        String actualPrice = productsPage.getProductPrice(name);
        softAssert.assertEquals(actualPrice, price, "Цена товара " + name + " не совпадает!");
        softAssert.assertAll();
    }
}