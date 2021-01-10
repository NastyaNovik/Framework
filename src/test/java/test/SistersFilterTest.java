package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.CatalogPage;

public class SistersFilterTest  extends CommonConditions{
    public static final String CATALOG_PAGE_URL = "https://sisters.by/catalog";
    @Test
    public void checkSortByDescendingPriceTest(){
        boolean correctCheck = new CatalogPage(driver)
                .openPage(CATALOG_PAGE_URL)
                .sortByDescendingPrice()
                .checkCorrectSortingDescending();
        Assert.assertTrue(correctCheck);
    }
    @Test
    public void checkSortByAscendingPriceTest(){
        boolean correctCheck = new CatalogPage(driver)
                .openPage(CATALOG_PAGE_URL)
                .sortByAscendingPrice()
                .checkCorrectSortingAscending();
        Assert.assertTrue(correctCheck);
    }
}
