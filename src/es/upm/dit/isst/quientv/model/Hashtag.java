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
	
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdat;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
		
	public Hashtag(String nombre, Date fechaInicio, Date fechaFin) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.createdat = new Date();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public String getId() {
		return id;
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
}
