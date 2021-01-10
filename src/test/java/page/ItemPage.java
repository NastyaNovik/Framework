package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static util.Resolver.resolveTemplate;

public class ItemPage extends AbstractPage{

    @FindBy(xpath = "//a[@class=\"b-btn basket blue buy\"]")
    private WebElement addToBasket;
    @FindBy(xpath = "//select[@class=\"b-input-select\"]")
    private WebElement sizeList;
    private String itemSizeTemplate = "//option[contains(text(), '%s')]";
    @FindBy(xpath = "//span[@class=\"b-custom-check__caption\"]")
    private WebElement colorSpan;
    @FindBy(xpath = "//a[@class=\"b-btn\"]")
    private WebElement goToBasket;

    public ItemPage (WebDriver driver) {
        super(driver);
    }
    @Override
    public ItemPage openPage(String url) {
        driver.get(url);
        return this;
    }
    public ItemPage chooseSize(String size){
        waitUntilElementIsClickable(sizeList).click();
        waitUntilElementIsClickable(By.xpath(resolveTemplate(itemSizeTemplate,size))).click();
        return this;
    }
    public ItemPage addToBasket () {
        waitUntilElementIsClickable(addToBasket).click();
        return this;
    }
    public BasketPage goToBasket(){
        waitUntilElementIsClickable(goToBasket).click();
        return new BasketPage(driver);
    }
}
