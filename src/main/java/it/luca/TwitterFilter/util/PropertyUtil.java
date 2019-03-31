package it.luca.TwitterFilter.util;

import java.io.IOException;
import java.util.Properties;

/**
 * This class load config.properties file allowing properties externalization
 * @author lucafornelli
 */
public class PropertyUtil 
{
	
	private static Properties properties = new Properties();
	
	/**
	 * point the config file 
	 */
    static 
    {
        try 
        {
            final Properties configProperties = new Properties();
            configProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            
            properties.putAll(configProperties);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

	/**
	 * This method give access to properties.
	 * Access properties has key-value access.
	 * @param key: the name of the property
	 * @return value of the property
	 */
    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }
}
