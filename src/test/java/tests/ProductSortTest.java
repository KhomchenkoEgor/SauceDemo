package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductSortTest extends BaseTest {

    @Test(
            testName = "Сортировка по имени Z-A",
            description = "Проверка алфавитной сортировки товаров в обратном порядке",
            groups = {"regression"})
    public void checkSortZA() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.selectSortOption("za");
        // 2. Получаем актуальный список имен товаров
        List<String> actualNames = productsPage.getProductNames();
        // 3. Создаем эталонный список (копируем актуальный и сортируем его в обратном порядке)
        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());
        // 4. Сравниваем
        assertEquals(actualNames, expectedNames, "Сортировка (Z to A) неверна!");
    }

    @Test(
            testName = "Сортировка цен Low to High",
            description = "Проверка сортировки стоимости товаров от меньшей к большей",
            groups = {"regression"})
    public void checkPriceSortLowToHigh() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        // 1. Выбираем сортировку "Price (low to high)"
        productsPage.selectSortOption("lohi");
        // 2. Получаем актуальный список цен
        List<Double> actualPrices = productsPage.getProductPrices();
        // 3. Создаем ожидаемый список (копируем и сортируем сами)
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        // 4. Сравниваем списки
        assertEquals(actualPrices, expectedPrices, "Цены отсортированы неверно!");
    }
}