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

import org.mortbay.log.Log;

import es.upm.dit.isst.quientv.dao.DataDAO;
import es.upm.dit.isst.quientv.dao.DataDAOImpl;
import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.dao.IdiomaDAO;
import es.upm.dit.isst.quientv.dao.IdiomaDAOImpl;
import es.upm.dit.isst.quientv.dao.LocalizacionDAO;
import es.upm.dit.isst.quientv.dao.LocalizacionDAOImpl;
import es.upm.dit.isst.quientv.dao.UsuarioDAO;
import es.upm.dit.isst.quientv.dao.UsuarioDAOImpl;
import es.upm.dit.isst.quientv.model.Data;
import es.upm.dit.isst.quientv.model.Hashtag;
import es.upm.dit.isst.quientv.model.Idioma;
import es.upm.dit.isst.quientv.model.Localizacion;
import es.upm.dit.isst.quientv.model.Usuario;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		List<Hashtag> hashtags = hashtagDao.getHashtagList();
		
		if (hashtags.size() > 0) {
			req.setAttribute("hashtag1", hashtags.get(0));
			req.setAttribute("hashtag2", hashtags.get(1));
			req.setAttribute("hashtag3", hashtags.get(2));
			req.setAttribute("hashtag4", hashtags.get(3));
			
			DataDAO dataDao = DataDAOImpl.getInstance();
			List<Data> dataH1 = dataDao.getDataListByHashtagId(hashtags.get(0).getId());
			List<Data> dataH2 = dataDao.getDataListByHashtagId(hashtags.get(1).getId());
			List<Data> dataH3 = dataDao.getDataListByHashtagId(hashtags.get(2).getId());
			List<Data> dataH4 = dataDao.getDataListByHashtagId(hashtags.get(3).getId());
			
			int tweetsH1 = 0;
			int tweetsH2 = 0;
			int tweetsH3 = 0;
			int tweetsH4 = 0;
			
			int retweetsH1 = 0;
			int retweetsH2 = 0;
			int retweetsH3 = 0;
			int retweetsH4 = 0;
			
			int favoritosH1 = 0;
			int favoritosH2 = 0;
			int favoritosH3 = 0;
			int favoritosH4 = 0;
			
			for(int i = 0; i < dataH1.size(); i++) {
				tweetsH1 += dataH1.get(i).getTweets();
				tweetsH2 += dataH2.get(i).getTweets();
				tweetsH3 += dataH3.get(i).getTweets();
				tweetsH4 += dataH4.get(i).getTweets();
				
				retweetsH1 += dataH1.get(i).getRetweets();
				retweetsH2 += dataH2.get(i).getRetweets();
				retweetsH3 += dataH3.get(i).getRetweets();
				retweetsH4 += dataH4.get(i).getRetweets();
				
				favoritosH1 += dataH1.get(i).getFavoritos();
				favoritosH2 += dataH2.get(i).getFavoritos();
				favoritosH3 += dataH3.get(i).getFavoritos();
				favoritosH4 += dataH4.get(i).getFavoritos();
			};
			
			UsuarioDAO usuarioDao = UsuarioDAOImpl.getInstance();
			List<Usuario> todosUsuarios = usuarioDao.getUsuarioList();
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			for(int i = 0; i < 8; i++) {
				usuarios.add(todosUsuarios.get(i));
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			String dateView = sdf.format(new Date());
			
			req.setAttribute("intervalos", dataH1.size() - 1);
			
			req.setAttribute("datosH1", dataH1);
			req.setAttribute("datosH2", dataH2);
			req.setAttribute("datosH3", dataH3);
			req.setAttribute("datosH4", dataH4);
			req.setAttribute("tweetsH1", tweetsH1);
			req.setAttribute("tweetsH2", tweetsH2);
			req.setAttribute("tweetsH3", tweetsH3);
			req.setAttribute("tweetsH4", tweetsH4);
			req.setAttribute("retweetsH1", retweetsH1);
			req.setAttribute("retweetsH2", retweetsH2);
			req.setAttribute("retweetsH3", retweetsH3);
			req.setAttribute("retweetsH4", retweetsH4);
			req.setAttribute("favoritosH1", favoritosH1);
			req.setAttribute("favoritosH2", favoritosH2);
			req.setAttribute("favoritosH3", favoritosH3);
			req.setAttribute("favoritosH4", favoritosH4);
			req.setAttribute("usuarios", usuarios);
			req.setAttribute("fecha", dateView);
					
			RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
			view.forward(req, resp);
		} else {
			req.setAttribute("hashtags", hashtags);
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
		
		if (hashtag1 != null && hashtag2 != null && hashtag3 != null && hashtag4 != null) {
			HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
			for(Hashtag hashtag: hashtagDao.getHashtagList()) {
				hashtagDao.deleteHashtag(hashtag.getId());
			};
						
			Hashtag h1 = hashtagDao.newHashtag(hashtag1);
			Hashtag h2 = hashtagDao.newHashtag(hashtag2);
			Hashtag h3 = hashtagDao.newHashtag(hashtag3);
			Hashtag h4 = hashtagDao.newHashtag(hashtag4);
			
			TimeZone.setDefault(TimeZone.getTimeZone("GMT+2:00"));
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			Date dateFin;
			Date d = new Date();
			try {			
				dateFin = df.parse(horaFin);
				d.setHours(dateFin.getHours());
				d.setMinutes(dateFin.getMinutes());
				long intervalSize = 1800000; //30 minutos en milisegundos
				long maxTime = d.getTime(); //Hora fin en milisegundos
				
				Date dateInicio = h1.getCreatedAt();
				long minTime = h1.getCreatedAt().getTime(); //Hora inicio en milisegundos
				
				int intervalQuantities = (int)((maxTime - minTime) / intervalSize);
				
				ArrayList<Data> datosH1 = new ArrayList<Data>();
				ArrayList<Data> datosH2 = new ArrayList<Data>();
				ArrayList<Data> datosH3 = new ArrayList<Data>();
				ArrayList<Data> datosH4 = new ArrayList<Data>();
				
				int tweetsH1 = 0;
				int tweetsH2 = 0;
				int tweetsH3 = 0;
				int tweetsH4 = 0;
				
				int retweetsH1 = 0;
				int retweetsH2 = 0;
				int retweetsH3 = 0;
				int retweetsH4 = 0;
				
				int favoritosH1 = 0;
				int favoritosH2 = 0;
				int favoritosH3 = 0;
				int favoritosH4 = 0;
				
				DataDAO dataDao = DataDAOImpl.getInstance();
				for(Data data: dataDao.getDataList()) {
					dataDao.deleteData(data.getId());
				};
				
				for(int i = 0; i <= intervalQuantities; i++) {
					long f = minTime + 1800000 * i;
					Date intervalo = new Date(f);
					Data d1 = dataDao.newData(h1.getId(), intervalo, (int)(Math.random()*(3000-500) + 500), (int)(Math.random()*(300-1) + 1), (int)(Math.random()*(100-1) + 1));
					Data d2 = dataDao.newData(h2.getId(), intervalo, (int)(Math.random()*(3000-500) + 500), (int)(Math.random()*(300-1) + 1), (int)(Math.random()*(100-1) + 1));
					Data d3 = dataDao.newData(h3.getId(), intervalo, (int)(Math.random()*(3000-500) + 500), (int)(Math.random()*(300-1) + 1), (int)(Math.random()*(100-1) + 1));
					Data d4 = dataDao.newData(h4.getId(), intervalo, (int)(Math.random()*(3000-500) + 500), (int)(Math.random()*(300-1) + 1), (int)(Math.random()*(100-1) + 1));
					datosH1.add(d1);
					datosH2.add(d2);
					datosH3.add(d3);
					datosH4.add(d4);
					
					tweetsH1 += d1.getTweets();
					tweetsH2 += d2.getTweets();
					tweetsH3 += d3.getTweets();
					tweetsH4 += d4.getTweets();
					
					retweetsH1 += d1.getRetweets();
					retweetsH2 += d2.getRetweets();
					retweetsH3 += d3.getRetweets();
					retweetsH4 += d4.getRetweets();
					
					favoritosH1 += d1.getFavoritos();
					favoritosH2 += d2.getFavoritos();
					favoritosH3 += d3.getFavoritos();
					favoritosH4 += d4.getFavoritos();
				};
				
				//Localizaciones
				LocalizacionDAO localizacionDao = LocalizacionDAOImpl.getInstance();
				for(Localizacion localizacion: localizacionDao.getLocalizacionList()) {
					localizacionDao.deleteLocalizacion(localizacion.getId());
				};
				
				String[] ciudades = {"Toledo", "Madrid", "Barcelona", "Sevilla"};
				
				for(int i = 0; i <= intervalQuantities; i++) {
					long f = minTime + 1800000 * i;
					Date intervalo = new Date(f);
					Localizacion l1 = localizacionDao.newLocalizacion(h1.getId(), intervalo, ciudades[(int)(Math.random()*4)], datosH1.get(i).getTweets());
					Localizacion l2 = localizacionDao.newLocalizacion(h2.getId(), intervalo, ciudades[(int)(Math.random()*4)], datosH2.get(i).getTweets());
					Localizacion l3 = localizacionDao.newLocalizacion(h3.getId(), intervalo, ciudades[(int)(Math.random()*4)], datosH3.get(i).getTweets());
					Localizacion l4 = localizacionDao.newLocalizacion(h4.getId(), intervalo, ciudades[(int)(Math.random()*4)], datosH4.get(i).getTweets());
				};
				
				//Idiomas
				IdiomaDAO idiomaDao = IdiomaDAOImpl.getInstance();
				for(Idioma idioma: idiomaDao.getIdiomaList()) {
					idiomaDao.deleteIdioma(idioma.getId());
				};
				
				String[] idiomas = {"Espanol", "Ingles", "Frances"};
				
				for(int i = 0; i <= intervalQuantities; i++) {
					long f = minTime + 1800000 * i;
					Date intervalo = new Date(f);
					Idioma i1 = idiomaDao.newIdioma(h1.getId(), intervalo, idiomas[(int)(Math.random()*3)], datosH1.get(i).getTweets());
					Idioma i2 = idiomaDao.newIdioma(h2.getId(), intervalo, idiomas[(int)(Math.random()*3)], datosH2.get(i).getTweets());
					Idioma i3 = idiomaDao.newIdioma(h3.getId(), intervalo, idiomas[(int)(Math.random()*3)], datosH3.get(i).getTweets());
					Idioma i4 = idiomaDao.newIdioma(h4.getId(), intervalo, idiomas[(int)(Math.random()*3)], datosH4.get(i).getTweets());
				};
				
				//Usuarios
				UsuarioDAO usuarioDao = UsuarioDAOImpl.getInstance();
				for(Usuario usuario: usuarioDao.getUsuarioList()) {
					usuarioDao.deleteUsuario(usuario.getId());
				};
				
				String[] users = {"@alfredo", "@fernando", "@sergio", "@andrea", "@manu", "@roberto"};
				String[] imgs = {"count1.jpeg", "count2.jpeg", "count4.jpeg", "count3.jpeg", "count5.jpeg", "count5.jpeg"};
				int[] seguidores = {503, 371, 640, 1305, 200, 437};
				String[] tweets = {"Miss mundo, Miss universo y Miss Aljarafe! Jaja",
						"Por ejemplo, este mantel",
						"Muy mal el mensaje final. 0 en educación 10 en alarmismo",
						"el azúcar esta presente en la mayoría de los alimentos, aunque este escondida con otro nombre",
						"Un cartel de Kortatu en",
						"Tu hermano chico. Qué clásico."};
				
				for(int i = 0; i <= intervalQuantities; i++) {
					long f = minTime + 1800000 * i;
					Date intervalo = new Date(f);
					int r1 = (int)(Math.random()*6);
					Usuario u1 = usuarioDao.newUsuario(h1.getId(), intervalo, users[r1], "https://twitter.com/", tweets[r1] + " #" + h1.getNombre(), imgs[r1], seguidores[r1]);
					int r2 = (int)(Math.random()*6);
					Usuario u2 = usuarioDao.newUsuario(h2.getId(), intervalo, users[r2], "https://twitter.com/", tweets[r2] + " #" + h2.getNombre(), imgs[r2], seguidores[r2]);
					int r3 = (int)(Math.random()*6);
					Usuario u3 = usuarioDao.newUsuario(h3.getId(), intervalo, users[r3], "https://twitter.com/", tweets[r3] + " #" + h3.getNombre(), imgs[r3], seguidores[r3]);
					int r4 = (int)(Math.random()*6);
					Usuario u4 = usuarioDao.newUsuario(h4.getId(), intervalo, users[r4], "https://twitter.com/", tweets[r4] + " #" + h4.getNombre(), imgs[r4], seguidores[r4]);
				};
				
				List<Usuario> todosUsuarios = usuarioDao.getUsuarioList();
				ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
				for(int i = 0; i < 8; i++) {
					usuarios.add(todosUsuarios.get(i));
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
				String dateView = sdf.format(new Date());
								
				req.setAttribute("intervalos", intervalQuantities);
				req.setAttribute("maxTime", maxTime);
				req.setAttribute("dateFin", d);
				req.setAttribute("minTime", minTime);
				req.setAttribute("dateInicio", dateInicio);
				req.setAttribute("resta", maxTime - minTime);
				req.setAttribute("datosH1", datosH1);
				req.setAttribute("datosH2", datosH2);
				req.setAttribute("datosH3", datosH3);
				req.setAttribute("datosH4", datosH4);
				req.setAttribute("tweetsH1", tweetsH1);
				req.setAttribute("tweetsH2", tweetsH2);
				req.setAttribute("tweetsH3", tweetsH3);
				req.setAttribute("tweetsH4", tweetsH4);
				req.setAttribute("retweetsH1", retweetsH1);
				req.setAttribute("retweetsH2", retweetsH2);
				req.setAttribute("retweetsH3", retweetsH3);
				req.setAttribute("retweetsH4", retweetsH4);
				req.setAttribute("favoritosH1", favoritosH1);
				req.setAttribute("favoritosH2", favoritosH2);
				req.setAttribute("favoritosH3", favoritosH3);
				req.setAttribute("favoritosH4", favoritosH4);
				req.setAttribute("usuarios", usuarios);
				req.setAttribute("fecha", dateView);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			req.setAttribute("hashtag1", h1);
			req.setAttribute("hashtag2", h2);
			req.setAttribute("hashtag3", h3);
			req.setAttribute("hashtag4", h4);
			
			RequestDispatcher view = req.getRequestDispatcher("/jsp/index.jsp");
			view.forward(req, resp);
		}
		
		resp.getWriter().println(hashtag1 + " " + hashtag2 + " " + hashtag3 + " " + hashtag4);

	}
}
