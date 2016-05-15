package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.dao.TweetDAO;
import es.upm.dit.isst.quientv.dao.TweetDAOImpl;
import es.upm.dit.isst.quientv.model.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		TweetDAO tweetDao = TweetDAOImpl.getInstance();

		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2:00"));

		// Configuración de Twitter
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("E2BkS641HFWmP8FpniuRV17sz")
		.setOAuthConsumerSecret("Hqm6syuHasgDtOt1W80Ug4imHViWljrOraejJgc4ps3vY2vYMe")
		.setOAuthAccessToken("185700333-rNEFh6XQQENeb2MW1aHyCdM1BKHragwOzKb0IaYO")
		.setOAuthAccessTokenSecret("9aPuPiIVOEfdSwPIN52GsC99OjXWta2h17xMYr6tV04CA");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		ArrayList<Tweet> insertedTweets = new ArrayList<Tweet>();
		String urlTwitter = "https://twitter.com/";

		for (int i = 0; i < hashtagDao.getHashtagListInSearchPeriod().size(); i++) {
			Query query = new Query("#" + hashtagDao.getHashtagListInSearchPeriod().get(i).getNombre());
			query.setCount(100);

			try {
				QueryResult result = twitter.search(query);

				for (Status status : result.getTweets()) {
					insertedTweets.add(tweetDao.newTweet(hashtagDao.getHashtagList().get(i).getId(), status.getText(), status.getLang(), status.getUser().getLocation(), status.getUser().getScreenName(), urlTwitter + status.getUser().getScreenName(), status.getUser().getProfileImageURL(), status.getUser().getFollowersCount(), status.getRetweetCount(), status.getFavoriteCount()));
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
		view.forward(req, resp);
	}
}


