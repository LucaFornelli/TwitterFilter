package it.luca.TwitterFilter.core;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Twitter4JPostCreator {

	private Twitter twitter;
	
	public Twitter4JPostCreator () {
		twitter = Twitter4JInstanceCreator.getTwitter();
	}
	
	public void createPost(String message) {
        Status status;
		try {
			status = twitter.updateStatus("Second Message");
	        System.out.println("Creator: " + status.getUser().getName());
	        System.out.println("Post: " + status.getText());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
