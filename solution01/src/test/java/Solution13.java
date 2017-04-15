import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Solution13 {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void canOperateWithCart() {
        int qtty = 0;
        do {
            driver.findElement(By.cssSelector("li.product")).click();
            //возможен запрос размера - выбираем размер
            try {
                new Select(driver.findElement(By.name("options[Size]"))).selectByIndex(1);
            } catch (NoSuchElementException e){};
            qtty = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
            driver.findElement(By.name("add_cart_product")).click();
            qtty++;
            //ждем увеличения счетчика товаров в корзине
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), Integer.toString(qtty)));
            driver.navigate().back();
        } while (qtty < 3); //набрали три товара?
        //входим в корзину и последовательно удаляем товары из корзины
        driver.findElement(By.partialLinkText("Checkout")).click();
        while (true) {
            List<WebElement> removeButtons = driver.findElements(By.name("remove_cart_item"));
            if (removeButtons.size() == 0)
                break;
            int numItemsBeforeDel = driver.findElements(By.cssSelector("td.item")).size();
            //ждем, пока первая кнопка удаления не появится в процессе анимации и кликаем
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(removeButtons.get(0)));
            removeButtons.get(0).click();
            //ждем, пока не обновится список товаров в корзине
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("td.item"), numItemsBeforeDel));
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
