package test;
import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.BasketPage;
import page.ItemPage;
import page.HomePage;
import service.UserCreator;
import model.User;
import service.ItemCreator;
import page.CatalogPage;

public class SistersTest extends CommonConditions{
    public static final String HOMEPAGE_URL = "https://sisters.by/";
    public static final String CATALOG_PAGE_URL = "https://sisters.by/catalog";
    Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
    Item expectedFirstItem = ItemCreator.withCredentialsFromProperty("first");
    Item expectedSecondItem = ItemCreator.withCredentialsFromProperty("second");
    User expectedUser = UserCreator.withCredentialsFromProperty("first");

    @Test
    public void addItemToBasketTest() throws InterruptedException {
        Item item =new ItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .chooseSize(expectedItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .getItem();
        Assert.assertTrue(expectedItem.equals(item));
        Assert.assertEquals(item.getItemCount(),"1");
    }
    @Test
    public void increaseItemInBasketTest() throws InterruptedException {
        Item item =new ItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .chooseSize(expectedItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .increaseItemInBasket()
                .getItem();
        Assert.assertEquals(item.getItemName(),expectedItem.getItemName());
        Assert.assertEquals(item.getItemSize(),expectedItem.getItemSize());
        Assert.assertEquals(item.getItemCost(),(expectedItem.getItemCost())*2);
        Assert.assertEquals(item.getItemCount(),"2");
    }
    @Test
    public void addItemsWithDifferentSizeToBasketTest() throws InterruptedException {
        Item firstItem = new ItemPage(driver)
                .openPage(expectedFirstItem.getItemUrl())
                .chooseSize(expectedFirstItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .getItem();
        Item secondItem = new ItemPage(driver)
                .openPage(expectedSecondItem.getItemUrl())
                .chooseSize(expectedSecondItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .getItemWithAnotherSize();
        Assert.assertEquals(firstItem.getItemName(),secondItem.getItemName());
        Assert.assertEquals(firstItem.getItemCost(),secondItem.getItemCost());
        Assert.assertEquals(firstItem.getItemCount(), secondItem.getItemCount(),"1");
        Assert.assertNotEquals(firstItem.getItemSize(),secondItem.getItemSize());
    }
    @Test
    public void deleteItemFromBasketTest() throws InterruptedException {
        int countOfItemsInBasket = new ItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .chooseSize(expectedItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .deleteItemFromBasket()
                .getCountOfItemsInBasket();
        Assert.assertEquals(countOfItemsInBasket,0);
    }
    @Test
    public void checkInvalidNumberOfDiscountCardTest() throws InterruptedException {
        String numberOfDiscountCard = new ItemPage(driver)
                .openPage(expectedItem.getItemUrl())
                .chooseSize(expectedItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .inputNumberOfDiscountCard("505050")
                .scrollToItem()
                .checkInvalidNumberOfDiscountCard();
        Assert.assertEquals(numberOfDiscountCard,"Недействительный номер дисконтной карты");
    }
    @Test
    public void checkTotalCostOfItemsInBasketTest() throws InterruptedException {
        Item firstItem = new ItemPage(driver)
                .openPage(expectedFirstItem.getItemUrl())
                .chooseSize(expectedFirstItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .getItem();
        Item secondItem = new ItemPage(driver)
                .openPage(expectedSecondItem.getItemUrl())
                .chooseSize(expectedSecondItem.getItemSize())
                .addToBasket()
                .goToBasket()
                .getItem();
        double totalCost = new BasketPage(driver)
                .totalCostOfItems();
        Assert.assertEquals(totalCost,expectedFirstItem.getItemCost()+expectedSecondItem.getItemCost());
    }
    @Test
    public void checkInvalidLoginAndPasswordInAuthorizationTest() throws InterruptedException {
        String invalidUserLogin = new HomePage(driver)
                .openPage(HOMEPAGE_URL)
                .selectAuthorization()
                .inputUserLoginInAuthorization(expectedUser.getUserLogin())
                .inputUserPasswordInAuthorization(expectedUser.getUserPassword())
                .Enter()
                .checkInvalidLoginAndPasswordInAuthorization();
        Assert.assertEquals(invalidUserLogin,"Пользователь с таким логином не зарегистирован");
    }
    @Test
    public void checkSortByDescendingPriceTest(){
        boolean correctCheck = new CatalogPage(driver)
                .openPage(CATALOG_PAGE_URL)
                .sortByDescendingPrice()
                .checkCorrectSorting();
        Assert.assertTrue(correctCheck);
    }
}
