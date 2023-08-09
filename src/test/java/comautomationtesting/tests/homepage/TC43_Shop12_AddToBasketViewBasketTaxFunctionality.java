package comautomationtesting.tests.homepage;

import comautomationtesting.pages.*;
import comautomationtesting.utilities.ConfigurationReader;
import comautomationtesting.utilities.Driver;
import comautomationtesting.utilities.ReusableMethods;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC43_Shop12_AddToBasketViewBasketTaxFunctionality {
    Homepage homepage = new Homepage();
    ShopPage shopPage=new ShopPage();
    BasketPage basketPage=new BasketPage();
    CheckoutPage checkoutPage=new CheckoutPage();
    @Test
    public void AddToBasketViewBasketTaxFunctionality() throws InterruptedException {
        //1) Open the browser
        //2) Enter the URL “http://practice.automationtesting.in/”
        Driver.getDriver().get(ConfigurationReader.getProperty("au_url"));
        //3) Click on Shop Menu
        ReusableMethods.clickWithJS(homepage.shopButton);
        Driver.getDriver().navigate().refresh();
        ReusableMethods.clickWithJS(homepage.shopButton);
        //4) Click on the Add To Basket button which adds that book to your basket
        shopPage.addToBasketButtonAndroidQickStartShop.click();
        //5) User can view that Book in the Menu item with price .
        assert shopPage.textPriceShop.isDisplayed();
        //6) Now click on Item link which navigates to proceed to check out page.
        Driver.getDriver().navigate().refresh();
        Thread.sleep(1000);
        ReusableMethods.clickWithJS(shopPage.itemButtonShop);
        //7) Now user can find total and subtotal values just above the Proceed to Checkout button.
        Assert.assertTrue(basketPage.subtotalPriceBasket.isDisplayed());
        Assert.assertTrue(basketPage.totalPriceBasket.isDisplayed());
        //8) The total always < subtotal because taxes are added in the subtotal
        String subTotal=basketPage.subtotalPriceBasket.getText();
        subTotal=subTotal.replaceAll("\\D","");
        String total=basketPage.totalPriceBasket.getText();
        total=total.replaceAll("\\D","");
        int totalInt=Integer.parseInt(total);
        int subtotalInt=Integer.parseInt(subTotal);
        Assert.assertTrue(totalInt>subtotalInt);
        //9) The tax rate variers for India compared to other countries
        ReusableMethods.clickWithJS(basketPage.proceedToCheckoutButtonBasket);
        //10) Tax rate for indian should be 2% and for abroad it should be 5%
       ReusableMethods.scrollIntoViewJS(checkoutPage.phoneNameCheckout);
       checkoutPage.phoneNameCheckout.sendKeys(Keys.SPACE,Keys.SPACE,Keys.TAB,Keys.ARROW_UP,Keys.ARROW_UP,Keys.ARROW_DOWN,Keys.ENTER);

       String subtotal=checkoutPage.subtoltalPriceAndTextCheckout.getText();
       subtotal=subtotal.replaceAll("\\D","");
       int yeniSubtotal= Integer.parseInt(subtotal);

       String tax=checkoutPage.taxPriceAndTextCheckout.getText();
       tax=tax.replaceAll("\\D","");
       int yeniTax=Integer.parseInt(tax);

       Assert.assertTrue(2/100==yeniTax/yeniSubtotal);
       Assert.assertEquals(2/100,yeniTax/yeniSubtotal);

        Driver.closeDriver();

    }
}
