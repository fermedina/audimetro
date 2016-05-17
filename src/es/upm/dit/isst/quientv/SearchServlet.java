package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

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
		
		// Obtenemos los tweets almacenados y los borramos antes de guardar los nuevos
		/*for(Tweet tweet: tweetDao.getTweetList()) {
			tweetDao.deleteTweet(tweet.getId());
		};*/
		
		String localizacion = "Indeterminada";
						
		for (int i = 0; i < hashtagDao.getHashtagListInSearchPeriod().size(); i++) {
			Query query = new Query("#" + hashtagDao.getHashtagListInSearchPeriod().get(i).getNombre());
			query.setCount(100);

			try {
				QueryResult result = twitter.search(query);

				for (Status status : result.getTweets()) {
					if (status.getGeoLocation() != null) {
						localizacion = getProvincia(status.getGeoLocation().getLatitude(), status.getGeoLocation().getLongitude());
					}
					
					Tweet tweet = new Tweet (hashtagDao.getHashtagListInSearchPeriod().get(i).getId(), 
												status.getText(), status.getLang(), 
												localizacion, 
												status.getUser().getScreenName(), 
												urlTwitter + status.getUser().getScreenName(), 
												status.getUser().getProfileImageURL(), 
												status.getUser().getFollowersCount(), 
												status.getRetweetCount(), 
												status.getFavoriteCount());
					if (!tweetDao.isStored(tweet.getUsuario(), tweet.getTexto())){
						insertedTweets.add(tweetDao.newTweet(tweet));
					}
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*RequestDispatcher view = req.getRequestDispatcher("/jsp/add.jsp");
		view.forward(req, resp);*/
	}
	
	public static String getProvincia(double lat, double lon) throws MalformedURLException, IOException{
		String key = "AIzaSyB0tRpeEfOEvt7X5i8TKKXOWzGqLsQh9_E";
		String urlString = "https://maps.googleapis.com/maps/api/geocode/json";
		urlString += "?latlng=" + lat + "," + lon;
		urlString += "&key=" + key;
		
		String placeJson = IOUtils.toString(new URL(urlString), "UTF-8");
		JSONObject json = new JSONObject(placeJson);
		
		//Si no se obtienen resultados
		if(!json.get("status").equals("OK")){
			return "Indifinida";
		}
		
		//Obtiene el elemento results
		JSONArray results = (JSONArray) json.get("results");
		
		//Obtiene el primer elemento de results
		JSONObject resultsObj = (JSONObject)results.getJSONObject(0);
		
		//Obtiene los valores de address_components
		JSONArray address_components = (JSONArray)resultsObj.getJSONArray("address_components");
		
		//Recorre address_components para buscar el valor deseado para la provincia
		for(int i = 0; i < address_components.length(); i++){		
			JSONObject addressObj = (JSONObject)address_components.getJSONObject(i);
			JSONArray array = (JSONArray) addressObj.get("types");
			
			if (array.get(0).equals("administrative_area_level_2")){
				return addressObj.get("long_name").toString();
			}
		}
		return "Indefinida";
	}
}


