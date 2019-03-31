package it.luca.TwitterFilter.core;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * This class allows users to search Tweets that match the message given in input
 * @author lucafornelli
 */
public class Twitter4JSearchTweets 
{
	private Twitter twitter;
	
	/**
	 * The constructor of this class get a reference to the 
	 * twitter class returned by the Twitter4JInstanceCreator
	 */
	public Twitter4JSearchTweets () 
	{
		twitter = Twitter4JInstanceCreator.getTwitter();
	}
	
	/**
	 * The SearchTweets method, take a message as a parameter and use it for the tweets search.
	 * For each tweet found, it prints to the console the name of the user and the tweet text.
	 * @param message: keyword used for research
	 */
	public void SearchTweets (String message) 
	{
        Query query = new Query(message);
        try 
        {
	        QueryResult result = twitter.search(query);
	        for (Status status : result.getTweets()) 
	        {
	            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	        }
        } 
        catch (TwitterException e) 
        {
        	e.printStackTrace();
        }
	}
	
	/**
	 * The SearchUsers method, take a message as a parameter and use it for the tweets search.
	 * For each tweet found, it prints to the console the name of the user and the profile description.
	 * @param message: keyword used for research
	 */
	public void SearchUsers (String message) 
	{
        try 
        {
	        List<User> result = twitter.searchUsers(message, 20);
	        for (User user : result) 
	        {
	            System.out.println("@" + user.getName() + ": " + user.getDescription());
	        }
        } 
        catch (TwitterException e) 
        {
        	e.printStackTrace();
        }
	}
}
