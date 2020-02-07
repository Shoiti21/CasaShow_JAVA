package br.com.show.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import br.com.show.model.Casa_Show;
import br.com.show.model.Eventos;
import br.com.show.repository.repCasaShow;
import br.com.show.repository.repEventos;

@Controller
public class SiteController {
	@Autowired
	private repCasaShow repShow;
	@Autowired
	private repEventos repEventos;
	
	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView("page_home");
		return mv;
	}
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView("login");
		return mv;
	}
	@RequestMapping("/lista")
	public ModelAndView listaShow() {
		ModelAndView mv=new ModelAndView("page_listshow");
		return mv;
	}
	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv=new ModelAndView("page_carrinho");
		return mv;
	}
//-------------------------------------------------------------------------------------	
	@RequestMapping("/registrar/show")
	public ModelAndView registrar_Show() {
		//List<Casa_Show> todosShow=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_registrarShow");
		//mv.addObject("list_show", todosShow);
		mv.addObject("show",new Casa_Show());
		return mv;
	}
	@RequestMapping(value="/registrar/salvarshow", method=RequestMethod.POST)
	public ModelAndView newShow(@Validated Casa_Show show, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv=new ModelAndView("page_registrarShow");
		if(errors.hasErrors()) {
			return mv;
		}
		repShow.save(show);
		attributes.addFlashAttribute("sucesso_show", "Casa de Show registrado com sucesso!");
		return mv;
	}
//-------------------------------------------------------------------------------------
	@RequestMapping("/registrar/evento")
	public ModelAndView registrar_Evento() {
		List<Eventos> todosEvento=repEventos.findAll();
		ModelAndView mv=new ModelAndView("page_registrarEvento");
		mv.addObject("list_evento", todosEvento);
		mv.addObject("evento", new Eventos());
		return mv;
	}
	@RequestMapping(value="/registrar/salvarevento", method=RequestMethod.POST)
	public ModelAndView newEvento(@Validated Eventos evento, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv=new ModelAndView("redirect:/registrar/evento");
		if(errors.hasErrors()) {
			return mv;
		}
		repEventos.save(evento);
		attributes.addFlashAttribute("sucesso_evento", "Evento registrado com sucesso!");
		return mv;
	}
//-------------------------------------------------------------------------------------	
	@Bean
	public LocaleResolver locale() {
		return new FixedLocaleResolver(new Locale("pt","BR"));
	}
}
