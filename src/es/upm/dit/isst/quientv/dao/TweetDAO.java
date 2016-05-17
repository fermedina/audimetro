package es.upm.dit.isst.quientv.dao;

import java.util.List;

import es.upm.dit.isst.quientv.model.Tweet;

public interface TweetDAO {
	
	public Tweet newTweet(Tweet tweet);
	
	public Tweet getTweet(String id);
	
	public boolean isStored(String usuario, String texto);
	
	public List<Tweet> getTweetList();
	
	public List<Tweet> getTweetListByHashtagId(String hashtagId);
	
	public List<Tweet> getTweetListByUserAndHashtagId(String hashtagId, String usuario);
		
	public Tweet updateTweet(Tweet tweet);
	
	public void deleteTweet(String id);

}
