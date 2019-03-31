package it.luca.TwitterFilter.core;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *This class allows users to create tweets on their personal profile
 * @author lucafornelli
 */

public class Twitter4JPostCreator 
{

	private Twitter twitter;
	
	/**
	 * Twitter4JPostCreator constructor
	 */
	public Twitter4JPostCreator () 
	{
		twitter = Twitter4JInstanceCreator.getTwitter();
	}

	/**
	 * This methods create a Tweet with the message specified as parameter
	 * @param message: Text to insert in the Tweet
	 * @return Status class, containing information about the Tweet
	 */
	public Status CreatePost(String message) 
	{
        Status status;
		try 
		{
			status = twitter.updateStatus("Second Message");
	        System.out.println("Creator: " + status.getUser().getName());
	        System.out.println("Post: " + status.getText());
			return status;
		} 
		catch (TwitterException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
