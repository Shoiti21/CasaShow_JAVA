package br.com.show.model;

public enum TipoUser {
	ADMIN("Administrador"),
	CLIENTE("Cliente");
	
	private String tipo;
	
	TipoUser(String tipo) {
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
