package web.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dao.ServidorDao;
import web.model.Servidor;


@Transactional
@Controller
@RequestMapping("/servidor")
public class ServidorController {
	
	private List<Servidor> lista_servidor; 
	
	@Autowired
	private ServidorDao dao;
	

	@RequestMapping("/novo")
	public String novo() {
		return "servidor/novo";
	}
	
	@RequestMapping(value = "/adiciona", method = RequestMethod.POST)
	public String adiciona(@Valid Servidor servidor, BindingResult result) {
		this.lista_servidor = dao.buscaPorTellEmail(servidor.getTell(), servidor.getEmail());
	    if (result.hasErrors() || this.lista_servidor.size()>0) {
	        return "redirect:novo";
	    }

	    dao.adiciona(servidor);

	    return "redirect:lista";
	}
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		this.lista_servidor = dao.listar();
		model.addAttribute("servidores", this.lista_servidor);
		return "servidor/lista";
	}
	
	@RequestMapping("/exibe")
	public String exibe(int id, Model model) {
		model.addAttribute("servidor", dao.buscaPorId(id));
		return "servidor/exibe";
	}

	@RequestMapping("/remove")
	public String remove(Servidor servidor) {
		dao.remove(servidor.getId());
		return "redirect:lista";
	}
	
	@RequestMapping("/edita")
	public String edita(int id, Model model) {
		model.addAttribute("servidor", dao.buscaPorId(id));
		return "servidor/edita";
	}

	@RequestMapping(value = "/altera", method = RequestMethod.POST)
	public String altera(@Valid Servidor servidor, BindingResult result) {
	     this.lista_servidor = dao.buscaPorTellEmail(servidor.getTell(),servidor.getEmail());
		
		if (result.hasErrors() ||this.lista_servidor.size() > 1 ) { 
			return "redirect:edita?id=" + servidor.getId();
		
		}else if(this.lista_servidor.size() != 0 && lista_servidor.get(0).getId() != servidor.getId()) {
			return "redirect:edita?id=" + servidor.getId();
		}

		dao.alterar(servidor);
		return "redirect:lista";
	}

}
