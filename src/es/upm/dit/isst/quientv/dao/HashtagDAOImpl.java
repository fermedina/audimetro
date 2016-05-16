package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Hashtag;

public class HashtagDAOImpl implements HashtagDAO {
	
	private static HashtagDAOImpl instance;
	
	private HashtagDAOImpl() {
	}
	
	public static HashtagDAOImpl getInstance() {
		if (instance == null) {
			instance = new HashtagDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Hashtag newHashtag(String nombre, Date fechaInicio, Date fechaFin) {
		Hashtag hashtag = null;
		EntityManager em = EMFService.get().createEntityManager();
		hashtag = new Hashtag(nombre, fechaInicio, fechaFin);
		em.persist(hashtag);
		
		em.close();
		return hashtag;
	}

	@Override
	public Hashtag getHashtag(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select h from Hashtag h where h.id = :id");
		q.setParameter("id", id);
		Hashtag res = null;
		List<Hashtag> hashtags = q.getResultList();
		if (hashtags.size() > 0) {
			res = (Hashtag) (q.getResultList().get(0));
		}
		
		em.close();
		return res;
	}

	@Override
	public List<Hashtag> getHashtagList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select h from Hashtag h order by h.createdat asc ");
		List<Hashtag> res = q.getResultList();
		
		em.close();
		return res;
	}
	
	@Override
	public List<Hashtag> getHashtagListInSearchPeriod() {
		EntityManager em = EMFService.get().createEntityManager();
		Date now = new Date();
		Query q = null;
		q = em.createQuery("SELECT h FROM Hashtag h WHERE h.fechaFin >= :now").setParameter("now", now);

		List<Hashtag> res = q.getResultList();
		for(Hashtag hashtag: res) {
			if (hashtag.getFechaInicio().after(now)){
				res.remove(hashtag);
			}
		};
		
		em.close();
		return res;
	}

	@Override
	public Hashtag updateHashtag(Hashtag hashtag) {
		EntityManager em = EMFService.get().createEntityManager();
		Hashtag res = em.merge(hashtag);
		
		em.close();
		return res;
	}

	@Override
	public void deleteHashtag(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Hashtag todo = em.find(Hashtag.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
