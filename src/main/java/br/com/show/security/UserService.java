package br.com.show.security;

import br.com.show.model.Usuario;

public interface UserService {
    void save(Usuario usuario);

    Usuario findByLogin(String login);
}