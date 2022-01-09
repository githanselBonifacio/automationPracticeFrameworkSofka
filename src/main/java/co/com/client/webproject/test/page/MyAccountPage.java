package co.com.client.webproject.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    @CacheLookup
    @FindBy(className = "account")
    WebElement customerName;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[1]")
    WebElement womenCategory;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[2]")
    WebElement dressesCategory;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"block_top_menu\"]/ul/li[3]")
    WebElement tShirtsCategory;

    public final By containerProducts = By.className("product-container");

    @FindBy(xpath = "//*[@title=\"Continue shopping\"]")
    WebElement btnContinueShopping;

    @FindBy(xpath = "//*[@title=\"View my shopping cart\"]")
    WebElement cartShoping;

    @FindBy(id = "button_order_cart")
    WebElement btnCheckOut;

    @FindBy(id = "total_price_without_tax")
    WebElement priceTotal;

    @FindBy(id = "total_shipping")
    WebElement totalShipping;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]/span")
    WebElement getBtnProcessCheckout;

    @FindBy(name = "processAddress")
    WebElement btnProcessAdress;

    @FindBy(id = "cgv")
    WebElement checkCondition;

    @FindBy(name = "processCarrier")
    WebElement btnProcessCarrier;

    @FindBy(className = "fancybox-error")
    WebElement errorConfirmCondition;

    public final By priceProducts = By.xpath("//*[@class=\"cart_total\"]/span");

    public WebElement getCustomerName() {
        return customerName;
    }

    public WebElement getWomenCategory() {
        return womenCategory;
    }

    public WebElement getDressesCategory() {
        return dressesCategory;
    }

    public WebElement gettShirtsCategory() {
        return tShirtsCategory;
    }

    public By getContainerProducts() {
        return containerProducts;
    }

    public WebElement getBtnCheckOut() {
        return btnCheckOut;
    }

    public WebElement getBtnContinueShopping() {
        return btnContinueShopping;
    }

    public WebElement getCartShoping() {
        return cartShoping;
    }

    public By getPriceProducts() {
        return priceProducts;
    }

    public WebElement getPriceTotal() {
        return priceTotal;
    }

    public WebElement getTotalShipping() {
        return totalShipping;
    }

    public WebElement getGetBtnProcessCheckout() {
        return getBtnProcessCheckout;
    }

    public WebElement getBtnProcessAdress() {
        return btnProcessAdress;
    }

    public WebElement getCheckCondition() {
        return checkCondition;
    }

    public WebElement getBtnProcessCarrier() {
        return btnProcessCarrier;
    }

    public WebElement getErrorConfirmCondition() {
        return errorConfirmCondition;
    }

    public MyAccountPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
}
