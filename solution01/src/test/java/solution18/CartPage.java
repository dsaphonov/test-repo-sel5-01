package solution18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    @FindBy(name = "remove_cart_item")
    private List<WebElement> removeButtons;
    @FindBy(css = "td.item")
    private List<WebElement> items;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int removeFirstProduct() {
        int numItemsBeforeDel = items.size();
        if (numItemsBeforeDel > 0) {
            //ждем, пока первая кнопка удаления не появится в процессе анимации и кликаем
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOf(removeButtons.get(0)));
            removeButtons.get(0).click();
            //ждем, пока не обновится список товаров в корзине
            new WebDriverWait(driver, 5)
                    .until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return items.size() < numItemsBeforeDel;
                        }
                    });
        }
        return items.size();
    }
}
