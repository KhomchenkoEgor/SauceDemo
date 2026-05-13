package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkCheckoutWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("Igor", "Shustov", "111");
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getTitle(), "Checkout: Overview", "OK");
    }

    @Test
    public void checkCheckoutWithEmptyFields() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("", "", "");
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "SO BAD");
    }

    @Test
    public void checkLoginWithEmptyLastName() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("111", "", "111");
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Last Name is required", "SO BAD");
    }

    @Test
    public void checkCheckoutWithEmptyZipCode() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickCart();
        cartPage.clickCheckout();
        checkoutPage.checkout("test", "test", "");
        checkoutPage.clickContinue();
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: Postal Code is required", "SO BAD");
    }
}