package com.ebitik.todo.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
	
	@Autowired
	private static ApplicationContext applicationContext;
	
	public static PasswordEncoder getPasswordEncoder() {
		return applicationContext.getBean(PasswordEncoder.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

}
