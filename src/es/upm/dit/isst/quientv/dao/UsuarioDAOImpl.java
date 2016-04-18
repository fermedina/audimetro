package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static UsuarioDAOImpl instance;
	
	private UsuarioDAOImpl() {
	}
	
	public static UsuarioDAOImpl getInstance() {
		if (instance == null) {
			instance = new UsuarioDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Usuario newUsuario(String hashtag_id, Date intervalo, String nombre, String link, String tweet, String img, int seguidores) {
		Usuario usuario = null;
		EntityManager em = EMFService.get().createEntityManager();
		usuario = new Usuario(hashtag_id, intervalo, nombre, link, tweet, img, seguidores);
		em.persist(usuario);
		
		em.close();
		return usuario;
	}

	@Override
	public Usuario getUsuario(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from Usuario u where u.id = :id");
		q.setParameter("id", id);
		Usuario res = null;
		List<Usuario> usuario = q.getResultList();
		if (usuario.size() > 0) {
			res = (Usuario) (q.getResultList().get(0));
		}
		
		em.close();
		return res;
	}

	@Override
	public List<Usuario> getUsuarioList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from Usuario u order by u.createdat asc");
		List<Usuario> res = q.getResultList();
		
		em.close();
		return res;
	}
	
	@Override
	public List<Usuario> getUsuarioListByHashtagId(String hashtag_id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select u from Usuario u where u.hashtag_id = :hashtag_id order by u.createdat asc");
		q.setParameter("hashtag_id", hashtag_id);
		List<Usuario> res = q.getResultList();
		
		em.close();
		return res;
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		EntityManager em = EMFService.get().createEntityManager();
		Usuario res = em.merge(usuario);
		
		em.close();
		return res;
	}

	@Override
	public void deleteUsuario(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Usuario todo = em.find(Usuario.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
