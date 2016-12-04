package com.ebitik.todo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Configuration for database pooling. HikariCP is used.
 * 
 * @author erdal.bitik
 * */

@Configuration
class DataSourceConfig {

	@Value("${spring.datasource.username:}")
	private String user;

	@Value("${spring.datasource.password:}")
	private String password;

	@Value("${spring.datasource.url:}")
	private String dataSourceUrl;

	@Value("${spring.datasource.dataSourceClassName:}")
	private String dataSourceClassName;

	@Value("${spring.datasource.connectionTimeout:20000}")
	private int connectionTimeout;

	@Value("${spring.datasource.maxLifetime:180000}")
	private int maxLifetime;
	
	@Value("${spring.datasource.maximumPoolSize:5}")
	private int maximumPoolSize;
	
	@Value("${spring.datasource.validationQuery:}")
	private String validationQuery;
	
	@Value("${spring.profiles.active:hsql}")
	private String environment;
	
	@Bean
	public DataSource primaryDataSource() {
		
		if("hsql".equals(environment)) {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.HSQL)
				//.addScript("import.sql")
				.build();
			return db;
		}
		
		Properties dsProps = new Properties();
		dsProps.put("url", dataSourceUrl);
		dsProps.put("user", user);
		dsProps.put("password", password);

		Properties cProps = new Properties();
		cProps.put("dataSourceProperties", dsProps);
		cProps.put("connectionTestQuery", validationQuery);
		cProps.put("connectionTimeout", connectionTimeout);
		cProps.put("dataSourceClassName", dataSourceClassName);
		cProps.put("maxLifetime", maxLifetime);
		
		HikariConfig hc = new HikariConfig(cProps);
		hc.setMaximumPoolSize(maximumPoolSize);
		
		HikariDataSource ds = new HikariDataSource(hc);
		return ds;
	}
	
}
