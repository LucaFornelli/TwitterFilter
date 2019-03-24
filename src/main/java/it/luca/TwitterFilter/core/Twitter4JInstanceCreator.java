package it.luca.TwitterFilter.core;

import it.luca.TwitterFilter.util.PropertyUtil;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4JInstanceCreator {

	private static Twitter twitter;
	private static TwitterStream twitterStream;
	
	static {
		if (twitter == null)
			twitter = getTwitterInstance();
		if (twitterStream == null)
			twitterStream = getTwitterStreamInstance();
	}
	
	private static Twitter getTwitterInstance() {
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
	
	private static TwitterStream getTwitterStreamInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(PropertyUtil.getPropertyValue("oauth.consumerKey"))
          .setOAuthConsumerSecret(PropertyUtil.getPropertyValue("oauth.consumerSecret"))
          .setOAuthAccessToken(PropertyUtil.getPropertyValue("oauth.accessToken"))
          .setOAuthAccessTokenSecret(PropertyUtil.getPropertyValue("oauth.accessTokenSecret"));
        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
        TwitterStream twitter = tf.getInstance();
        return twitter;
	}
	
	public static Twitter getTwitter() {
		return twitter;
	}
	
	public static TwitterStream getTwitterStream() {
		return twitterStream;
	}
	
}
