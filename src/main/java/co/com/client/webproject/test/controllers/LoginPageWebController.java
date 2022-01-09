package co.com.client.webproject.test.controllers;

import co.com.client.webproject.test.models.Credential;
import co.com.client.webproject.test.page.LandingPage;
import co.com.client.webproject.test.page.LoginPage;
import co.com.sofka.test.actions.WebAction;
import co.com.sofka.test.evidence.reports.Report;
import co.com.sofka.test.exceptions.WebActionsException;

import static co.com.client.webproject.test.helpers.AdminCredential.getCredential;

public class LoginPageWebController {

    private WebAction webAction;

    public void setWebAction(WebAction webAction) {
        this.webAction = webAction;
    }

    public void goToLoginPage(){
        try{
            LandingPage landingPage = new LandingPage(webAction.getDriver());
            webAction.click(landingPage.getSignIn(), 5, true);
        } catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar ir a la página de login.", e);
        }
    }
    public void logIn(){
        try{
            Credential credential = getCredential();
            LoginPage loginPage = new LoginPage(webAction.getDriver());
                webAction.sendText(loginPage.getEmailLogin(),credential.getEmail(),5,true);
                webAction.sendText(loginPage.getPassword(),credential.getPassword(),true);
                webAction.click(loginPage.getBtnSignIn(),true);
        }catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar iniciar session", e);
        }
    }
}
