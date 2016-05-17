package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.quientv.dao.BusquedaDAO;
import es.upm.dit.isst.quientv.dao.BusquedaDAOImpl;
import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.dao.TweetDAO;
import es.upm.dit.isst.quientv.dao.TweetDAOImpl;
import es.upm.dit.isst.quientv.model.Busqueda;
import es.upm.dit.isst.quientv.model.Hashtag;
import es.upm.dit.isst.quientv.model.Tweet;

@SuppressWarnings("serial")
public class HashtagDetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		
		// Id del hashtag recibido como parámetro en la URL
		String hashtagId = req.getParameter("id");
		
		// Instancias de los DAOs
		BusquedaDAO busquedaDao = BusquedaDAOImpl.getInstance();
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		TweetDAO tweetDao = TweetDAOImpl.getInstance();
		
		// Obtenemos la lista de Búsquedas realizadas para pintarlas en el menú lateral
		List<Busqueda> searchList = busquedaDao.getBusquedaList();
		req.setAttribute("searchList", searchList);
		
		// Obtenemos el objeto Hashtag con el Id del hashtag recibido como parámetro
		Hashtag hashtagSelected = hashtagDao.getHashtag(hashtagId);
		req.setAttribute("hashtag", hashtagSelected);
		
		// Obtenemos la lista de los hashtags guardados
		List<Hashtag> hashtags = hashtagDao.getHashtagListByBusquedaId(hashtagSelected.getBusquedaId());
		
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
		}
		
		// Obtenemos la lista de los tweets puestos con el hashtag recibido como parámetro
		List<Tweet> tweetsOfHashtag = tweetDao.getTweetListByHashtagId(hashtagId);	
		req.setAttribute("tweetList", tweetsOfHashtag);
		
		// Obtenemos los idiomas de los tweets que contienen el hashtag recibido como parámetro
		// Obtenemos la cuenta de retweets y favoritos totales
		// Obtenemos las provincias de los tweets que contienen el hashtag recibido como parámetro
		ArrayList<String> languages = new ArrayList<String>();
		ArrayList<Integer> languageFrecuencies = new ArrayList<Integer>();
		
		ArrayList<String> locations =  new ArrayList<String>();
		ArrayList<Integer> locationFrecuencies = new ArrayList<Integer>();
		
		int retweetsCount = 0;
		int favsCount = 0;
		
		ArrayList<String> users = new ArrayList<String>();
		
		for(Tweet tweet : tweetsOfHashtag) {
			retweetsCount += tweet.getRetweets(); // Sumamos los retweets
			favsCount += tweet.getFavoritos(); // Sumamos los favoritos
			
			// Obtenemos el idioma a partir del idioma que devuelve Twitter en ISO 639-1
			Locale locale = new Locale(tweet.getIdioma());
			String displayLanguage = locale.getDisplayLanguage();
			String languageCapitalized = Character.toUpperCase(displayLanguage.charAt(0)) + displayLanguage.substring(1);
			
			// Si hay algún idioma indeterminado lo ignoramos
			if (!languageCapitalized.equals("Indeterminada")) {
				
				// Si languages no contiene el idioma, se añade y se comienza la cuenta de las veces que aparece
				if(!languages.contains(languageCapitalized)) {			
					languages.add(languageCapitalized);
					languageFrecuencies.add(1);
				} else {
					int index = languages.indexOf(languageCapitalized);
					int value = languageFrecuencies.get(index);
					value++;
					languageFrecuencies.set(index, value);
				}
			}
			
			// Si locations no contiene la provincia, se añade y se comienza la cuenta de las veces que aparece
			if(!locations.contains(tweet.getLocalizacion())) {			
				locations.add(tweet.getLocalizacion());
				locationFrecuencies.add(1);
			} else {
				int index = locations.indexOf(tweet.getLocalizacion());
				int value = locationFrecuencies.get(index);
				value++;
				locationFrecuencies.set(index, value);
			}
			
			// Obtenemos los usuarios para después obtener el top 5
			users.add(tweet.getUsuario());
		}		
		
		req.setAttribute("languages", languages);
		req.setAttribute("languageFrecuencies", languageFrecuencies);
		
		req.setAttribute("locations", locations);
		req.setAttribute("locationFrecuencies", locationFrecuencies);
		
		req.setAttribute("retweetsCount", retweetsCount);
		req.setAttribute("favsCount", favsCount);
		
		// Obtenemos la frecuencia con la que aparecen los usuarios en la lista de tweets
		Set<String> setUsers = new HashSet<String>(users);
		ArrayList<String> topUsers = new ArrayList<String>();
		ArrayList<String> topLinks = new ArrayList<String>();
		ArrayList<Integer> topFollowers = new ArrayList<Integer>();
		ArrayList<String> topAvatars = new ArrayList<String>();
		ArrayList<Integer> frecuencies = new ArrayList<Integer>();
		
		for(String s: setUsers){
			topUsers.add(s);
			frecuencies.add(Collections.frequency(users,s));
			
			List<Tweet> tweetsOfUser = tweetDao.getTweetListByUserAndHashtagId(hashtagId, s);
			if (tweetsOfUser.size() > 0) {
				topLinks.add(tweetsOfUser.get(0).getLinkProfile());
				topFollowers.add(tweetsOfUser.get(0).getSeguidoresUsuario());
				topAvatars.add(tweetsOfUser.get(0).getAvatar());
			}	
		}
		
		// Ordenamos los arrays de los usuarios, los links, los seguidores y los avatares en función de la frecuencia con la que aparece el usuario
		// para quedarnos en la vista con los 5 más habituales
		for(int i = 0; i<frecuencies.size()-1; i++){
            for(int j=i+1; j<frecuencies.size(); j++){
                if(frecuencies.get(i) < frecuencies.get(j)){
                    //Intercambiamos valores
                    String variableAuxiliar1 = topUsers.get(i);
                    int variableAuxiliar2 = frecuencies.get(i);
                    String variableAuxiliar3 = topLinks.get(i);
                    int variableAuxiliar4 = topFollowers.get(i);
                    String variableAuxiliar5 = topAvatars.get(i);
                                                         
                    topUsers.set(i, topUsers.get(j));
                    topUsers.set(j, variableAuxiliar1);
                    frecuencies.set(i, frecuencies.get(j));
                    frecuencies.set(j, variableAuxiliar2);
                    topLinks.set(i, topLinks.get(j));
                    topLinks.set(j, variableAuxiliar3);
                    topFollowers.set(i, topFollowers.get(j));
                    topFollowers.set(j, variableAuxiliar4);
                    topAvatars.set(i, topAvatars.get(j));
                    topAvatars.set(j, variableAuxiliar5);
                }
            }
        }
		
		req.setAttribute("topUsers", topUsers);
		req.setAttribute("topFrecuencies", frecuencies);
		req.setAttribute("topLinks", topLinks);
		req.setAttribute("topFollowers", topFollowers);
		req.setAttribute("topAvatars", topAvatars);
		
		RequestDispatcher view = req.getRequestDispatcher("/jsp/hashtagDetail.jsp");
		view.forward(req, resp);
	}
}
