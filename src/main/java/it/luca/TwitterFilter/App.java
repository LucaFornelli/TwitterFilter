package it.luca.TwitterFilter;

import java.util.List;

import it.luca.util.PropertyUtil;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Twitter twitter = getTwitterinstance();
        
        getTweets(twitter);
    }

	private static void getTweets(Twitter twitter) {
		try {
        	Query query = new Query("user:anal");
            query.setCount(100);
            query.lang("en");

            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
    
    public static String createTweet() throws TwitterException {
        Twitter twitter = getTwitterinstance();
        Status status = twitter.updateStatus("Second Message");
        return status.getText();
    }
    
    static Twitter getTwitterinstance() {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(PropertyUtil.getPropertyValue("oauth.consumerKey"))
          .setOAuthConsumerSecret(PropertyUtil.getPropertyValue("oauth.consumerSecret"))
          .setOAuthAccessToken(PropertyUtil.getPropertyValue("oauth.accessToken"))
          .setOAuthAccessTokenSecret(PropertyUtil.getPropertyValue("oauth.accessTokenSecret"));
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
    }
    
    public static void searchtweets() throws TwitterException {
    	  
        Twitter twitter = getTwitterinstance();
        Query query = new Query("source:twitter4j yusukey");
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
    }
    
}
