package co.com.client.webproject.test.controllers;

import co.com.client.webproject.test.helpers.CategoryClothes;
import co.com.client.webproject.test.helpers.ChoiseOption;
import co.com.client.webproject.test.page.MyAccountPage;
import co.com.sofka.test.actions.WebAction;
import co.com.sofka.test.evidence.reports.Report;
import co.com.sofka.test.exceptions.WebActionsException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static co.com.client.webproject.test.helpers.CategoryClothes.*;
import static co.com.client.webproject.test.helpers.ChoiseOption.choiseOption;
import static co.com.client.webproject.test.helpers.ToolsNumber.chooseNumberRamdon;
import static co.com.client.webproject.test.helpers.UtilFile.cleanDouble;


public class BuyProductController {

    private WebAction webAction;
    private static final Logger logger = LogManager.getLogger(BuyProductController.class);

    public void setWebAction(WebAction webAction) {
        this.webAction = webAction;
    }

    public void selectCategoryRandomly(){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        ChoiseOption optionCategory =  choiseOption(new CategoryClothes[]{
                                                            WOMEN,
                                                            DRESSES,
                                                            T_SHIRTS});

        CategoryClothes catergoryChothes = (CategoryClothes) optionCategory.selectOptionRandon();
        try{
            switch (catergoryChothes){
                case WOMEN:
                    webAction.click(myAccountPage.getWomenCategory(),true);
                    break;
                case DRESSES:
                    webAction.click(myAccountPage.getDressesCategory(),true);
                    break;
                case T_SHIRTS:
                    webAction.click(myAccountPage.gettShirtsCategory(),true);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + catergoryChothes);
            }
        }catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar iniciar session", e);
        }
    }

    public void selectProductRamdonly(){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        try {
            int noProducts = webAction.getDriver()
                                       .findElements(myAccountPage.getContainerProducts())
                                        .size();

            int index = chooseNumberRamdon(noProducts);

            String containerProduct = "//*[@id=\"center_column\"]/ul/li[%s]/div";
            String addCart = "//*[@id=\"center_column\"]/ul/li[%s]/div/div[2]/div[2]/a[1]";

            By cardProduct = By.xpath(String.format(containerProduct,index));
            By btnAddCart = By.xpath(String.format(addCart,index));


            webAction.waitForVisibility(webAction.getDriver().findElement(cardProduct),true);
            webAction.moveTo(webAction.getDriver().findElement(cardProduct),true);
            webAction.click(webAction.getDriver().findElement(btnAddCart),true);

            webAction.click(myAccountPage.getBtnContinueShopping(),true);

       } catch (WebActionsException e) {
           Report.reportFailure("Ocurrio un error al intentar agregar producto al carrito", e);
        }
    }

    public void selectProducts(int cant){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        for(int i=0;i<cant;i++){
            selectCategoryRandomly();
            selectProductRamdonly();
        }
        try {
            webAction.moveTo(myAccountPage.getCartShoping(),true);
            webAction.click(myAccountPage.getBtnCheckOut(),true);
        } catch (WebActionsException e) {
            Report.reportFailure("Ocurrio un error al intentar presionar el botón check out del carrito de compras", e);
        }
    }
    public  double getTotalPrice(){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        double sum = 0.0;

        try {

            double totalShipping = cleanDouble(
                                             webAction.getText(
                                             myAccountPage.getTotalShipping(),true));

            webAction.waitForVisibility(myAccountPage.getPriceTotal(),true);
            List<WebElement> priceProducts = webAction.getDriver()
                                             .findElements(myAccountPage.getPriceProducts());

            for(WebElement webElement:priceProducts){
                webElement.getText();
                sum+= cleanDouble(webElement.getText());
            }
            logger.info("Suma de precio fue <"+sum+">");
            return sum+totalShipping;

        } catch (WebActionsException e) {
            Report.reportFailure("Ocurrio un error al intentar obtener valores de la tabla de resumen de productos", e);
        }
        return sum;
    }
    public  double getTotalPriceCalculedSistem(){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        double priceTotal=0.0;
        try {
            priceTotal= cleanDouble(
                    webAction.getText(myAccountPage.getPriceTotal(),true));

            logger.info("El precio calculado por el sistema fue <"+priceTotal+">");
        } catch (WebActionsException e) {
            Report.reportFailure("Ocurrio un error al intentar obtener valores de la tabla de resumen de productos", e);
        }
        return priceTotal;
    }

    public void goToShippingSection(){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        try{
            webAction.click(myAccountPage.getGetBtnProcessCheckout(),5,true);
            webAction.click(myAccountPage.getBtnProcessAdress(),5,true);
            webAction.click(myAccountPage.getBtnProcessCarrier(),5,true);
        }catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar dirigirse a la página de envío: ",e);
        }
    }
    public String gotErrorMessage(){
        MyAccountPage myAccountPage = new MyAccountPage(webAction.getDriver());
        try{
            return webAction.getText(myAccountPage.getErrorConfirmCondition(),5,true);

        }catch (WebActionsException e){
            Report.reportFailure("Ocurrio un error al intentar dirigirse a la página de envío");
        }
        return null;
    }
}
