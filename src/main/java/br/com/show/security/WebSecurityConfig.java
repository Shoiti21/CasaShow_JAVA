package br.com.show.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers("/","/registrar","/registrar_salvar","/sair","/lista").permitAll()
		.antMatchers("/gerEvento","/registrar/evento","/registrar/salvarevento","/editarEvento/{evento_id}","/gerShow","/registrar/show","/registrar/salvarshow","/editarShow/{show_id}").hasRole("ADMIN")
		.antMatchers("/carrinho","/carrinho/{id_evento}","/remover/{id_evento}","/finalizar","/historico").hasRole("CLIENT")
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll().successForwardUrl("/")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder())
		;
	}
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**","/img/**");
	}
}
