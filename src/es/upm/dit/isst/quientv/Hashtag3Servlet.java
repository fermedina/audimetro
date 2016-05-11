package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.model.Hashtag;

@SuppressWarnings("serial")
public class Hashtag3Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		
		/*HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		Hashtag hashtag = hashtagDao.getHashtagList().get(2);
		req.setAttribute("hashtag", hashtag);
		
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
		
		RequestDispatcher view = req.getRequestDispatcher("/jsp/hashtag3.jsp");
		view.forward(req, resp);*/
	}
}
