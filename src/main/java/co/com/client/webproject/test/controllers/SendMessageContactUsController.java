package co.com.client.webproject.test.controllers;


import co.com.client.webproject.test.page.ContactUsPage;
import co.com.client.webproject.test.page.LandingPage;

import co.com.sofka.test.actions.WebAction;
import co.com.sofka.test.evidence.reports.Report;
import co.com.sofka.test.exceptions.WebActionsException;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;

import static co.com.client.webproject.test.helpers.ToolsNumber.chooseIndexRamdon;

public class SendMessageContactUsController {
    private WebAction webAction;
    public void setWebAction(WebAction webAction) {
        this.webAction = webAction;
    }

    public void goToContactUsPage(){
        try{
            LandingPage landingPage = new LandingPage(webAction.getDriver());
            webAction.click(landingPage.getContactUs(), 5, true);
        } catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar ir a la página de contact US", e);
        }
    }
    public void fillMessageForm(){
        try{
            ContactUsPage contactUsPage = new ContactUsPage(webAction.getDriver());
            Faker faker = new Faker();
            WebElement[] remitter = new WebElement[]{contactUsPage.getWebMasterOption(),
                                                                    contactUsPage.getCustomerServiceOption()};

            webAction.click( remitter[chooseIndexRamdon(remitter.length)],true);
            webAction.sendText(contactUsPage.getEmail(),faker.internet().emailAddress(),true);
        }catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar iniciar session", e);
        }
    }
    public void fillFieldMessage(){
        Faker faker = new Faker();
        ContactUsPage contactUsPage = new ContactUsPage(webAction.getDriver());
        try {
            webAction.sendText(contactUsPage.getMessage(),faker.lorem().paragraph(),5,true);
        } catch (WebActionsException e) {
            Report.reportFailure("Ocurrio un error al ingresar cuerpo del mensaje", e);
        }
    }
    public void sendMessage(){
        ContactUsPage contactUsPage = new ContactUsPage(webAction.getDriver());
        try {
            webAction.click(contactUsPage.getBtnSend(),5,true);
        } catch (WebActionsException e) {
            Report.reportFailure("Ocurrio un error al ingresar al enviar mensaje", e);
        }
    }

    public String confirmationMessage(){
        ContactUsPage contactUsPage = new ContactUsPage(webAction.getDriver());
        try {
            return webAction.getText(contactUsPage.getMessageConfirm(),true);
        } catch (WebActionsException e) {
            Report.reportFailure("error al recibir mensaje de confirmación", e);
        }
        return null;
    }
    public String errorMessage(){
        ContactUsPage contactUsPage = new ContactUsPage(webAction.getDriver());
        try {
            return webAction.getText(contactUsPage.getMessageError(),5,true);
        } catch (WebActionsException e) {
            Report.reportFailure("error al recibir mensaje de error", e);
        }
        return null;
    }

}
