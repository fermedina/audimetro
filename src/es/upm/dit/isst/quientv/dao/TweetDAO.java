package es.upm.dit.isst.quientv.dao;

import java.util.List;

import es.upm.dit.isst.quientv.model.Tweet;

public interface TweetDAO {
	
	public Tweet newTweet(String hashtagId, String texto, String idioma, String localizacion,
		String usuario, String linkProfile, String avatar, int seguidoresUsuario,
		int retweets, int favoritos);
	
	public Tweet getTweet(String id);
	
	public List<Tweet> getTweetList();
	
	public List<Tweet> getTweetListByHashtagId(String hashtagId);
	
	public Tweet updateTweet(Tweet tweet);
	
	public void deleteTweet(String id);

}
