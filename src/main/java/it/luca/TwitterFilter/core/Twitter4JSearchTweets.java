package it.luca.TwitterFilter.core;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class Twitter4JSearchTweets {

	private Twitter twitter;
	
	public Twitter4JSearchTweets () {
		twitter = Twitter4JInstanceCreator.getTwitter();
	}
	
	public void SearchTweets (String message) {

        Query query = new Query(message);
        try {
	        QueryResult result = twitter.search(query);
	        for (Status status : result.getTweets()) {
	            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	        }
        } catch (TwitterException e) {
        	e.printStackTrace();
        }
	}
	
	public void SearchUsers (String message) {
        try {
	        List<User> result = twitter.searchUsers(message, 20);
	        for (User user : result) {
	            System.out.println("@" + user.getName() + ": " + user.getDescription());
	        }
        } catch (TwitterException e) {
        	e.printStackTrace();
        }
	}
}
