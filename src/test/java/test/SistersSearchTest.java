package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class SistersSearchTest extends CommonConditions{
    public static final String HOMEPAGE_URL = "https://sisters.by/";

    @Test
    public void checkCorrectTemplateSearchingTest(){
        String template = new HomePage(driver)
                .openPage(HOMEPAGE_URL)
                .inputInSearchString("атье");
        boolean checkTemplate = new HomePage(driver)
                .checkCorrectSearching(template);
        Assert.assertTrue(checkTemplate);
    }
}
