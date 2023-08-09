package comautomationtesting.tests.homepage;

import comautomationtesting.pages.BasketPage;
import comautomationtesting.pages.Homepage;
import comautomationtesting.pages.ProductPage;
import comautomationtesting.utilities.ConfigurationReader;
import comautomationtesting.utilities.Driver;
import comautomationtesting.utilities.ReusableMethods;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TC07_ArrivalsAddToBasketWithMoreBooks {
    @Test
    public void ArrivalsAddToBasketWithMoreBooks() throws InterruptedException {
        Homepage homepage = new Homepage();
        ProductPage productPage = new ProductPage();
        BasketPage basketPage = new BasketPage();

        //1) Open the browser
        //2) Enter the URL “http://practice.automationtesting.in/”
        Driver.getDriver().get(ConfigurationReader.getProperty("au_url"));
        Reporter.log("Sayfaya gidilir");
        //3) Click on Shop Menu
        ReusableMethods.clickWithJS(homepage.shopButton);
        Reporter.log("Shop butona tiklanir");
        //4) Now click on Home menu button
        Driver.getDriver().navigate().refresh();
        ReusableMethods.clickWithJS(homepage.shopButton);
        ReusableMethods.clickWithJS(homepage.homeButton);
        Reporter.log("Home butona tiklanir");
        //5) Test whether the Home page has Three Arrivals only
        //6) The Home page must contains only three Arrivals
        Assert.assertEquals(homepage.arriwals.size(), 3);
        Reporter.log("Sayfada sadece 3 urun oldugu dogrulanir");
        //7) Now click the image in the Arrivals
        ReusableMethods.clickWithJS(homepage.firstArrivals);
        Reporter.log("İlk urune tiklanir");
        //8) Test whether it is navigating to next page where the user can add that book into his basket.
        Assert.assertTrue(productPage.textSeleniumRubyProduct.isDisplayed());
        Reporter.log("Secilen kitabin sepete eklenecegi sayfanin acildigi dogrulanir");
        //9) Image should be clickable and shoul navigate to next page where user can add that book to his basket
        ReusableMethods.clickWithJS(productPage.imagesSeleniumRubyProduct);
        Reporter.log("İlk urunun resmine tiklanir");
        Driver.getDriver().navigate().refresh();
        ReusableMethods.clickWithJS(productPage.addtobasketButtonProduct);
        Reporter.log("Kullanici urun ekleme butonuna tiklar");
        ReusableMethods.clickWithJS(productPage.viewbasketButtonProduct);
        Reporter.log("İlk urunun sepet sayfasina gidilir");
        Assert.assertTrue(basketPage.textBasketTotalsBasket.isDisplayed());
        Reporter.log("Kullanicinin sepet sayfasinda oldugu dogrulanir");
        //10) Click on the Add To Basket button which adds that book to your basket
        Driver.getDriver().navigate().back();
        ReusableMethods.clickWithJS(productPage.addtobasketButtonProduct);
        Reporter.log("Kullanici sepete ekle butonuna tiklar");
        //11) User can view that Book in the Menu item with price.
        Assert.assertTrue(productPage.text500$$Product.isDisplayed());
        Reporter.log("Kullanici urunun fiyatini sayfada görebilir");
        //12) User can add a book by clicking on Add To Basket button which adds that book in to his Basket
        ReusableMethods.clickWithJS(productPage.viewbasketButtonProduct);
        Reporter.log("Kullanici sepete ekle butonuna tiklar");
        Assert.assertTrue(basketPage.textSeleniumRubyBasket.isDisplayed());
        Reporter.log("Kullanici secilen kitabin sepete eklendigini dogrular");
        Thread.sleep(2000);
        //13) Select more books than the books in stock.Ex if the stock has 20 books, try to add 21.
        Driver.getDriver().navigate().back();
        productPage.sendKeysAddToBasketProduct.clear();
        Thread.sleep(2000);
        productPage.sendKeysAddToBasketProduct.sendKeys("9999999999", Keys.ENTER);
        Reporter.log("Kullanici sayfaya istedigi kadar urun sayisi girebilir");
        //14) Click the add to basket button
        ReusableMethods.clickWithJS(productPage.addtobasketButtonProduct);
        //15) Now it throws an error prompt like you must enter a value between 1 and 20
        Assert.assertTrue(productPage.textAddedToYourBasket.isDisplayed());
        Reporter.log("Kullanici ekledigi urun sayisini gorebilir");
        Driver.closeDriver();

    }
}
