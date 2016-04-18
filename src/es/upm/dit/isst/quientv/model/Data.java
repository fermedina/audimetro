package es.upm.dit.isst.quientv.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Data implements Serializable {
	
	private static final long serialVersionUID = 01L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) String id;
	
	private String hashtag_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdat;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date intervalo;
	
	private int tweets, retweets, favoritos;
	
	public Data(String hashtag_id, Date intervalo, int tweets, int retweets, int favoritos) {
		this.id = UUID.randomUUID().toString();
		this.hashtag_id = hashtag_id;
		this.createdat = new Date();
		this.intervalo = intervalo;
		this.tweets = tweets;
		this.retweets = retweets;
		this.favoritos = favoritos;
	}
	
	public String getId() {
		return id;
	}

	public String getHashtagId() {
		return hashtag_id;
	}

	public void setHashtagId(String hashtag_id) {
		this.hashtag_id = hashtag_id;
	}

	public int getRetweets() {
		return retweets;
	}

	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	public int getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(int favoritos) {
		this.favoritos = favoritos;
	}

	public int getTweets() {
		return tweets;
	}

	public void setTweets(int tweets) {
		this.tweets = tweets;
	}
	
	public Date getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Date intervalo) {
		this.intervalo = intervalo;
	}
}
