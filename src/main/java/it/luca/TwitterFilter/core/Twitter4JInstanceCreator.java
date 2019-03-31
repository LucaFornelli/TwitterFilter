package it.luca.TwitterFilter.core;

import it.luca.TwitterFilter.util.PropertyUtil;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *This class implements twitter authentication and, thanks to singleton pattern to 
 *make sure that twitter and twitterStream will be instantiate at least one time
 * @author lucafornelli
 */
public class Twitter4JInstanceCreator 
{

	private static Twitter twitter;
	private static TwitterStream twitterStream;
	
	/* Instantiate Twitter and TwitterStream class */
	static 
	{
		if (twitter == null)
			twitter = getTwitterInstance();
		if (twitterStream == null)
			twitterStream = getTwitterStreamInstance();
	}
	
	/**
	 * @return an instance of Twitter class
	 */
	private static Twitter getTwitterInstance() 
	{
		ConfigurationBuilder cb = GetAuthentication();
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        return twitter;
	}
	
	/**
	 * @return an instance of TwitterStream class
	 */
	private static TwitterStream getTwitterStreamInstance() 
	{
		ConfigurationBuilder cb = GetAuthentication();
        TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
        TwitterStream twitter = tf.getInstance();
        return twitter;
	}

	/**
	 * Authenticate the user with keys stored in config.properties file
	 * @return a ConfigurationBuilder class
	 */
	private static ConfigurationBuilder GetAuthentication() 
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(PropertyUtil.getPropertyValue("oauth.consumerKey"))
          .setOAuthConsumerSecret(PropertyUtil.getPropertyValue("oauth.consumerSecret"))
          .setOAuthAccessToken(PropertyUtil.getPropertyValue("oauth.accessToken"))
          .setOAuthAccessTokenSecret(PropertyUtil.getPropertyValue("oauth.accessTokenSecret"));
		return cb;
	}
	
	/**
	 * @return Twitter class
	 */
	public static Twitter getTwitter() 
	{
		return twitter;
	}
	
	/**
	 * @return TwitterStream class
	 */
	public static TwitterStream getTwitterStream() 
	{
		return twitterStream;
	}
	
}
