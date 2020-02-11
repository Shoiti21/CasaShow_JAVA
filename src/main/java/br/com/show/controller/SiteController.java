package br.com.show.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import br.com.show.model.Casa;
import br.com.show.model.Eventos;
import br.com.show.model.Genero;
import br.com.show.repository.repCasaShow;
import br.com.show.repository.repEventos;
import br.com.show.repository.filter.EventoFiltro;

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
	public ModelAndView listaShow(@ModelAttribute("filtro") EventoFiltro filtro) {
		String nome=filtro.getNome()==null || filtro.getNome().isEmpty() ? "%":filtro.getNome();
		ModelAndView mv=new ModelAndView("page_list");
		if(nome=="%") {
			List<Eventos> todosEvento=repEventos.findAll();
			mv.addObject("list_evento", todosEvento);
			return mv;
		}
		List<Eventos> todosEvento=repEventos.findByNomeContaining(nome);
		mv.addObject("list_evento", todosEvento);
		return mv;
	}
//-------------------------------------------------------------------------------------	
	@RequestMapping("/gerShow")
	public ModelAndView ger_Show() {
		List<Casa> todasCasas=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_gerShow");
		mv.addObject("list_casa", todasCasas);
		//mv.addObject(new Casa());
		return mv;
	}
	@RequestMapping("/registrar/show")
	public ModelAndView registrar_Show() {
		ModelAndView mv=new ModelAndView("page_registrarShow");
		mv.addObject(new Casa());
		return mv;
	}
	@RequestMapping(value="/registrar/salvarshow", method=RequestMethod.POST)
	public String newShow(@Validated Casa show, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "page_registrarShow";
		}
		repShow.save(show);
		attributes.addFlashAttribute("sucesso_show", "Casa de Show registrado com sucesso!");
		return "redirect:/registrar/show";
	}
	@RequestMapping("/editarShow/{show_id}")
	public ModelAndView editar_show(@PathVariable Long show_id) {
		ModelAndView mv=new ModelAndView("page_registrarShow");
		Optional<Casa> casa=repShow.findById(show_id);
		mv.addObject(casa.get());
		return mv;
	}
//-------------------------------------------------------------------------------------
	@RequestMapping("/gerEvento")
	public ModelAndView ger_Evento() {
		List<Eventos> todosEvento=repEventos.findAll();
		ModelAndView mv=new ModelAndView("page_gerEvento");
		mv.addObject("list_evento", todosEvento);
		//mv.addObject(new Eventos());
		return mv;
	}
	@RequestMapping("/registrar/evento")
	public ModelAndView registrar_Evento() {
		List<Casa> todasCasas=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_registrarEvento");
		mv.addObject("genero", Genero.values());
		mv.addObject("list_casa", todasCasas);
		mv.addObject(new Eventos());
		return mv;
	}
	@RequestMapping(value="/registrar/salvarevento", method=RequestMethod.POST)
	public String newEvento(@Validated Eventos evento, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "page_registrarEvento";
		}
		evento.setQtdIngresso(evento.getQtdIngressoMax());
		repEventos.save(evento);
		attributes.addFlashAttribute("sucesso_evento", "Evento registrado com sucesso!");
		return "redirect:/registrar/evento";
	}
	@RequestMapping("/editarEvento/{evento_id}")
	public ModelAndView editar_Evento(@PathVariable Long evento_id) {
		List<Casa> todasCasas=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_registrarEvento");
		Optional<Eventos> eventos=repEventos.findById(evento_id);
		mv.addObject("genero", Genero.values());
		mv.addObject("list_casa", todasCasas);
		mv.addObject(eventos.get());
		return mv;
	}
	@RequestMapping(value="/editarEvento/{evento_id}", method=RequestMethod.DELETE)
	public ModelAndView excluir(@PathVariable Long evento_id) {
		repEventos.deleteById(evento_id);
		ModelAndView mv=new ModelAndView("redirect:/registrar/show");
		return mv;
	}
//-------------------------------------------------------------------------------------
	@RequestMapping("/carrinho")
	public ModelAndView carrinho() {
		ModelAndView mv=new ModelAndView("page_carrinho");
		return mv;
	}
	@RequestMapping("/comprar/{id_evento}")
	public ModelAndView addcarrinho() {
		ModelAndView mv=new ModelAndView("page_carrinho");
		return mv;
	}
}
