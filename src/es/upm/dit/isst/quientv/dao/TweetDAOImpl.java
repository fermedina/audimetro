package es.upm.dit.isst.quientv.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Tweet;

public class TweetDAOImpl implements TweetDAO {
	
	private static TweetDAOImpl instance;
	
	private TweetDAOImpl() {
	}
	
	public static TweetDAOImpl getInstance() {
		if (instance == null) {
			instance = new TweetDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Tweet newTweet(Tweet tweet) {
		EntityManager em = EMFService.get().createEntityManager();
		
		em.persist(tweet);
		
		em.close();
		return tweet;
	}

	@Override
	public Tweet getTweet(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Tweet res = em.find(Tweet.class, id);
		
		em.close();
		return res;
	}
	
	@Override
	public boolean isStored(Tweet tweet) {
		EntityManager em = EMFService.get().createEntityManager();
		
		boolean isStored = em.contains(tweet);
		
		em.close();
		return isStored;
	}

	@Override
	public List<Tweet> getTweetList() {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("select t from Tweet t order by t.createdAt asc");
		List<Tweet> res = q.getResultList();
		
		em.close();
		return res;
	}
	
	@Override
	public List<Tweet> getTweetListByHashtagId(String hashtagId) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Query q = em.createQuery("select t from Tweet t where t.hashtagId = :hashtagId order by t.createdAt asc");
		q.setParameter("hashtagId", hashtagId);
		List<Tweet> res = q.getResultList();
		
		em.close();
		return res;
	}

	@Override
	public Tweet updateTweet(Tweet tweet) {
		EntityManager em = EMFService.get().createEntityManager();
		
		Tweet res = em.merge(tweet);
		
		em.close();
		return res;
	}

	@Override
	public void deleteTweet(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		
		try {
			Tweet todo = em.find(Tweet.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
