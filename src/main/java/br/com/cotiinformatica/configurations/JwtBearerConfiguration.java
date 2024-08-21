package br.com.cotiinformatica.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.filters.JwtBearerFilter;

@Configuration
public class JwtBearerConfiguration {

	@Bean
	FilterRegistrationBean<JwtBearerFilter> jwtFilter() {
		FilterRegistrationBean<JwtBearerFilter> filter = new FilterRegistrationBean<>();

		filter.setFilter(new JwtBearerFilter());

		filter.addUrlPatterns("/api/usuario/obter-dados");
		return filter;
	}
}
