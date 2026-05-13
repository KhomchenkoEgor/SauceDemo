package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocators() {

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("item_4_title_link"));
        driver.findElement(By.name("add-to-cart-sauce-labs-bike-light"));
        driver.findElement(By.className("inventory_item_price"));
        driver.findElement(By.linkText("Sauce Labs Backpack"));
        driver.findElement(By.partialLinkText("Backpack"));
        driver.findElement(By.tagName("img"));

        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        driver.findElement(By.xpath("//a[contains(@id, 'item_4')]"));
        driver.findElement(By.xpath("//div[contains(text(), 'laptop and tablet protection')]"));
        driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']/parent::a"));
        driver.findElement(By.xpath("//*[text()='9.99']//ancestor::div"));
        driver.findElement(By.xpath("//a[contains(@data-test,'item-0')]//descendant::div"));
        driver.findElement(By.xpath("//div[@class='inventory_item_label']//following::button"));
        driver.findElement(By.xpath("//div[@class='inventory_item_price']//preceding::img[1]"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack' and @data-test='inventory-item-name']"));

        driver.findElement(By.cssSelector(".inventory_item_name"));
        driver.findElement(By.cssSelector(".btn.btn_inventory"));
        driver.findElement(By.cssSelector(".inventory_item_description .inventory_item_price"));
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("div.inventory_item_name"));
        driver.findElement(By.cssSelector("[data-test='inventory-item-name']"));
        driver.findElement(By.cssSelector("[class~='btn_primary']"));
        driver.findElement(By.cssSelector("[id|='add']"));
        driver.findElement(By.cssSelector("[id^='add-to-cart']"));
        driver.findElement(By.cssSelector("[id$='backpack']"));
        driver.findElement(By.cssSelector("[id*='labs-backpack']"));
    }
}