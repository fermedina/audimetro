package es.upm.dit.isst.quientv.dao;

import java.util.List;

import es.upm.dit.isst.quientv.model.Busqueda;

public interface BusquedaDAO {
	
public Busqueda newBusqueda(String nombre, String nombreCliente, String cif);
	
	public Busqueda getBusqueda(String id);
	
	public List<Busqueda> getBusquedaList();
	
	public Busqueda updateBusqueda(Busqueda busqueda);
	
	public void deleteBusqueda(String id);
}
