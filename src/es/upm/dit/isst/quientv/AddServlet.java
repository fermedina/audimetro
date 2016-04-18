package es.upm.dit.isst.quientv;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.upm.dit.isst.quientv.dao.HashtagDAO;
import es.upm.dit.isst.quientv.dao.HashtagDAOImpl;
import es.upm.dit.isst.quientv.model.Hashtag;

@SuppressWarnings("serial")
public class AddServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		HashtagDAO hashtagDao = HashtagDAOImpl.getInstance();
		List<Hashtag> hashtags = hashtagDao.getHashtagList();
		
		req.setAttribute("hashtags", hashtags);
		RequestDispatcher view = req.getRequestDispatcher("/jsp/add.jsp");
		view.forward(req, resp);
	}
}
