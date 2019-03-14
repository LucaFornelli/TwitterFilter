package it.luca.TwitterFilter;

import it.luca.util.PropertyUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PropertyUtil prop = new PropertyUtil();
        System.out.println(prop.getPropertyValue("ciaone"));
    }
}
