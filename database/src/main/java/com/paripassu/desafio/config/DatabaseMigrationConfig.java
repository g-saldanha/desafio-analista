package com.paripassu.desafio.config;

import liquibase.Liquibase;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
public class DatabaseMigrationConfig {

    @Value("${spring.liquibase.contexts}")
    private String liquibaseContexts;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase() {
            @Override
            protected Liquibase createLiquibase(Connection arg0) throws LiquibaseException {
                Liquibase createLiquibase = super.createLiquibase(arg0);
                createLiquibase.clearCheckSums();
                return createLiquibase;
            }
        };
        liquibase.setContexts(this.liquibaseContexts);
        liquibase.setDataSource(this.dataSource);
        liquibase.setChangeLog("classpath:db/changelog-master.yaml");
        liquibase.setShouldRun(true);
        return liquibase;
    }

}
