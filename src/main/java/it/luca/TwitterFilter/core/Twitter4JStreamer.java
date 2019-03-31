package it.luca.TwitterFilter.core;

import it.luca.TwitterFilter.util.PropertyUtil;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

/**
 * This class allows users to search Tweets that match the message pattern given in input
 * @author lucafornelli
 */
public class Twitter4JStreamer {
	
	private static Long startTime = System.currentTimeMillis();
	private static Long elapsedTime;
	private static Long totalPosts = 0L;
	private static long pps = Integer.valueOf(PropertyUtil.getPropertyValue("PostsPerSecond"));
	
	/**
	 * This method filters the tweetws stream and returns all the tweets which text match at least one of the filter words.
	 * @param filter: words used for filtering tweets stream
	 */
	public static void filteredSearch(String[] filter) {
		
	    StatusListener listener = getStatusListener();
	    
	    TwitterStream twitterStream = Twitter4JInstanceCreator.getTwitterStream();
	 
	    twitterStream.addListener(listener);
	    FilterQuery query = new FilterQuery();
	    query.track(filter);
	    
	    twitterStream.filter(query);

	}

	/**
	 * This method returns random tweets. It will be called when not any word is specified in the properties file
	 */
	public static void sampleSearch() {
		
	    StatusListener listener = getStatusListener();
	    
	    TwitterStream twitterStream = Twitter4JInstanceCreator.getTwitterStream();
	 
	    twitterStream.addListener(listener);
	    twitterStream.sample();
	}
	
	/**
	 * This method manages the numbers of tweets that will be displayed. 
	 * It checks if the limit of max number of tweets per second is reached
	 * @return true: if the limit isn't reached, else it returns false.
	 */
	public static boolean checkPostPerSecondAverage() {
		
		double dElapsedTime;
		double dTotalPost;
		double average;
		
    	elapsedTime = System.currentTimeMillis()-startTime;
    	dElapsedTime = Double.valueOf(elapsedTime);
    	dTotalPost = Double.valueOf(totalPosts);
    	average = ((dTotalPost+1) / (dElapsedTime/1000));
    	
    	if (pps > average) {
        	totalPosts++;
        	return true;
    	} else
    		return false;
    }
	
	/**
	 * This method creates and returns an instance of the StatusListener.
	 * @return StatusListener: a listener for filters the stream and implements different behaviors.
	 */
	private static StatusListener getStatusListener() {
		StatusListener listener = new StatusListener() {
	 
	    	@Override
            public void onStatus(Status status) {
                if (checkPostPerSecondAverage()) {
                	System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            	
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
                
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
	    };
		return listener;
	}
}

