package br.com.show.model;

public enum Genero {
	ROCK("Administrador"),
	POP("Cliente"),
	HIPHOP("Hip-Hop"),
	PAGODE("Pagode"),
	SERTANEJO("Sertanejo"),
	TRAP("Trap"),
	BLUES("Blues"),
	GOSPEL("Gospel"),
	MPB("MPB");
	
	
	private String tipo;
	
	Genero(String tipo) {
		this.tipo=tipo;
	}

	public String getTipo() {
		return tipo;
	}
}

