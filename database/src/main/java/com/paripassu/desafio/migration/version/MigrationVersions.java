package com.paripassu.desafio.migration.version;

import com.paripassu.desafio.migration.runner.GpLiquibaseRunner;
import com.paripassu.desafio.migration.runner.MigrationRunner;

public class MigrationVersions {

	private MigrationVersions() {
		// utility class
	}

	public static final MigrationRunner V1000  = new GpLiquibaseRunner("1.0.00");

}
