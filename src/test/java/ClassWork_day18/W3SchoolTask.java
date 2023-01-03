package ClassWork_day18;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class W3SchoolTask {
    WebDriver driver;
    String tutorial = "Tutorial";

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void trainActionsOnW3SchoolTest() {

        driver.get("https://www.w3schools.com/java/");
        WebElement accept = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id='accept-choices']"))));
        accept.click();
        WebElement title = driver.findElement(By.xpath("//*[text()='" + tutorial + "']"));

        Actions act = new Actions(driver);
        act.moveToElement(title).doubleClick().build().perform();
        // Copy
        act.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).build().perform();
        driver.navigate().to("https://google.com");
        WebElement acceptAll = driver.findElement(By.xpath("//*[text()='Принять все']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", acceptAll);
        acceptAll.click();
        driver.findElement(By.name("q")).click();
        // Paste
        act.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).sendKeys(Keys.ENTER).build().perform();

        List<String> headers = driver.findElements(By.xpath("//*[@data-header-feature='0']//a/h3"))
                .stream()
                .map(h -> h.getText().toLowerCase())
                .collect(Collectors.toList());

        headers.stream().map(s -> s.contains("tutorial")).forEach(Assert::assertTrue);

    }

    @After
    public void shutDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
