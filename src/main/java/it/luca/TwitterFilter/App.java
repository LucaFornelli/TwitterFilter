package it.luca.TwitterFilter;

import it.luca.util.PropertyUtil;
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
        PropertyUtil prop = new PropertyUtil();
        System.out.println(PropertyUtil.getPropertyValue("ciaone"));
        System.out.println(PropertyUtil.getPropertyValue("oauth.consumerKey"));
        System.out.println(PropertyUtil.getPropertyValue("oauth.consumerSecret"));
        System.out.println(PropertyUtil.getPropertyValue("oauth.accessToken"));
        System.out.println(PropertyUtil.getPropertyValue("oauth.accessTokenSecret"));
        try {
			System.out.println(createTweet());
		} catch (TwitterException e) {
			e.printStackTrace();
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
    
}
