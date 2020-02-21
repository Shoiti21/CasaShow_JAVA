package br.com.show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.show.model.Carrinho;
import br.com.show.service.serviceCarrinho;

@Controller
public class CarrinhoController {
	@Autowired
	private serviceCarrinho serviceCarrinho;
	
	@RequestMapping("/carrinho") //PÁGINA
	public ModelAndView carrinho() {
		ModelAndView mv=new ModelAndView("page_carrinho");
		mv.addObject("listcarrinho", Carrinho.getListcarrinho());
		mv.addObject(new Carrinho());
		return mv;
	}
	@RequestMapping("/carrinho/{id_evento}") //ADICIONAR
	public ModelAndView compra_carrinho(@PathVariable Long id_evento) {
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		serviceCarrinho.adicionar(id_evento);
		return mv;	
	}
	@RequestMapping("/remover/{id_evento}") //REMOVER
	public ModelAndView remover_carrinho(@PathVariable Long id_evento) {
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		serviceCarrinho.remover(id_evento);
		return mv;	
	}
	@RequestMapping("/finalizar") //FINALIZAR COMPRA
	public ModelAndView finalizar_compra(RedirectAttributes attributes) {
		ModelAndView mv=new ModelAndView("redirect:/carrinho");
		if(serviceCarrinho.verificar()==true) {
			serviceCarrinho.salvar();
			attributes.addFlashAttribute("mensagem", "Compra finalizada!");
		}
		else {
			attributes.addFlashAttribute("erromensagem", "O produto não está disponível!");
		}
		return mv;
	}
}
