package utils;

import java.util.Properties;

public class ConfigUtils {
    private  Properties properties;
    private static ConfigUtils configUtils ;

    private ConfigUtils(){
                properties  = PropertiesUtils.loadProperties("src/test/java/config/staging.properties");
        }

    public static ConfigUtils getInstance(){
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getBaseUrl(){
        String prop =  properties.getProperty("baseUrl");
        if (prop != null) return prop;
        throw new RuntimeException("could not find the base url in the properties file");
    }

    public String getMerchantEmail(){
        String prop =  properties.getProperty("loginEmail");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant Email in the properties file");
    }

    public String getMerchantPassword(){
        String prop =  properties.getProperty("loginPass");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant password in the properties file");
    }
    public String getInvoiceID(){
        String prop =  properties.getProperty("invoiceId");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant password in the properties file");
    }
    public String getOrderID(){
        String prop =  properties.getProperty("orderId");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant password in the properties file");
    }
    public String getTransactionID(){
        String prop =  properties.getProperty("transactionID");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant password in the properties file");
    }
    public String getNewPassword(){
        String prop =  properties.getProperty("newPass");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant password in the properties file");
    }
    public String getConfirmPassword(){
        String prop =  properties.getProperty("confirmPass");
        if (prop != null) return prop;
        throw new RuntimeException("could not find Merchant password in the properties file");
    }

}
