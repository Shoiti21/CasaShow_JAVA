package br.com.show.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

public class Carrinho {
	private static List<Produto> listcarrinho= new ArrayList<Produto>();
	@NumberFormat(pattern = "#,##0.00")
	private static BigDecimal totalvalor;
	
	public static BigDecimal getTotalvalor() {
		return totalvalor;
	}
	public static void setTotalvalor(BigDecimal totalvalor) {
		Carrinho.totalvalor = totalvalor;
	}
	public static List<Produto> getListcarrinho() {
		return listcarrinho;
	}
	public static void setListcarrinho(List<Produto> listcarrinho) {
		Carrinho.listcarrinho = listcarrinho;
	}
}
