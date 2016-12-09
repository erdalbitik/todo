package com.ebitik.todo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		ctx = appContext;
	}
	
	public static PasswordEncoder getPasswordEncoder() {
		return ctx.getBean(PasswordEncoder.class);
	}

}
