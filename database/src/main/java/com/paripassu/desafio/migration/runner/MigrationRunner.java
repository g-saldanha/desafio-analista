package com.paripassu.desafio.migration.runner;

import javax.sql.DataSource;

public interface MigrationRunner {

	void run(DataSource ds, boolean verificaCheckSum) throws Exception;

	String getMessage();

	String getVersion();
}