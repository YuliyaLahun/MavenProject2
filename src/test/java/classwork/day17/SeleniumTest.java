package classwork.day17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.findElement(By.xpath("//div[text()='Принять все']")).click();
        //task1
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.quit();

        //task2
        WebDriver driver1 = new ChromeDriver();
        driver1.get("https://google.com");
        driver1.findElement(By.xpath("//div[text()='Принять все']")).click();
        driver1.navigate().to("https://ya.ru");
        Thread.sleep(200);
        driver1.navigate().back();
        driver1.navigate().refresh();

    }
}
