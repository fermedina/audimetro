package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.quientv.model.Idioma;

public interface IdiomaDAO {
	
public Idioma newIdioma(String hashtag_id, Date intervalo, String idioma, int tweets);
	
	public Idioma getIdioma(int id);
	
	public List<Idioma> getIdiomaList();
	
	public List<Idioma> getIdiomaListByHashtagId(String hashtag_id);
	
	public Idioma updateIdioma(Idioma idioma);
	
	public void deleteIdioma(String id);

}
