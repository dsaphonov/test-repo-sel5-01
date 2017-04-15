package solution18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Solution18 {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @Test
    public void canOperateWithCart() {
        MainPage mainPage = new MainPage(driver);

        int qttyBefore = mainPage.getCartElement().getQtty();
        do {
            mainPage.getFirstProduct().addToCart();
            mainPage = new MainPage(driver);
        } while (mainPage.getCartElement().getQtty() < qttyBefore + 3); //набрали три товара?

        //входим в корзину и последовательно удаляем товары из корзины
        CartPage cartPage = mainPage.getCartElement().checkOut();
        while (cartPage.removeFirstProduct() > 0);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
