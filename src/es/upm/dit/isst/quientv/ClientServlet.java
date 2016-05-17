package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.quientv.dao.BusquedaDAO;
import es.upm.dit.isst.quientv.dao.BusquedaDAOImpl;
import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.dao.TweetDAO;
import es.upm.dit.isst.quientv.dao.TweetDAOImpl;
import es.upm.dit.isst.quientv.model.Busqueda;
import es.upm.dit.isst.quientv.model.Hashtag;
import es.upm.dit.isst.quientv.model.Tweet;
import es.upm.dit.isst.utilities.Utilities;

@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");

		BusquedaDAO busquedaDao = BusquedaDAOImpl.getInstance();
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		TweetDAO tweetDao = TweetDAOImpl.getInstance();
		
		List<Busqueda> searchList = busquedaDao.getBusquedaList();
		req.setAttribute("searchList", searchList);
		
		// Id de la búsqueda que queremos ver
		String busquedaEncript = req.getParameter("searchId");
		req.setAttribute("client", busquedaEncript);

		String inputBusquedaId = "";
		try {
			inputBusquedaId = Utilities.desencriptar(busquedaEncript.replace(' ','+'));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Busqueda busqueda = busquedaDao.getBusqueda(inputBusquedaId);		
		req.setAttribute("busqueda", busqueda);
		
		List<Hashtag> hashtags = hashtagDao.getHashtagListByBusquedaId(inputBusquedaId);
		List<Tweet> allTweets = tweetDao.getTweetList();
		ArrayList<Tweet> insertedTweets = new ArrayList<Tweet>();
		
		for(Hashtag hashtag : hashtags) {
			for(Tweet tweet : allTweets) {
				if (tweet.getHashtagId().equals(hashtag.getId())) {
					insertedTweets.add(tweet);
				}
			}
		}
		
		req.setAttribute("insertedTweets", insertedTweets);
				
		if (hashtags.size() > 0) {
			Hashtag hashtag1 = hashtags.get(0);
			
			Hashtag hashtag2 = null;
			Hashtag hashtag3 = null;
			Hashtag hashtag4 = null;

			if (hashtags.size() == 2) {
				hashtag2 = hashtags.get(1);
			}
			if (hashtags.size() == 3) {
				hashtag2 = hashtags.get(1);				
				hashtag3 = hashtags.get(2);
			}
			if (hashtags.size() == 4) {
				hashtag2 = hashtags.get(1);				
				hashtag3 = hashtags.get(2);				
				hashtag4 = hashtags.get(3);
			}

			// Si hay tweets insertados en la base de datos: extraigo 8 usuarios y sus tweets para la tabla "Últimos tweets"
			if (insertedTweets.size() > 0) {

				ArrayList<Tweet> lastTweets = new ArrayList<Tweet>();
				for(int i = 0; i < 7; i++) {
					int pos = (insertedTweets.size() - 1) -i;
					lastTweets.add(insertedTweets.get(pos));
				}

				String[] hashtagNames = {"", "", "", ""};
				int [] tweetsCount = {0, 0, 0, 0};
				int [] retweetsCount = {0, 0, 0, 0};
				int [] favCount = {0, 0, 0, 0};

				for(int i = 0; i < insertedTweets.size(); i++) {
					String hashtagId = insertedTweets.get(i).getHashtagId();
					
					if (hashtagId.equals(hashtags.get(0).getId())) {
						hashtagNames[0] = hashtags.get(0).getNombre(); 
						tweetsCount[0]++;
						retweetsCount[0] += insertedTweets.get(i).getRetweets();
						favCount[0] += insertedTweets.get(i).getFavoritos();
					}
					if (hashtag2 != null) {
						if (hashtagId.equals(hashtags.get(1).getId())) {
							hashtagNames[1] = hashtags.get(1).getNombre();
							tweetsCount[1]++;
							retweetsCount[1] += insertedTweets.get(i).getRetweets();
							favCount[1] += insertedTweets.get(i).getFavoritos();
						}
					}
					if (hashtag3 != null) {
						if (hashtagId.equals(hashtags.get(2).getId())) {
							hashtagNames[2] = hashtags.get(2).getNombre();
							tweetsCount[2]++;
							retweetsCount[2] += insertedTweets.get(i).getRetweets();
							favCount[2] += insertedTweets.get(i).getFavoritos();
						}
					}
					if (hashtag4 != null) {
						if (hashtagId.equals(hashtags.get(3).getId())) {
							hashtagNames[3] = hashtags.get(3).getNombre();
							tweetsCount[3]++;
							retweetsCount[3] += insertedTweets.get(i).getRetweets();
							favCount[3] += insertedTweets.get(i).getFavoritos();
						}
					}
				}

				req.setAttribute("hashtagNames", hashtagNames);
				req.setAttribute("lastTweets", lastTweets);
				req.setAttribute("tweetsCount", tweetsCount);
				req.setAttribute("retweetsCount", retweetsCount);
				req.setAttribute("favCount", favCount);
				
				String encriptH1Id = Utilities.encriptar(hashtag1.getId());
				hashtag1.setId(encriptH1Id);
				
				if (hashtag2 != null) {
					String encriptH2Id = Utilities.encriptar(hashtag2.getId());
					hashtag2.setId(encriptH2Id);
				}
				if (hashtag3 != null) {
					String encriptH3Id = Utilities.encriptar(hashtag3.getId());
					hashtag3.setId(encriptH3Id);
				}
				if (hashtag4 != null) {
					String encriptH4Id = Utilities.encriptar(hashtag4.getId());
					hashtag4.setId(encriptH4Id);
				}
				
				req.setAttribute("hashtag1", hashtag1);
				req.setAttribute("hashtag2", hashtag2);
				req.setAttribute("hashtag3", hashtag3);
				req.setAttribute("hashtag4", hashtag4);
			}

			RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
			view.forward(req, resp);

		} else {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/add.jsp");
			view.forward(req, resp);
		}	
	}
}
