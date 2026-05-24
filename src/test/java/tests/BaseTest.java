package tests;

import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import listners.TestListener;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;


import java.time.Duration;
import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CheckoutPage checkoutPage;
    CartPage cartPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    @Step("Инициализация браузера {browser} и подготовка страниц")
    public void setUp(@Optional("chrome") String browser, ITestContext iTestContext) {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        iTestContext.setAttribute("driver", driver);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    @Step("Закрытие сессии браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}