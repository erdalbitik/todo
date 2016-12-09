package com.ebitik.todo.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebitik.todo.domain.User;
import com.ebitik.todo.domain.UserRole;

/**
 * checks authentication with given cridentials
 * 
 * @author erdal.bitik
 * */

@Service
public class LoginService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String email = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());
		
		User user = userService.findByEmail(email);
		if(Objects.isNull(user)) {
			//a message key is used for i18n purposes
			throw new UsernameNotFoundException("message.login.usernameNotFound");
		}
		
		boolean passwordMatch = passwordEncoder.matches(password, user.getPasswordHash());
		if(!passwordMatch) {
			throw new BadCredentialsException("message.login.badCredentials");
		}
		
		//from now on principal is user object
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getPasswordHash(), getAuthorities(user));
		
		return token;
	}
	/**
	 * returns authorities of given user
	 * 
	 * @return List<GrantedAuthority> authorities of given user
	 * */
	private List<GrantedAuthority> getAuthorities(User user) {
		List<UserRole> urList = userService.findUserRoles(user);
		List<GrantedAuthority> authList = urList.stream().map(ur -> new SimpleGrantedAuthority(ur.getRole())).collect(Collectors.toList());
		return authList;
	}

}
