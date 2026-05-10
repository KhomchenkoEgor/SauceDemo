package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    private final By FIRST_NAME_FIELD = By.id("first-name");
    private final By LAST_NAME_FIELD = By.id("last-name");
    private final By ZIP_POSTAL_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.cssSelector("[data-test=continue]");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test=error]");
    private final By COMPLETE_MESSAGE = By.cssSelector("[data-test=complete-header]");
    private final By FINISH_BUTTON = By.cssSelector("[data-test=finish]");
    private final By TITLE_CHECKOUT = By.cssSelector("[data-test = title]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void checkout(String firstName, String lastName, String zipPostalCode){
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(ZIP_POSTAL_FIELD).sendKeys(zipPostalCode);
    }

    public String getErrorMessage(){
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void clickContinue() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void clickFinish() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public String getCompletedMessage(){
        return driver.findElement(COMPLETE_MESSAGE).getText();
    }

    public String getTitle() {
        return driver.findElement(TITLE_CHECKOUT).getText();
    }
}
