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
public class Localizacion implements Serializable {
	
	private static final long serialVersionUID = 01L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) String id;
	
	private String hashtag_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdat;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date intervalo;
	
	private String ciudad;
	
	private int tweets;
	
	public Localizacion(String hashtag_id, Date intervalo, String ciudad, int tweets) {
		this.id = UUID.randomUUID().toString();
		this.hashtag_id = hashtag_id;
		this.createdat = new Date();
		this.intervalo = intervalo;
		this.ciudad = ciudad;
		this.tweets = tweets;
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
	
	public Date getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Date intervalo) {
		this.intervalo = intervalo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getTweets() {
		return tweets;
	}

	public void setTweets(int tweets) {
		this.tweets = tweets;
	}
}
