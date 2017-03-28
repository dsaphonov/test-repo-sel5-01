import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Solution11 {
    private WebDriver driver;

    @BeforeTest
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart");
    }

    @Test
    public void userRegistration() {
        String email = Calendar.getInstance().getTimeInMillis() + "email@test.ru";
        String pwd = "password";
        driver.findElement(By.linkText("New customers click here")).click();
        driver.findElement(By.name("firstname")).sendKeys("First");
        driver.findElement(By.name("lastname")).sendKeys("Last");
        driver.findElement(By.name("address1")).sendKeys("Address");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("City");
        new Select(driver.findElement(By.name("country_code"))).selectByValue("US");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("123");
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.name("confirmed_password")).sendKeys(pwd);
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.linkText("Logout")).click();
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.name("login")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
