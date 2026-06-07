package tests;


import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(
            priority = 1,
            testName = "Успешная авторизация",
            description = "Проверка логина с валидными кредами",
            groups = {"smoke", "regression"})
    @Owner("Khomchenko E.S.")
    @Epic("Sauce Demo 1")
    @Feature("Log in")
    @Story("Log in with positive credentials")
    @Description("Проверка логина с валидными кредами")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Link(name = "Аналитика", url = "")
    @TmsLink("SD-T01")
    @Issue("BUG-01")
    public void checkLoginWithPositiveCred() {
        log.info("Тест: Успешная авторизация standard_user");
        loginPage.openPage()
                .login(user, password);
        productsPage.isPageOpened();
        assertEquals(productsPage.getTitle(), "Products", "SO BAD");
    }

    @Test(
            priority = 2,
            testName = "Авторизация с пустым логином",
            description = "Проверка ошибки при незаполненном поле Username",
            groups = {"regression"})
    @Story("Валидация полей формы авторизации при незаполненном поле Username")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithEmptyUserName() {
        log.info("Тест: Авторизация с пустой строкой вместо логина");
        loginPage.openPage()
                .login("", password);
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "SO BAD");
    }

    @Test(
            priority = 3,
            testName = "Авторизация с пустым паролем",
            description = "Проверка ошибки при незаполненном поле Password",
            groups = {"regression"})
    @Story("Валидация полей формы авторизации при незаполненном поле Password")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithEmptyPassword() {
        log.info("Тест: Авторизация с пустым полем пароля");
        loginPage.openPage()
                .login(user, "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "SO BAD");
    }

    @Test(
            priority = 4,
            testName = "Авторизация с неверными данными",
            description = "Проверка ошибки при вводе несуществующего пользователя",
            groups = {"regression"})
    @Story("Валидация полей формы авторизации при вводе несуществующего пользователя")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithNegativeCred() {
        log.info("Тест: Авторизация под несуществующим пользователем");
        loginPage.openPage()
                .login("test", "test");

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
    @Story("Валидация полей формы авторизации различными комбинациями невалидных данных через DataProvider")
    @Severity(SeverityLevel.NORMAL)
    public void checkLoginWithNegativeCred1(String user, String password, String errorMessage) {
        log.info("Параметризованный тест авторизации для пользователя: {}", user);
        loginPage.openPage()
                .login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage, "SO BAD");
    }
}