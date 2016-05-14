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
		ArrayList<String> languages = new ArrayList<String>();
		int retweetsCount = 0;
		int favsCount = 0;
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<Integer> followers = new ArrayList<Integer>();
		ArrayList<String> avatars = new ArrayList<String>();
		for(Tweet tweet : tweetsOfHashtag) {
			retweetsCount += tweet.getRetweets();
			favsCount += tweet.getFavoritos();
			
			Locale locale = new Locale(tweet.getIdioma());
			String displayLanguage = locale.getDisplayLanguage();
			String languageCapitalized = Character.toUpperCase(displayLanguage.charAt(0)) + displayLanguage.substring(1);
			
			if (!(languageCapitalized.equals("Indeterminada")) && !languages.contains(languageCapitalized)) {			
				languages.add(languageCapitalized);
			}
			
			users.add(tweet.getUsuario());
			links.add(tweet.getLinkProfile());
			followers.add(tweet.getSeguidoresUsuario());
			avatars.add(tweet.getAvatar());
		}
		
		String[] colors = {"#F7464A", "#01DF01", "#2251EB", "#EDF904", "#F904F5"};
		String[] highlightColors = {"#E64043", "#00B200", "#2251EB", "#F4FB6D", "#FA73F8"};
		
		req.setAttribute("languagesLength", languages.size());
		req.setAttribute("languages", languages);
		req.setAttribute("colors", colors);
		req.setAttribute("highlightColors", highlightColors);
		
		req.setAttribute("retweetsCount", retweetsCount);
		req.setAttribute("favsCount", favsCount);
				
		Set<String> mySet = new HashSet<String>(users);
		ArrayList<String> topUsers = new ArrayList<String>();
		ArrayList<Integer> frecuencies = new ArrayList<Integer>();
		for(String s: mySet){
			topUsers.add(s);
			frecuencies.add(Collections.frequency(users,s));			
		}
		
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		int retweets = 0;
		
		int favoritos = 0;
		
		DataDAO dataDao = DataDAOImpl.getInstance();
		List<Data> data = dataDao.getDataListByHashtagId(hashtag.getId());
		
		for(int i = 0; i < data.size(); i++) {
			retweets += data.get(i).getRetweets();		
			favoritos += data.get(i).getFavoritos();
		};
		
		LocalizacionDAO localizacionDao = LocalizacionDAOImpl.getInstance();
		List<Localizacion> localizaciones = localizacionDao.getLocalizacionListByHashtagId(hashtag.getId());
				
		String[] ciudades = {"Toledo", "Madrid", "Barcelona", "Sevilla"};
		int tweetsToledo = 0;
		int tweetsMadrid = 0;
		int tweetsBarcelona = 0;
		int tweetsSevilla = 0;
		
		for(int i = 0; i < localizaciones.size(); i++) {
			
			if (localizaciones.get(i).getCiudad().equals("Toledo")) {
				tweetsToledo += localizaciones.get(i).getTweets();
			}
			if (localizaciones.get(i).getCiudad().equals("Madrid")) {
				tweetsMadrid += localizaciones.get(i).getTweets();
			}
			if (localizaciones.get(i).getCiudad().equals("Barcelona")) {
				tweetsBarcelona += localizaciones.get(i).getTweets();
			}
			if (localizaciones.get(i).getCiudad().equals("Sevilla")) {
				tweetsSevilla += localizaciones.get(i).getTweets();
			}
		};
		
		IdiomaDAO idiomaDao = IdiomaDAOImpl.getInstance();
		List<Idioma> idiomas = idiomaDao.getIdiomaListByHashtagId(hashtag.getId());
		
		String[] langs = {"Espanol", "Ingles", "Frances"};
		int tweetsEspanol = 0;
		int tweetsIngles = 0;
		int tweetsFrances = 0;
		
		for(int i = 0; i < idiomas.size(); i++) {
			
			if (idiomas.get(i).getIdioma().equals("Espanol")) {
				tweetsEspanol += idiomas.get(i).getTweets();
			}
			if (idiomas.get(i).getIdioma().equals("Ingles")) {
				tweetsIngles += idiomas.get(i).getTweets();
			}
			if (idiomas.get(i).getIdioma().equals("Frances")) {
				tweetsFrances += idiomas.get(i).getTweets();
			}
		};
		
		UsuarioDAO usuarioDao = UsuarioDAOImpl.getInstance();
		List<Usuario> usuarios = usuarioDao.getUsuarioListByHashtagId(hashtag.getId());
		
		String[] users = {"@alfredo", "@fernando", "@sergio", "@andrea", "@manu", "@roberto"};
		//String[] langs = {"Espanol", "Ingles", "Frances"};
		int countAlfredo = 0;
		int countFernando = 0;
		int countSergio = 0;
		int countAndrea = 0;
		int countManu = 0;
		int countRoberto = 0;
		
		Usuario alfredo = null;
		Usuario fernando = null;
		Usuario sergio = null;
		Usuario andrea = null;
		Usuario manu = null;
		Usuario roberto = null;
		
		Usuario[] usuariosSinRepetir = {alfredo, fernando, sergio, andrea, manu, roberto};
		
		for(int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNombre().equals("@alfredo")) {
				usuariosSinRepetir[0] = usuarios.get(i);
				countAlfredo++;
			}
			
			if (usuarios.get(i).getNombre().equals("@fernando")) {
				usuariosSinRepetir[1] = usuarios.get(i);
				countFernando++;
			}
			
			if (usuarios.get(i).getNombre().equals("@sergio")) {
				usuariosSinRepetir[2] = usuarios.get(i);
				countSergio++;
			}
			
			if (usuarios.get(i).getNombre().equals("@andrea")) {
				usuariosSinRepetir[3] = usuarios.get(i);
				countAndrea++;
			}
			
			if (usuarios.get(i).getNombre().equals("@manu")) {
				usuariosSinRepetir[4] = usuarios.get(i);
				countManu++;
			}
			
			if (usuarios.get(i).getNombre().equals("@roberto")) {
				usuariosSinRepetir[5] = usuarios.get(i);
				countRoberto++;
			}
		};
		
		int[] contadores = {countAlfredo, countFernando, countSergio, countAndrea, countManu, countRoberto};
		
		for(int j = 0; j<(contadores.length-1); j++){
            for(int k=j+1; k<contadores.length; k++){
                if(contadores[j]<contadores[k]){
                    //Intercambiamos valores
                    int variableauxiliar1 = contadores[j];
                    Usuario variableauxiliar2 = usuariosSinRepetir[j];
                    contadores[j] = contadores[k];
                    contadores[k] = variableauxiliar1;
                    usuariosSinRepetir[j] = usuariosSinRepetir[k];
                    usuariosSinRepetir[k] = variableauxiliar2;
                }
            }
        }
						
		req.setAttribute("datos", data);
		req.setAttribute("intervalos", data.size() - 1);
		req.setAttribute("retweets", retweets);
		req.setAttribute("favoritos", favoritos);
		req.setAttribute("tweetsToledo", tweetsToledo);
		req.setAttribute("tweetsMadrid", tweetsMadrid);
		req.setAttribute("tweetsBarcelona", tweetsBarcelona);
		req.setAttribute("tweetsSevilla", tweetsSevilla);
		req.setAttribute("tweetsEspanol", tweetsEspanol);
		req.setAttribute("tweetsIngles", tweetsIngles);
		req.setAttribute("tweetsFrances", tweetsFrances);
		req.setAttribute("top1", usuariosSinRepetir[0]);
		req.setAttribute("top2", usuariosSinRepetir[1]);
		req.setAttribute("top3", usuariosSinRepetir[2]);
		req.setAttribute("top4", usuariosSinRepetir[3]);
		req.setAttribute("top5", usuariosSinRepetir[4]);
		req.setAttribute("contador1", contadores[0]);
		req.setAttribute("contador2", contadores[1]);
		req.setAttribute("contador3", contadores[2]);
		req.setAttribute("contador4", contadores[3]);
		req.setAttribute("contador5", contadores[4]);
		*/
		RequestDispatcher view = req.getRequestDispatcher("/jsp/hashtagDetail.jsp");
		view.forward(req, resp);
	}
}
