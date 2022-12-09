package HomeTask_day17;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookingFirstTask {

    @Test
    public void bookingTestWithXpath() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://booking.com");
        Thread.sleep(200);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Thread.sleep(200);
        WebElement calendar;
        WebElement destination;
        if (driver.findElements(By.xpath("//*[@data-testid='searchbox-dates-container']")).size() > 0) {
            calendar = driver.findElement(By.xpath("//*[@data-testid='searchbox-dates-container']"));
            destination = driver.findElement(By.name("ss"));
        } else {
            calendar = driver.findElement(By.xpath("//*[@class='bk-icon -experiments-calendar sb-date-picker_icon-svg'][1]"));
            destination = driver.findElement(By.xpath("//*[@class='c-autocomplete__input sb-searchbox__input sb-destination__input']"));
        }

        calendar.click();
        Thread.sleep(200);
        WebElement dateFrom = driver.findElement(By.xpath("//*[@data-date='2023-01-16']"));
        dateFrom.click();
        WebElement dateTo = driver.findElement(By.xpath("//*[@data-date='2023-01-22']"));
        dateTo.click();
        destination.click();
        destination.sendKeys("Milano");
        driver.findElements(By.xpath("//*[@class='sb-searchbox__button '] | //button[@type='submit']")).get(0).click();

        Assert.assertTrue(driver.findElements(By.xpath("//*[@data-testid='property-card']")).size() > 0);
    }

    @Test
    public void bookingTestWithCss() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://booking.com");
        Thread.sleep(200);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Thread.sleep(200);
        WebElement calendar;
        WebElement destination;
        if (driver.findElements(By.cssSelector("[data-testid=searchbox-dates-container]")).size() > 0) {
            calendar = driver.findElement(By.cssSelector("[data-testid=searchbox-dates-container]"));
            destination = driver.findElement(By.name("ss"));
        } else {
            calendar = driver.findElement(By.cssSelector(".bk-icon.-experiments-calendar.sb-date-picker_icon-svg:first-of-type"));
            destination = driver.findElement(By.cssSelector(".c-autocomplete__input.sb-searchbox__input.sb-destination__input"));
        }

        calendar.click();
        Thread.sleep(200);
        WebElement dateFrom = driver.findElement(By.cssSelector("[data-date='2023-01-16']"));
        dateFrom.click();
        WebElement dateTo = driver.findElement(By.cssSelector("[data-date='2023-01-22']"));
        dateTo.click();
        destination.click();
        destination.sendKeys("Milano");
        driver.findElements(By.cssSelector(".sb-searchbox__button, button[type=submit]")).get(0).click();

        Assert.assertTrue(driver.findElements(By.cssSelector("[data-testid=property-card]")).size() > 0);
    }
}
