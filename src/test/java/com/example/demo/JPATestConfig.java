package com.example.demo;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import lombok.experimental.var;

@Configuration
public class JPATestConfig {
	
	@Bean
	@Profile("test")
	public DataSource dataSource() {
		var datas = new DriverManagerDataSource();
		datas.setDriverClassName(null);
		datas.setUrl(null);
		return datas;
	}
	
}
