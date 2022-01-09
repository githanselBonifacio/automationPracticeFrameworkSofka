package co.com.client.webproject.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    @CacheLookup
    @FindBy(css = ".login")
    WebElement signIn;

    @CacheLookup
    @FindBy(id = "contact-link")
    WebElement contactUs;

    public WebElement getSignIn() {
        return signIn;
    }

    public WebElement getContactUs() {
        return contactUs;
    }

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

}
