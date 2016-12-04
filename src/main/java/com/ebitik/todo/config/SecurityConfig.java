package com.ebitik.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for Spring Security
 * 
 * @author erdal.bitik
 * */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	protected void configure(final HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().anyRequest().authenticated()
		.and().formLogin().permitAll().loginPage("/login").loginProcessingUrl("/login").failureUrl("/login?error").defaultSuccessUrl("/entry")
		.and().logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login");		

		http.csrf().disable();
	}

	/**
	 * urls which will be ignored by Spring security
	 * it means those urls are accessible without login.
	 * */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
	    .antMatchers("/register");
		//web.debug(true);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//we can also use a user details service.
		auth.authenticationProvider(authenticationProvider);
	}

}
