package br.com.show.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.show.model.Registro;

public interface repRegistro extends JpaRepository<Registro, Long>{
	public List<Registro> findByUsuarioLogin(String nome);
}
