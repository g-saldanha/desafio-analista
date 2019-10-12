package com.paripassu.desafio.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConditionalOnMissingBean(DataSource.class)
public class DataSourceConfig {

	@Autowired
	private DatabaseProperties databaseProperties;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(this.databaseProperties.getUrl());
		config.setUsername(this.databaseProperties.getUsername());
		config.setPassword(this.databaseProperties.getPassword());
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setInitializationFailFast(true);

		return new HikariDataSource(config);
	}

}
