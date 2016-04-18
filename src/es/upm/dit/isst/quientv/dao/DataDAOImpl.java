package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Data;

public class DataDAOImpl implements DataDAO {
	
	private static DataDAOImpl instance;
	
	private DataDAOImpl() {
	}
	
	public static DataDAOImpl getInstance() {
		if (instance == null) {
			instance = new DataDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Data newData(String hashtag_id, Date intervalo, int tweets, int retweets, int favoritos) {
		Data data = null;
		EntityManager em = EMFService.get().createEntityManager();
		data = new Data(hashtag_id, intervalo, tweets, retweets, favoritos);
		em.persist(data);
		
		em.close();
		return data;
	}

	@Override
	public Data getData(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select d from Data d where d.id = :id");
		q.setParameter("id", id);
		Data res = null;
		List<Data> data = q.getResultList();
		if (data.size() > 0) {
			res = (Data) (q.getResultList().get(0));
		}
		
		em.close();
		return res;
	}

	@Override
	public List<Data> getDataList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select d from Data d order by d.createdat asc");
		List<Data> res = q.getResultList();
		
		em.close();
		return res;
	}
	
	@Override
	public List<Data> getDataListByHashtagId(String hashtag_id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select d from Data d where d.hashtag_id = :hashtag_id order by d.createdat asc");
		q.setParameter("hashtag_id", hashtag_id);
		List<Data> res = q.getResultList();
		
		em.close();
		return res;
	}

	@Override
	public Data updateData(Data data) {
		EntityManager em = EMFService.get().createEntityManager();
		Data res = em.merge(data);
		
		em.close();
		return res;
	}

	@Override
	public void deleteData(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Data todo = em.find(Data.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
