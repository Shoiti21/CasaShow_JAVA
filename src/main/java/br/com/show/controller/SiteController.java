package br.com.show.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.show.model.Carrinho;
import br.com.show.model.Casa;
import br.com.show.model.Eventos;
import br.com.show.model.Genero;
import br.com.show.model.Produto;
import br.com.show.model.Registro;
import br.com.show.repository.repCasaShow;
import br.com.show.repository.repCompras;
import br.com.show.repository.repEventos;
import br.com.show.repository.filter.EventoFiltro;

@Controller
public class SiteController {
	@Autowired
	private repCasaShow repShow;
	@Autowired
	private repEventos repEventos;
	@Autowired
	private repCompras repCompras;
	
	
	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv=new ModelAndView("page_home");
		return mv;
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
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
//-------------------------------------------------------------------------------------	
	//SHOW
	
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
//------------------------------------------------------------------------------------- 
	//EVENTO
	
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
//-------------------------------------------------------------------------------------
	//CARRINHO
	
	@RequestMapping("/carrinho") //PÁGINA
	public ModelAndView carrinho() {
		ModelAndView mv=new ModelAndView("page_carrinho");
		
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping("/carrinho/{id_evento}") //ADICIONAR
	public ModelAndView compra_carrinho(@PathVariable Long id_evento) {
		Optional<Eventos> evento=repEventos.findById(id_evento);
		List<Produto> cart=new ArrayList<Produto>();
		cart=Carrinho.getListcarrinho();
		if (Carrinho.getListcarrinho().isEmpty()) {
			cart.add(new Produto (evento.get(),1));	
		}
		else {
			for (Produto produto : cart) {
				if(produto.getProduto().getId().equals(id_evento)) {
					int qtd=produto.getQtd_produto()+1;
					produto.setQtd_produto(qtd);
					Carrinho.setListcarrinho(cart);
					ModelAndView mv=new ModelAndView("redirect:/carrinho");
					return mv;	
				}
			}
			cart.add(new Produto (evento.get(),1));	
		}
		Carrinho.setListcarrinho(cart);
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		return mv;	
	}
	@RequestMapping("/remover/{id_evento}") //REMOVER
	public ModelAndView remover_carrinho(@PathVariable Long id_evento) {
		List<Produto> cart=new ArrayList<Produto>();
		cart=Carrinho.getListcarrinho();
		int index = -1;
		for (int i = 0; i < cart.size(); i++) {
			if(cart.get(i).getProduto().getId().equals(id_evento)) {
				index=i;
			}
		}
		
		cart.remove(index);
		
		Carrinho.setListcarrinho(cart);
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		return mv;	
	}
	@RequestMapping("/finalizar") //FINALIZAR COMPRA
	public ModelAndView finalizar_compra(RedirectAttributes attributes) {
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		List<Produto> cart=new ArrayList<Produto>();
		cart=Carrinho.getListcarrinho();
		
		Date data = new Date(); 

		for(Produto produtos : cart) {
			int estoque=produtos.getProduto().getQtdIngresso()-produtos.getQtd_produto();
			if(estoque>=0) {
				Registro regCompras=new Registro();
				regCompras.setData(data);
				regCompras.setEvento(produtos.getProduto());
				regCompras.setQtd(produtos.getQtd_produto());
				produtos.getProduto().setQtdIngresso(estoque);
				repCompras.save(regCompras);
				repEventos.save(produtos.getProduto());
			}
			else {
				attributes.addFlashAttribute("erromensagem", "O produto não está disponível!");
				return mv;
			}
		}
		cart.clear();
		Carrinho.setListcarrinho(cart);
		
		attributes.addFlashAttribute("mensagem", "Compra finalizada!");
		return mv;
	}
}
