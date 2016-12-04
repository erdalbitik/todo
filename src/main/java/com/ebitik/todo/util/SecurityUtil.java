package com.ebitik.todo.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ebitik.todo.domain.User;

/**
 * Utility class for Security
 * 
 * @author erdal.bitik
 * */

public class SecurityUtil {
	
	public static String DEFAULT_ROLE = "ROLE_USER";
	
	/**
	 * @return login User from Spring security context
	 * */
	public static User getLoginUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

}
