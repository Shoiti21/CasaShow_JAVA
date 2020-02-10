package br.com.show.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Eventos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "O campo Nome é obrigatorio!")
	@NotNull(message="O campo Nome é obrigatorio!")
	private String nome;
    @ManyToOne
    @JoinColumn
	private Casa casashow;
    @DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
    @Enumerated(EnumType.STRING)
	private Genero genero;
	@NumberFormat(pattern="#,##0.00")
	private double valor;
	private int qtdIngressoMax;
	private int qtdIngresso;

	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Casa getCasashow() {
		return casashow;
	}
	public void setCasashow(Casa id_casashow) {
		this.casashow = id_casashow;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getQtdIngresso() {
		return qtdIngresso;
	}
	public void setQtdIngresso(int qtdIngresso) {
		this.qtdIngresso = qtdIngresso;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public int getQtdIngressoMax() {
		return qtdIngressoMax;
	}
	public void setQtdIngressoMax(int qtdIngressoMax) {
		this.qtdIngressoMax = qtdIngressoMax;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventos other = (Eventos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
