package web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import web.model.ItemSolicitacao;

@Repository
public class ItemSolicitacaoDao {
	
	@PersistenceContext
	private EntityManager manager;
	

	

	public void adiciona(ItemSolicitacaoDao itemSolicitacao) {
		manager.persist(itemSolicitacao);
	}

	public void alterar(ItemSolicitacaoDao itemSolicitacao) {
		manager.merge(itemSolicitacao);
	}

	public List<ItemSolicitacao> listar() {
		return manager.createQuery("select i from ItemSolicitacao i", ItemSolicitacao.class).getResultList();
	}



	public ItemSolicitacao buscaPorId(int id) {
		return manager.find(ItemSolicitacao.class, id);
	}

	public void remove(int id) {
		manager.createQuery("delete from ItemSolicitacao i where i.id = :id").setParameter("id", id).executeUpdate();
	}

}
