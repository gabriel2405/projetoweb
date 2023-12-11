package web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import web.model.Item;

@Repository
public class ItemDao {

	@PersistenceContext
	private EntityManager manager;

	public void adiciona(Item item) {
		manager.persist(item);
	}

	public void alterar(Item item) {
		manager.merge(item);
	}

	public List<Item> listar() {
	    return manager.createQuery("SELECT i FROM Item i WHERE i.qtd > 0", Item.class)
	                  .getResultList();
	}

	public Item buscaPorNome(String nome) {
		try {
			return manager.createQuery("SELECT i FROM Item i WHERE i.nome = :nome", Item.class)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public Item buscaPorId(int id) {
		return manager.find(Item.class, id);
	}

	public void remove(int id) {
		manager.createQuery("delete from Item i where i.id = :id").setParameter("id", id).executeUpdate();
	}

}
