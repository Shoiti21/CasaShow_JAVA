package br.com.show.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.show.model.Usuario;
import br.com.show.repository.repUsuario;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{
	@Autowired
	private repUsuario ru;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario=ru.findByLogin(login);
		if(usuario==null) {
			throw new UsernameNotFoundException("Login não foi encontrado!");
		}
		return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
	}

}
