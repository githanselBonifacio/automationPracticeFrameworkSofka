package co.com.client.webproject.test.helpers;

import java.util.Random;

public class ToolsNumber {
    private static final int CONTROL_CERO_INDEX = 1;
    private ToolsNumber() {
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
