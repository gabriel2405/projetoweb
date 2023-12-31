package web.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dao.ItemDao;
import web.dao.ItemSolicitacaoDao;
import web.dao.ServidorDao;
import web.dao.SolicitacaoDao;
import web.model.Item;
import web.model.ItemSolicitacao;
import web.model.Servidor;
import web.model.Solicitacao;

@Transactional
@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {
    
	private List<ItemSolicitacao> lista_itemSolicitacao;
	private List<Servidor> lista_servidor;
	private List<Solicitacao> lista_solicitacao;
	private List<Item> lista_item;

	@Autowired
	private SolicitacaoDao dao;

	@Autowired
	private ServidorDao servidorDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private ItemSolicitacaoDao itemSolicitacaoDao;

	@RequestMapping("/novo")
	public String novo(Model model) {
		if (servidorDao.listarServidor().size() > 0 && itemDao.listar().size() > 0) {
			this.lista_servidor = servidorDao.listarServidor();
			model.addAttribute("servidores", this.lista_servidor);
			return "solicitacao/novo";
		}
		return "index";

	}

	@RequestMapping(value = "/adiciona_item_solicitacao", method = RequestMethod.POST)
	public String adiciona(int id_solicitacao, int[] itemId, int[] qtd) {
		if (itemId != null && itemId.length > 0) {
			Solicitacao solicitacao = dao.buscaPorId(id_solicitacao);
			for (int i = 0; i < itemId.length; i++) {
				Item item = itemDao.buscaPorId(itemId[i]);
				ItemSolicitacao itemSolicitacao = new ItemSolicitacao();
				itemSolicitacao.setItem(item);
				itemSolicitacao.setQtd(qtd[i]);
				itemSolicitacao.setSolicitacao(solicitacao);
				itemSolicitacaoDao.adiciona(itemSolicitacao);

			}
			return "redirect:lista_pendente";
		} else {
			return "redirect:lista_item?id=" + id_solicitacao;
		}

	}

	@RequestMapping(value = "/adiciona", method = RequestMethod.POST)
	public String adiciona(@Valid Solicitacao solicitacao, BindingResult result) {
		solicitacao.setData(new Date());
		if (result.hasErrors()) {
			return "redirect:novo";
		}

		dao.adiciona(solicitacao);
		return "redirect:lista_item?id=" + solicitacao.getId();
	}

	@RequestMapping("/lista_item")
	public String novoItem(int id, Model model) {
		if (dao.buscaPorId(id) != null) {
			this.lista_item = itemDao.listar();
			model.addAttribute("idSolicitacao", id);
			model.addAttribute("itens", this.lista_item);
			model.addAttribute("itensSolicitacao", itemSolicitacaoDao.listarItensPorSolicitacaoId(id));
			return "solicitacao/lista_item";
		}

		return "redirect:novo";

	}

	@RequestMapping("/lista")
	public String lista(Model model) {
		this.lista_solicitacao = dao.listar();
		model.addAttribute("solicitacoes", this.lista_solicitacao);
		return "solicitacao/lista";
	}

	@RequestMapping("/lista_incompleta")
	public String listaIncompleta(Model model) {
		this.lista_solicitacao = dao.listar_incompleta();
		model.addAttribute("solicitacoes", this.lista_solicitacao);
		model.addAttribute("status", "incompleta");
		return "solicitacao/lista";
	}

	@RequestMapping("/lista_pendente")
	public String listaPendente(Integer id, Model model) {
		
		this.lista_solicitacao = dao.listar_pendentes();
		model.addAttribute("solicitacoes", this.lista_solicitacao);
		model.addAttribute("status", "pendente");
		if(id != null && servidorDao.buscaPorId(id) != null) {
			model.addAttribute("responsavel", servidorDao.buscaPorId(id));
		}
		

		return "solicitacao/lista";
	}
	
	@RequestMapping("/lista_concluida")
	public String listaConcluida(Model model) {
		this.lista_solicitacao = dao.listarConcluidas();
		model.addAttribute("solicitacoes", this.lista_solicitacao);
		model.addAttribute("status", "aceito");
		return "solicitacao/lista";
	}
	

	@RequestMapping("/edita")
	public String edita(int id, Model model) {
		if (dao.buscaPorId(id) != null) {
			this.lista_servidor = servidorDao.listarServidor();
			model.addAttribute("servidores", this.lista_servidor);
			model.addAttribute("solicitacao", dao.buscaPorId(id));
			return "solicitacao/edita";
		}
		return "redirect:lista_incompleta";
	}

	@RequestMapping(value = "/edita_item_solicitacao", method = RequestMethod.POST)
	public String editaItem(int id_solicitacao, int[] itemId, int[] qtd) {
		boolean achou = false;

		if (dao.buscaPorId(id_solicitacao) == null || itemId == null || qtd == null || itemId.length != qtd.length) {
			return "redirect:lista_item?id=" + id_solicitacao;
		}

		for (int i = 0; i < itemId.length; i++) {
			if (itemDao.buscaPorId(itemId[i]) == null) {
				achou = true;
			}
		}

		if (!achou) {
			itemSolicitacaoDao.deleteBySolicitacaoId(id_solicitacao);

			for (int i = 0; i < itemId.length; i++) {
				Item item = itemDao.buscaPorId(itemId[i]);
				Solicitacao solicitacao = dao.buscaPorId(id_solicitacao);
				solicitacao.setData(new Date());
				ItemSolicitacao itemSolicitacao = new ItemSolicitacao();
				itemSolicitacao.setItem(item);
				itemSolicitacao.setQtd(qtd[i]);
				itemSolicitacao.setSolicitacao(solicitacao);
				itemSolicitacaoDao.adiciona(itemSolicitacao);

			}

			return "redirect:lista_pendente";
		} else {
			return "redirect:lista_item?id=" + id_solicitacao;
		}
	}

	@RequestMapping("/exibe")
	public String editaItem(int id, Model model) {
		if (dao.buscaPorId(id) != null) {
			model.addAttribute("itemSolicitacao", itemSolicitacaoDao.listarItensPorSolicitacaoId(id));
			return "solicitacao/exibe";
		}
		return "redirect:lista_pendente";

	}

	@RequestMapping("/altera")
	public String altera(@Valid Solicitacao solicitacao, BindingResult result) {

		System.out.println(result);
		solicitacao.setData(new Date());
		if (result.hasErrors()) {
			return "redirect:edita?" + solicitacao.getId();
		}

		dao.alterar(solicitacao);
		return "redirect:lista_incompleta";

	}

	@RequestMapping("/remove")
	public String remove(int id) {
		
		if (dao.buscaPorId(id) != null) {
			boolean apagou = false;
			List<ItemSolicitacao> lista_itemSolicitacao = itemSolicitacaoDao.listarItensPorSolicitacaoId(id);
			if(lista_itemSolicitacao.size()>0) {
				apagou = true;
				itemSolicitacaoDao.deleteBySolicitacaoId(id);
			}
			dao.remove(id);
			
			if(apagou) {
			return	"redirect:lista_concluida";
			}
		}

		return "redirect:lista_incompleta";
	}
	
	
	@RequestMapping("/responder_solicitacao")
	public String solicitacao(Model model) {
		model.addAttribute("responsaveis",servidorDao.listarResponsavel());
		return "responsavel/solicitacao";
	}

	@RequestMapping("/aceitar_solicitacao")
	public String aceitaSolicitacao(Integer idResponsavel,Integer idSolicitacao) {
		if(idResponsavel == null && idSolicitacao == null) {
			return "redirect:responder_solicitacao";
		}
		    lista_itemSolicitacao = itemSolicitacaoDao.listarItensPorSolicitacaoId(idSolicitacao);
			
		    boolean achou = false;
		    for(int i =0;i< lista_itemSolicitacao.size();i++) {
		    	Item item = itemDao.buscaPorId(lista_itemSolicitacao.get(i).getItem().getId());
		    	if( lista_itemSolicitacao.get(i).getQtd() > item.getQtd()) {
		    		achou = true;
		    		break;
		    	}
		    }
		    
		    if(!achou) {		    	 
		    	Servidor Responsavel = servidorDao.buscaPorId(idResponsavel);
				Solicitacao solicitacao = dao.buscaPorId(idSolicitacao);
				solicitacao.setResponsavel(Responsavel);
				solicitacao.setStatus("Aceito");
				for(int i =0;i< lista_itemSolicitacao.size();i++) {
			    	Item item =itemDao.buscaPorId(lista_itemSolicitacao.get(i).getItem().getId());
			    	item.setQtd(item.getQtd() - lista_itemSolicitacao.get(i).getQtd());
			    	
			    }
		    }
			
		
		
		return "redirect:lista_pendente?id="+idResponsavel;
	}
	
	@RequestMapping("/recusar_solicitacao")
	public String recusarSolicitacao(Integer idResponsavel,Integer idSolicitacao) {
		if(idResponsavel == null && idSolicitacao == null) {
			return "redirect:responder_solicitacao";
		}
		Servidor Responsavel = servidorDao.buscaPorId(idResponsavel);
		Solicitacao solicitacao = dao.buscaPorId(idSolicitacao);
		solicitacao.setResponsavel(Responsavel);
		solicitacao.setStatus("Recusado");
		return "redirect:lista_pendente?id="+idResponsavel;
	}
	
	
}
