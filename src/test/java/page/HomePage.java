package page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class HomePage extends AbstractPage{

    public HomePage (WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[@class=\"im-popup-link\"]")
    private WebElement authorizationButton;
    @FindBy(id= "login")
    private WebElement inputLogin;
    @FindBy(id= "password")
    private WebElement inputPassword;
    @FindBy(xpath = " //*[@id=\"enter\"]/div/form/input")
    private WebElement enterButton;
    @FindBy(name = "q")
    private WebElement searchString;
    @FindBy(xpath = " //*[@id=\"items-place\"]/div[1]/article/a[2]/span[3]")
    private WebElement nameOfItemInCatalog;

    @Override
    public HomePage openPage(String url) {
        driver.get(url);
        return this;
    }
    public HomePage selectAuthorization(){
        waitUntilElementIsClickable(authorizationButton).click();
        return this;
    }
    public HomePage inputUserLoginInAuthorization(String login){
        waitUntilElementIsClickable(inputLogin).click();
        waitUntilVisibilityOf(inputLogin).sendKeys(login);
        return this;
    }
    public HomePage inputUserPasswordInAuthorization(String password){
        waitUntilElementIsClickable(inputPassword).click();
        waitUntilVisibilityOf(inputPassword).sendKeys(password);
        return this;
    }
    public HomePage Enter() {
        waitUntilElementIsClickable(enterButton).click();
        return this;
    }
    public String checkInvalidLoginAndPasswordInAuthorization() throws InterruptedException {
        Thread.sleep(1000);
        WebElement invalidLoginString = driver.findElements(By.xpath("//*[@id=\"_error-message\"]")).get(2);
        return invalidLoginString.getText();
    }
    public String inputInSearchString(String searchItem){
        waitUntilElementIsClickable(searchString).click();
        waitUntilVisibilityOf(searchString).sendKeys(searchItem);
        searchString.sendKeys(Keys.ENTER);
        return searchItem;
    }
    public boolean checkCorrectSearching(String searchItem){
        return nameOfItemInCatalog.getText().contains(searchItem);
    }
}
