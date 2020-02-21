package br.com.show.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.show.model.Eventos;
import br.com.show.model.Usuario;

public interface repEventos extends JpaRepository<Eventos, Long>{
	public List<Eventos> findByNomeContaining(String nome);

}
