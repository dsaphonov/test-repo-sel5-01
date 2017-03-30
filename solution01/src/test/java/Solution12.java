import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Solution12 {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void canCreateNewProduct() throws URISyntaxException {
        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
        driver.findElement(By.linkText("Rubber Ducks")).click();
        int initalRealDuckNum = driver.findElements(By.linkText("Real Duck")).size();
        driver.findElement(By.partialLinkText("Add New Product")).click();
        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys("Real Duck");
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("rd006");
        String imagePath = ClassLoader.getSystemResource("realduck.png").toURI().getPath().substring(1);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imagePath);
        driver.findElement(By.linkText("Information")).click();
        new Select(driver.findElement(By.name("manufacturer_id"))).selectByIndex(1);
        driver.findElement(By.linkText("Data")).click();
        WebElement we = driver.findElement(By.cssSelector("input[name='weight']"));
        we.clear();
        we.sendKeys("2.5");
        driver.findElement(By.linkText("Prices")).click();
        we = driver.findElement(By.cssSelector("input[name='purchase_price']"));
        we.clear();
        we.sendKeys("123.45");
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByValue("EUR");
        driver.findElement(By.cssSelector("button[name='save']")).click();
        int finalRealDuckNum = driver.findElements(By.linkText("Real Duck")).size();
        Assert.assertEquals(finalRealDuckNum - initalRealDuckNum, 1, "Новый товар не добавлен");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
