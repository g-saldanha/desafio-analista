package com.paripassu.desafio.migration.change.sql;

import java.sql.SQLException;

public interface SqlBatch {

	public void executeBatch() throws SQLException;

}