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
import br.com.show.model.Eventos;
import br.com.show.model.Genero;
import br.com.show.repository.repCasaShow;
import br.com.show.repository.repEventos;

@Controller
public class EventoController {
	@Autowired
	private repCasaShow repShow;
	@Autowired
	private repEventos repEventos;
	
	@RequestMapping("/gerEvento") //PÁGINA	
	public ModelAndView ger_Evento() {
		List<Eventos> todosEvento=repEventos.findAll();
		ModelAndView mv=new ModelAndView("page_gerEvento");
		mv.addObject("list_evento", todosEvento);
		mv.addObject(new Eventos());
		
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping("/registrar/evento") //REGISTRAR
	public ModelAndView registrar_Evento() {
		List<Casa> todasCasas=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_registrarEvento");
		mv.addObject("genero", Genero.values());
		mv.addObject("list_casa", todasCasas);
		mv.addObject(new Eventos());
		return mv;
	}
	@RequestMapping(value="/registrar/salvarevento", method=RequestMethod.POST) //SALVAR
	public String newEvento(@Validated Eventos evento, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "page_registrarEvento";
		}
		evento.setQtdIngresso(evento.getQtdIngressoMax());
		repEventos.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento registrado com sucesso!");
		return "redirect:/registrar/evento";
	}
	@RequestMapping("/editarEvento/{evento_id}") //EDITAR
	public ModelAndView editar_Evento(@PathVariable Long evento_id) {
		List<Casa> todasCasas=repShow.findAll();
		ModelAndView mv=new ModelAndView("page_registrarEvento");
		Optional<Eventos> eventos=repEventos.findById(evento_id);
		mv.addObject("genero", Genero.values());
		mv.addObject("list_casa", todasCasas);
		mv.addObject(eventos.get());
		
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping(value="/editarEvento/{evento_id}", method=RequestMethod.POST) //REMOVER
	public ModelAndView excluir_Evento(@PathVariable Long evento_id, RedirectAttributes attributes) {
		repEventos.deleteById(evento_id);
		ModelAndView mv=new ModelAndView("redirect:/gerEvento");
		attributes.addFlashAttribute("mensagem", "Evento excluido com sucesso!");
		return mv;
	}
}
