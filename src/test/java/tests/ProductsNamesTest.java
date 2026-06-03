package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProductsNamesTest extends BaseTest {

    @Epic("Sauce Demo 2")
    @Feature("Каталог и цены")
    @Owner("Khomchenko E.S.")

    @DataProvider(name = "productsData")
    public Object[][] getProducts() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Test.allTheThings() T-Shirt (Red)"},
                {"Sauce Labs Bike Light"}
        };
    }

    @Test(
            testName = "Проверка имен товаров",
            description = "Проверка корректного отображения имени каждого товара в корзине",
            groups = {"regression"},
            dataProvider = "productsData")
    @Story("Проверка метаданных товаров в корзине")
    @Severity(SeverityLevel.NORMAL)
    public void checkEachProduct(String productName) {
        log.info("Тест: Проверка имени товара в корзине для [{}]", productName);
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened();
        productsPage.addToCart(productName)
                .clickCart();
        String actualName = productsPage.getProductName(productName);
        assertEquals(actualName, productName, "Товар не найден в корзине!");
    }
}