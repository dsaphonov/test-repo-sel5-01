package solution18;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    @FindBy(name = "options[Size]")
    private WebElement productSize;
    @FindBy(name = "add_cart_product")
    private WebElement addBtn;
    private CartElement cartElement;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        cartElement = new CartElement(driver);
    }

    public void addToCart() {
        //возможен запрос размера - выбираем размер
        try {
            new Select(productSize).selectByIndex(1);
        } catch (NoSuchElementException e){};
        int qtty = cartElement.getQtty();
        addBtn.click();
        //ждем увеличения счетчика товаров в корзине
        new WebDriverWait(driver, 5)
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return cartElement.getQtty() > qtty;
                    }
                });
    }
}
