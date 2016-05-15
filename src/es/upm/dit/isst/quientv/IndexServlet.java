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

import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.dao.TweetDAO;
import es.upm.dit.isst.quientv.dao.TweetDAOImpl;
import es.upm.dit.isst.quientv.model.Hashtag;
import es.upm.dit.isst.quientv.model.Tweet;


@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");

		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		TweetDAO tweetDao = TweetDAOImpl.getInstance();

		List<Hashtag> hashtags = hashtagDao.getHashtagList();
		List<Tweet> insertedTweets = tweetDao.getTweetList();


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

			req.setAttribute("hashtag1", hashtag1);
			req.setAttribute("hashtag2", hashtag2);
			req.setAttribute("hashtag3", hashtag3);
			req.setAttribute("hashtag4", hashtag4);

			// Si hay tweets insertados en la base de datos: extraigo 8 usuarios y sus tweets para la tabla "Usuarios totales". Esto es para test?
			if (insertedTweets.size() > 0) {

				ArrayList<Tweet> lastTweets = new ArrayList<Tweet>();
				for(int i = 0; i < 7; i++) {
					int pos = (insertedTweets.size() - 1) -i;
					lastTweets.add(insertedTweets.get(pos));
				}

				int [] tweetsCount = {0, 0, 0, 0};
				int [] retweetsCount = {0, 0, 0, 0};
				int [] favCount = {0, 0, 0, 0};

				for(int i = 0; i < insertedTweets.size(); i++) {
					String hashtagId = insertedTweets.get(i).getHashtagId();

					if (hashtagId.equals(hashtags.get(0).getId())) {
						tweetsCount[0]++;
						retweetsCount[0] += insertedTweets.get(i).getRetweets();
						favCount[0] += insertedTweets.get(i).getFavoritos();
					}
					if (hashtag2 != null) {
						if (hashtagId.equals(hashtags.get(1).getId())) {
							tweetsCount[1]++;
							retweetsCount[1] += insertedTweets.get(i).getRetweets();
							favCount[1] += insertedTweets.get(i).getFavoritos();
						}
					}
					if (hashtag3 != null) {
						if (hashtagId.equals(hashtags.get(2).getId())) {
							tweetsCount[2]++;
							retweetsCount[2] += insertedTweets.get(i).getRetweets();
							favCount[2] += insertedTweets.get(i).getFavoritos();
						}
					}
					if (hashtag4 != null) {
						if (hashtagId.equals(hashtags.get(3).getId())) {
							tweetsCount[3]++;
							retweetsCount[3] += insertedTweets.get(i).getRetweets();
							favCount[3] += insertedTweets.get(i).getFavoritos();
						}
					}
				}

				req.setAttribute("lastTweets", lastTweets);
				req.setAttribute("tweetsCount", tweetsCount);
				req.setAttribute("retweetsCount", retweetsCount);
				req.setAttribute("favCount", favCount);				
			}

			RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
			view.forward(req, resp);

		} else {
			RequestDispatcher view = req.getRequestDispatcher("/jsp/add.jsp");
			view.forward(req, resp);
		}	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String hashtag1 = req.getParameter("hashtag1");
		String hashtag2 = req.getParameter("hashtag2");
		String hashtag3 = req.getParameter("hashtag3");
		String hashtag4 = req.getParameter("hashtag4");

		String horaFin = req.getParameter("hora_fin");

		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		TweetDAO tweetDao = TweetDAOImpl.getInstance();

		TimeZone.setDefault(TimeZone.getTimeZone("GMT+2:00"));

		if (hashtag1 != null) {

			// Obtenemos los hashtags almacenados y los borramos antes de guardar los nuevos
			for(Hashtag hashtag: hashtagDao.getHashtagList()) {
				hashtagDao.deleteHashtag(hashtag.getId());
			};

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date dateFin; // Momento de fin de la búsqueda

			try {
				dateFin = df.parse(horaFin);
				long intervalSize = 1800000; //30 minutos en milisegundos
				long maxTime = dateFin.getTime(); //Hora fin en milisegundos

				//Date dateInicio = h1.getCreatedAt(); // Momento de inicio de la búsqueda
				Date dateInicio = new Date();
				dateInicio.setSeconds(dateInicio.getSeconds() - 30);
				long minTime = dateInicio.getTime(); //Hora inicio en milisegundos

				// Calculamos la cantidad de intervalos de 30 minutos entre la fecha de inicio y la de fin
				int intervalQuantities = Math.abs((int)((maxTime - minTime) / intervalSize)); 
				// Guardamos los nuevos hashtags

				Hashtag h1 = hashtagDao.newHashtag(hashtag1, dateInicio, dateFin);
				Hashtag h2 = null;
				Hashtag h3 = null;
				Hashtag h4 = null;

				if (!hashtag2.isEmpty()) { h2 = hashtagDao.newHashtag(hashtag2, dateInicio, dateFin); }
				if (!hashtag3.isEmpty()) { h3 = hashtagDao.newHashtag(hashtag3, dateInicio, dateFin); }
				if (!hashtag4.isEmpty()) { h4 = hashtagDao.newHashtag(hashtag4, dateInicio, dateFin); }

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			RequestDispatcher rd= req.getRequestDispatcher("/search");
			rd.include(req, resp);

			/*RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
			view.forward(req, resp);*/
		}
	}
}
