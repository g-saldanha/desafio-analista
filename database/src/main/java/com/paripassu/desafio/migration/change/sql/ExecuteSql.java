package com.paripassu.desafio.migration.change.sql;

import java.sql.SQLException;
import java.sql.Statement;

import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;

public class ExecuteSql implements CloseableSql, SqlBatch {

	private JdbcConnection connection;
	private Statement statement;
	private int size;

	private String sql;
	private int batchSize = SqlConstants.BATCH_SIZE;

	public ExecuteSql(JdbcConnection connection) {
		this.connection = connection;
		this.size = 0;
	}

	/**
	 * Adiciona o registro atual no batch.
	 * Caso o batch atinja o tamanho máximo configurado, ele é executado.
	 * @throws DatabaseException
	 */
	public void addBatch(String sql) throws SQLException, DatabaseException {
		if (this.statement == null) {
			this.statement = this.connection.createStatement();
		}

		this.sql = sql;
		this.internalAddBatch();

		if (this.size == this.batchSize) {
			this.executeBatch();
		}
	}

	private void internalAddBatch() throws SQLException {
		this.size++;
		this.statement.addBatch(this.sql);
	}

	@Override
	public void executeBatch() throws SQLException {
		if (this.statement != null) {
			this.statement.executeBatch();
			this.size = 0;
		}
	}

	@Override
	public void close() throws SQLException {
		if (this.statement != null) {
			this.statement.close();
			this.statement = null;
		}

	}
}