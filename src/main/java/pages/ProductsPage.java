package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    private final By TITLE_PRODUCTS = By.cssSelector("[data-test = title]");
    private final By CART = By.cssSelector("[data-test = shopping-cart-link]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");
    private final By PRODUCT_NAMES = By.className("inventory_item_name");
    private final String ADD_PRODUCT_PATTERN =
            "//div[@data-test='inventory-item-name' and text()='%s']";
    private final String PRODUCT_PRICE_PATTERN =
            "//div[@class='cart_item' and .//div[text()='%s']]//div[@class='inventory_item_price']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия ProductsPage")
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_PRODUCTS));
        return this;
    }

    @Override
    @Step("Открыть ProductsPage")
    public ProductsPage openPage() {
        driver.get(BASE_URL + "/inventory.html");
        return isPageOpened();
    }
    public String getTitle() {
        return driver.findElement(TITLE_PRODUCTS).getText();
    }

    @Step("Добавление в корзину товара с именем: '{product}'")
    public ProductsPage addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    public String getProductName(String productName) {
        By dynamicLocator = By.xpath(String.format(ADD_PRODUCT_PATTERN, productName));
        return driver.findElement(dynamicLocator).getText();
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE_PATTERN, productName))).getText();
    }

    @Step("Переход в корзину")
    public CartPage clickCart() {
        driver.findElement(CART).click();
        return new CartPage(driver).isPageOpened();
    }

    @Step("Выбрать опцию сортировки товаров в каталоге: '{value}'")
    public ProductsPage selectSortOption(String value) {
        Select dropdown = new Select(driver.findElement(SORT_DROPDOWN));
        dropdown.selectByValue(value);
        return this;
    }

    @Step("Получить список имен всех товаров на странице")
    public List<String> getProductNames() {
        return driver.findElements(PRODUCT_NAMES).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Получить список цен всех товаров на странице")
    public List<Double> getProductPrices() {
        return driver.findElements(By.className("inventory_item_price"))
                .stream()
                .map(el -> Double.parseDouble(el.getText().replace("$", "")))
                .collect(Collectors.toList());
    }
}