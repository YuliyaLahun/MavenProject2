package ClassWork_day18;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingTask1 {
    WebDriver driver;
    WebElement calendar;
    WebElement destination;
    int numberOfNights = 7;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://booking.com");
    }

    @Test
    public void bookingTestWithXpath() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();

        if (driver.findElements(By.xpath("//*[@data-testid='searchbox-dates-container']")).size() > 0) {
            calendar = driver.findElement(By.xpath("//*[@data-testid='searchbox-dates-container']"));
            destination = driver.findElement(By.name("ss"));
        } else {
            calendar = driver.findElement(By.xpath("//*[@class='bk-icon -experiments-calendar sb-date-picker_icon-svg'][1]"));
            destination = driver.findElement(By.xpath("//*[@class='c-autocomplete__input sb-searchbox__input sb-destination__input']"));
        }
        calendar.click();

//selecting dates
        LocalDate date = LocalDate.now().plusDays(3);
        WebElement dateFrom = driver
                .findElement(By.xpath(String.format("//*[@data-date='%s']", date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        dateFrom.click();
        WebElement dateTo = driver
                .findElement(By.xpath(String.format("//*[@data-date='%s']", date.plusDays(numberOfNights).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
        dateTo.click();
//selecting city
        destination.click();
        destination.sendKeys("Париж");
//increase amount of adults
        driver.findElement(By.xpath("//*[@class='xp__guests__count'] | //*[@data-testid='occupancy-config']")).click();
        driver.findElement(By.xpath("//*[@aria-label='Взрослых: увеличить количество'] | //*[@data-testid='occupancy-popup']//*[3]/*[3]")).click();
        driver.findElement(By.xpath("//*[@aria-label='Взрослых: увеличить количество'] | //*[@data-testid='occupancy-popup']//*[3]/*[3]")).click();
//increase amount of hotel rooms
        driver.findElement(By.xpath("//*[@aria-label='Номера: увеличить количество'] | //*[@id='no_rooms']/following-sibling::div[2]/*[3]")).click();
//search
        driver.findElement(By.cssSelector(".sb-searchbox__button, button[type=submit]")).click();
//filter by max price
        driver.findElement(By.xpath("//*[@data-filters-item='pri:pri=5']")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath("//*[@data-testid='overlay-wrapper']")));
//sort by less price
        driver.findElement(By.xpath("//*[@data-testid='sorters-dropdown-trigger']")).click();
        WebElement lessPrice = new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[text()='Цена (сначала самая низкая)']")));
        lessPrice.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath("//*[@data-testid='overlay-wrapper']")));

        double oneNightPrice = getPrice("//*[@data-testid='price-and-discounted-price']") / numberOfNights;
        double maxFilterPrice = getPrice("//*[@data-filters-item='pri:pri=5']//*[@data-testid='filters-group-label-content']");

        Assert.assertTrue(Double.compare(oneNightPrice, maxFilterPrice) >= 0);
    }

    private double getPrice(String xpath) {
        String price = driver.findElement(By.xpath(xpath)).getText();
        return Double.parseDouble(price.replaceAll("[^\\d.,]", ""));
    }

    @After
    public void shutDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
