package br.com.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.show.model.Eventos;

public interface repEventos extends JpaRepository<Eventos, Long>{

}
