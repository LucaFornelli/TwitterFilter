package it.luca.TwitterFilter;

import it.luca.TwitterFilter.core.Twitter4JSearchTweets;

/**
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Twitter4JSearchTweets st = new Twitter4JSearchTweets();
    	st.SearchUsers("twitter4j");
    	System.out.println("hello world");
    }
    
}
