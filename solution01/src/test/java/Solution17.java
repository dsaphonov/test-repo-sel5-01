import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Solution17 {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void noAnyBrowserErrorsInProducts() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        ArrayList<String> links = new ArrayList<>();
        for (WebElement product : driver.findElements(By.cssSelector("a:nth-child(2)[href*='product_id']"))) {
            links.add(product.getAttribute("href"));
        }
        for (String link : links) {
            driver.get(link);
            driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
            driver.navigate().back();
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }
}
