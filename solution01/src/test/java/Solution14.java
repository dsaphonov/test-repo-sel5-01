import com.google.common.base.Function;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Solution14 {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void linksOpenNewWindow() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a.button")).click(); // кнопка Add NewCountry
        List<WebElement> links= driver.findElements(By.cssSelector("td#content tbody a[target=_blank]")); // список ссылок
        for (WebElement link : links) {
            String mainWindow = driver.getWindowHandle();
            final Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            String newWindow = (new WebDriverWait(driver, 5))
                    .until(new ExpectedCondition<String>() {
                               public String apply(WebDriver driver) {
                                   Set<String> newWindows = driver.getWindowHandles();
                                   newWindows.removeAll(oldWindows);
                                   return newWindows.size() > 0 ? newWindows.iterator().next() : null;
                               }
                           }
                    );
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
