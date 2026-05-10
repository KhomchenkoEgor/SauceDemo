package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        Assert.assertEquals(productsPage.getTitle(),"Products", "SO BAD");
    }

    @Test
    public void chekLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("","secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username is required", "SO BAD");
    }

    @Test
    public void chekLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user","");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Password is required", "SO BAD");
    }

    @Test
    public void chekLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test","test");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "SO BAD");
    }
}
