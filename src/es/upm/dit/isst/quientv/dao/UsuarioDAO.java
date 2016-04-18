package es.upm.dit.isst.quientv.dao;

import java.util.Date;
import java.util.List;

import es.upm.dit.isst.quientv.model.Usuario;

public interface UsuarioDAO {
	
public Usuario newUsuario(String hashtag_id, Date intervalo, String nombre, String link, String tweet, String img, int seguidores);
	
	public Usuario getUsuario(int id);
	
	public List<Usuario> getUsuarioList();
	
	public List<Usuario> getUsuarioListByHashtagId(String hashtag_id);
	
	public Usuario updateUsuario(Usuario usuario);
	
	public void deleteUsuario(String id);

}
