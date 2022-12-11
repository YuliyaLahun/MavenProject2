package HomeTask_day17;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

//3. задание booking 2:
//3.1. Перейти на сайт booking.com
//3.2. Выбрать нужные (любые) даты
//3.3 Ввести в поиск «Izmir», выбрать для проживания 2 гостей и 1 номер.
//3.4 Отфильтровать отели с максимальным рейтингом
//3.5 Открыть страницу с первым таким отелем и проверить, что его рейтинг >=9
public class BookingSecondTask {

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
        destination.sendKeys("Izmir");
        driver.findElements(By.xpath("//*[@class='sb-searchbox__button '] | //button[@type='submit']")).get(0).click();
        driver.findElement(By.xpath("//*[@data-testid='sorters-dropdown-trigger']")).click();
        driver.findElement(By.xpath("//*[text()='Оценка + кол-во отзывов']")).click();
        driver.navigate().refresh();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid=\"title-link\"]/*[1]")));
        element.click();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
        String text = driver.findElement(By.xpath("//*[@id=\"js--hp-gallery-scorecard\"]"))
                .getAttribute("data-review-score");
        System.out.println(text);

        Assert.assertTrue(Double.valueOf(text) >= 9.0);
        //driver.quit();

    }
}
