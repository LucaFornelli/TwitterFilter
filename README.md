# TwitterFilter
This program allow users to see real time tweets, filtering them by keywords.

This program fetch tweets from the Twitter Streaming APIs and sends data to the Consumer, printing data to the console.

[A] -> [B] -> [C]

The producer (A) connects to the Twitter Stream API and read in all tweets sending them to the producer-consumer (B). The producer-consumer (B) will check if the hashtag appears in the tweet. If yes, it will send it to the consumer ( C ). The sink ( C ) will then log the tweet with a timestamp to the console.

Requirements
JRE (Java Runtime Environment)

Configuration
In the file config.properties, placed in the .jar file, you should add the following keys and secrets. You can get them on dev.twitter.com

oauth.consumerKey =
oauth.consumerSecret =
oauth.accessToken =
oauth.accessTokenSecret =

The PostsPerSecond property indicates the maximum number of posts per seconds that will be displayed

PostsPerSecond =

The filters property indicate the keywords that will be used for filters the stream. Insert them separated by a semicolon

filters = love,friendship,money

Run the application
In the folder of .jar application run: java -jar TwitterFilter.jar