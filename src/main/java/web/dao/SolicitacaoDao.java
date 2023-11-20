package web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import web.model.Solicitacao;

@Repository
public class SolicitacaoDao {
	
	@PersistenceContext
	EntityManager manager;
	

	public void adiciona(Solicitacao solicitacao) {
		manager.persist(solicitacao);
	}

	public void alterar(Solicitacao solicitacao) {
		manager.merge(solicitacao);
	}

	public List<Solicitacao> listar() {
		return manager.createQuery("select s from Solicitacao s", Solicitacao.class).getResultList();
	}
	
	public List<Solicitacao> listarSolicitacaoCResposta() {
		return manager.createQuery("select s from Solicitacao s where s.responsavel_id is not null and s.responsavel_id <> ''",  Solicitacao.class)
		        .getResultList();
	}



	public Solicitacao buscaPorId(int id) {
		return manager.find(Solicitacao.class, id);
	}

	public void remove(int id) {
		manager.createQuery("delete from Item i where i.id = :id").setParameter("id", id).executeUpdate();
	}
	
	

}
