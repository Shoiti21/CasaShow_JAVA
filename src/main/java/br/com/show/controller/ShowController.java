package br.com.show.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.show.model.Carrinho;
import br.com.show.model.Casa;
import br.com.show.repository.repCasaShow;

@Controller
public class ShowController {
	@Autowired
	private repCasaShow repShow;

	@RequestMapping("/gerShow") //PÁGINA	
	public ModelAndView ger_Show() {
		List<Casa> todasCasas=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_gerShow");
		mv.addObject("list_casa", todasCasas);
		mv.addObject(new Casa());
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping("/registrar/show") //REGISTRAR
	public ModelAndView registrar_Show() {
		ModelAndView mv=new ModelAndView("page_registrarShow");
		mv.addObject(new Casa());
		
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping(value="/registrar/salvarshow", method=RequestMethod.POST) //SALVAR
	public String newShow(@Validated Casa show, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "page_registrarShow";
		}
		repShow.save(show);
		attributes.addFlashAttribute("mensagem", "Casa de Show registrado com sucesso!");
		return "redirect:/registrar/show";
	}
	@RequestMapping("/editarShow/{show_id}") //EDITAR
	public ModelAndView editar_show(@PathVariable Long show_id) {
		ModelAndView mv=new ModelAndView("page_registrarShow");
		Optional<Casa> casa=repShow.findById(show_id);
		mv.addObject(casa.get());
		return mv;
	}
	@RequestMapping(value="/editarShow/{show_id}", method=RequestMethod.POST) //REMOVER
	public ModelAndView excluir_show(@PathVariable Long show_id, RedirectAttributes attributes) {
		repShow.deleteById(show_id);
		ModelAndView mv=new ModelAndView("redirect:/gerShow");
		attributes.addFlashAttribute("mensagem", "Casa de Show excluido com sucesso!");
		return mv;
	}
}
