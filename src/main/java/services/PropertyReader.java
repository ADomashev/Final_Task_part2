package services;

import java.util.ResourceBundle;

public class PropertyReader {

    private ResourceBundle bundle;

    public PropertyReader(String propertyFileName) {
        this.bundle = ResourceBundle.getBundle(propertyFileName);
    }

    public String getProperty(String propertyName){
        return this.bundle.getString(propertyName);
    }
}
