package com.ebitik.todo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Spring boot startup config
 * 
 * @author erdal.bitik
 * */

@SpringBootApplication
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableJpaRepositories(basePackages = "com.ebitik.todo.dao")
@EntityScan(basePackages="com.ebitik.todo.domain")
@ComponentScan(basePackages = "com.ebitik.todo")
public class TodoAppConfig {

	public static void main(String[] args) {
		SpringApplication.run(TodoAppConfig.class, args);
	}

}
