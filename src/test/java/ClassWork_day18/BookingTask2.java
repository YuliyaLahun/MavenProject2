package ClassWork_day18;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingTask2 {
    WebDriver driver;
    Actions action;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        action = new Actions(driver);
    }


    @Test
    public void testCurrencyTooltip() {
        driver.get("https://booking.com");

        Assert.assertEquals("Выберите валюту",
                displayTooltip("//*[@class='bui-group__item'][1]",
                action, "//*[@class='bui-tooltip__content']").getText());
    }

    private WebElement displayTooltip(String xpathOfElementToHover, Actions hover, String xpathOfTooltip) {
        hover.moveToElement(driver.findElement(By.xpath(xpathOfElementToHover))).perform();
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpathOfTooltip))));
    }

    @Test
    public void testLanguageTooltip() {
        driver.get("https://booking.com");
        // hover lang
        WebElement lang = driver.findElement(By.xpath("//*[@class='bui-group__item'][2]"));
        action.moveToElement(lang).perform();
        WebElement langTooltip = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@class='bui-tooltip__content']"))));

        Assert.assertEquals("Выберите язык", langTooltip.getText());
    }

    @After
    public void shutDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
