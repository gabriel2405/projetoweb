package web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import web.model.ItemSolicitacao;

@Repository
public class ItemSolicitacaoDao {
	
	@PersistenceContext
	private EntityManager manager;
	

	

	public void adiciona(ItemSolicitacao itemSolicitacao) {
		manager.persist(itemSolicitacao);
	}

	public void alterar(ItemSolicitacao itemSolicitacao) {
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
	
	public List<ItemSolicitacao> listarItensPorSolicitacaoId(int solicitacaoId) {
		TypedQuery<ItemSolicitacao> query = manager.createQuery(
			"SELECT i FROM ItemSolicitacao i WHERE i.solicitacao.id = :solicitacaoId", ItemSolicitacao.class);
		query.setParameter("solicitacaoId", solicitacaoId);
		return query.getResultList();
	}

}
