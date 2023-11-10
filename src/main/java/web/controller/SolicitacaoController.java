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
import web.dao.ServidorDao;
import web.dao.SolicitacaoDao;
import web.model.Item;
import web.model.Servidor;
import web.model.Solicitacao;

@Transactional
@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoController {
	
	private List<Servidor> lista_servidor;
	private List<Solicitacao> lista_solicitacao;
	private List<Item> lista_item;

	@Autowired
	private SolicitacaoDao dao;
	
	@Autowired
	private ServidorDao servidorDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@RequestMapping("/novo")
	public String novo(Model model) {
		this.lista_servidor = servidorDao.listarServidor();
		model.addAttribute("servidores",this.lista_servidor);
		return "solicitacao/novo";
		
	}
	
	@RequestMapping("/novo_item")
	public String novoItem(Model model) {
		this.lista_item = itemDao.listar();
		model.addAttribute("itens",this.lista_item);
		return "solicitacao/novo_item";
		
	}
	
	@RequestMapping(value = "/adiciona", method = RequestMethod.POST)
	public String adiciona(@Valid Solicitacao solicitacao, BindingResult result) {
		solicitacao.setData(new Date());
		System.out.println(result);
	    if (result.hasErrors()) {
	        return "redirect:novo";
	    }
	    

	    dao.adiciona(solicitacao);

	    return "redirect:novo_item";
	}
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		this.lista_solicitacao = dao.listar();
		model.addAttribute("solicitacoes", this.lista_solicitacao);
		return "solicitacao/lista";
	}
}
