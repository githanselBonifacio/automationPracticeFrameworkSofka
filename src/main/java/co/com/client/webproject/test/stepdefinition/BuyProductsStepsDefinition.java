package co.com.client.webproject.test.stepdefinition;

import co.com.client.webproject.test.controllers.BuyProductController;
import co.com.client.webproject.test.controllers.LoginPageWebController;
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

public class BuyProductsStepsDefinition extends GeneralSetup{
    private WebAction webAction;
    private static final int CANT_PRODUCTS =3;
    private static final String MESSAGE_ERROR_TERMS_AND_CONDITIONS ="You must agree to the terms of service before continuing.";
    BuyProductController buyProductController;
    @Before
    public void setUp(Scenario scenario) {
        testInfo = new TestInfo(scenario);
        webAction = new WebAction(testInfo.getFeatureName());
        webAction.setScenario(testInfo.getScenarioName());
    }

    @Dado("que el cliente ha iniciado sesión")
    public void queElClienteHaIniciadoSesion() {
        StartBrowserWebController startBrowserWebController = new StartBrowserWebController();
        startBrowserWebController.setBrowser(browser());
        startBrowserWebController.setWebAction(webAction);
        startBrowserWebController.setFeature(testInfo.getFeatureName());
        startBrowserWebController.openLandingPage();

        LoginPageWebController loginPageWebController = new LoginPageWebController();
        loginPageWebController.setWebAction(webAction);
        loginPageWebController.goToLoginPage();
        loginPageWebController.logIn();
    }

    @Cuando("agregue productos al carrito y se dirija al carrito de compras")
    public void agregueProductosAlCarritoYSeDirijaAlCarritoDeCompras() {
        buyProductController = new BuyProductController();
        buyProductController.setWebAction(webAction);

        buyProductController.selectProducts(CANT_PRODUCTS);
    }

    @Entonces("la suma de los precios debe ser igual a la calculada por el sistema")
    public void laSumaDeLosPreciosDebeSerIgualALaCalculadaPorElSistema() {
        Report.reportInfo("La suma de los productos más envío es: "+buyProductController.getTotalPrice());
        Report.reportInfo("El precio total calculado por el sistema es: "+buyProductController.getTotalPriceCalculedSistem());
        Assert.Hard
                .thatIsEqual(
                        buyProductController.getTotalPrice(),
                        buyProductController.getTotalPriceCalculedSistem()
                );
    }

    @Cuando("intente seguir a la sección de pagos sin aceptar los terminos y condiciones")
    public void intenteSeguirALaSeccionDePagosSinAceptarLosTerminosYCondiciones (){
        buyProductController.goToShippingSection();
    }
    @Entonces("se mostrará un mensaje de error")
    public void seMostraraUnMensajeDeError(){
        Assert.Hard
                .thatIsEqual(MESSAGE_ERROR_TERMS_AND_CONDITIONS,
                        buyProductController.gotErrorMessage());
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
