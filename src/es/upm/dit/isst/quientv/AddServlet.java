package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.quientv.dao.BusquedaDAO;
import es.upm.dit.isst.quientv.dao.BusquedaDAOImpl;
import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.model.Busqueda;
import es.upm.dit.isst.quientv.model.Hashtag;

@SuppressWarnings("serial")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		
		BusquedaDAO busquedaDao = BusquedaDAOImpl.getInstance();
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		
		// Obtenemos la lista de Búsquedas realizadas para pintarlas en el menú lateral
		List<Busqueda> searchList = busquedaDao.getBusquedaList();
		req.setAttribute("searchList", searchList);
		
		String busquedaId = searchList.get(searchList.size() - 1).getId();
		
		List<Hashtag> hashtags = hashtagDao.getHashtagListByBusquedaId(busquedaId);
		
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
		
		req.setAttribute("hashtags", hashtags);
		RequestDispatcher view = req.getRequestDispatcher("/jsp/add.jsp");
		view.forward(req, resp);
	}
}
