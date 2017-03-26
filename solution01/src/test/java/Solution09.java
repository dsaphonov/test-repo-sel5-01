import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution09 {
    private WebDriver driver;

    @BeforeClass
    public void setUpDriver() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void isCountriesAlphabeticalSorted() {
        ArrayList<String> countryNames = new ArrayList<String>();
        for (WebElement link : driver.findElements(By.cssSelector("form[name=countries_form] td:nth-child(5) a"))) {
            countryNames.add(link.getText());
        }
        System.out.println("Проверка сортировки списка из " + countryNames.size() + " стран");
        Assert.assertTrue(isSorted(countryNames), "Cписок стран не отсортирован");
    }

    @Test
    public void isZonesOfCountryAlphabeticalSorted() {
        ArrayList<String> countryCodes = new ArrayList<String>();
        for (WebElement we : driver.findElements(By.cssSelector("form[name=countries_form] td:nth-child(4)"))) {
            countryCodes.add(we.getText());
        }
        for (String countryCode : countryCodes) {
            WebElement numZones = driver.findElement(By.xpath("//td[contains(text(),'" + countryCode + "')]/../td[6]"));
            if (!numZones.getText().equals("0")) {
                driver.findElement(By.xpath("//td[contains(text(),'" + countryCode + "')]/../td[5]/a")).click();
                List<String> zones = new ArrayList<String>();
                for (WebElement zone : driver.findElements(By.cssSelector("#table-zones td:nth-child(3) input"))) {
                    String zoneName = zone.getAttribute("value");
                    if(!zoneName.equals("")) {
                        zones.add(zoneName);
                    }
                }
                driver.navigate().back();
                System.out.println("Проверка сортировки списка из " + zones.size() + " зон для страны " + countryCode);
                Assert.assertTrue(isSorted(zones), "Cписок зон не отсортирован");
            }
        }
    }

    private boolean isSorted(List<String> list) {
        List<String> sorted = new ArrayList<String>();
        sorted.addAll(list);
        Collections.sort(sorted);
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(sorted.get(i)))
                return false;
        }
        return true;
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
