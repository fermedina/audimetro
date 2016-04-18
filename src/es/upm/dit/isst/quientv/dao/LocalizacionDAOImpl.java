package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Localizacion;

public class LocalizacionDAOImpl implements LocalizacionDAO {
	
	private static LocalizacionDAOImpl instance;
	
	private LocalizacionDAOImpl() {
	}
	
	public static LocalizacionDAOImpl getInstance() {
		if (instance == null) {
			instance = new LocalizacionDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Localizacion newLocalizacion(String hashtag_id, Date intervalo, String ciudad, int tweets) {
		Localizacion localizacion = null;
		EntityManager em = EMFService.get().createEntityManager();
		localizacion = new Localizacion(hashtag_id, intervalo, ciudad, tweets);
		em.persist(localizacion);
		
		em.close();
		return localizacion;
	}

	@Override
	public Localizacion getLocalizacion(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select l from Localizacion l where l.id = :id");
		q.setParameter("id", id);
		Localizacion res = null;
		List<Localizacion> localizacion = q.getResultList();
		if (localizacion.size() > 0) {
			res = (Localizacion) (q.getResultList().get(0));
		}
		
		em.close();
		return res;
	}

	@Override
	public List<Localizacion> getLocalizacionList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select l from Localizacion l order by l.createdat asc");
		List<Localizacion> res = q.getResultList();
		
		em.close();
		return res;
	}
	
	@Override
	public List<Localizacion> getLocalizacionListByHashtagId(String hashtag_id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select l from Localizacion l where l.hashtag_id = :hashtag_id order by l.createdat asc");
		q.setParameter("hashtag_id", hashtag_id);
		List<Localizacion> res = q.getResultList();
		
		em.close();
		return res;
	}

	@Override
	public Localizacion updateLocalizacion(Localizacion localizacion) {
		EntityManager em = EMFService.get().createEntityManager();
		Localizacion res = em.merge(localizacion);
		
		em.close();
		return res;
	}

	@Override
	public void deleteLocalizacion(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Localizacion todo = em.find(Localizacion.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
