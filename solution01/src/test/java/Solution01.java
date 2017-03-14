import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Solution01 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https:\\www.yandex.ru");
        driver.close();
    }
}
