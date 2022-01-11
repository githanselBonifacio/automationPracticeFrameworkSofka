package co.com.client.webproject.test.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class ToolsNumber {
    private static final int CONTROL_CERO_INDEX = 1;
    public static final int TWO_DECIMALS = 2;
    private ToolsNumber() {
    }

    public static double reduceDecimal(double number, int noDecimal){
        BigDecimal bd = BigDecimal.valueOf(number).setScale(noDecimal, RoundingMode.HALF_UP);
        return  bd.doubleValue();
    }
    public static int chooseIndexRamdon(int bound) {
        return new Random().nextInt(bound);
    }

    public static String generateNumberString(int lenght){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<lenght;i++){
            str.append(chooseIndexRamdon(9));
        }
        return str.toString();
    }
    public static int chooseNumberRamdon(int bound){
        return chooseIndexRamdon(bound)+ CONTROL_CERO_INDEX;
    }
}
