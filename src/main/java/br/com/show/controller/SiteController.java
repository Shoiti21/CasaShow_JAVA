package br.com.show.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.show.model.Carrinho;
import br.com.show.model.Eventos;
import br.com.show.model.Registro;
import br.com.show.repository.repEventos;
import br.com.show.repository.repRegistro;
import br.com.show.repository.filter.EventoFiltro;

@Controller
public class SiteController {
	@Autowired
	private repEventos repEventos;
	@Autowired
	private repRegistro repRegistro;
	
	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView("page_home");
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping("/historico")
	public ModelAndView historico() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user=auth.getName();
		ModelAndView mv=new ModelAndView("page_historico");
		List<Registro> list_historico=repRegistro.findByUsuarioLogin(user);
		mv.addObject("list_his", list_historico);
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping("/lista")
	public ModelAndView listaShow(@ModelAttribute("filtro") EventoFiltro filtro) {
		String nome=filtro.getNome()==null || filtro.getNome().isEmpty() ? "%":filtro.getNome();
		ModelAndView mv=new ModelAndView("page_list");
		if(nome=="%") {
			List<Eventos> todosEvento=repEventos.findAll();
			mv.addObject("list_evento", todosEvento);
			
			mv.addObject("listcarrinho", Carrinho.getListcarrinho());
			mv.addObject(new Carrinho());
			return mv;
		}
		List<Eventos> todosEvento=repEventos.findByNomeContaining(nome);
		mv.addObject("list_evento", todosEvento);
		
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
}
