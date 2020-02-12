package br.com.show;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class CasaShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasaShowApplication.class, args);
	}
	@Bean
	public LocaleResolver localeResolver() {
		/*CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("pt", "BR"));
		return localeResolver;*/
		return new FixedLocaleResolver(new Locale("pt","BR"));
		
	}

}
