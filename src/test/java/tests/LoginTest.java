package tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            priority = 1,
            testName = "Успешная авторизация",
            description = "Проверка логина с валидными кредами",
            groups = {"smoke", "regression"})
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products", "SO BAD");
    }

    @Test(
            priority = 2,
            testName = "Авторизация с пустым логином",
            description = "Проверка ошибки при незаполненном поле Username",
            groups = {"regression"})
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "SO BAD");
    }

    @Test(
            priority = 3,
            testName = "Авторизация с пустым паролем",
            description = "Проверка ошибки при незаполненном поле Password",
            groups = {"regression"})
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "SO BAD");
    }

    @Test(
            priority = 4,
            testName = "Авторизация с неверными данными",
            description = "Проверка ошибки при вводе несуществующего пользователя",
            groups = {"regression"})
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service", "SO BAD");
    }

    @DataProvider(name = "Параметризованный тест")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(
            priority = 5,
            testName = "Параметризованная проверка негативной авторизации",
            description = "Проверка различных комбинаций невалидных данных через DataProvider",
            groups = {"regression"},
            dataProvider = "Параметризованный тест")

    public void checkLoginWithNegativeCred1(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "SO BAD");
    }
}