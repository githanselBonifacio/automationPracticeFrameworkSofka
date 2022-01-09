package co.com.client.webproject.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactUsPage {


    @FindBy(id = "id_contact")
    WebElement subjectHeading;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"id_contact\"]/option[@value=\"1\"]")
    WebElement webMasterOption;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"id_contact\"]/option[@value=\"2\"]")
    WebElement customerServiceOption;

    @CacheLookup
    @FindBy(id = "email")
    WebElement email;

    @CacheLookup
    @FindBy(id = "message")
    WebElement message;

    @CacheLookup
    @FindBy(id = "submitMessage")
    WebElement btnSend;

    @FindBy(xpath = "//p[@class=\"alert alert-success\"]")
    WebElement messageConfirm;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"center_column\"]/div/ol/li")
    WebElement messageError;

    public WebElement getSubjectHeading() {
        return subjectHeading;
    }

    public WebElement getWebMasterOption() {
        return webMasterOption;
    }

    public WebElement getCustomerServiceOption() {
        return customerServiceOption;
    }

    public WebElement getEmail() {
        return email;
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getBtnSend() {
        return btnSend;
    }

    public WebElement getMessageError() {
        return messageError;
    }

    public WebElement getMessageConfirm() {
        return messageConfirm;
    }

    public ContactUsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
