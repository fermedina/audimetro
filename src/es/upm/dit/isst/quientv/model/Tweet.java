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
public class Tweet implements Serializable {
	
	private static final long serialVersionUID = 01L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) String id;
	
	private String hashtagId, texto, idioma, localizacion, usuario,
	linkProfile, avatar;
	private int seguidoresUsuario, retweets, favoritos;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
		
	public Tweet(String hashtagId, String texto, String idioma, String localizacion,
			String usuario, String linkProfile, String avatar, int seguidoresUsuario,
			int retweets, int favoritos) {
		
		this.id = UUID.randomUUID().toString();
		this.hashtagId = hashtagId;
		this.texto = texto;
		this.idioma = idioma;
		this.localizacion = localizacion;
		this.usuario = usuario;
		this.linkProfile = linkProfile;
		this.avatar = avatar;
		this.seguidoresUsuario = seguidoresUsuario;
		this.retweets = retweets;
		this.favoritos = favoritos;
		this.createdAt = new Date();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the hashtagId
	 */
	public String getHashtagId() {
		return hashtagId;
	}

	/**
	 * @param hashtagId the hashtagId to set
	 */
	public void setHashtagId(String hashtagId) {
		this.hashtagId = hashtagId;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * @param localizacion the localizacion to set
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the linkProfile
	 */
	public String getLinkProfile() {
		return linkProfile;
	}

	/**
	 * @param linkProfile the linkProfile to set
	 */
	public void setLinkProfile(String linkProfile) {
		this.linkProfile = linkProfile;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the seguidoresUsuario
	 */
	public int getSeguidoresUsuario() {
		return seguidoresUsuario;
	}

	/**
	 * @param seguidoresUsuario the seguidoresUsuario to set
	 */
	public void setSeguidoresUsuario(int seguidoresUsuario) {
		this.seguidoresUsuario = seguidoresUsuario;
	}

	/**
	 * @return the retweets
	 */
	public int getRetweets() {
		return retweets;
	}

	/**
	 * @param retweets the retweets to set
	 */
	public void setRetweets(int retweets) {
		this.retweets = retweets;
	}

	/**
	 * @return the favoritos
	 */
	public int getFavoritos() {
		return favoritos;
	}

	/**
	 * @param favoritos the favoritos to set
	 */
	public void setFavoritos(int favoritos) {
		this.favoritos = favoritos;
	}
	
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
}
