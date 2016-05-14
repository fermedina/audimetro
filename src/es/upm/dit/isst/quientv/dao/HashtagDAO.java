package es.upm.dit.isst.quientv.dao;

import java.util.List;

import es.upm.dit.isst.quientv.model.Hashtag;

public interface HashtagDAO {
	
public Hashtag newHashtag(String nombre);
	
	public Hashtag getHashtag(String id);
	
	public List<Hashtag> getHashtagList();
	
	public Hashtag updateHashtag(Hashtag hashtag);
	
	public void deleteHashtag(String id);

}
