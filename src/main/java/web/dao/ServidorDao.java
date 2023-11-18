package web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import web.model.Servidor;

@Repository
public class ServidorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void adiciona(Servidor servidor) {
		manager.persist(servidor);
	}
	
	public void alterar(Servidor servidor) {
		manager.merge(servidor);
	}

	public List<Servidor> listar() {
		return manager.createQuery("select s from Servidor s", Servidor.class).getResultList();
	}
	
	public List<Servidor> listarServidor() {
		return manager.createQuery("select s from Servidor s where s.tipo = 'Servidor'", Servidor.class).getResultList();
	}


	public List<Servidor> buscaPorTellEmail(String tell,String email) {
		
			return manager.createQuery("SELECT s FROM Servidor s WHERE s.tell = :tell or s.email = :email", Servidor.class).setParameter("tell", tell).setParameter("email", email).getResultList();
		
	}
	
	public Servidor buscaPorId(int id) {
		return manager.find(Servidor.class, id);
	}
	
	

	public void remove(int id) {
		manager.createQuery("delete from Servidor s where s.id = :id").setParameter("id", id).executeUpdate();
	}
}
