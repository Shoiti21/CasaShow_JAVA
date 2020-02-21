package br.com.show.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.show.model.Carrinho;
import br.com.show.model.Produto;
import br.com.show.model.Role;
import br.com.show.model.Usuario;
import br.com.show.repository.repRole;
import br.com.show.repository.repUsuario;

@Controller
public class LoginController {
	@Autowired
	private repUsuario repUsuario;
	@Autowired
	private repRole repRole;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mv=new ModelAndView("login");
		mv.addObject(new Usuario());
		return mv;
	}
	@RequestMapping("/sair")
	public ModelAndView logout() {
		ModelAndView mv=new ModelAndView("redirect:/logout");
		List<Produto> cart=new ArrayList<Produto>();
		cart.clear();
		Carrinho.setListcarrinho(cart);
		return mv;
	}
	@RequestMapping("/registrar")
	public ModelAndView registrar() {
		ModelAndView mv=new ModelAndView("login_registrar");
		mv.addObject(new Usuario());
		return mv;
	}
	@RequestMapping(value="/registrar", method=RequestMethod.POST)
	public String salvar_conta(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return "login_registrar";
		}
		else if(!usuario.getSenha().equals(usuario.getConfirmarsenha())){
			attributes.addFlashAttribute("erromensagem", "Senha de confirmação incorreta!");
			return "redirect:/registrar";
		}
		else if(repUsuario.existsById(usuario.getLogin())) {
			attributes.addFlashAttribute("erromensagem", "Login já existe");
			return "redirect:/registrar";
		}
		else {
			Role client=repRole.findByNomeRole("ROLE_CLIENT");
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			usuario.setRoles(Arrays.asList(client));
			attributes.addFlashAttribute("mensagem", "Conta registrada!");
			repUsuario.save(usuario);
			return "redirect:/login";
		}
	}
}
