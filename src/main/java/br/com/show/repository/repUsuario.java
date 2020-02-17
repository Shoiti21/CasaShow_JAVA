package br.com.show.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.show.model.Usuario;

public interface repUsuario extends CrudRepository<Usuario, String>{
	Usuario findByLogin(String login);
}
