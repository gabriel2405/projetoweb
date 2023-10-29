package web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dao.ItemDao;
import web.model.Item;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
	
	@Autowired
	ItemDao dao;
	
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

}
