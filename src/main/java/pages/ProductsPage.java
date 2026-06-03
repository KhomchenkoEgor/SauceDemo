package pages;

import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductsPage extends BasePage {

    By TITLE_PRODUCTS = By.cssSelector("[data-test = title]");
    By CART = By.cssSelector("[data-test = shopping-cart-link]");
    String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");
    By PRODUCT_NAMES = By.className("inventory_item_name");
    String ADD_PRODUCT_PATTERN =
            "//div[@data-test='inventory-item-name' and text()='%s']";
    String PRODUCT_PRICE_PATTERN =
            "//div[@class='cart_item' and .//div[text()='%s']]//div[@class='inventory_item_price']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Ожидание открытия ProductsPage")
    public ProductsPage isPageOpened() {
        log.info("Ожидание загрузки каталога товаров (Products)");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_PRODUCTS));
        return this;
    }

    @Override
    @Step("Открыть ProductsPage")
    public ProductsPage openPage() {
        log.info("Переход в каталог товаров по URL");
        driver.get(BASE_URL + "/inventory.html");
        return isPageOpened();
    }
    public String getTitle() {
        return driver.findElement(TITLE_PRODUCTS).getText();
    }

    @Step("Добавление в корзину товара с именем: '{product}'")
    public ProductsPage addToCart(String product) {
        log.info("Добавление в корзину товара: {}", product);
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
        log.info("Переход со страницы каталога в корзину");
        driver.findElement(CART).click();
        return new CartPage(driver).isPageOpened();
    }

    @Step("Выбрать опцию сортировки товаров в каталоге: '{value}'")
    public ProductsPage selectSortOption(String value) {
        log.info("Выбор сортировки каталога со значением: {}", value);
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