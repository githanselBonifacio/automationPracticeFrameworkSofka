package co.com.client.webproject.test.helpers;

public enum PathFile {
    ACCESS_CREDENTIALS_LOGIN(System.getProperty("user.dir")+
            "\\src\\main\\resources\\file\\customer\\lastAccountRegister.Json");

    private final String value;


    PathFile(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
