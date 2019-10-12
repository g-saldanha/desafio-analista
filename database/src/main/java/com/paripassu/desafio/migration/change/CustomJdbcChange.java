package com.paripassu.desafio.migration.change;

import com.paripassu.desafio.migration.change.sql.CloseableSql;
import com.paripassu.desafio.migration.change.sql.ExecuteSql;
import com.paripassu.desafio.migration.change.sql.SelectSql;
import com.paripassu.desafio.migration.change.sql.SqlBatch;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class CustomJdbcChange implements CustomTaskChange {

	protected ResourceAccessor resourceAccessor;
	protected Database database;
	private JdbcConnection connection;
	private List<CloseableSql> sqls = new LinkedList<>();

	@Override
	public String getConfirmationMessage() {
		return this.getClass().getSimpleName()+".java executed.";
	}

	@Override
	public void setUp() throws SetupException {

	}

	@Override
	public void setFileOpener(ResourceAccessor resourceAccessor) {
		this.resourceAccessor = resourceAccessor;
	}

	@Override
	public ValidationErrors validate(Database database) {
		return null;
	}

	@Override
	public void execute(Database database) throws CustomChangeException {
		this.database = database;
		this.connection = (JdbcConnection) database.getConnection();
		try {
			this.migrate(this.connection);
			for (CloseableSql sql : this.sqls) {
				if (sql instanceof SqlBatch) {
					((SqlBatch) sql).executeBatch();
				}
				sql.close();
			}
		} catch (Exception e) {
			throw new CustomChangeException("Erro ao executar migração.", e);
		}
		this.connection = null;
	}

	public abstract void migrate(JdbcConnection connection) throws SQLException, DatabaseException;

	protected SelectSql newSelect(String sql) throws SQLException, DatabaseException {
		SelectSql selectSql = new SelectSql(this.connection, sql);
		this.sqls.add(selectSql);
		return selectSql;
	}

	protected void executeSql(String sql) throws SQLException, DatabaseException {
		ExecuteSql executeSql = new ExecuteSql(this.connection);
		executeSql.addBatch(sql);
		executeSql.executeBatch();
		executeSql.close();
	}

}
