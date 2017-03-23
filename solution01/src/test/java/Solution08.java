import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Solution08 {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart");
    }

    @Test
    public void isStickersExist() {
        for (WebElement product : driver.findElements(By.cssSelector("li.product"))) {
            Assert.assertTrue("Отсутвует стикер товара",
                    1 == product.findElements(By.cssSelector("div.sticker")).size());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
