package com.paripassu.desafio;

import com.paripassu.desafio.config.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { DatabaseApplication.class })
@EnableConfigurationProperties(value = { DatabaseProperties.class })
public class DatabaseApplication {
	
	public DatabaseApplication() {
		//Do nothing
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DatabaseApplication.class);

		application.run(args);
	}

}
