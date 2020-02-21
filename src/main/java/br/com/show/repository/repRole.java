package br.com.show.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.show.model.Role;

public interface repRole extends JpaRepository<Role, String> {
	Role findByNomeRole(String login);

}
