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

import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.dao.TweetDAO;
import es.upm.dit.isst.quientv.dao.TweetDAOImpl;
import es.upm.dit.isst.quientv.model.Hashtag;
import es.upm.dit.isst.quientv.model.Tweet;

@SuppressWarnings("serial")
public class HashtagDetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		
		// Id del hashtag recibido como parámetro en la URL
		String hashtagId = req.getParameter("id");
		
		// Instancias de los DAOs
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		TweetDAO tweetDao = TweetDAOImpl.getInstance();
		
		// Obtenemos el objeto Hashtag con el Id del hashtag recibido como parámetro
		Hashtag hashtagSelected = hashtagDao.getHashtag(hashtagId);
		req.setAttribute("hashtag", hashtagSelected);
		
		// Obtenemos la lista de los hashtags guardados
		List<Hashtag> hashtags = hashtagDao.getHashtagList();
		
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
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<Integer> followers = new ArrayList<Integer>();
		ArrayList<String> avatars = new ArrayList<String>();
		
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
			
			// Obtenemos los usuarios, sus links, sus seguidores y sus imágenes de perfil de todos los tweets para después obtener el top 5
			users.add(tweet.getUsuario());
			links.add(tweet.getLinkProfile());
			followers.add(tweet.getSeguidoresUsuario());
			avatars.add(tweet.getAvatar());
		}		
		
		req.setAttribute("languages", languages);
		req.setAttribute("languageFrecuencies", languageFrecuencies);
		
		req.setAttribute("locations", locations);
		req.setAttribute("locationFrecuencies", locationFrecuencies);
		
		req.setAttribute("retweetsCount", retweetsCount);
		req.setAttribute("favsCount", favsCount);
		
		// Obtenemos la frecuencia con la que aparecen los usuarios en la lista de tweets
		Set<String> mySet = new HashSet<String>(users);
		ArrayList<String> topUsers = new ArrayList<String>();
		ArrayList<Integer> frecuencies = new ArrayList<Integer>();
		for(String s: mySet){
			topUsers.add(s);
			frecuencies.add(Collections.frequency(users,s));			
		}
		
		// Ordenamos los arrays de los usuarios, los links, los seguidores y los avatares en función de la frecuencia con la que aparece el usuario
		// para quedarnos en la vista con los 5 más habituales
		for(int i = 0; i<frecuencies.size() - 1; i++){
            for(int j=i+1; j<frecuencies.size(); j++){
                if(frecuencies.get(i) < frecuencies.get(j)){
                    //Intercambiamos valores
                    String variableAuxiliar1 = topUsers.get(i);
                    int variableAuxiliar2 = frecuencies.get(i);
                    String variableAuxiliar3 = links.get(i);
                    int variableAuxiliar4 = followers.get(i);
                    String variableAuxiliar5 = avatars.get(i);
                                                         
                    topUsers.set(i, topUsers.get(j));
                    topUsers.set(j, variableAuxiliar1);
                    frecuencies.set(i, frecuencies.get(j));
                    frecuencies.set(j, variableAuxiliar2);
                    links.set(i, links.get(j));
                    links.set(j, variableAuxiliar3);
                    followers.set(i, followers.get(j));
                    followers.set(j, variableAuxiliar4);
                    avatars.set(i, avatars.get(j));
                    avatars.set(j, variableAuxiliar5);
                }
            }
        }
		
		req.setAttribute("topUsers", topUsers);
		req.setAttribute("topFrecuencies", frecuencies);
		req.setAttribute("topLinks", links);
		req.setAttribute("topFollowers", followers);
		req.setAttribute("topAvatars", avatars);
		
		RequestDispatcher view = req.getRequestDispatcher("/jsp/hashtagDetail.jsp");
		view.forward(req, resp);
	}
}
