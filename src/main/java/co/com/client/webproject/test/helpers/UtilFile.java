package co.com.client.webproject.test.helpers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class UtilFile {
    public static final Logger logger = LogManager.getLogger(UtilFile.class);

    private UtilFile() {
    }

    public static final Configuration config =
            Configuration.builder()
                    .jsonProvider(new GsonJsonProvider())
                    .mappingProvider(new GsonMappingProvider())
                    .build();


    public static String readJson(String path) {
        DocumentContext json;
        try {
            json = JsonPath.parse(new File(path));
            return json.jsonString();
        } catch (IOException e) {
            logger.warn("Error open file");
            return null;
        }
    }

    public static String cleanQuoteString(String string){
        return string.replaceAll("[\" ]","");
    }

    public static String cleanSpecialCharacters ( String string) {
        return string.replaceAll("[A-Z-a-z$_\" ]","");
    }


    public static Double cleanDouble(String string){
        return Double.parseDouble(cleanSpecialCharacters(string));
    }
}
