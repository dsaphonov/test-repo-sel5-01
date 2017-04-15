package solution18;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartElement {
    private WebDriver driver;
    @FindBy(css = "span.quantity")
    private WebElement cartQtty;
    @FindBy(partialLinkText = "Checkout")
    private WebElement checkOut;

    CartElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getQtty() {
        return Integer.parseInt(cartQtty.getText());
    }

    public CartPage checkOut() {
        checkOut.click();
        return new CartPage(driver);
    }
}
