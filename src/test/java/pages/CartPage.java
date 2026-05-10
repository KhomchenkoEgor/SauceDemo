package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    private final By TITLE_CART = By.cssSelector("[data-test = title]");
    private final By CHECKOUT = By.cssSelector("[data-test = checkout]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE_CART).getText();
    }

    public void clickCheckout() {
        driver.findElement(CHECKOUT).click();
    }
}
