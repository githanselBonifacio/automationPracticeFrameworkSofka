package co.com.client.webproject.test.helpers;
import co.com.client.webproject.test.models.Credential;
import co.com.client.webproject.test.models.Customer;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static co.com.client.webproject.test.helpers.PathFile.ACCESS_CREDENTIALS_LOGIN;
import static co.com.client.webproject.test.helpers.UtilFile.*;


public class AdminCredential {

    public static final Logger logger = LogManager.getLogger(AdminCredential.class);

    private AdminCredential() {
    }

    public static String uptateAccessCredentialUser(Customer customer){
        String json = readJson(ACCESS_CREDENTIALS_LOGIN.getValue());

        DocumentContext context = JsonPath.parse(json, config);
        context.set("$.email",customer.getEmail());
        context.set("$.password", customer.getPassword());
        context.set("$.firstName", customer.getFirstName());
        context.set("$.lastName", customer.getLastName());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCESS_CREDENTIALS_LOGIN.getValue()))) {
            bw.write(context.jsonString());
            logger.info("credentials have been updated");
        } catch (IOException ex) {
            Logger.getLogger(UtilFile.class.getName());
        }

        return context.jsonString();
    }

    public static Map<String,String> readAccessCredentialUser(){
        Map<String,String> map = new HashMap<>();
        String json = readJson(ACCESS_CREDENTIALS_LOGIN.getValue());

        DocumentContext context = JsonPath.parse(json, config);
        map.put("email",context.read("$.email").toString());
        map.put("password",context.read("$.password").toString());
        map.put("firstName",context.read("$.firstName").toString());
        map.put("lastName",context.read("$.lastName").toString());

        return map;
    }
    public static Credential getCredential(){
        Map<String,String> mapCredential = readAccessCredentialUser();

        return new Credential(cleanQuoteString(mapCredential.get("firstName")),
                              cleanQuoteString(mapCredential.get("lastName")),
                              cleanQuoteString(mapCredential.get("email")),
                              cleanQuoteString(mapCredential.get("password")));
    }
}
