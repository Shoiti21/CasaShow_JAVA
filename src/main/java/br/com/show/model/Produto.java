package br.com.show.model;

public class Produto{
	private Eventos produto;
	private int qtd_produto;
	
	public Produto(Eventos produto, int qtd) {
		this.produto=produto;
		this.qtd_produto=qtd;
	}
	public Eventos getProduto() {
		return produto;
	}
	public void setProduto(Eventos produto) {
		this.produto = produto;
	}
	public int getQtd_produto() {
		return qtd_produto;
	}
	public void setQtd_produto(int qtd_produto) {
		this.qtd_produto = qtd_produto;
	}
}
