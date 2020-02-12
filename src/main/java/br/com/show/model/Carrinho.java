package br.com.show.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho extends Eventos {
	public static List<Eventos> listcarrinho= new ArrayList<Eventos>();

	public static List<Eventos> getListcarrinho() {
		return listcarrinho;
	}
	public static void setListcarrinho(List<Eventos> listcarrinho) {
		Carrinho.listcarrinho = listcarrinho;
	}
}
