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
public class Hashtag implements Serializable {
	
	private static final long serialVersionUID = 01L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) String id;
	
	private String nombre, busquedaId, programa;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdat;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
		
	public Hashtag(String nombre, Date fechaInicio, Date fechaFin, String busquedaId, String programa) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.createdat = new Date();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.busquedaId = busquedaId;
		this.programa = programa;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Date getCreatedAt() {
		return createdat;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setCreatedAt(Date createdat) {
		this.createdat = createdat;
	}

	/**
	 * @return the busquedaId
	 */
	public String getBusquedaId() {
		return busquedaId;
	}

	/**
	 * @param busquedaId the busquedaId to set
	 */
	public void setBusquedaId(String busquedaId) {
		this.busquedaId = busquedaId;
	}

	/**
	 * @return the programa
	 */
	public String getPrograma() {
		return programa;
	}

	/**
	 * @param programa the programa to set
	 */
	public void setPrograma(String programa) {
		this.programa = programa;
	}
}
