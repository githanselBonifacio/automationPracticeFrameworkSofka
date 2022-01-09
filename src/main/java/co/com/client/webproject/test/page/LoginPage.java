package co.com.client.webproject.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @CacheLookup
    @FindBy(id = "email")
    WebElement emailLogin;

    @CacheLookup
    @FindBy(id = "passwd")
    WebElement password;

    @CacheLookup
    @FindBy(id = "SubmitLogin")
    WebElement btnSignIn;


    public WebElement getEmailLogin() {
        return emailLogin;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getBtnSignIn() {
        return btnSignIn;
    }

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
