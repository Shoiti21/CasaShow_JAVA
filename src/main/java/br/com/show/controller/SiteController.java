package br.com.show.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.show.model.Casa_Show;
import br.com.show.repository.repCasaShow;

@Controller
public class SiteController {
	@Autowired
	private repCasaShow repShow;
	
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
	@RequestMapping("/registrar/show")
	public ModelAndView registrar_Show() {
		List<Casa_Show> todosShow=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_registrarShow");
		mv.addObject("list_show", todosShow);
		return mv;
	}
	@RequestMapping("/registrar/evento")
	public ModelAndView registrar_Evento() {
		ModelAndView mv=new ModelAndView("page_registrarEvento");
		return mv;
	}
}
