package com.easyapp;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class EasyAppApplication {
	@Bean
	public DataSource dataSource() {
	  JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
	  DataSource dataSource = dataSourceLookup.getDataSource("jdbc/EasyApp");
	  return dataSource;
	}
	
	public static void main(final String[] args) {
		SpringApplication.run(EasyAppApplication.class, args);
	}
}
