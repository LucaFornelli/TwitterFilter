package it.luca.TwitterFilter.core;

import it.luca.TwitterFilter.util.PropertyUtil;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

public class Twitter4JStreamer {
	
	private static Long startTime = System.currentTimeMillis();
	private static Long elapsedTime;
	private static Long totalPosts = 0L;
	private static long pps = Integer.valueOf(PropertyUtil.getPropertyValue("PostsPerSecond"));
	
	public static void filteredSearch(String[] filter) {
		
	    StatusListener listener = new StatusListener() {
	 
	    	@Override
            public void onStatus(Status status) {
                if (checkPostPerSecondAverage()) {
                	System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            	
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
//                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
//                System.out.println("Got stall warning:" + warning);
                
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
	    };
	    
	    TwitterStream twitterStream = Twitter4JInstanceCreator.getTwitterStream();
	 
	    twitterStream.addListener(listener);
	    FilterQuery query = new FilterQuery();
	    query.track(filter);
	    
	    twitterStream.filter(query);

	}
	
	public static void sampleSearch() {
		
	    StatusListener listener = new StatusListener() {
	 
	    	@Override
            public void onStatus(Status status) {
                if (checkPostPerSecondAverage()) {
                	System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
//                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            	
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
//                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
//                System.out.println("Got stall warning:" + warning);
                
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
	    };
	    
	    TwitterStream twitterStream = Twitter4JInstanceCreator.getTwitterStream();
	 
	    twitterStream.addListener(listener);
	    twitterStream.sample();
	}
	
	public static boolean checkPostPerSecondAverage() {
    	elapsedTime = System.currentTimeMillis()-startTime;
    	double dElapsedTime = Double.valueOf(elapsedTime);
    	double dTotalPost = Double.valueOf(totalPosts);
    	double average = ((dTotalPost+1) / (dElapsedTime/1000));
    	if (pps > average) {
        	totalPosts++;
        	return true;
    	} else
    		return false;
    }
}

