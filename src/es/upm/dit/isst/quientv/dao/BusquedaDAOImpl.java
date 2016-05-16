package es.upm.dit.isst.quientv.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.isst.quientv.model.Busqueda;

public class BusquedaDAOImpl implements BusquedaDAO {
	
	private static BusquedaDAOImpl instance;
	
	private BusquedaDAOImpl() {
	}
	
	public static BusquedaDAOImpl getInstance() {
		if (instance == null) {
			instance = new BusquedaDAOImpl();
		}
		
		return instance;
	}

	@Override
	public Busqueda newBusqueda(String nombre, String nombreCliente, String cif) {
		Busqueda busqueda = null;
		EntityManager em = EMFService.get().createEntityManager();
		busqueda = new Busqueda(nombre, nombreCliente, cif);
		em.persist(busqueda);
		
		em.close();
		return busqueda;
	}

	@Override
	public Busqueda getBusqueda(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Busqueda b where b.id = :id");
		q.setParameter("id", id);
		Busqueda res = null;
		List<Busqueda> busquedas = q.getResultList();
		if (busquedas.size() > 0) {
			res = (Busqueda) (q.getResultList().get(0));
		}
		
		em.close();
		return res;
	}

	@Override
	public List<Busqueda> getBusquedaList() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Busqueda b order by b.createdAt asc");
		List<Busqueda> res = q.getResultList();
		
		em.close();
		return res;
	}

	@Override
	public Busqueda updateBusqueda(Busqueda busqueda) {
		EntityManager em = EMFService.get().createEntityManager();
		Busqueda res = em.merge(busqueda);
		
		em.close();
		return res;
	}

	@Override
	public void deleteBusqueda(String id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Busqueda todo = em.find(Busqueda.class, id);
			em.remove(todo);
		} finally {
			em.close();
		}
	}

}
