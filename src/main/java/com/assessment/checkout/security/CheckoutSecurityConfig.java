package com.assessment.checkout.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class CheckoutSecurityConfig extends WebSecurityConfigurerAdapter {

	// Authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// DB auth where hashed passwords are stored can be done instead of
		// in-memory
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
				.withUser("admin").password("password").roles("ADMIN");
	}

	// Authorization
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/**").hasRole("ADMIN").and().csrf()
				.disable().headers().frameOptions().disable();
	}

}
