package pages;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartPage extends BasePage {

    By TITLE_CART = By.cssSelector("[data-test = title]");
    By CHECKOUT = By.cssSelector("[data-test = checkout]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия CartPage")
    public CartPage isPageOpened() {
        log.info("Ожидание загрузки страницы корзины");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_CART));
        return this;
    }

    @Override
    @Step("Открыть CartPage")
    public CartPage openPage() {
        log.info("Переход на страницу корзины по URL");
        driver.get(BASE_URL + "/cart.html");
        return isPageOpened();
    }

    public String getTitle() {
        return driver.findElement(TITLE_CART).getText();
    }

    @Step("Переход к Checkout")
    public CheckoutPage clickCheckout() {
        log.info("Клик по кнопке оформления заказа Checkout");
        driver.findElement(CHECKOUT).click();
        return new CheckoutPage(driver).isPageOpened();
    }

    public boolean isProductInCart(String product) {
        return driver.findElement(By.xpath(String.format("//*[@class='cart_item']//*[text()='%s']", product))).isDisplayed();
    }

    public String getProductNameFromCart(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
    }

    public ArrayList<String> getProductsName() {
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        ArrayList<String> names = new ArrayList<>();
        for (WebElement product: allProductsElements) {
            names.add(product.getText());
        }
        return names;
    }

    public void clickProduct(String name) {
        log.info("Переход на карточку товара из корзины по имени: {}", name);
        List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement product: products) {
            if (product.getText().contains(name)){
                product.click();
                break;
            }
        }
    }
}