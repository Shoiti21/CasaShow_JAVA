package br.com.show.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.show.model.Carrinho;
import br.com.show.model.Eventos;
import br.com.show.model.Produto;
import br.com.show.model.Registro;
import br.com.show.model.Usuario;
import br.com.show.repository.repEventos;
import br.com.show.repository.repUsuario;
import br.com.show.repository.repRegistro;

@Service
public class serviceCarrinho {
	@Autowired
	private repEventos repEventos;
	@Autowired
	private repRegistro repRegistro;
	@Autowired
	private repUsuario repUsuario;
	
	public Boolean verificar() {
		List<Produto> carrinho=new ArrayList<Produto>();
		carrinho=Carrinho.getListcarrinho();
		for(Produto produtos : carrinho) {
			int estoque=produtos.getProduto().getQtdIngresso()-produtos.getQtd_produto();
			if(estoque<=0) {
				return false;
			}
		}
		return true;
	}
	public void adicionar(Long id) {
		Optional<Eventos> evento=repEventos.findById(id);
		List<Produto> cart=new ArrayList<Produto>();
		cart=Carrinho.getListcarrinho();
		if (Carrinho.getListcarrinho().isEmpty()) {
			cart.add(new Produto (evento.get(),1));	
		}
		else {
			for (Produto produto : cart) {
				if(produto.getProduto().getId().equals(id)) {
					int qtd=produto.getQtd_produto()+1;
					produto.setQtd_produto(qtd);
					Carrinho.setListcarrinho(cart);
				}
				else {
					cart.add(new Produto (evento.get(),1));	
				}
			}
			
		}
		Carrinho.setListcarrinho(cart);
	}
	public void remover(Long id) {
		List<Produto> carrinho=new ArrayList<Produto>();
		carrinho=Carrinho.getListcarrinho();
		int index = -1;
		for (int i = 0; i < carrinho.size(); i++) {
			if(carrinho.get(i).getProduto().getId().equals(id)) {
				index=i;
			}
		}
		carrinho.remove(index);
		Carrinho.setListcarrinho(carrinho);
	}
	public void salvar() {
		List<Produto> carrinho=new ArrayList<Produto>();
		carrinho=Carrinho.getListcarrinho();
		Date data = new Date(); 

		for(Produto produtos : carrinho) {
			int estoque=produtos.getProduto().getQtdIngresso()-produtos.getQtd_produto();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user=auth.getName();
			Optional<Usuario> name=repUsuario.findById(user);
			produtos.getProduto().setQtdIngresso(estoque);
			
			for (int i = 0; i < produtos.getQtd_produto(); i++) {
				Registro regCompra=new Registro();
				regCompra.setData(data);
				regCompra.setEvento(produtos.getProduto());
				regCompra.setUsuario(name.get());
				regCompra.setTicket(UUID.randomUUID());
				repRegistro.save(regCompra);
			}
			repEventos.save(produtos.getProduto());
		}
		carrinho.clear();
		Carrinho.setListcarrinho(carrinho);
	}
}
