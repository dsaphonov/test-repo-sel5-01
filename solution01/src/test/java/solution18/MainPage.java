package solution18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;
    @FindBy(css = "li.product")
    private WebElement product;
    private CartElement cartElement;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        cartElement = new CartElement(driver);
        PageFactory.initElements(driver, this);
        driver.get("http://localhost/litecart/");
    }

    ProductPage getFirstProduct() {
        product.click();
        return new ProductPage(driver);
    }

    public CartElement getCartElement() {
        return cartElement;
    }
}
