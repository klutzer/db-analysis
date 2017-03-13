package com.softexpert.db.analysis.control;

public class PostgreSQLConnectionManager extends AbstractConnectionManager {

	@Override
	public String getURL() {
		return "jdbc:postgresql://localhost:5432/sesuite206?defaultRowFetchSize=10";
	}

	@Override
	public String getUsr() {
		return "postgres";
	}

	@Override
	public String getPwd() {
		return "XXXXXXX";
	}
	
	@Override
	public String getCreateTableSQL() {
		return "create table customers (idvo integer primary key, namevo varchar(100), textvo varchar(4000), randomdata bytea)";
	}

	@Override
	public String getDriverName() {
		return "org.postgresql.Driver";
	}

}
