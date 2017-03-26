import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Solution10 {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
    }

    @Test
    public void isStickersExist() {

        WebElement prod = driver.findElement(By.cssSelector("#box-campaigns li.product"));
        WebElement weRegularPrice = null;
        WebElement weCampaignPrice = null;
        String prodName = prod.findElement(By.cssSelector("div.name")).getText();
        String regularPrice = null;
        String campaignPrice = null;
        float regularPriceFontSize = 0;
        float campaignPriceFontSize = 0;

        System.out.println("Проверка атрибутов товара акционного списка...");
        weRegularPrice = prod.findElement(By.cssSelector(".regular-price"));
        weCampaignPrice = prod.findElement(By.cssSelector(".campaign-price"));
        regularPrice = weRegularPrice.getText();
        campaignPrice = weCampaignPrice.getText();

        System.out.println("Обычная цена зачеркнута?");
        Assert.assertEquals(weRegularPrice.getCssValue("text-decoration"), "line-through",
                "Обычная цена должна быть зачеркнута");

        System.out.println("Обычная цена серая?");
        Assert.assertTrue(weRegularPrice.getCssValue("color").contains("(119, 119, 119"),
                "Обычная цена должна быть серого цвета");

        System.out.println("Акционная цена жирным шрифтом?");
        String fontWeight = weCampaignPrice.getCssValue("font-weight");
        fontWeight = fontWeight.equals("900") ? "bold" : fontWeight;
        Assert.assertEquals(fontWeight, "bold", "Акционная цена должна быть жирным шрифтом");

        System.out.println("Акционная цена красная?");
        Assert.assertTrue(weCampaignPrice.getCssValue("color").contains(", 0, 0"),
                "Акционная цена должна быть красного цвета");

        System.out.println("Акционная цена на главной странице крупнее обычной цены?");
        regularPriceFontSize = Float.parseFloat(weRegularPrice.getCssValue("font-size").replace("px", ""));
        campaignPriceFontSize = Float.parseFloat(weCampaignPrice.getCssValue("font-size").replace("px", ""));
        Assert.assertTrue(campaignPriceFontSize > regularPriceFontSize, "Акционная цена дожна быть крупнее обычной");

        prod.click();

        System.out.println("Проверка атрибутов товара в описании...");
        System.out.println("Наименование товара с главной страницы совпадает с деталями?");
        Assert.assertEquals(prodName, driver.findElement(By.cssSelector("h1.title")).getText());

        System.out.println("Обычная цена товара с главной страницы совпадает с деталями?");
        weRegularPrice = driver.findElement(By.cssSelector(".regular-price"));
        Assert.assertEquals(regularPrice, weRegularPrice.getText());

        System.out.println("Акционная цена товара с главной страницы совпадает с деталями?");
        weCampaignPrice = driver.findElement(By.cssSelector(".campaign-price"));
        Assert.assertEquals(campaignPrice, weCampaignPrice.getText());

        System.out.println("Акционная цена жирным шрифтом?");
        fontWeight = weCampaignPrice.getCssValue("font-weight");
        fontWeight = fontWeight.equals("900") ? "bold" : fontWeight;
        Assert.assertEquals(fontWeight, "bold", "Акционная цена должна быть жирным шрифтом");

        System.out.println("Акционная цена красная?");
        Assert.assertTrue(weCampaignPrice.getCssValue("color").contains(", 0, 0"),
                "Акционная цена должна быть красного цвета");

        System.out.println("Акционная цена на главной странице крупнее обычной цены?");
        regularPriceFontSize = Float.parseFloat(weRegularPrice.getCssValue("font-size").replace("px", ""));
        campaignPriceFontSize = Float.parseFloat(weCampaignPrice.getCssValue("font-size").replace("px", ""));
        Assert.assertTrue(campaignPriceFontSize > regularPriceFontSize, "Акционная цена дожна быть крупнее обычной");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
