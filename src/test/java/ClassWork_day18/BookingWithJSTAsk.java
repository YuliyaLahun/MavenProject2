package ClassWork_day18;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingWithJSTAsk {
    WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://booking.com");
        driver.manage().window().maximize();
    }

    @Test
    public void bookingTestWithJS() {

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
        WebElement destination = driver.findElement(By.name("ss"));
        destination.sendKeys("London");

        driver.findElement(By.cssSelector(".sb-searchbox__button, button[type=submit]")).click();

        WebElement propertyCard_10 = driver.findElements(By.xpath("//*[@data-testid='property-card']")).get(9);
        WebElement hotelTitle_10 = driver.findElements(By.xpath("//*[@data-testid='title']")).get(9);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", propertyCard_10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", propertyCard_10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", hotelTitle_10);
        String color = hotelTitle_10.getCssValue("color");

        Assert.assertEquals(Color.fromString(color).asRgba(), Colors.RED.getColorValue().asRgba());
    }

    @After
    public void shutDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
