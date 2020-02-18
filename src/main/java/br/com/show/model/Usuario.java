package br.com.show.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Size(min=5)
	private String login;
	@NotNull(message="aaaaaaaaaaa")
	private String nomeCompleto;
	@NotEmpty(message="bbbbbbbbbbbb")
	@NotNull(message="cccccccccccc")
	private String senha;
    @Transient
    @NotEmpty(message="ddddddddddd")
    @NotNull(message="eeeeeeeeee")
    private String confirmarsenha;
	@ManyToMany
	@JoinTable(name="usuarios_roles", joinColumns= @JoinColumn(
			name="usuario_id", referencedColumnName="login"),
			inverseJoinColumns= @JoinColumn(
			name="role_id", referencedColumnName="nomeRole"))
	private List<Role> roles;
	
	public String getConfirmarsenha() {
		return confirmarsenha;
	}
	public void setConfirmarsenha(String confirmarsenha) {
		this.confirmarsenha = confirmarsenha;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return (Collection<? extends GrantedAuthority>) this.roles;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
