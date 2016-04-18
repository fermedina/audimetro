package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.quientv.model.Localizacion;

public interface LocalizacionDAO {
	
public Localizacion newLocalizacion(String hashtag_id, Date intervalo, String ciudad, int tweets);
	
	public Localizacion getLocalizacion(int id);
	
	public List<Localizacion> getLocalizacionList();
	
	public List<Localizacion> getLocalizacionListByHashtagId(String hashtag_id);
	
	public Localizacion updateLocalizacion(Localizacion localizacion);
	
	public void deleteLocalizacion(String id);

}
