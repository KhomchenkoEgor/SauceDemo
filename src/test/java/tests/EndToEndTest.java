package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndToEndTest extends BaseTest {

    String expectedCompleteMessage = "Thank you for your order!";

    @Test(
            testName = "Сквозной сценарий покупки",
            description = "Полный цикл покупки товаров от страницы авторизации до финального подтверждения заказа",
            groups = {"smoke"})
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
