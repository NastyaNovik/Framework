package page;

import org.openqa.selenium.*;
import model.Item;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends AbstractPage{
    public BasketPage (WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath= "//*[@id=\"f_order\"]/div[2]/div[1]/div/span[1]")
    private WebElement itemName;
    @FindBy(xpath = " //*[@id=\"f_order\"]/div[2]/div[1]/div/span[3]")
    private WebElement firstItemSize;
    @FindBy(xpath = " //*[@id=\"f_order\"]/div[3]/div[1]/div/span[3]")
    private WebElement secondItemSize;
    @FindBy(xpath = " //span[@class=\"b-basket-table__price\"]")
    private WebElement itemCost;
    @FindBy(xpath = " //input[@class=\"b-select-amount__numb\"]")
    private WebElement itemCount;
    @FindBy(xpath = " //a[@class=\"b-select-amount__plus js_plus\"]")
    private WebElement plusButton;
    @FindBy(xpath = " //a[@class=\"b-basket-table__remove del\"]")
    private WebElement deleteButton;
    @FindBy(xpath = " //span[@class=\"amount\"]")
    private WebElement countOfItem;
    @FindBy(id = "percent_code")
    private WebElement inputNumberOfDiscountCardString;
    @FindBy(id = "_error-message")
    private WebElement invalidNumberOfDiscountCardString;
    @FindBy(xpath = " //span[@class=\"b-total__price\"]")
    private WebElement totalCost;

    @Override
    public BasketPage openPage(String url) {
        driver.get(url);
        return this;
    }
    public BasketPage scrollToItem() throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputNumberOfDiscountCardString);
        return this;
    }
    public Item getItem() throws InterruptedException {
        Thread.sleep(1000);
        String name = itemName.getText();
        String size = firstItemSize.getText().substring(8);
        double cost = Double.parseDouble(itemCost.getText());
        String count = itemCount.getAttribute("value");
        return new Item(name,size,cost,count);
    }
    public Item getItemWithAnotherSize() throws InterruptedException {
        Thread.sleep(1000);
        String name = itemName.getText();
        String sizeOfSecondItem = secondItemSize.getText().substring(8);
        double cost = Double.parseDouble(itemCost.getText());
        String count = itemCount.getAttribute("value");
        return new Item(name,sizeOfSecondItem,cost,count);
    }
    public BasketPage increaseItemInBasket(){
        waitUntilElementIsClickable(plusButton).click();
        return this;
    }
    public BasketPage deleteItemFromBasket(){
        waitUntilElementIsClickable(deleteButton).click();
        return this;
    }
    public int getCountOfItemsInBasket() throws InterruptedException {
        Thread.sleep(2000);
        return Integer.parseInt(countOfItem.getText());
    }
    public BasketPage inputNumberOfDiscountCard(String number){
        waitUntilElementIsClickable(inputNumberOfDiscountCardString).click();
        waitUntilVisibilityOf(inputNumberOfDiscountCardString).sendKeys(number);
        inputNumberOfDiscountCardString.sendKeys(Keys.ENTER);
        return this;
    }
    public String checkInvalidNumberOfDiscountCard(){
        return invalidNumberOfDiscountCardString.getText();
    }

    public double totalCostOfItems(){
        double totalCostOfItems=Double.parseDouble(totalCost.getText());
        return  totalCostOfItems;
    }

}
