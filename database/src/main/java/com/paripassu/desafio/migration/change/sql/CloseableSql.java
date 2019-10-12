package com.paripassu.desafio.migration.change.sql;

import java.sql.SQLException;

public interface CloseableSql {
	void close() throws SQLException;
}