package com.paripassu.desafio.migration.runner;

import lombok.Getter;

@Getter
public class GpLiquibaseRunner extends LiquibaseRunner {

	private String version;
	private String message;
	private String changeLogFile;

	public GpLiquibaseRunner(String version) {
		super();
		this.version = version;
		this.message = String.format("script %s PEC", version);
		this.changeLogFile = String.format("db/v%s/%s.yaml", version.replace(".", ""), version);

	}

}
