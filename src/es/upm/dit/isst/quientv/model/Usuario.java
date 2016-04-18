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
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 01L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) String id;
	
	private String hashtag_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdat;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date intervalo;
	
	private String nombre, link, tweet, img;
	
	private int seguidores;
	
	public Usuario(String hashtag_id, Date intervalo, String nombre, String link, String tweet, String img, int seguidores) {
		this.id = UUID.randomUUID().toString();
		this.hashtag_id = hashtag_id;
		this.createdat = new Date();
		this.intervalo = intervalo;
		this.nombre = nombre;
		this.link = link;
		this.tweet = tweet;
		this.img = img;
		this.seguidores = seguidores;
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

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(int seguidores) {
		this.seguidores = seguidores;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
