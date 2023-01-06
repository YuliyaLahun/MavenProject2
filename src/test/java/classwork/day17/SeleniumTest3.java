package classwork.day17;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class SeleniumTest3 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.findElement(By.xpath("//div[text()='Принять все']")).click();

        LocalDate localDate = LocalDate.now();

        int dayNumber = localDate.plusDays(1).getDayOfWeek().getValue();

        String day = localDate.plusDays(1).format(DateTimeFormatter
                .ofPattern( "EEEE" , new Locale("ru") ));

        WebElement poisk = driver.findElement(By.name("q"));
        poisk.sendKeys("погода минск");
        poisk.click();
        driver.findElement(By.xpath("//ul[@role='listbox']/child::li[1]")).click();
        driver.findElement(By.xpath(String.format("//*[@data-wob-di='%d']",dayNumber))).click();
        WebElement element = driver
                .findElement(By.xpath(String.format("//*[contains(@aria-label,'Celsius %s 12:00')][1]",day)));
        System.out.println("Температура в Минске завтра в полдень: "+Arrays
                .stream(element.getAttribute("aria-label")
                        .split("°"))
                        .findFirst()
                        .get());


    }
}
