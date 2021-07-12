package com.gopaychain.thork;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@SpringBootApplication

public class ThorkApp {

	public static void main(String[] args) {
		SpringApplication.run(ThorkApp.class, args);
	}


	@Bean
	ConnectionFactoryInitializer connectionFactoryInitializer(@Autowired ConnectionFactory connectionFactory)
	{
		ConnectionFactoryInitializer connectionFactoryInitializer = new ConnectionFactoryInitializer();
		connectionFactoryInitializer.setConnectionFactory(connectionFactory);
		connectionFactoryInitializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("data.sql")));
		return connectionFactoryInitializer;

	}
}
