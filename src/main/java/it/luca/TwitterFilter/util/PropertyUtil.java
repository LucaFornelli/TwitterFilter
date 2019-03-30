package it.luca.TwitterFilter.util;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties properties = new Properties();
	
    static {
    	
        try {
            
            final Properties configProperties = new Properties();
            configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            
            properties.putAll(configProperties);
            
        } catch (IOException e) {

            e.printStackTrace();

        }        

    }

    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
