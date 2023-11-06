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

import web.dao.ItemDao;
import web.model.Item;

@Transactional
@Controller
@RequestMapping(value = "/item")
public class ItemController {
	
	private List<Item> lista_itens;
	
	@Autowired
	ItemDao dao;
	
	
	@RequestMapping("/novo")
	public String novo() {
		return "item/novo";
	}
	
	@RequestMapping(value = "/adiciona", method = RequestMethod.POST)
	public String adiciona(@Valid Item item, BindingResult result) {
	
	    if (result.hasErrors()) {
	        return "redirect:novo";
	    }else if (dao.buscaPorNome(item.getNome()) != null) {
	        return "redirect:novo";
	    }

	    dao.adiciona(item);

	    return "redirect:lista";
	}
	
	@RequestMapping("/lista")
	public String lista(Model model) {
		this.lista_itens = dao.listar();
		model.addAttribute("itens", this.lista_itens);
		return "item/lista";
	}
	
	@RequestMapping("/exibe")
	public String exibe(int id, Model model) {
		model.addAttribute("item", dao.buscaPorId(id));
		return "item/exibe";
	}

	@RequestMapping("/remove")
	public String remove(Item item) {
		dao.remove(item.getId());
		return "redirect:lista";
	}
	
	@RequestMapping("/edita")
	public String edita(int id, Model model) {
		model.addAttribute("item", dao.buscaPorId(id));
		return "item/edita";
	}

	@RequestMapping(value = "/altera", method = RequestMethod.POST)
	public String altera(@Valid Item item, BindingResult result) {
		
		
		if (result.hasErrors() || dao.buscaPorNome(item.getNome()) != null  && dao.buscaPorNome(item.getNome()).getId() != item.getId()) {
			return "redirect:edita?id=" + item.getId();
		}

		dao.alterar(item);
		return "redirect:lista";
	}

}
