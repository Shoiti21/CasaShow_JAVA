package br.com.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.show.model.Users;

public interface repUsers extends JpaRepository<Users, Long>{

}
