package com.ebitik.todo.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebitik.todo.config.TodoAppConfig;
import com.ebitik.todo.domain.User;
import com.ebitik.todo.service.UserService;

/**
 * Security Tests
 *
 * @author erdal.bitik
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TodoAppConfig.class})
public class SecurityTest {

	@Autowired
	private UserService service;

	@Autowired
	private ApplicationContext context;

	private Authentication authentication;

	@Before
	public void init() {
		//delte user if exist
		User user = service.findByEmail("security@gmail.com");
		if(Objects.nonNull(user)) {
			service.deleteUser(user);
		}
		//create user
		user = new User("security@gmail.com", "Erdal Bitik");
		user.setPassword("password");
		service.addUser(user);
		
		AuthenticationManager authenticationManager = this.context
				.getBean(AuthenticationManager.class);
		this.authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("security@gmail.com", user.getPassword()));
	}

	@After
	public void close() {
		SecurityContextHolder.clearContext();
	}

	/**
	 * call service without authentication
	 * */
	@Test(expected = AuthenticationException.class)
	public void secure() throws Exception {
		assertThat("Security").isEqualTo(this.service.secureMethod());
	}

	/**
	 * call service with authentication
	 * */
	@Test
	public void authenticated() throws Exception {
		SecurityContextHolder.getContext().setAuthentication(this.authentication);
		assertThat("Security").isEqualTo(this.service.secureMethod());
	}

}
