package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CatalogPage  extends AbstractPage{
    public CatalogPage (WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath= "/html/body/div[1]/div[2]/div/div/div[4]/div/a[2]")
    private WebElement sortButtonDesc;
    @FindBy(xpath= "/html/body/div[1]/div[2]/div/div/div[4]/div/a[3]")
    private WebElement sortButtonAsc;
    @FindBy(xpath= "//*[@id=\"items-place\"]/div[1]/article/a[2]/span[5]/span[1]")
    private WebElement firstPrice;
    @FindBy(xpath= "//*[@id=\"items-place\"]/div[2]/article/a[2]/span[5]/span[1]")
    private WebElement secondPrice;
    @FindBy(xpath= "//*[@id=\"items-place\"]/div[3]/article/a[2]/span[5]/span")
    private WebElement thirdPrice;
    @Override
    public CatalogPage openPage(String url) {
        driver.get(url);
        return this;
    }
    public CatalogPage sortByDescendingPrice() {
        waitUntilElementIsClickable(sortButtonDesc).click();
        return this;
    }
    public boolean checkCorrectSortingDescending(){
        return Double.parseDouble(firstPrice.getText().replaceAll("\\s+",""))>=Double.parseDouble(secondPrice.getText().replaceAll("\\s+",""))&&Double.parseDouble(secondPrice.getText().replaceAll("\\s+",""))>=
                Double.parseDouble(thirdPrice.getText().replaceAll("\\s+",""));
    }
    public CatalogPage sortByAscendingPrice() {
        waitUntilElementIsClickable(sortButtonAsc).click();
        return this;
    }
    public boolean checkCorrectSortingAscending(){
        return Double.parseDouble(firstPrice.getText().replaceAll("\\s+",""))<=Double.parseDouble(secondPrice.getText().replaceAll("\\s+",""))&&Double.parseDouble(secondPrice.getText().replaceAll("\\s+",""))<=
                Double.parseDouble(thirdPrice.getText().replaceAll("\\s+",""));
    }
}
