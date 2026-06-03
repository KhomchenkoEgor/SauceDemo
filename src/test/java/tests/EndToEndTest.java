package tests;

import dto.Customer;
import io.qameta.allure.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@EqualsAndHashCode(callSuper = true)
@Log4j2
public class EndToEndTest extends BaseTest {

    String expectedCompleteMessage = "Thank you for your order!";

    @Test(
            testName = "Сквозной сценарий покупки",
            description = "Полный цикл покупки товаров от страницы авторизации до финального подтверждения заказа",
            groups = {"smoke"})
    @Story("Покупка товаров через корзину (End-to-End)")
    @Severity(SeverityLevel.CRITICAL)
    public void checkEToETest() {
        log.info("Тест: Сквозной сценарий покупки от корзины до подтверждения заказа");
        Customer customer = new Customer("Igor", "Shustov", "1111");

        loginPage.openPage()
                .login("standard_user", "secret_sauce")
                .isPageOpened();
        productsPage.addToCart("Sauce Labs Backpack")
                .clickCart()
                .clickCheckout()
                .checkout(customer)
                .clickContinue()
                .clickFinish();

        assertEquals(checkoutPage.getCompletedMessage(), expectedCompleteMessage, "Сообщение об успешном заказе не совпадает");
    }
}