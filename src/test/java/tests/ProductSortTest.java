package tests;


import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ProductSortTest extends BaseTest {

    @Epic("Sauce Demo 2")
    @Feature("Каталог и цены")
    @Owner("Khomchenko E.S.")
    @Test(
            testName = "Сортировка по имени Z-A",
            description = "Проверка алфавитной сортировки товаров в обратном порядке",
            groups = {"regression"})
    @Story("Сортировка товаров в каталоге")
    @Severity(SeverityLevel.NORMAL)
    public void checkSortZA() {
        log.info("Тест: Алфавитная сортировка товаров Z-A");
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .selectSortOption("za");
        List<String> actualNames = productsPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());
        assertEquals(actualNames, expectedNames, "Сортировка (Z to A) неверна!");
    }

    @Test(
            testName = "Сортировка цен Low to High",
            description = "Проверка сортировки стоимости товаров от меньшей к большей",
            groups = {"regression"})
    @Story("Сортировка товаров в каталоге")
    @Severity(SeverityLevel.NORMAL)
    public void checkPriceSortLowToHigh() {
        log.info("Тест: Сортировка стоимости товаров Low to High");
        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .selectSortOption("lohi");
        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        assertEquals(actualPrices, expectedPrices, "Цены отсортированы неверно!");
    }
}