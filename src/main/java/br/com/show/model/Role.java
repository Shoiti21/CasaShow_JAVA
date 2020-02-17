package br.com.show.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	@Id
	private String nomeRole;
	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}
	@Override
	public String getAuthority() {
		return this.nomeRole;
	}

}
