package it.luca.TwitterFilter.core;

import it.luca.TwitterFilter.util.PropertyUtil;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Twitter4JInstanceCreator {
	
	private static Twitter twitter;
	
	static {
		if (twitter == null)
			twitter = getTwitterInstance();
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
	
	public static Twitter getTwitter() {
		return twitter;
	}
	
}
