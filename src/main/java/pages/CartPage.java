package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By TITLE_CART = By.cssSelector("[data-test = title]");
    private final By CHECKOUT = By.cssSelector("[data-test = checkout]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия CartPage")
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_CART));
        return this;
    }

    @Override
    @Step("Открыть CartPage")
    public CartPage openPage() {
        driver.get(BASE_URL + "/cart.html");
        return isPageOpened();
    }

    public String getTitle() {
        return driver.findElement(TITLE_CART).getText();
    }

    @Step("Переход к Checkout")
    public CheckoutPage clickCheckout() {
        driver.findElement(CHECKOUT).click();
        return new CheckoutPage(driver).isPageOpened(); // Цепочка на CheckoutPage
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
        List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement product: products) {
            if (product.getText().contains(name)){
                product.click();
                break;
            }
        }
    }
}
