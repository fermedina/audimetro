package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Idioma;
import es.upm.dit.isst.quientv.model.Localizacion;

public class IdiomaDAOImpl implements IdiomaDAO {
	
	private static IdiomaDAOImpl instance;
	
	private IdiomaDAOImpl() {
	}
	
	public static IdiomaDAOImpl getInstance() {
		if (instance == null) {
			instance = new IdiomaDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Idioma newIdioma(String hashtag_id, Date intervalo, String lang, int tweets) {
		Idioma idioma = null;
		EntityManager em = EMFService.get().createEntityManager();
		idioma = new Idioma(hashtag_id, intervalo, lang, tweets);
		em.persist(idioma);
		
		em.close();
		return idioma;
	}

	@Override
	public Idioma getIdioma(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select i from Idioma i where i.id = :id");
		q.setParameter("id", id);
		Idioma res = null;
		List<Idioma> idioma = q.getResultList();
		if (idioma.size() > 0) {
			res = (Idioma) (q.getResultList().get(0));
		}
		
		em.close();
		return res;
	}

	@Override
	public List<Idioma> getIdiomaList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select i from Idioma i order by i.createdat asc");
		List<Idioma> res = q.getResultList();
		
		em.close();
		return res;
	}
	
	@Override
	public List<Idioma> getIdiomaListByHashtagId(String hashtag_id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select i from Idioma i where i.hashtag_id = :hashtag_id order by i.createdat asc");
		q.setParameter("hashtag_id", hashtag_id);
		List<Idioma> res = q.getResultList();
		
		em.close();
		return res;
	}

	@Override
	public Idioma updateIdioma(Idioma idioma) {
		EntityManager em = EMFService.get().createEntityManager();
		Idioma res = em.merge(idioma);
		
		em.close();
		return res;
	}

	@Override
	public void deleteIdioma(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Idioma todo = em.find(Idioma.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
