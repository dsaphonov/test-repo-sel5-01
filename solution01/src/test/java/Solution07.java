import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Solution07 {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");
    }

    @Test
    public void clickAllMenus() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<String> hrefsTop = new ArrayList<String>();
        for (WebElement we : driver.findElements(By.cssSelector("ul#box-apps-menu li#app- a"))) {
            hrefsTop.add(we.getAttribute("href"));
        }
        for (String hrefTop :hrefsTop) {
            driver.findElement(By.cssSelector("[href='" + hrefTop + "']")).click();
            List<String> hrefsSub = new ArrayList<String>();
            for (WebElement subWe : driver.findElements(By.cssSelector("li#app-.selected ul.docs a"))) {
                hrefsSub.add(subWe.getAttribute("href"));
            }
            for (String hrefSub : hrefsSub) {
                driver.findElement(By.cssSelector("[href='" + hrefSub + "']")).click();
                Assert.assertTrue("Отсутвует заголовок",
                        1 == driver.findElements(By.cssSelector("td#content h1")).size());
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
