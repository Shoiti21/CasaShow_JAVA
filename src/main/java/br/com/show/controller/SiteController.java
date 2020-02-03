package br.com.show.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SiteController {
	@RequestMapping("/test")
	public String test() {
		return "page_registrarShow";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping
	public String home() {
		return "page_home";
	}
	@RequestMapping("/lista")
	public String listaShow() {
		return "page_listshow";
	}
	@RequestMapping("/carrinho")
	public String carrinho() {
		return "page_carrinho";
	}
}
