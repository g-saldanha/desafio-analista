package com.paripassu.desafio.migration.runner;

import java.sql.Connection;

import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.CompositeResourceAccessor;
import liquibase.resource.FileSystemResourceAccessor;
import liquibase.resource.ResourceAccessor;

@Slf4j
public abstract class LiquibaseRunner implements MigrationRunner {

	private static final String LOG_TABLE_NAME = "TB_MIGRACAO_ESTRUTURA";
	private static final String LOG_LOCK_TABLE_NAME = "TB_MIGRACAO_ESTRUTURA_TRAVA";

	public abstract String getChangeLogFile();

	@Override
	public void run(DataSource ds, boolean verificaCheckSum) throws Exception {
		String changeLogFile = this.getChangeLogFile();
		@Cleanup
		Database database = null;
		@Cleanup
		Connection connection = null;
		Liquibase liquibase = null;
		try {
			DataSource dataSource = ds;
			connection = dataSource.getConnection();

			Thread currentThread = Thread.currentThread();
			ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			ResourceAccessor threadClFO = new ClassLoaderResourceAccessor(contextClassLoader);

			ResourceAccessor clFO = new ClassLoaderResourceAccessor();
			ResourceAccessor fsFO = new FileSystemResourceAccessor();

			database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
			database.setDatabaseChangeLogTableName(LOG_TABLE_NAME);
			database.setDatabaseChangeLogLockTableName(LOG_LOCK_TABLE_NAME);
			database.setAutoCommit(false);

			liquibase = new Liquibase(changeLogFile, new CompositeResourceAccessor(clFO, fsFO, threadClFO), database);
			if (!verificaCheckSum) {
				liquibase.clearCheckSums();
			}
			liquibase.update("");
		} catch (DatabaseException e) {
			throw e;
		} catch (LiquibaseException e) {
			log.error(e.getMessage(), e);
			if (liquibase != null) {
				liquibase.forceReleaseLocks();
			}
			throw e;
		}
	}

}