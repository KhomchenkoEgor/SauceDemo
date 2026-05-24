package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndTest extends BaseTest {

    String expectedCompleteMessage = "Thank you for your order!";

    @Test(
            testName = "Сквозной сценарий покупки",
            description = "Полный цикл покупки товаров от страницы авторизации до финального подтверждения заказа",
            groups = {"smoke"})
    @Story("Покупка товаров через корзину (End-to-End)")
    @Severity(SeverityLevel.CRITICAL)
    public void checkEToETest() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("Igor", "Shustov", "1111");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        assertEquals(checkoutPage.getCompletedMessage(), expectedCompleteMessage, "Сообщение об успешном заказе не совпадает");
    }
}
