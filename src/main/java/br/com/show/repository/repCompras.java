package br.com.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.show.model.Registro;

public interface repCompras extends JpaRepository<Registro, Long>{

}
