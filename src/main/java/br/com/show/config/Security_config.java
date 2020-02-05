package br.com.show.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class Security_config extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.
				authorizeRequests()
					//.antMatchers("/registrar_show","/carrinho").hasAnyRole("ACESSO_ADM")
					.anyRequest()
					.authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
				.logout()
					.logoutSuccessUrl("/login?sair")
					.permitAll();
		}
}
