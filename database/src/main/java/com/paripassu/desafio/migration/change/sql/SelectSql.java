package com.paripassu.desafio.migration.change.sql;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;

public class SelectSql implements CloseableSql {
	private ResultSet rs;

	private JdbcConnection connection;

	private Statement statement;

	public SelectSql(JdbcConnection connection, String sql) throws SQLException, DatabaseException {
		this.connection = connection;
		this.statement = this.connection.createStatement();
		this.rs = this.statement.executeQuery(sql);
	}

	@Override
	public void close() throws SQLException {
		if (this.statement != null) {
			this.statement.close();
			this.statement = null;
		}
	}

	public boolean next() throws SQLException {
		return this.rs.next();
	}

	public String getString(String columnName) throws SQLException {
		return this.rs.getString(columnName);
	}

	public Long getLong(String columnName) throws SQLException {
		Long value = this.rs.getLong(columnName);
		if (this.rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public Integer getInteger(String columnName) throws SQLException {
		Integer value = this.rs.getInt(columnName);
		if (this.rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public Date getDate(String columnName) throws SQLException {
		Date value = this.rs.getDate(columnName);
		if (this.rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public Timestamp getTimestamp(String columnName) throws SQLException {
		Timestamp value = this.rs.getTimestamp(columnName);
		if (this.rs.wasNull()) {
			value = null;
		}
		return value;
	}

	public Object getObject(String columnName) throws SQLException {
		return this.rs.getObject(columnName);
	}
}