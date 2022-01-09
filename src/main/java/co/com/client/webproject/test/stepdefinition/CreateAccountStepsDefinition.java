package co.com.client.webproject.test.stepdefinition;

import co.com.client.webproject.test.controllers.CreateAnAccountWebController;
import co.com.client.webproject.test.controllers.LoginPageWebController;
import co.com.client.webproject.test.controllers.MyAccountWebController;
import co.com.client.webproject.test.controllers.openwebpage.StartBrowserWebController;
import co.com.client.webproject.test.data.objects.TestInfo;
import co.com.client.webproject.test.models.Customer;
import co.com.sofka.test.actions.WebAction;
import co.com.sofka.test.evidence.reports.Assert;
import co.com.sofka.test.evidence.reports.Report;
import co.com.sofka.test.exceptions.WebActionsException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import static co.com.client.webproject.test.helpers.AdminCredential.uptateAccessCredentialUser;
import static co.com.client.webproject.test.helpers.Dictionary.SPACE_STRING;

public class CreateAccountStepsDefinition extends GeneralSetup{

    private WebAction webAction;
    private Customer customer;

    @Before
    public void setUp(Scenario scenario) {
        testInfo = new TestInfo(scenario);
        webAction = new WebAction(testInfo.getFeatureName());
        webAction.setScenario(testInfo.getScenarioName());
    }

    @Dado("que el cliente esta en la página de inicio")
    public void queElClienteEstaEnLaPaginaDeInicio() {
        StartBrowserWebController startBrowserWebController = new StartBrowserWebController();
        startBrowserWebController.setBrowser(browser());
        startBrowserWebController.setWebAction(webAction);
        startBrowserWebController.setFeature(testInfo.getFeatureName());
        startBrowserWebController.openLandingPage();
    }

    @Cuando("el cliente registra sus datos para una cuenta en línea de forma exitosa")
    public void elClienteRegistraSusDatosParaUnaCuentaEnLineaDeFormaExitosa() {
        LoginPageWebController loginPageWebController = new LoginPageWebController();
        loginPageWebController.setWebAction(webAction);
        loginPageWebController.goToLoginPage();

        CreateAnAccountWebController createAnAccountWebController = new CreateAnAccountWebController();
        createAnAccountWebController.setWebAction(webAction);
        createAnAccountWebController.createAccount();
        customer = createAnAccountWebController.getCustomer();
    }

    @Entonces("como resultado el usuario quedará logueado dentro de su respectiva sesión per se.")
    public void comoResultadoElUsuarioQuedaraLogueadoDentroDeSuRespectivaSesionPerSe() {
        MyAccountWebController myAccountWebController = new MyAccountWebController();
        myAccountWebController.setWebAction(webAction);

        Assert.Hard
                .thatString(myAccountWebController.getNameNewUser())
                .isEqualTo(customer.getFirstName() + SPACE_STRING + customer.getLastName());

        uptateAccessCredentialUser(customer);
    }

    @After
    public void tearDown() throws WebActionsException {

        if (webAction != null && webAction.getDriver() != null)
            webAction.closeBrowser();

        Report.reportInfo("***** HA FINALIZADO LA PRUEBA******"
                .concat(testInfo.getFeatureName())
                .concat("-")
                .concat(testInfo.getScenarioName()));
    }

}
