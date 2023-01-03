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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class Selects {
    WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/select-menu");
    }


    @Test
    public void selectValue() {
        driver.findElement(By.id("withOptGroup")).click();
        WebElement aRootOption = new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-2-option-2")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", aRootOption);

        Assert.assertEquals("A root option",
                driver.findElement(By.xpath("//*[@id='withOptGroup']/*[2]/*[1]/*[1]")).getText());
    }

    @Test
    public void selectOne() {
        driver.findElement(By.id("selectOne")).click();
        WebElement mister = new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-3-option-0-1")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", mister);

        Assert.assertEquals("Mr.",
                driver.findElement(By.xpath("//*[@id='selectOne']/*[2]/*[1]/*[1]")).getText());
    }

    @Test
    public void selectOldStyle() {
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByIndex(9);
        Assert.assertEquals("Magenta", select.getFirstSelectedOption().getText());
    }

    @Test
    public void multiDDL() {
        WebElement ddl = driver.findElement(By.xpath("//*[@id='react-select-4-input']/../../../.."));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ddl);
        ddl.click();

        WebElement green = new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-4-option-0")));
        WebElement red = new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.id("react-select-4-option-3")));
        js.executeScript("arguments[0].click()", green);
        js.executeScript("arguments[0].click()", red);
        ddl.click();
        List<String> selectedColors = driver.findElements(By.xpath("//*[@class='css-1rhbuit-multiValue']"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals("Green, Red",
                selectedColors.toString().replaceAll("[\\[\\]]", ""));
    }

    @Test
    public void multiSelect() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("cars")));
        Select select = new Select(driver.findElement(By.id("cars")));
        select.selectByIndex(0);
        select.selectByIndex(3);
        List<String> selectedCars = select.getAllSelectedOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertEquals("Volvo, Audi", selectedCars.toString().replaceAll("[\\[\\]]", ""));
    }


    @After
    public void shutDown() {
        driver.quit();
    }
}
