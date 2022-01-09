package co.com.client.webproject.test.stepdefinition;

import co.com.client.webproject.test.controllers.SendMessageContactUsController;
import co.com.client.webproject.test.controllers.openwebpage.StartBrowserWebController;
import co.com.client.webproject.test.data.objects.TestInfo;
import co.com.sofka.test.actions.WebAction;
import co.com.sofka.test.evidence.reports.Assert;
import co.com.sofka.test.evidence.reports.Report;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class SendMessageContactUsStepDefinition extends GeneralSetup{
    private WebAction webAction;
    public static final String MESSAGE_RECEIVED = "Your message has been successfully sent to our team.";
    public static final String MESSAGE_ERROR_RECEIVED = "The message cannot be blank.";

    private SendMessageContactUsController sendMessageContactUsController;

    @Before
    public void setUp(Scenario scenario) {
        testInfo = new TestInfo(scenario);
        webAction = new WebAction(testInfo.getFeatureName());
        webAction.setScenario(testInfo.getScenarioName());
    }
    @Dado("el cliente está en el formulario para enviar un mensaje al servicio al cliente")
    public void elClienteEstaEnElFormularioParaEnviarUnMensajeAlServicioAlCliente() {
        StartBrowserWebController startBrowserWebController = new StartBrowserWebController();
        startBrowserWebController.setBrowser(browser());
        startBrowserWebController.setWebAction(webAction);
        startBrowserWebController.setFeature(testInfo.getFeatureName());
        startBrowserWebController.openLandingPage();

        sendMessageContactUsController= new SendMessageContactUsController();
        sendMessageContactUsController.setWebAction(webAction);
        sendMessageContactUsController.goToContactUsPage();

    }

    @Cuando("el cliente llene todos los campos requeridos y envíe")
    public void elClienteLleneTodosLosCamposRequeridosYEnvie() {

        sendMessageContactUsController.fillMessageForm();
        sendMessageContactUsController.fillFieldMessage();
        sendMessageContactUsController.sendMessage();
    }

    @Entonces("el cliente debería ver un mensaje de confirmación de envío")
    public void elClienteDeberiaVerUnMensajeDeConfirmacionDeEnvio() {
        Assert.Hard
                        .thatIsEqual(MESSAGE_RECEIVED,
                                sendMessageContactUsController.confirmationMessage());

    }
    @Cuando("Cuando el cliente complete todos los campos requeridos excepto el mensaje y envíe")
    public void cuandoElClienteCompleteTodosLosCamposRequeridosExceptoElMensajeYEnvie() {
        sendMessageContactUsController.fillMessageForm();
        sendMessageContactUsController.sendMessage();
    }

    @Entonces("Entonces el cliente debería ver un mensaje de error de envío")
    public void entoncesElClienteDeberiaVerUnMensajeDeErrorDeEnvio() {
        Assert.Hard
                .thatIsEqual(MESSAGE_ERROR_RECEIVED,
                        sendMessageContactUsController.errorMessage());
    }
    @After
    public void tearDown()  {

        if (webAction != null && webAction.getDriver() != null)
            webAction.closeBrowser();

        Report.reportInfo("***** HA FINALIZADO LA PRUEBA******"
                .concat(testInfo.getFeatureName())
                .concat("-")
                .concat(testInfo.getScenarioName()));
    }
}
